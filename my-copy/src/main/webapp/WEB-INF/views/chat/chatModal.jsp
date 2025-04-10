<%@ page contentType="text/html;charset=UTF-8" %>
<%
    HttpSession session1 = request.getSession(false);
    String userId = null;
    String userNickName = "친구";

    if (session1 != null) {
        userId = (String) session1.getAttribute("userId");
        String nicknameFromSession = (String) session1.getAttribute("userNickName");
        if (nicknameFromSession != null) {
            userNickName = nicknameFromSession;
        }
    }

    if (userId == null) {
        response.sendRedirect("/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/chat/chat-modal.css">
    <title>GPT 심리 상담 - 모달</title>
</head>
<body>
<div id="chat-modal-wrapper">
    <div id="chat-modal">
        <div class="chat-header">
            <span> ✨ KOYOI Open Chat ✨</span>
            <button class="close-btn" type="button" onclick="closeChatModal()">&#x2715;</button>
        </div>

        <div id="chat-box"></div>

        <div class="chat-input">
            <input type="text" id="user-input" placeholder="メッセージを入力してください。">
            <button id="send-btn" type="button">送信</button>
            <button id="end-btn" type="button">会話終了</button>
        </div>

        <form action="/livechatreservation" method="get">
            <!-- <button type="submit">カウンセリング予約</button> -->
            <button type="submit" class="modal-link-btn">
                深いお悩みは、専門家と一緒に。→
            </button>
        </form>

    </div>
</div>

<script>
    const userName = "<%= userNickName %>";
    console.log("[chatModal.jsp] userName =", userName);
</script>
<%-- ❌ JS 로드 금지! 중복 실행 방지 목적 --%>
</body>
</html>
