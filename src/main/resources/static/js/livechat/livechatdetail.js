
let stompClient = null;
let reconnectAttempts = 0;
const MAX_RECONNECT_ATTEMPTS = 5;

function formatDate(counselingDate) {
    try {
        console.log("📌 formatDate() 원본 값:", counselingDate);

        // 날짜 문자열에서 연, 월, 일을 추출
        let dateParts = counselingDate.split(" "); // ["Fri", "Mar", "21", "00:00:00", "KST", "2025"]

        if (dateParts.length < 6) {
            console.error("🚨 날짜 문자열 파싱 실패! 원본 값:", counselingDate);
            return "날짜 변환 실패";
        }

        let monthMap = {
            "Jan": "01", "Feb": "02", "Mar": "03", "Apr": "04",
            "May": "05", "Jun": "06", "Jul": "07", "Aug": "08",
            "Sep": "09", "Oct": "10", "Nov": "11", "Dec": "12"
        };

        let year = dateParts[5]; // 연도
        let month = monthMap[dateParts[1]] || "00"; // 월 변환
        let day = dateParts[2].padStart(2, "0"); // 날짜

        let formattedDate = `${year}년 ${month}월 ${day}일`;
        console.log("✅ 변환된 날짜:", formattedDate);
        return formattedDate;
    } catch (error) {
        console.error("🚨 formatDate() 오류:", error);
        return "날짜 변환 실패";
    }
}


function connect(sessionId) {
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

    }, function (error) {
        console.error("🚨 WebSocket 연결 실패: ", error);
    });
}
function startCounseling() {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let category = chatContainer.dataset.category;
    let counselingDate = chatContainer.dataset.counselingDate;
    let counselingTime = chatContainer.dataset.counselingTime;

    console.log("📌 [startCounseling] counselingDate 원본:", counselingDate);

    let formattedDate = formatDate(counselingDate);  // ✅ 수정된 formatDate 사용
    let formattedTime = String(counselingTime).padStart(2, "0") + "시 00분"; // 시간 변환

    let welcomeMessage = {
        session_id: sessionId,
        sender: "상담사",
        content: `안녕하세요! ${formattedDate} ${formattedTime}에 예약된 '${category}' 관련 상담을 도와드리겠습니다.<br>😊 편하게 하고 싶은 말씀을 들려주세요.`,
        type: "COUNSELOR"
    };

    console.log("📨 상담사 자동 메시지 생성됨:", welcomeMessage);

    removeNoMessagesText();
    showMessage(welcomeMessage);
    saveChatToLocal(welcomeMessage);

    if (stompClient && stompClient.connected) {
        stompClient.send("/app/chat", {}, JSON.stringify(welcomeMessage));
        console.log("✅ WebSocket을 통해 상담사 메시지 전송 완료");
    } else {
        console.error("🚨 WebSocket이 연결되지 않아 메시지를 보낼 수 없음");
    }
}



function showMessage(message) {
    let chatBox = document.getElementById("chatBox");

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
        console.error("🚨 sessionId가 없습니다!");
        return;
    }

    if (isCompleted) {
        console.log("✅ 상담이 완료된 상태입니다.");
        if (enterButton) enterButton.style.display = "none"; // 상담 시작 버튼 숨기기
        chatInputContainer.style.display = "none"; // 입력창 숨기기
        loadChatsFromServer(sessionId); // 기존 채팅 내역 불러오기
    } else {
        console.log("✅ 상담이 진행 중입니다. 상담 시작 버튼을 표시합니다.");
        if (enterButton) {
            enterButton.addEventListener("click", function () {
                console.log("✅ 상담 시작 버튼 클릭됨!");
                enterButton.style.display = "none"; // 상담 시작 버튼 숨기기
                chatInputContainer.style.display = "flex"; // 입력창 보이기

                // ✅ WebSocket 연결
                connect(sessionId);

                // ✅ 상담 시작 메시지 전송
                startCounseling();
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
        content: messageContent,
        type: "USER",
        timestamp: new Date().toISOString()
    };

    showMessage(message);
    saveChatToLocal(message);

    // 메시지 저장
    fetch("/chatmessage", {
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
function goBack() {
    window.location.href = "/usermypage";
}

function confirmExit() {
    let isConfirmed = confirm("정말 상담을 종료하시겠습니까?");
    if (isConfirmed) {
        let chatContainer = document.querySelector(".chat-container");
        let sessionId = chatContainer.dataset.sessionId;

        // 채팅 로그 가져오기
        let chatLogs = JSON.parse(localStorage.getItem("chat_" + sessionId) || "[]");

        // 채팅 로그 요약 (50자까지만 저장)
        let summary = chatLogs.map(chat => chat.content).join(" ").substring(0, 50);

        fetch("/livechat/complete", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ session_id: parseInt(sessionId, 10), summary: summary })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("✅ 상담이 완료되었습니다!");
                    localStorage.removeItem(`chat_${sessionId}`);
                    window.location.href = "/usermypage";
                } else {
                    alert("❌ 상담 종료 실패. 다시 시도해주세요.");
                }
            })
            .catch(error => console.error("🚨 상담 종료 오류:", error));
    }
}

