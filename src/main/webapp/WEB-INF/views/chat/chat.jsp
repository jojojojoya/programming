<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 세션 체크 및 유저 정보 추출 --%>
<%
    HttpSession session1 = request.getSession(false); // 기존 세션 가져오기
    String userId = null;
    String userNickName = "친구"; // 기본 닉네임

    if (session1 != null) {
        userId = (String) session1.getAttribute("userId");
        String nicknameFromSession = (String) session1.getAttribute("userNickName");
        if (nicknameFromSession != null) {
            userNickName = nicknameFromSession;
        }
    }

    if (userId == null) {
        response.sendRedirect("/login"); // 세션 없거나 만료되었으면 로그인으로 이동
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GPT 심리 상담</title>
</head>
<body>
<h2>GPT 심리 상담 챗봇</h2>

<div id="chat-box" style="border:1px solid #aaa; width:500px; height:300px; overflow-y:scroll;"></div>

<input type="text" id="user-input" placeholder="메시지를 입력하세요" style="width:400px;">
<button id="send-btn">보내기</button>
<button id="end-btn">대화 종료</button>
<form>
    <button>상담사 예약</button>
</form>

<!-- ✅ JS에서 사용할 닉네임 변수 넘기기 -->
<script>
    const userName = "<%= userNickName %>";
</script>

<script src="/static/js/chat/chat.js"></script>
</body>
</html>
