let stompClient = null;
let reconnectAttempts = 0;
const MAX_RECONNECT_ATTEMPTS = 5;

function connect(sessionId, userId, category, counselingDate, counselingTime, isCompleted) {
    console.log("✅ WebSocket 연결 시도...");

    let socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log("✅ WebSocket 연결 성공: " + frame);
        reconnectAttempts = 0;

        stompClient.subscribe("/topic/chat/" + sessionId, function (message) {
            let chatMessage = JSON.parse(message.body);
            console.log("📩 수신된 메시지:", chatMessage);
            saveChatToLocal(chatMessage);
            showMessage(chatMessage);
        });

        // ✅ 상담사 자동 메시지 추가 (상담이 대기 상태일 경우)
        if (!isCompleted) {
            let welcomeMessageSent = localStorage.getItem(`welcome_msg_${sessionId}`);
            console.log(`🔍 welcome_msg_${sessionId} 값:`, welcomeMessageSent);
            console.log(counselingDate);
            console.log(counselingTime)
            if (!welcomeMessageSent || welcomeMessageSent !== "true") {
                let formattedDateTime = formatDateTime(counselingDate, counselingTime);
                let welcomeMessage = {
                    session_id: sessionId,
                    sender: "상담사",
                    content: `안녕하세요! ${formattedDateTime}에 예약된 '${category}' 관련 상담을 도와드리겠습니다.<br>😊 편하게 하고 싶은 말씀을 들려주세요.`,
                    type: "COUNSELOR"
                };

                console.log("📨 상담사 자동 메시지 생성됨:", welcomeMessage);

                setTimeout(() => {
                    console.log("📨 상담사 자동 메시지 전송 시작:", welcomeMessage);
                    document.querySelector(".no-messages").remove()
                    showMessage(welcomeMessage);

                    // ✅ WebSocket을 통해 서버에도 전송
                    if (stompClient && stompClient.connected) {
                        stompClient.send("/app/chat", {}, JSON.stringify(welcomeMessage));
                        console.log("✅ WebSocket을 통해 상담사 메시지 전송 완료");
                    } else {
                        console.error("🚨 WebSocket이 연결되지 않아 메시지를 보낼 수 없음");
                    }

                    localStorage.setItem(`welcome_msg_${sessionId}`, "true");
                }, 500);
            } else {
                console.log("⚠️ 상담사 자동 메시지가 이미 전송됨 (중복 방지)");
            }
        }
    }, function (error) {
        console.error("🚨 WebSocket 연결 실패: ", error);
    });
}

document.addEventListener("DOMContentLoaded", function () {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;

    if (!sessionId) {
        console.error("🚨 sessionId가 없습니다!");
        return;
    }

    let isCompleted = chatContainer.dataset.isCompleted === "true"; // 상담 완료 여부
    let enterButton = document.getElementById("enterButton");
    let chatInputContainer = document.querySelector(".chat-input");

    if (isCompleted) {
        console.log("✅ 상담이 완료된 상태입니다.");
        if (enterButton) enterButton.style.display = "none"; // 상담 시작 버튼 숨기기
        chatInputContainer.style.display = "none"; // 입력창 숨기기

        // ✅ 기존 채팅 내역을 DB에서 불러오기
        loadChatsFromServer(sessionId);
    } else {
        console.log("✅ 상담이 진행 중입니다. 상담 시작 버튼을 표시합니다.");
        if (enterButton) {
            enterButton.addEventListener("click", function () {
                console.log("✅ 상담 시작 버튼 클릭됨!");
                enterButton.style.display = "none"; // 상담 시작 버튼 숨기기
                chatInputContainer.style.display = "flex"; // 입력창 보이기

                // ✅ WebSocket 연결 및 자동 메시지 전송
                connect(
                    chatContainer.dataset.sessionId,
                    chatContainer.dataset.userId,
                    chatContainer.dataset.category,
                    chatContainer.dataset.counselingDate,
                    chatContainer.dataset.counselingTime,
                    isCompleted
                );
            });
        }
    }
});


