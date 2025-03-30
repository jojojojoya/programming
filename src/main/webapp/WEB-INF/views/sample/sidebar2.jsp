<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/sidebar2.css">
</head>
<body>

<!-- 전체 컨테이너 -->
<div class="container">

    <!-- 왼쪽 컨테이너 -->
    <div class="left-container">
        <!-- 🌙 달 로고 -->
        <div>
            <img class="sidebar-logo" src="/static/imgsource/layout/logo.png" alt="KOYOI 로고">
        </div>

        <!-- 🟣 사이드바 -->
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn"><img src="/static/imgsource/smile.png" alt="프로필"></button>
                <button onclick="location.href= '/diary'" class="sidebar-btn">
                    <img src="/static/imgsource/layout/calandar.png" alt="캘린더">
                </button>
                <button class="sidebar-btn"><img src="/static/imgsource/layout/pencil.png" alt="메모"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/layout/chat.png" alt="채팅"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/layout/settingss.png" alt="설정"></button>
            </nav>
        </aside>
    </div>


    <!-- 오른쪽 컨테이너 (헤더바 + 콘텐츠) -->
    <div class="right-container">
        <header class="header-bar">
            <h1 class="brand-title">KOYOI</h1>
            <div class="header-icons">
                <button class="header-btn">
                    <img src="/static/imgsource/layout/chat.png" alt="채팅">
                </button>
                <button class="header-btn">
                    <img src="/static/imgsource/layout/settingss.png" alt="설정">
                </button>
                <img class="profile-img" src="/static/imgsource/layout/testprofile.png" alt="프로필">
            </div>
        </header>

        <main class="content">
            <p>메인 콘텐츠 영역</p>
        </main>


    </div>

</div>

</body>
</html>
