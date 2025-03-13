<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/habit/habit.css">
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
                    <div class="habit-list">
                        <div class="myhabit">내 습관</div>
                        <div class="myhabit-list">
                            <!-- HabitVO에서 받은 습관 목록을 반복문을 통해 출력 -->
                            <c:forEach var="habit" items="${habits}">
                                <div>
                                    <input type="checkbox" id="habit-${habit.habit_id}" />
                                    <label for="habit-${habit.habit_id}">${habit.habit_name}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="habit-recommend">
                        <div class="habit-tabs">
                            <div id="habit-tab-신체건강" class="habit-tab habit-active" onclick="habitShowTab('신체건강')">신체건강</div>
                            <div id="habit-tab-정신건강" class="habit-tab" onclick="habitShowTab('정신건강')">정신건강</div>
                        </div>

                        <div id="habit-신체건강" class="habit-content">
                            <div style="float: left; width: 25%;" >
                                <p id="exercise">✅ 운동</p>
                                <p id="meal">🍽️ 식사</p>
                                <p id="diet">🏋️ 체중관리</p>
                            </div>
                            <div style="float: left; width: 25%;" >
                                <p id="supplement">✅ 영양제</p>
                                <p id="water">🍽️ 물마시기</p>
                                <p id="posture">🏋️ 자세</p>
                            </div>
                            <div style="float: left; width: 25%;">
                                <p id="sunshine">✅ 햇볕</p>
                                <p id="rest">🍽️ 휴식</p>
                                <p id="stretch">🏋️ 스트레칭</p>
                            </div>
                        </div>

                        <div id="habit-정신건강" class="habit-content habit-hidden">
                            <div style="float: left; width: 40%;">
                                <p id="music" >🎵 음악듣기</p>
                                <p id="diary">📝 일기쓰기</p>
                                <p id="draw">📝 그림그리기</p>
                            </div>
                            <div style="float: left; width: 40%;">
                                <p id="meditation">🎵 명상</p>
                                <p id="book">📝 독서</p>
                                <p id="breath">📝 호흡</p>
                            </div>
                            <div style="float: left; width: 40%;">
                                <p id="smile">🎵 웃기</p>
                                <p id="communication">📝 소통</p>
                                <p id="compliment">📝 칭찬</p>
                            </div>
                        </div>
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
