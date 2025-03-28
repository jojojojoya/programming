let stompClient = null;
let reconnectAttempts = 0;
const MAX_RECONNECT_ATTEMPTS = 5;

function formatDate(counselingDate) {
    try {
        if (/^\d{4}-\d{2}-\d{2}$/.test(counselingDate)) {
            let [year, month, day] = counselingDate.split("-");
            return `${year}年 ${month}月 ${day}日`;
        }
        let dateParts = counselingDate.split(" ");
        let monthMap = {"Jan": "01", "Feb": "02", "Mar": "03", "Apr": "04", "May": "05", "Jun": "06", "Jul": "07", "Aug": "08", "Sep": "09", "Oct": "10", "Nov": "11", "Dec": "12"};
        let year = dateParts[5];
        let month = monthMap[dateParts[1]] || "00";
        let day = dateParts[2].padStart(2, "0");
        return `${year}年 ${month}月 ${day}日`;
    } catch (error) {
        return "日付の変換に失敗";
    }
}


function connect(sessionId, callback) {
    let socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        console.log("✅ STOMP 연결 성공");
        stompClient.subscribe("/topic/chat/" + sessionId, function (message) {
            let chatMessage = JSON.parse(message.body);
            showMessage(chatMessage);
            saveChatToLocal(chatMessage);
        });

        if (callback) callback(); // 콜백 실행
    }, function (error) {
        console.error("❌ STOMP 연결 실패:", error);
    });
}

async function startCounseling() {
    const container = document.querySelector(".chat-container");
    const userType = container.dataset.userType;
    const sessionId = container.dataset.sessionId;
    const counselorId = container.dataset.counselorId;

    // 유저가 아니면 자동 메시지 X
    if (userType !== "1") return;

    // 이미 welcome 메시지가 있는지 서버에 확인
    const resCheck = await fetch(`/chatmessage/checkWelcome?sessionId=${sessionId}`);
    const checkData = await resCheck.json();
    if (checkData.exists) return;

    // 메시지 생성
    const welcomeMessage = {
        session_id: sessionId,
        sender: counselorId,
        user_type: "COUNSELOR",
        message: `こんにちは！${container.dataset.counselingDate} ${container.dataset.counselingTime}時00分に予約された「${container.dataset.category}」に関するご相談を担当させていただきます。😊 ご自由にお話しくださいね。`,
        timestamp: new Date().toISOString()
    };

    // DB 저장
    const res = await fetch("/chatmessage", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(welcomeMessage)
    });

    const data = await res.json();
    if (data.success) {
        stompClient.send("/app/chat", {}, JSON.stringify(welcomeMessage));
    }
}

function showMessage(message) {
    const chatBox = document.getElementById("chatBox");
    const container = document.querySelector(".chat-container");
    const loginUserId = container.dataset.userId;

    // 중복 자동 메시지 방지
    if (message.user_type === "COUNSELOR" && message.message.includes("ご相談を担当させていただきます")) {
        // 자동 메시지가 이미 있는지 검사
        const alreadyExists = [...chatBox.children].some(el =>
            el.textContent.includes("ご相談を担当させていただきます")
        );
        if (alreadyExists) return;
    }

    const isMyMessage = message.sender === loginUserId;

    const msg = document.createElement("div");
    msg.className = `chat-message ${isMyMessage ? "my-msg" : "other-msg"}`;
    msg.innerHTML = `<strong>${message.sender}:</strong> ${message.message}`;
    chatBox.appendChild(msg);
    chatBox.scrollTop = chatBox.scrollHeight;
}

function saveChatToLocal(message) {
    let sessionId = document.querySelector(".chat-container").dataset.sessionId;
    let chatLogs = JSON.parse(localStorage.getItem("chat_" + sessionId) || "[]");
    chatLogs.push(message);
    localStorage.setItem("chat_" + sessionId, JSON.stringify(chatLogs));
}

function sendAndSaveMessage(message) {
    return fetch("/chatmessage", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(message)
    }).then(response => response.json())
        .then(data => {
            if (data.success) {
                showMessage(message);
                saveChatToLocal(message);
                stompClient.send("/app/chat", {}, JSON.stringify(message));
            } else {
                alert("メッセージの送信に失敗しました。")
            }
        });
}

