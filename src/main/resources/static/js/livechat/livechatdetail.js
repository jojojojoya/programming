
let stompClient = null;
let reconnectAttempts = 0;
const MAX_RECONNECT_ATTEMPTS = 5;

function formatDate(counselingDate) {
    try {
        console.log("📌 formatDate() 元の値:", counselingDate);

        // 날짜가 "2025-03-28" 형식인지 확인
        if (/^\d{4}-\d{2}-\d{2}$/.test(counselingDate)) {
            let [year, month, day] = counselingDate.split("-");
            return `${year}年 ${month}月 ${day}日`;
        }

        // 기존의 요일이 포함된 형식 처리 (예: "Fri Mar 21 00:00:00 KST 2025")
        let dateParts = counselingDate.split(" ");
        if (dateParts.length < 6) {
            console.error("🚨 日付の文字列解析に失敗しました！元の値:", counselingDate);
            return "日付の変換に失敗";
        }

        let monthMap = {
            "Jan": "01", "Feb": "02", "Mar": "03", "Apr": "04",
            "May": "05", "Jun": "06", "Jul": "07", "Aug": "08",
            "Sep": "09", "Oct": "10", "Nov": "11", "Dec": "12"
        };

        let year = dateParts[5];
        let month = monthMap[dateParts[1]] || "00";
        let day = dateParts[2].padStart(2, "0");

        let formattedDate = `${year}년 ${month}월 ${day}일`;
        console.log("✅ 変換された日付:", formattedDate);
        return formattedDate;
    } catch (error) {
        console.error("🚨 formatDate() エラー:", error);
        return "日付の変換に失敗";
    }
}


function connect(sessionId) {
    if (!sessionId || sessionId === "undefined") {
        console.warn("⚠️ WebSocket接続待機中：sessionIdがまだ取得できません。2秒後に再試行します...");
        setTimeout(() => {
            let newSessionId = document.querySelector(".chat-container").dataset.sessionId;
            if (newSessionId && newSessionId !== "undefined") {
                connect(newSessionId);  // 다시 연결 시도
            }
        }, 2000); // 2초 후 다시 체크
        return;
    }

    console.log(" ✅ WebSocket接続を試みます...");

    let socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log("✅ WebSocket接続成功:" + frame);
        reconnectAttempts = 0;

        stompClient.subscribe("/topic/chat/" + sessionId, function (message) {
            let chatMessage = JSON.parse(message.body);
            console.log("📩 受信したメッセージ:", chatMessage);
            saveChatToLocal(chatMessage);
            showMessage(chatMessage);
        });

    }, function (error) {
        console.error("🚨 WebSocket接続に失敗しました: ", error);
    });
}


async function startCounseling() {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let category = chatContainer.dataset.category;
    let counselingDate = chatContainer.dataset.counselingDate;
    let counselingTime = chatContainer.dataset.counselingTime;

    let formattedDate = formatDate(counselingDate);
    let formattedTime = String(counselingTime).padStart(2, "0") + "時00分";

    let welcomeMessage = {
        session_id: sessionId,
        sender: "カウンセラー",
        message: `こんにちは！${formattedDate} ${formattedTime}に予約された「${category}」に関するご相談を担当させていただきます。😊 ご自由にお話しくださいね。`,
        user_type: "COUNSELOR",
        timestamp: new Date().toISOString()
    };

    try {
        let response = await fetch("/chatmessage", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(welcomeMessage)
        });

        let data = await response.json();
        if (data.success) {
            showMessage(welcomeMessage);
            saveChatToLocal(welcomeMessage);
            stompClient.send("/app/chat", {}, JSON.stringify(welcomeMessage));
        } else {
            console.error("❌ カウンセラーメッセージの保存に失敗しました:", data.message);
        }
    } catch (error) {
        console.error("🚨 カウンセラーメッセージの保存中にエラーが発生しました:", error);
    }
}