function showMessage(message) {
    let chatBox = document.getElementById("chatBox");

    // ✅ 기존 "대화 내용이 없습니다." 메시지 삭제
    let noMessagesElement = chatBox.querySelector(".no-messages");
    if (noMessagesElement) {
        noMessagesElement.remove();
    }

    let msgElement = document.createElement("div");
    msgElement.className = `chat-message ${message.type === "USER" ? "user-msg" : "counselor-msg"}`;

    let senderHtml = message.sender && message.sender.trim() !== "" ? `<strong>${message.sender}:</strong> ` : "";
    msgElement.innerHTML = `${senderHtml}${message.content}`;

    chatBox.appendChild(msgElement);
    chatBox.scrollTop = chatBox.scrollHeight; // 스크롤 자동 이동

    // ✅ 상담사 메시지일 경우, 상담 시작 버튼이 눌리면 보이도록 처리
    if (message.type === "COUNSELOR") {
        let isChatStarted = localStorage.getItem("chat_started") === "true"; // 🔥 상담 시작 여부 확인
        if (isChatStarted) {
            msgElement.style.display = "block"; // 🔥 상담이 시작되었을 때만 보이게 설정
        }
    }

    document.getElementById("enterButton").addEventListener("click", function () {
        localStorage.setItem("chat_started", "true"); // 🔥 상담 시작 상태 저장

        // ✅ "대화 내용이 없습니다." 문구 즉시 삭제
        removeNoMessagesText();

        // ✅ 기존 상담사 메시지를 보이게 설정
        document.querySelectorAll(".counselor-msg").forEach(msg => {
            msg.style.display = "block";
        });

        let chatContainer = document.querySelector(".chat-container");
        connect(
            chatContainer.dataset.sessionId,
            chatContainer.dataset.userId,
            chatContainer.dataset.category,
            chatContainer.dataset.counselingDate,
            chatContainer.dataset.counselingTime,
            chatContainer.dataset.isCompleted === "true"
        );

        // ✅ 상담 시작 버튼을 누르면 상담사 자동 메시지 전송
        let formattedDateTime = formatDateTime(chatContainer.dataset.counselingDate, chatContainer.dataset.counselingTime);
        let welcomeMessage = {
            session_id: chatContainer.dataset.sessionId,
            sender: "상담사",
            content: `안녕하세요! ${formattedDateTime}에 예약된 '${chatContainer.dataset.category}' 관련 상담을 도와드리겠습니다.<br>😊 편하게 하고 싶은 말씀을 들려주세요.`,
            type: "COUNSELOR"
        };

        console.log("📨 상담사 자동 메시지 전송:", welcomeMessage);
        showMessage(welcomeMessage); // ✅ 화면에 메시지 출력
        saveChatToLocal(welcomeMessage); // ✅ localStorage에 저장

        // ✅ WebSocket을 통해 서버에도 메시지 전송
        if (stompClient && stompClient.connected) {
            stompClient.send("/app/chat", {}, JSON.stringify(welcomeMessage));
            console.log("✅ WebSocket을 통해 상담사 메시지 전송 완료");
        } else {
            console.error("🚨 WebSocket이 연결되지 않아 메시지를 보낼 수 없음");
        }
    });

    setInterval(keepWebSocketAlive, 30000);

}
function goBack() {
    window.location.href = "/usermypage";
}
function confirmExit() {
    let isConfirmed = confirm("정말 상담을 종료하시겠습니까?");
    if (isConfirmed) {
        let chatContainer = document.querySelector(".chat-container");
        let sessionId = chatContainer.dataset.sessionId;

        fetch("/completeCounseling", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({counseling_id: parseInt(chatContainer.dataset.counselingId, 10)})
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("✅ 상담이 완료되었습니다!");
                    localStorage.removeItem(`chat_started_${sessionId}`); // ✅ 상담 상태 초기화
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
        stompClient.send("/app/ping", {}, JSON.stringify({message: "ping"}));
        console.log("📡 WebSocket ping 메시지 전송!");
    }
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
function loadChats() {
    console.log("🔄 채팅 내역 불러오는 중...");
    loadChatsFromServer();
}
// ✅ 상담이 "완료" 상태일 때 DB에서 기존 채팅 내역 불러오기
function loadChatsFromServer(sessionId) {
    fetch(`/getChatLogs?sessionId=${sessionId}`)
        .then(response => response.json())
        .then(chatLogs => {
            let chatBox = document.getElementById("chatBox");
            if (!chatBox) return;

            if (chatLogs.length > 0) {
                chatLogs.forEach(chat => showMessage(chat));
            } else {
                chatBox.innerHTML = "<div class='no-messages'>대화 내용이 없습니다.</div>";
            }
        })
        .catch(error => console.error("🚨 DB에서 채팅 내역 불러오기 오류:", error));
}
function formatDateTime(date, time) {
    // 날짜 객체 변환
    let dt = new Date(date);

    // 연, 월, 일 추출
    let year = dt.getFullYear();
    let month = String(dt.getMonth() + 1).padStart(2, "0"); // 월(0부터 시작하므로 +1), 두 자리로 변환
    let day = String(dt.getDate()).padStart(2, "0"); // 두 자리로 변환

    // 시간 변환 (정수 타입이라면 그대로 사용)
    let hour = String(time).padStart(2, "0"); // 두 자리로 변환

    // 포맷팅된 문자열 반환
    return `${year}년 ${month}월 ${day}일 ${hour}시`;
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
        type: "USER",
        timestamp: new Date().toISOString()
    };

    // ✅ 메시지를 화면에 표시
    showMessage(message);
    saveChatToLocal(message);

    // ✅ 메시지를 서버에 저장
    fetch("/saveChat", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(message)
    })
        .then(response => response.json())
        .then(data => {
            if (!data.success) {
                console.error("❌ 메시지 저장 실패");
            }
        })
        .catch(error => console.error("🚨 메시지 저장 오류:", error));

    stompClient.send("/app/chat", {}, JSON.stringify(message));

    chatInput.value = "";
}