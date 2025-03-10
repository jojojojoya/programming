<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/habit.css">
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
            <div class="brand-title">
                <img src="/static/imgsource/logo.png" alt="KOYOI 로고">
            </div>
            <div class="header-icons">
                <img class="profile-img" src="/static/imgsource/testprofile.png" alt="프로필">
            </div>
        </header>

        <main class="content">
            <div class="habit-page">
                <div class="habit-partone">
                    <div class="habit-list">습관목록</div>
                    <div class="habit-recommend">
                        <div class="habit-tabs">
                            <div id="habit-tab-신체건강" class="habit-tab habit-active" onclick="habitShowTab('신체건강')">신체건강</div>
                            <div id="habit-tab-정신건강" class="habit-tab" onclick="habitShowTab('정신건강')">정신건강</div>
                        </div>

                        <div id="habit-신체건강" class="habit-content">
                            <p>✅ 산책</p>
                            <p>🍽️ 식사</p>
                            <p>🏋️ 운동</p>
                        </div>

                        <div id="habit-정신건강" class="habit-content habit-hidden">
                            <p>🎵 음악듣기</p>
                            <p>📝 일기쓰기</p>
                        </div>
                    </div>
                </div>
                <div class="habit-parttwo">
                    <div class="habit-parttwo-first">
                        <div class="habit-calendar">달력</div>
                        <div class="habit-week">주간습관</div>
                    </div>
                    <div class="habit-parttwo-second">
                        <div class="habit-rate">습관달성률</div>
                        <div class="habit-memo">유저총평</div>
                    </div>
                </div>
            </div>
        </main>
    </div>

</div>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="/static/js/habit/habit.js"></script>
</body>
</html>
