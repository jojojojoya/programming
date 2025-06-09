<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/livechat/livechatdetail.css">
</head>
<body>
<div class="livechatdetail-form">
        <div class="chat-container"
             data-session-id="${counseling.session_id}"
             data-counseling-id="${counseling.counseling_id}"
             data-category="${counseling.category}"
             data-counseling-date="${counseling.counseling_date}"
             data-counseling-time="${counseling.counseling_time}"
             data-user-id="${user.user_id}"
             data-user-type="${userType}"
             data-counselor-id="${counseling.counselor_id}"
        data-is-completed="${isCompleted}">
            <c:out value="${counseling.session_id}" />


        <div class="chat-box" id="chatBox">
            <c:choose>
                <c:when test="${not empty chatLogs}">
                    <c:forEach var="chat" items="${chatLogs}">
                        <div class="chat-message ${chat.sender eq user.user_id ? 'my-msg' : 'other-msg'}">
                            <strong>${chat.sender}:</strong> ${chat.message}
                        </div>

                    </c:forEach>


                </c:when>
                <c:otherwise>
                    <div class="no-messages"> 相談履歴がありません。</div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="chat-input" style="display: none;">
            <input type="text" id="chatInput" placeholder="メッセージを入力してください。">
            <button onclick="sendMessage()">送信</button>
        </div>
</div>

<div class="chat-buttons">
    <c:if test="${counseling.status ne '完了'}">
        <button id="enterButton" class="enter-chat-btn">相談を開始する</button>
    </c:if>
    <button id="exitButton" class="end-chat-btn"
            onclick="${counseling.status eq '完了' ? 'goBack()' : 'confirmExit()'}">
        ${counseling.status eq '完了' ? '戻る' : '終了する'}
    </button>

</div>

</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/static/js/livechat/livechatdetail.js"></script>
</body>
</html>