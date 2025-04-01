let stompClient = null;

function formatDate(counselingDate) {
    try {
        if (/^\d{4}-\d{2}-\d{2}$/.test(counselingDate)) {
            let [year, month, day] = counselingDate.split("-");
            return `${year}年 ${month}月 ${day}日`;
        }
        let dateParts = counselingDate.split(" ");
        let monthMap = {
            "Jan": "01", "Feb": "02", "Mar": "03", "Apr": "04", "May": "05", "Jun": "06",
            "Jul": "07", "Aug": "08", "Sep": "09", "Oct": "10", "Nov": "11", "Dec": "12"
        };
        let year = dateParts[5];
        let month = monthMap[dateParts[1]] || "00";
        let day = dateParts[2].padStart(2, "0");
        return `${year}年 ${month}月 ${day}日`;
    } catch (error) {
        return "日付の変換に失敗";
    }
}

function connect(sessionId) {
    return new Promise((resolve, reject) => {
        if (stompClient && stompClient.connected) {
            return resolve();
        }

        let socket = new SockJS(`${window.location.protocol}//${window.location.host}/ws`);
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function () {
            console.log("STOMP 연결 성공");

            stompClient.subscribe("/topic/chat/" + sessionId, function (message) {
                console.log("수신 메시지:", message.body);
                const chatMessage = JSON.parse(message.body);
                showMessage(chatMessage);
                saveChatToLocal(chatMessage);
            });

            resolve();
        }, function (error) {
            console.error("STOMP 연결 실패:", error);
            reject(error);
        });
    });
}

async function startCounseling() {
    const container = document.querySelector(".chat-container");
    const userType = container.dataset.userType;
    const sessionId = container.dataset.sessionId;
    const counselorId = container.dataset.counselorId;

    if (userType !== "1") return;

    const resCheck = await fetch(`/chatmessage/checkWelcome?sessionId=${sessionId}`);
    const checkData = await resCheck.json();
    if (checkData.exists) {
        console.log("자동 메시지 이미 전송됨");
        return;
    }

    const welcomeMessage = {
        session_id: sessionId,
        sender: counselorId,
        user_type: "COUNSELOR",
        message: `こんにちは！${container.dataset.counselingDate} ${container.dataset.counselingTime}時00分に予約された「${container.dataset.category}」に関するご相談を担当させていただきます。😊 ご自由にお話しくださいね。`,
        timestamp: new Date().toISOString()
    };

    const res = await fetch("/chatmessage", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(welcomeMessage)
    });

    const data = await res.json();

    if (data.success) {
        console.log("자동 메시지 저장 후 전송!");

        showMessage(welcomeMessage);
        saveChatToLocal(welcomeMessage);

        stompClient.send("/app/chat", {}, JSON.stringify(welcomeMessage));
    }
}

function showMessage(message) {
    const chatBox = document.getElementById("chatBox");
    const container = document.querySelector(".chat-container");

    const noMessagesEl = document.querySelector(".no-messages");
    if (noMessagesEl) noMessagesEl.remove();

    if (
        message.user_type === "COUNSELOR" &&
        message.message.includes("ご相談を担当させていただきます")
    ) {
        const exists = [...chatBox.children].some(m =>
            m.textContent.includes("ご相談を担当させていただきます")
        );
        if (exists) return;
    }

    const isMyMessage = message.sender === container.dataset.userId;

    const msg = document.createElement("div");
    msg.className = `chat-message ${isMyMessage ? "my-msg" : "other-msg"}`;
    msg.innerHTML = `<strong>${message.sender}:</strong> ${message.message}`;
    chatBox.appendChild(msg);
    chatBox.scrollTop = chatBox.scrollHeight;
    console.log(" showMessage(): ", message);
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
                alert("メッセージの送信に失敗しました。");
            }
        });
}
// 3/31 기존 ver
// document.addEventListener("DOMContentLoaded", function () {
//     const container = document.querySelector(".chat-container");
//     const sessionId = container.dataset.sessionId;
//     const isCompleted = container.dataset.isCompleted === "true";
//     const enterButton = document.getElementById("enterButton");
//     const chatInputContainer = document.querySelector(".chat-input");
//     const userType = container.dataset.userType;
//
//     if (isCompleted) {
//         enterButton && (enterButton.style.display = "none");
//         chatInputContainer.style.display = "none";
//     } else {
//         enterButton.addEventListener("click", async function () {
//             enterButton.style.display = "none";
//             try {
//                 await connect(sessionId);     // 연결 및 구독 완료
//                 if (userType === "1") {
//                     await startCounseling();  // 구독 완료 후 전송해야 유저가 받을 수 있음
//                 }
//                 chatInputContainer.style.display = "flex";
//             } catch (err) {
//                 alert("WebSocket接続に失敗しました。");
//                 console.error(err);
//             }
//         });
//     }
// });

document.addEventListener("DOMContentLoaded", async function () {
        const container = document.querySelector(".chat-container");

        console.log("[라이브챗] livechatdetail.jsp 로딩됨");
        console.log("→ sessionId:", container.dataset.sessionId);
        console.log("→ counselingId:", container.dataset.counselingId);
        console.log("→ userId:", container.dataset.userId);
        console.log("→ userType:", container.dataset.userType);
        console.log("→ counselorId:", container.dataset.counselorId);
        console.log("→ isCompleted:", container.dataset.isCompleted);
    console.log("세션 ID:", document.querySelector('.chat-container').dataset.sessionId);


    const sessionId = container.dataset.sessionId;
    const isCompleted = container.dataset.isCompleted === "true";
    const enterButton = document.getElementById("enterButton");
    const chatInputContainer = document.querySelector(".chat-input");
    const userType = container.dataset.userType;

    try {
        await connect(sessionId);
        console.log("WebSocket 구독 완료");
    } catch (err) {
        console.error("WebSocket 연결 실패", err);
    }

    if (isCompleted) {
        if (enterButton) enterButton.style.display = "none";
        chatInputContainer.style.display = "none";
        return;
    }

    if (enterButton) {
        enterButton.addEventListener("click", async function () {
            enterButton.style.display = "none";

            if (userType === "1") {
                // 유저일 경우 자동 메시지 전송 (상담사 입장 메시지)
                await startCounseling();
            }

            chatInputContainer.style.display = "flex";
        });
    } else {
        chatInputContainer.style.display = "flex";
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

    // showMessage(message);
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
    chatInput.value = "";
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
    const userType = chatContainer.dataset.userType;

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