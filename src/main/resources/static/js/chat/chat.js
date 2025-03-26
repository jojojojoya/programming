let chatHistory = [];

async function sendMessage() {
    const input = document.getElementById('user-input');
    const userInput = input.value;
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
    chatBox.innerHTML += `<p><b>나:</b> ${userInput}</p><p><b>GPT:</b> ${gptReply}</p>`;
    input.value = '';
}

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

    window.location.href = "/main";
}

document.addEventListener("DOMContentLoaded", () => {
    const input = document.getElementById("user-input");
    const sendBtn = document.getElementById("send-btn");
    const endBtn = document.getElementById("end-btn");

    // 자동 인사 출력
    const welcomeMessage = `안녕 ${userName}! 오늘 하루는 어땠어?`;
    document.getElementById("chat-box").innerHTML += `<p><b>GPT:</b> ${welcomeMessage}</p>`;
    chatHistory.push({ role: "assistant", content: welcomeMessage });

    // 엔터키 이벤트 (Shift+Enter = 줄바꿈, Enter = 전송)
    input.addEventListener("keydown", function (event) {
        if (event.key === "Enter" && !event.shiftKey) {
            event.preventDefault();
            sendMessage();
        }
    });

    // 버튼 클릭 이벤트 따로 연결
    sendBtn.addEventListener("click", sendMessage);
    endBtn.addEventListener("click", endChat);
});