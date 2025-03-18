<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/livechat/livechatdetail.css">
</head>
<body>

<!-- 전체 컨테이너 -->
<div class="container">
    <!-- 사이드바 -->
    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn"><img src="/static/imgsource/home.png" alt="홈"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/calandar.png" alt="목록"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/pencil.png" alt="채팅"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/chat.png" alt="공유"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/settingss.png" alt="설정"></button>
                <div class="bbiyak"><img src="/static/imgsource/bbiyak.png"></div>
            </nav>
        </aside>
    </div>

    <!-- 우측 컨텐츠 -->
    <div class="right-container">
        <header class="header-bar">
            <div class="brand-title"><img src="/static/imgsource/logo.png" alt="KOYOI 로고"></div>
            <div class="header-icons">
                <img class="myprofile-img" src="/static/imgsource/testprofile.png" alt="프로필">
            </div>
        </header>

        <main class="content">
            <div class="chat-container"
                 data-session-id="${counseling.session_id}"
                 data-counseling-id="${counseling.counseling_id}"
                 data-category="${counseling.category}"
                 data-counseling-date="${counseling.counseling_date}"
                 data-counseling-time="${counseling.counseling_time}"
                 data-user-id="${user.user_id}"
                 data-user-type="USER"
                 data-is-completed="${counseling.status eq '완료' ? 'true' : 'false'}"
                 data-chat-logs='${chatLogsJson}'> <!-- JSON으로 채팅 내역 전달 -->


                <div class="chat-box" id="chatBox">
                    <c:choose>
                        <c:when test="${not empty chatLogs}">
                            <c:forEach var="chat" items="${chatLogs}">
                                <c:if test="${not empty chat.content}">
                                    <div class="chat-message ${chat.user_type eq 'USER' ? 'user-msg' : 'counselor-msg'}">
                                        <c:if test="${not empty chat.sender}">
                                            <strong>${chat.sender}:</strong>
                                        </c:if>
                                            ${chat.content}
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="no-messages">대화 내용이 없습니다.</div>
                        </c:otherwise>
                    </c:choose>
                </div>





                <c:if test="${counseling.status ne '완료'}">
                    <button id="enterButton" class="enter-chat-btn">상담 시작하기</button>
                    <div class="chat-input" style="display: none;">
                        <input type="text" id="chatInput" placeholder="메시지를 입력하세요">
                        <button onclick="sendMessage()">전송</button>
                    </div>
                </c:if>
                <button id="exitButton" class="end-chat-btn"
                        onclick="${counseling.status eq '완료' ? 'goBack()' : 'confirmExit()'}">
                    ${counseling.status eq '완료' ? '돌아가기' : '나가기'}
                </button>

            </div>
        </main>
    </div>
</div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <script src="/static/js/livechat/livechatdetail.js"></script>

</body>
</html>