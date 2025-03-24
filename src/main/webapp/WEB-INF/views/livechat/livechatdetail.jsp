<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    HttpSession session1 = request.getSession(false);
    String userId = null;

    if (session1 != null) {
        userId = (String) session1.getAttribute("userId");
    }

    if (userId == null) {
        response.sendRedirect("/login");
        return;
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/livechat/livechatdetail.css">
</head>
<body>

<div class="container">
    <!-- 왼쪽 사이드바 -->
    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn"><img src="/static/imgsource/layout/home.png" alt="홈"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/layout/calandar.png" alt="목록"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/layout/pencil.png" alt="채팅"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/layout/chat.png" alt="공유"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/layout/settingss.png" alt="설정"></button>
                <div class="bbiyak"><img src="/static/imgsource/layout/bbiyak.png"></div>
            </nav>
        </aside>
    </div>

    <!-- 오른쪽 메인 컨텐츠 -->
    <div class="right-container">
        <header class="header-bar">
            <div class="brand-title"><img src="/static/imgsource/layout/logo.png" alt="KOYOI 로고"></div>
            <div class="header-icons">
<<<<<<< HEAD
                <img class="myprofile-img" src="/static/imgsource/layout/testprofile.png" alt="프로필">
=======
                <button class="header-btn"><img src="/static/imgsource/logout.png" alt="logout"></button>
                <img class="myprofile-img" src="${user.user_img}?v=${now}" alt="프로필">
>>>>>>> 665f79712c2f0fc6ea52b7a29711d39434c45fda
            </div>
        </header>

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

                <!-- 🔹 채팅 메시지 박스 -->
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

                <!-- 🔹 입력창 (상담 시작 시 보이게) -->
                <div class="chat-input" style="display: none;">
                    <input type="text" id="chatInput" placeholder="메시지를 입력하세요">
                    <button onclick="sendMessage()">전송</button>
                </div>
                </div>

                <!-- 🔹 버튼들 정리 -->
                <div class="chat-buttons">
                    <c:if test="${counseling.status ne '완료'}">
                        <button id="enterButton" class="enter-chat-btn">상담 시작하기</button>
                    </c:if>
                    <button id="exitButton" class="end-chat-btn"
                            onclick="${counseling.status eq '완료' ? 'goBack()' : 'confirmExit()'}">
                        ${counseling.status eq '완료' ? '돌아가기' : '나가기'}
                    </button>
                </div>
            </div>
    </div>

        </main>

<!-- 스크립트 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/static/js/livechat/livechatdetail.js"></script>

</body>
</html>
