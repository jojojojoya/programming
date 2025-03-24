// 채팅 기록을 저장하는 배열 → 나중에 요약 기능에 사용됨
let chatHistory = [];

/**
 * 메시지 전송 함수
 * 1. 사용자가 입력한 메시지를 읽고
 * 2. 서버에 전송한 뒤
 * 3. GPT 응답을 받아서 화면에 출력하고 기록에 추가함
 */
async function sendMessage() {
    const inputField = document.getElementById('user-input');
    const userInput = inputField.value;

    // 빈 값이면 전송 안 함
    if (!userInput) return;

    // 사용자의 메시지를 기록에 추가
    chatHistory.push({ role: "user", content: userInput });

    // 서버에 POST 요청 보내서 GPT 응답 받기
    const response = await fetch("/api/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: userInput }) // ChatMessageDTO로 변환되어 컨트롤러로 전달됨
    });

    const gptReply = await response.text();

    // GPT 응답도 기록에 추가
    chatHistory.push({ role: "assistant", content: gptReply });

    // 채팅 박스에 대화 내역 출력 추가
    const chatBox = document.getElementById('chat-box');
    chatBox.innerHTML += `
        <p><b>나:</b> ${userInput}</p>
        <p><b>GPT:</b> ${gptReply}</p>
    `;

    // 입력창 초기화
    inputField.value = '';
}

/**
 * 대화 종료 처리 함수
 * 1. 대화를 종료할지 확인
 * 2. 오늘 대화 요약을 저장할지 확인하고
 * 3. 저장 요청을 서버에 보내고 완료되면 메인 페이지로 이동
 */
async function endChat() {
    // 대화 종료 확인
    const confirmEnd = confirm("대화를 종료하시겠습니까?");
    if (!confirmEnd) return;

    // 요약 저장 여부 확인
    const saveSummary = confirm("오늘 대화를 요약해서 저장하시겠습니까?");
    if (saveSummary) {
        // 서버에 요약 저장 요청
        const response = await fetch("/api/chat/summary", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ messages: chatHistory }) // ChatHistoryDTO로 변환되어 컨트롤러로 전달됨
        });

        const result = await response.text();
        alert(result);
    }

    // 메인 페이지로 이동 (URL은 프로젝트에 맞게 수정 가능)
    window.location.href = "/main";
}
