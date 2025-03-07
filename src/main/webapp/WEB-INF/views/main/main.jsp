<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>KOYOI</title>
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/main/main.css">
    <script src="/static/js/main.js"></script>
</head>
<body>

<div class="container">

    <header class="header-bar">

        <div class="logo-container">
            <img class="logo-icon" src="/static/imgsource/logo.png" alt="KOYOI">
        </div>
        <div class="header-icons">
            <button class="header-btn">
                <img src="/static/imgsource/notice.png" alt="notice">
            </button>
            <button class="header-btn">
                <img src="/static/imgsource/chat.png" alt="message">
            </button>
            <button class="header-btn">
                <img src="/static/imgsource/logout.png" alt="logout">
            </button>
            <img class="profile-img" src="/static/imgsource/testprofile.png" alt="profile">
        </div>

    </header>

    <main class="main-container">

        <div class="quotes-container">
            <div class="quotes-content">"Life is like riding a bicycle. To keep your balance you must keep moving" - Albert Einstein</div>
        </div>

        <div class="content-wrapper">
            <!-- 왼쪽 영역: 달력 -->
            <div class="left-content">
                <div class="calendar-container">
                    <jsp:include page="maincalendar.jsp"/>
                </div>
               <%-- <div class="chat-connect">
                    <div> 챗봇 </div>
                    <div> 라이브챗 </div>
                </div>--%>
            </div>

            <!-- 오른쪽 영역: 무드 그래프 + 체크리스트 -->
            <!-- 오른쪽 영역: 무드 그래프 + 챗봇 + 체크리스트 -->
            <div class="right-content">
                <!-- 무드 그래프 + 챗봇 -->
                <div class="upper-section">
                    <%--<div class="mood-graph"> 무드 그래프 </div>--%>
                    <div class="chat-connect">
                        <button class="chatbot"> 챗봇 </button>
                        <button class="livechat"> 라이브챗 </button>
                    </div>
                    <div class="mood-graph"> 무드 그래프 </div>
                </div>

                <!-- 체크리스트 -->
                <div class="checklists">
                    <div class="checklist"> 체크리스트1 </div>
                    <div class="checklist"> 체크리스트2 </div>
                </div>
            </div>
        </div>

    </main>

</div>
</body>
</html>