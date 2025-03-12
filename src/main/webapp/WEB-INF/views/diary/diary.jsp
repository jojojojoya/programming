<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/static/css/finalindex.css">
    <link rel="stylesheet" href="/static/css/diary/diary.css">
    <script src="/static/js/diary/diary.js" defer></script>
</head>
<body>
<!-- 전체 컨테이너 -->
<div class="container">
    <!-- 🟠 왼쪽 컨테이너 (사이드바) -->
    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn">
                    <img src="/static/imgsource/home.png" alt="홈">
                </button>
                <button class="sidebar-btn"><img src="/static/imgsource/calandar.png" alt="목록"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/pencil.png" alt="채팅"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/chat.png" alt="공유"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/settingss.png" alt="설정"></button>
                <div class="bbiyak">
                    <img src="/static/imgsource/bbiyak.png">
                </div>
            </nav>
        </aside>
    </div>
    <!-- 🟣 오른쪽 컨테이너 (헤더바 + 콘텐츠) -->
    <div class="right-container">
        <header class="header-bar">
            <!-- 🌙 중앙 브랜드 로고 -->
            <div class="brand-title">
                <img src="/static/imgsource/logo.png" alt="KOYOI 로고">
            </div>

            <!-- 🟡 우측 상단 아이콘 -->
            <div class="header-icons">
                <img class="profile-img" src="/static/imgsource/testprofile.png" alt="프로필">
            </div>
        </header>
        <!-- 📌 메인 콘텐츠 (달력 & 일기 작성) -->
        <main class="content">
            <div class="calendar-container">
                <!-- 📅 달력 -->
                <div id="calendar"></div>
                <!-- 📖 일기 작성 -->
                <div id="diary-section">
                    <p>TODAY: <span id="diaryDate"></span></p>
                    <div>
                        <span id="🙂" class="emoji-option selected" onclick="selectEmoji('🙂')">🙂</span>
                        <span id="😢" class="emoji-option" onclick="selectEmoji('😢')">😢</span>
                        <span id="😡" class="emoji-option" onclick="selectEmoji('😡')">😡</span>
                        <span id="😆" class="emoji-option" onclick="selectEmoji('😆')">😆</span>
                        <span id="🥰" class="emoji-option" onclick="selectEmoji('🥰')">🥰</span>
                    </div>
                    <textarea id="diaryTitle" rows="10" cols="50"></textarea><br>
                    <textarea id="diaryContent" rows="10" cols="50"></textarea><br>
                    <button onclick="saveDiary()">일기 등록하기</button>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>