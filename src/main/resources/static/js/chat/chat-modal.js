(() => {
    console.log("[DEBUG] setupChatEvents 실행");

    // ✅ 중복 실행 방지
    if (window.chatModalInitialized) {
        console.log("[chat-modal.js] 이미 초기화됨 - 실행 중단");
        return;
    }
    window.chatModalInitialized = true;

    let chatHistory = [];

    // ✅ 모달 열기
    function openChatModal() {
        console.log("[DEBUG] openChatModal 호출됨");

        if (document.getElementById("chat-modal-wrapper")) {
            console.log("[openChatModal] 이미 열려 있음");
            return;
        }

        fetch("/chat/modal")
            .then(res => res.text())
            .then(html => {
                const modal = document.createElement("div");
                modal.innerHTML = html;
                document.body.appendChild(modal);
                console.log("[openChatModal] 모달 삽입 완료");

                setupChatEvents();
            });
    }

    // ✅ 이벤트 바인딩
    function setupChatEvents() {
        const input = document.getElementById("user-input");
        const sendBtn = document.getElementById("send-btn");
        const endBtn = document.getElementById("end-btn");
        const chatBox = document.getElementById("chat-box");

        if (!input || !sendBtn || !endBtn || !chatBox) {
            console.error("[setupChatEvents] 요소 찾기 실패");
            return;
        }

        // 중복 방지를 위해 초기화
        input.onkeydown = null;
        sendBtn.onclick = null;
        endBtn.onclick = null;
        document.onkeydown = null;

        // 인사 출력
        const welcomeMessage = `안녕 ${userName}! 오늘 하루는 어땠어?`;
        chatBox.innerHTML += `<p><b>GPT:</b> ${welcomeMessage}</p>`;
        chatHistory.push({ role: "assistant", content: welcomeMessage });

        // Enter 전송 감지
        let bindCount = 0;
        input.onkeydown = function (event) {
            if (event.key === "Enter" && !event.shiftKey) {
                console.log(`[Enter] called → 바인딩 횟수: ${++bindCount}`);
                event.preventDefault();
                sendMessage();
            }
        };

        sendBtn.onclick = () => {
            console.log("[Send Button] 클릭됨");
            sendMessage();
        };

        endBtn.onclick = () => {
            console.log("[End Button] 클릭됨");
            endChat();
        };

        document.onkeydown = function (event) {
            if (event.key === "Escape") {
                closeChatModal();
            }
        };
    }

    // ✅ 메시지 전송
    async function sendMessage() {
        const input = document.getElementById('user-input');
        const userInput = input.value.trim();
        if (!userInput) return;

        chatHistory.push({ role: "user", content: userInput });

        const res = await fetch("/api/chat", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ message: userInput })
        });

        const gptReply = await res.text();
        chatHistory.push({ role: "assistant", content: gptReply });

        const chatBox = document.getElementById('chat-box');
        chatBox.innerHTML += `<p><b>${userName}:</b> ${userInput}</p><p><b>GPT:</b> ${gptReply}</p>`;
        input.value = '';
        chatBox.scrollTop = chatBox.scrollHeight;
    }

    // ✅ 상담 종료 및 요약 저장
    async function endChat() {
        const confirmEnd = confirm("대화를 종료하시겠습니까?");
        if (!confirmEnd) return;

        const save = confirm("오늘 대화를 요약해서 저장하시겠습니까?");
        if (save) {
            const res = await fetch("/api/chat/summary", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ messages: chatHistory })
            });
            const result = await res.text();
            alert(result);
        }

        closeChatModal();
    }

    // ✅ 모달 닫기
    function closeChatModal() {
        const modal = document.getElementById("chat-modal-wrapper");
        if (modal) modal.remove();
        window.chatModalInitialized = false;
        console.log("[closeChatModal] 모달 닫힘 및 초기화 해제");
    }

    // ✅ 전역에서 사용할 수 있도록 등록
    window.openChatModal = openChatModal;
    window.closeChatModal = closeChatModal;
})();
