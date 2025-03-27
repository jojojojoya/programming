<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/livechat/livechatdetail.css">
</head>
<body>
        <main class="content">
            <div class="chat-container"
                 data-session-id="${counseling.session_id}"
                 data-counseling-id="${counseling.counseling_id}"
                 data-category="${counseling.category}"
                 data-counseling-date="${counseling.counseling_date}"
                 data-counseling-time="${counseling.counseling_time}"
                 data-user-id="user5"
                 data-user-type="USER"
                 data-is-completed="${isCompleted}">

                <div class="chat-box" id="chatBox">
                    <c:choose>
                        <c:when test="${not empty chatLogs}">
                            <c:forEach var="chat" items="${chatLogs}">
                                <div class="chat-message ${chat.user_type eq 'USER' ? 'user-msg' : 'counselor-msg'}">
                                    <strong>${chat.sender}:</strong> ${chat.message}
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="no-messages">대화 내용이 없습니다.</div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="chat-input" style="display: none;">
                    <input type="text" id="chatInput" placeholder="메시지를 입력하세요">
                    <button onclick="sendMessage()">전송</button>
                </div>
                </div>

                <div class="chat-buttons">
                    <c:if test="${counseling.status ne '완료'}">
                        <button id="enterButton" class="enter-chat-btn">상담 진행하기</button>
                    </c:if>
                    <button id="exitButton" class="end-chat-btn"
                            onclick="${counseling.status eq '완료' ? 'goBack()' : 'confirmExit()'}">
                        ${counseling.status eq '완료' ? '돌아가기' : '나가기'}
                    </button>
                </div>

        </main>

<!-- 스크립트 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/static/js/livechat/livechatdetail.js"></script>

</body>
</html>
