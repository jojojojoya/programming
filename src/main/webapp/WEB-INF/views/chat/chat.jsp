<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String userName = (String) session.getAttribute("userName");
    if (userName == null) userName = "친구"; // 예외 처리
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

<!-- ✅ 사용자 이름을 JS 변수로 넘기기 -->
<script>
    const userName = "<%= userName %>";
</script>

<!-- ✅ JS 기능 파일 -->
<script src="/static/js/chat/chat.js"></script>
</body>
</html>
