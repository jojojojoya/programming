
let stompClient = null;
let reconnectAttempts = 0;
const MAX_RECONNECT_ATTEMPTS = 5;

function formatDate(counselingDate) {
    try {
        console.log("📌 formatDate() 원본 값:", counselingDate);

        // 날짜가 "2025-03-28" 형식인지 확인
        if (/^\d{4}-\d{2}-\d{2}$/.test(counselingDate)) {
            let [year, month, day] = counselingDate.split("-");
            return `${year}년 ${month}월 ${day}일`;
        }

        // 기존의 요일이 포함된 형식 처리 (예: "Fri Mar 21 00:00:00 KST 2025")
        let dateParts = counselingDate.split(" ");
        if (dateParts.length < 6) {
            console.error("🚨 날짜 문자열 파싱 실패! 원본 값:", counselingDate);
            return "날짜 변환 실패";
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
        console.log("✅ 변환된 날짜:", formattedDate);
        return formattedDate;
    } catch (error) {
        console.error("🚨 formatDate() 오류:", error);
        return "날짜 변환 실패";
    }
}


function connect(sessionId) {
    if (!sessionId || sessionId === "undefined") {
        console.warn("⚠️ WebSocket 연결 대기: sessionId가 아직 없음. 2초 후 다시 시도...");
        setTimeout(() => {
            let newSessionId = document.querySelector(".chat-container").dataset.sessionId;
            if (newSessionId && newSessionId !== "undefined") {
                connect(newSessionId);  // 다시 연결 시도
            }
        }, 2000); // 2초 후 다시 체크
        return;
    }

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


async function startCounseling() {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;
    let category = chatContainer.dataset.category;
    let counselingDate = chatContainer.dataset.counselingDate;
    let counselingTime = chatContainer.dataset.counselingTime;

    let formattedDate = formatDate(counselingDate);
    let formattedTime = String(counselingTime).padStart(2, "0") + "시 00분";

    let welcomeMessage = {
        session_id: sessionId,
        sender: "상담사",
        message: `안녕하세요! ${formattedDate} ${formattedTime}에 예약된 '${category}' 관련 상담을 도와드리겠습니다.<br>😊 편하게 하고 싶은 말씀을 들려주세요.`,
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
            console.error("❌ 상담사 메시지 저장 실패:", data.message);
        }
    } catch (error) {
        console.error("🚨 상담사 메시지 저장 오류:", error);
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

    // ✅ 여기서 `message.message`로 접근해야 함!
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
            enterButton.addEventListener("click", async function () {
                console.log("✅ 상담 시작 버튼 클릭됨!");
                enterButton.style.display = "none";

                // ✅ WebSocket 연결
                connect(sessionId);

                // ✅ 상담사 자동 메시지 먼저 전송 및 DB 저장
                await startCounseling();

                // ✅ 유저 입력창 활성화는 그 이후!
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
        message: messageContent,  // ✅ content → message 로 바꿈
        user_type: "USER",
        timestamp: new Date().toISOString()
    };

    console.log("📩 [프론트엔드] 전송할 메시지:", message);

    showMessage(message);  // ✅ 메시지를 UI에 표시
    saveChatToLocal(message);  // ✅ 로컬 저장

    // ✅ 메시지 저장 API 호출
    fetch("/chatmessage", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(message)
    })
        .then(response => response.json())
        .then(data => {
            if (!data.success) {
                console.error("❌ [프론트엔드] 메시지 저장 실패: ", data.message);
            } else {
                console.log("✅ [프론트엔드] 메시지 저장 성공!");
            }
        })
        .catch(error => console.error("🚨 [프론트엔드] 메시지 저장 오류:", error));

    stompClient.send("/app/chat", {}, JSON.stringify(message));  // ✅ WebSocket 전송
    chatInput.value = ""; // 입력창 비우기
}


function goBack() {
    window.location.href = "/usermypage";
}


function confirmExit() {
    let chatContainer = document.querySelector(".chat-container");
    let sessionId = chatContainer.dataset.sessionId;

    if (!sessionId) {
        console.error("🚨 [오류] sessionId가 없습니다!");
        alert("세션 정보가 없습니다. 다시 시도해주세요.");
        return;
    }

    // ✅ 1. sessionId를 이용해 counselingId 가져오기
    fetch(`/livechat/getCounselingId?sessionId=${sessionId}`)
        .then(response => response.json())
        .then(data => {
            if (!data.success || !data.counseling_id) {
                alert("❌ 상담 정보를 찾을 수 없습니다. 다시 시도해주세요.");
                return;
            }

            let counselingId = data.counseling_id;

            // ✅ 2. 상담 상태를 '완료'로 변경
            fetch("/livechat/updateStatus", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ counseling_id: counselingId, status: "완료" })
            })
                .then(response => response.json())
                .then(updateData => {
                    if (updateData.success) {
                        alert("✅ 상담이 완료되었습니다!");
                        window.location.href = "/usermypage";
                    } else {
                        alert("❌ 상담 상태 업데이트 실패. 다시 시도해주세요.");
                    }
                })
                .catch(error => console.error("🚨 상담 상태 업데이트 오류:", error));
        })
        .catch(error => console.error("🚨 상담 ID 조회 오류:", error));
}