function sendMessage() {
    let chatInput = document.getElementById("chatInput");
    let messageContent = chatInput.value.trim();
    if (messageContent === "") return;

    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let userId = chatContainer.dataset.userId;
    let userType = chatContainer.dataset.userType;

    let message = {
        session_id: sessionId,
        sender: userId,
        message: messageContent,
        user_type: userType,
        timestamp: new Date().toISOString()
    };

    sendAndSaveMessage(message);
    chatInput.value = "";
}

function confirmExit() {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let userType = chatContainer.dataset.userType; // ← 이걸로 상담사인지 확인

    fetch(`/livechat/getCounselingId?sessionId=${sessionId}`)
        .then(response => response.json())
        .then(data => {
            let counselingId = data.counseling_id;
            return fetch("/livechat/updateStatus", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ counseling_id: counselingId, status: "完了" })
            });
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("相談が完了しました！");
                if (userType === "COUNSELOR") {
                    window.location.href = "/counselormypage";
                } else {
                    window.location.href = "/usermypage";
                }
            }
        });
}

document.addEventListener("DOMContentLoaded", function () {
    const container = document.querySelector(".chat-container");
    const sessionId = container.dataset.sessionId;
    const isCompleted = container.dataset.isCompleted === "true";
    const enterButton = document.getElementById("enterButton");
    const chatInputContainer = document.querySelector(".chat-input");
    const userType = container.dataset.userType;


    if (isCompleted) {
        enterButton && (enterButton.style.display = "none");
        chatInputContainer.style.display = "none";
    } else {
        enterButton.addEventListener("click", function () {
            enterButton.style.display = "none";

            connect(sessionId, async function afterConnected() {
                if (userType === "1") {
                    await startCounseling(); // 자동 메시지 전송
                }
                chatInputContainer.style.display = "flex";
            });
        });
    }
});

function sendMessage() {
    let chatInput = document.getElementById("chatInput");
    let messageContent = chatInput.value.trim();

    if (messageContent === "") return;

    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let userId = chatContainer.dataset.userId;

    let message = {
        session_id: sessionId,
        sender: userId,
        message: messageContent,
        user_type: "USER",
        timestamp: new Date().toISOString()
    };

    console.log("[Frontend] 送信するメッセージ:", message);

    showMessage(message);
    saveChatToLocal(message);

    fetch("/chatmessage", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(message)
    })
        .then(response => response.json())
        .then(data => {
            if (!data.success) {
                console.error("[Frontend] メッセージ保存に失敗しました:", data.message);
            } else {
                console.log("[Frontend] メッセージ保存に成功しました！");
            }
        })
        .catch(error => console.error("[Frontend] メッセージ保存中にエラーが発生しました:", error));

    stompClient.send("/app/chat", {}, JSON.stringify(message));
    chatInput.value = ""; // 입력창 비우기
}


function goBack() {
    const chatContainer = document.querySelector(".chat-container");
    const userType = chatContainer.dataset.userType;

    if (userType === "2") {
        window.location.href = "/counselormypage";
    } else {
        window.location.href = "/usermypage";
    }
}

function confirmExit() {
    const chatContainer = document.querySelector(".chat-container");
    const sessionId = chatContainer.dataset.sessionId;
    const userType = chatContainer.dataset.userType; // "1" = 유저, "2" = 상담사

    if (!sessionId) {
        alert("セッション情報が見つかりません。もう一度お試しください。");
        return;
    }

    fetch(`/livechat/getCounselingId?sessionId=${sessionId}`)
        .then(response => response.json())
        .then(data => {
            const counselingId = data.counseling_id;
            return fetch("/livechat/updateStatus", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ counseling_id: counselingId, status: "完了" })
            });
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("相談が完了しました！");
                if (userType === "2") {
                    window.location.href = "/counselormypage";
                } else {
                    window.location.href = "/usermypage";
                }
            }
        })
        .catch(error => {
            console.error("相談終了中にエラーが発生しました:", error);
            alert("エラーが発生しました。もう一度お試しください。");
        });
}
