let stompClient = null;
let reconnectAttempts = 0;
const MAX_RECONNECT_ATTEMPTS = 5;

function connect(sessionId, userId, category, counselingDate, counselingTime, isCompleted) {
    console.log("✅ WebSocket 연결 시도...");

    let socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log("✅ WebSocket 연결됨: " + frame);
        reconnectAttempts = 0;

        stompClient.subscribe("/topic/chat/" + sessionId, function (message) {
            let chatMessage = JSON.parse(message.body);
            console.log("📩 수신된 메시지:", chatMessage);
            saveChatToLocal(chatMessage);
            showMessage(chatMessage);
        });

        if (!isCompleted && !document.querySelector(".counselor-msg")) {
            let formattedDateTime = formatDateTime(counselingDate, counselingTime);
            let welcomeMessage = {
                session_id: sessionId,
                sender: "상담사",
                content: `안녕하세요! ${formattedDateTime}에 예약된 '${category}' 관련 상담을 도와드리겠습니다.<br>😊 편하게 하고 싶은 말씀을 들려주세요.`,
                type: "COUNSELOR"
            };

            console.log("📨 상담사 자동 환영 메시지 전송:", welcomeMessage);
            showMessage(welcomeMessage);
            saveChatToLocal(welcomeMessage);
            stompClient.send("/app/chat", {}, JSON.stringify(welcomeMessage));
        }
    }, function (error) {
        console.error("🚨 WebSocket 연결 실패: ", error);
        if (reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
            let timeout = Math.min(5000 * reconnectAttempts, 30000);
            console.log(`🔄 ${timeout / 1000}초 후 WebSocket 재연결 시도...`);
            setTimeout(() => connect(sessionId, userId, category, counselingDate, counselingTime, isCompleted), timeout);
            reconnectAttempts++;
        } else {
            alert("⚠️ WebSocket 연결이 지속적으로 실패했습니다. 네트워크를 확인하세요.");
        }
    });
}

document.addEventListener("DOMContentLoaded", function () {
    let chatContainer = document.querySelector(".chat-container");
    let chatBox = document.getElementById("chatBox");

    let sessionId = chatContainer.dataset.sessionId;
    let category = chatContainer.dataset.category;
    let counselingDate = chatContainer.dataset.counselingDate;
    let counselingTime = chatContainer.dataset.counselingTime;
    let userId = chatContainer.dataset.userId;
    let userType = chatContainer.dataset.userType || "USER";  // 🔥 userType이 없을 경우 기본값 설정

    let isCompleted = chatContainer.dataset.isCompleted === "true";

    let chatInputContainer = document.querySelector(".chat-input");
    let enterButton = document.getElementById("enterButton");
    let exitButton = document.getElementById("exitButton");

    loadChatsFromLocal();

    if (isCompleted) {
        console.log("✅ 상담 완료 상태입니다.");
        enterButton.style.display = "none";
        chatInputContainer.style.display = "none";
        exitButton.textContent = "돌아가기";
        exitButton.setAttribute("onclick", "goBack()");
        return;
    }

    console.log("⏳ 상담 대기 상태입니다. 버튼 활성화!");
    enterButton.style.display = "block";
    chatInputContainer.style.display = "none";
    exitButton.textContent = "나가기";
    exitButton.setAttribute("onclick", "confirmExit()");

    enterButton.addEventListener("click", function () {
        connect(sessionId, userId, category, counselingDate, counselingTime, isCompleted);
        chatInputContainer.style.display = "flex";
        enterButton.style.display = "none";
    });
});
function showMessage(message) {
    let chatBox = document.getElementById("chatBox");

    // 🔥 기존 "대화 내용이 없습니다." 삭제
    let noMessagesElement = chatBox.querySelector(".no-messages");
    if (noMessagesElement) {
        noMessagesElement.remove();
    }

    // 상담사 메시지가 처음 전송되기 전에는 화면에 표시되지 않도록 숨김 처리
    let msgElement = document.createElement("div");
    msgElement.className = `chat-message ${message.type === "USER" ? "user-msg" : "counselor-msg"}`;

    // 🔥 상담사 메시지일 경우, 메시지가 화면에 나타나지 않도록 숨김 처리
    if (message.type === "COUNSELOR") {
        msgElement.style.display = "none";
    }

    // 🔥 sender가 비어 있지 않을 때만 표시
    let senderHtml = message.sender && message.sender.trim() !== "" ? `<strong>${message.sender}:</strong> ` : "";
    msgElement.innerHTML = `${senderHtml}${message.content}`;

    chatBox.appendChild(msgElement);
    chatBox.scrollTop = chatBox.scrollHeight; // 스크롤 아래로 자동 이동

    // 상담사 메시지가 화면에 나타나도록 설정
    if (message.type === "COUNSELOR") {
        setTimeout(() => {
            msgElement.style.display = "block"; // 상담사 메시지를 화면에 표시
        }, 1000); // 1초 뒤에 나타나게 설정 (원하는 시간으로 조정 가능)
    }
}

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
        content: messageContent,
        type: "USER"
    };

    showMessage(message);
    saveChatToLocal(message);
    stompClient.send("/app/chat", {}, JSON.stringify(message));

    chatInput.value = "";
}

function goBack() {
    window.location.href = "/usermypage";
}

function confirmExit() {
    let isConfirmed = confirm("정말 상담을 종료하시겠습니까?");
    if (isConfirmed) {
        let chatContainer = document.querySelector(".chat-container");
        let counselingId = chatContainer.dataset.counselingId;

        if (!counselingId) {
            console.error("🚨 상담 ID가 존재하지 않습니다.");
            alert("❌ 상담 ID가 유효하지 않습니다.");
            return;
        }

        fetch("/completeCounseling", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ counseling_id: parseInt(counselingId, 10) })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("✅ 상담이 완료되었습니다!");
                    localStorage.removeItem("chat_" + chatContainer.dataset.sessionId);
                    window.location.href = "/usermypage";
                } else {
                    alert("❌ 상담 종료 실패. 다시 시도해주세요.");
                }
            })
            .catch(error => console.error("🚨 상담 종료 오류:", error));
    }
}

function keepWebSocketAlive() {
    if (stompClient && stompClient.connected) {
        stompClient.send("/app/ping", {}, JSON.stringify({ message: "ping" }));
        console.log("📡 WebSocket ping 메시지 전송!");
    }
}
setInterval(keepWebSocketAlive, 30000);

function saveChatToLocal(message) {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let chatLogs = JSON.parse(localStorage.getItem("chat_" + sessionId) || "[]");

    chatLogs.push(message);
    localStorage.setItem("chat_" + sessionId, JSON.stringify(chatLogs));
}

function loadChatsFromLocal() {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let chatLogs = JSON.parse(localStorage.getItem("chat_" + sessionId) || "[]");

    chatLogs.forEach(chat => showMessage(chat));
}
