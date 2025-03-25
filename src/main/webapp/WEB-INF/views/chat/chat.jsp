<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
<button onclick="sendMessage()">보내기</button>
<button onclick="endChat()">대화 종료</button>

<script src="/static/js/chat/chat.js"></script>
</body>
</html>