function showMessage(message) {
    let chatBox = document.getElementById("chatBox");

    let noMessagesElement = chatBox.querySelector(".no-messages");
    if (noMessagesElement) {
        noMessagesElement.remove();
    }

    let msgElement = document.createElement("div");
    msgElement.className = `chat-message ${message.user_type === "USER" ? "user-msg" : "counselor-msg"}`;

    let senderHtml = message.sender && message.sender.trim() !== "" ? `<strong>${message.sender}:</strong> ` : "";

    msgElement.innerHTML = `${senderHtml}${message.message}`;

    chatBox.appendChild(msgElement);
    chatBox.scrollTop = chatBox.scrollHeight; // 스크롤 자동 이동
}


function saveChatToLocal(message) {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let chatLogs = JSON.parse(localStorage.getItem("chat_" + sessionId) || "[]");

    chatLogs.push(message);
    localStorage.setItem("chat_" + sessionId, JSON.stringify(chatLogs));
}

function removeNoMessagesText() {
    let noMessagesElement = document.querySelector(".no-messages");
    if (noMessagesElement) {
        noMessagesElement.remove();
    }
}

document.addEventListener("DOMContentLoaded", function () {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let isCompleted = chatContainer.dataset.isCompleted === "true"; // 상담 완료 여부

    let enterButton = document.getElementById("enterButton");
    let chatInputContainer = document.querySelector(".chat-input");

    if (!sessionId) {
        console.error("🚨 sessionIdがありません！");
        return;
    }

    if (isCompleted) {
        console.log("✅ 相談はすでに完了しています。");
        if (enterButton) enterButton.style.display = "none"; // 상담 시작 버튼 숨기기
        chatInputContainer.style.display = "none"; // 입력창 숨기기
        loadChatsFromServer(sessionId); // 기존 채팅 내역 불러오기
    } else {
        console.log("✅ 相談は進行中です。「相談開始」ボタンを表示します。");
        if (enterButton) {
            enterButton.addEventListener("click", async function () {
                console.log("✅ 「相談開始」ボタンがクリックされました！");
                enterButton.style.display = "none";

                connect(sessionId);

                await startCounseling();
                chatInputContainer.style.display = "flex";
            });

        }
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

    console.log("📩 [Frontend] 送信するメッセージ:", message);

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
                console.error("❌ [Frontend] メッセージ保存に失敗しました:", data.message);
            } else {
                console.log(" ✅ [Frontend] メッセージ保存に成功しました！");
            }
        })
        .catch(error => console.error(" 🚨 [Frontend] メッセージ保存中にエラーが発生しました:", error));

    stompClient.send("/app/chat", {}, JSON.stringify(message));
    chatInput.value = ""; // 입력창 비우기
}


function goBack() {
    window.location.href = "/usermypage";
}


function confirmExit() {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;

    if (!sessionId) {
        console.error("🚨 sessionIdがありません！");
        alert("❌ セッション情報が見つかりません。もう一度お試しください。");
        return;
    }

    fetch(`/livechat/getCounselingId?sessionId=${sessionId}`)
        .then(response => response.json())
        .then(data => {
            if (!data.success || !data.counseling_id) {
                alert("❌ 相談情報が見つかりません。もう一度お試しください。");
                return;
            }

            let counselingId = data.counseling_id;

            fetch("/livechat/updateStatus", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ counseling_id: counselingId, status: "完了" })
            })
                .then(response => response.json())
                .then(updateData => {
                    if (updateData.success) {
                        alert("✅ 相談が完了しました！");
                        window.location.href = "/usermypage";
                    } else {
                        alert("❌ ステータスの更新に失敗しました。再度お試しください。");
                    }
                })
                .catch(error => console.error("🚨 相談ステータスの更新中にエラーが発生しました:", error));
        })
        .catch(error => console.error("🚨 相談IDの取得中にエラーが発生しました:", error));
}
