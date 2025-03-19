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
    <!-- 서버세션으로 전환 후 활성화
    <script>
        const selectedDate = "${selectedDate}"; // 서버에서 내려준 값
        console.log("✅ 서버에서 받은 selectedDate:", selectedDate);
    </script> -->

</head>
<body>
<!-- 전체 컨테이너 -->
<div class="container">
    <!-- 🟠 왼쪽 컨테이너 (사이드바) -->
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

    <!-- 🟣 오른쪽 컨테이너 -->
    <div class="right-container">
        <header class="header-bar">
            <div class="brand-title"><img src="/static/imgsource/logo.png" alt="KOYOI 로고"></div>
            <div class="header-icons"><img class="profile-img" src="/static/imgsource/testprofile.png" alt="프로필"></div>
        </header>

        <!-- 📌 메인 콘텐츠 (달력 & 일기 작성) -->
        <main class="content">
            <div class="calendar-container">
                <!-- 📅 달력 -->
                <div id="calendar"></div>

                <!-- ✅ 일기 작성 뷰 -->
                <div id="diaryWriteSection" style="display: block;">
                    <!-- 날짜 -->
                    <p><strong>TODAY:</strong> <span id="diaryDate"></span></p>

                    <!-- 이모지 선택 -->
                    <div>
                        <span id="write-🙂" class="emoji-option" onclick="selectEmoji('🙂')">🙂</span>
                        <span id="write-😢" class="emoji-option" onclick="selectEmoji('😢')">😢</span>
                        <span id="write-😡" class="emoji-option" onclick="selectEmoji('😡')">😡</span>
                        <span id="write-😆" class="emoji-option" onclick="selectEmoji('😆')">😆</span>
                        <span id="write-🥰" class="emoji-option" onclick="selectEmoji('🥰')">🥰</span>
                    </div>

                    <!-- 타이틀 입력 -->
                    <input type="text" id="diaryTitle" placeholder="오늘 하루를 한 줄로 표현해 보세요!" style="width: 100%; padding: 10px; margin-bottom: 10px; font-size: 1em; border-radius: 8px; border: 1px solid #ccc;">

                    <!-- 일기 내용 입력 -->
                    <textarea id="diaryContent" rows="10" cols="50" placeholder="오늘 있었던 일이나 감정을 적어보세요 :)"></textarea><br>

                    <!-- 버튼 -->
                    <button id="saveBtn" onclick="saveDiary()">일기 등록하기</button>
                    <button id="updateBtn" onclick="updateDiary()" style="display: none;">일기 수정 완료</button>
                </div>

                <!-- 상세 조회 뷰 -->
                <div id="diaryViewSection" style="display: none;">
                    <!-- 날짜 -->
                    <p>TODAY: <span id="viewDiaryDate"></span></p>


                    <!-- 이모지 선택 (비활성화 모드) -->
                    <div>
                        <span id="view-🙂" class="emoji-option readonly" onclick="selectEmoji('🙂')">🙂</span>
                        <span id="view-😢" class="emoji-option readonly" onclick="selectEmoji('😢')">😢</span>
                        <span id="view-😡" class="emoji-option readonly" onclick="selectEmoji('😡')">😡</span>
                        <span id="view-😆" class="emoji-option readonly" onclick="selectEmoji('😆')">😆</span>
                        <span id="view-🥰" class="emoji-option readonly" onclick="selectEmoji('🥰')">🥰</span>
                    </div>
                    <!-- 타이틀 출력 -->
                    <h3 id="viewDiaryTitle" style="margin: 10px 0; font-size: 1.5em;">오늘의 타이틀</h3>

                    <!-- 일기 내용 출력 -->
                    <div id="viewDiaryContent" class="diary-content-view"></div>

                    <!-- 수정/삭제 버튼 -->
                    <button id="editBtn" class="btn-edit" onclick="switchToEditMode()">일기 수정하기</button>
                    <button id="deleteBtn" class="btn-delete" onclick="deleteDiary()">일기 삭제하기</button>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- ✅ 오늘의 감정 점수 모달 -->
<div id="emotionScoreModal" class="modal-overlay" style="display: none;">
    <div class="modal-container">
        <h2>오늘 하루는 어땠나요?</h2>
        <p>점수를 입력해 주세요! (10점 ~ 100점)</p>
        <div class="score-slider-container">
            <input type="range" id="emotionScoreInput" min="10" max="100" value="50" step="10" oninput="updateScoreValue(this.value)">
            <div class="score-value"><span id="scoreDisplay">50</span> 점</div>
        </div>
        <div class="modal-buttons">
            <button id="confirmBtn" class="btn-confirm" onclick="saveEmotionScore()">저장하기</button>
            <button id="cancelBtn" class="btn-cancel" onclick="closeEmotionModal()">취소</button>
        </div>
    </div>
</div>
</body>
</html>
