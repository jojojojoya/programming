<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/static/css/diary/diary.css">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/static/js/diary/diary.js" defer></script>
<script>window.selectedDate = "${selectedDate}";</script>

<!-- 달력 & 위클리 -->
<div class="calendar-container">

    <div id="calendar"></div>

    <div class="weekly-summary">
        <div class="weekly-summary-title">일주일간의 감정</div>
        <ul>
            <c:forEach var="diary" items="${weeklyDiaries}">
                <li class="weekly-item" data-diary-id="${diary.diary_id}">
                    <span class="weekly-item-emoji">${diary.emotion_emoji}</span>
                        ${diary.created_at.toLocalDate()} ${diary.title}
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<!-- 일기 작성 & 조회 -->
<div class="diary-section-container">
    <div class="diary-wrapper">
        <!-- 일기 작성 뷰 -->
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
            <input type="text" id="diaryTitle" placeholder="오늘 하루를 한 줄로 표현해 보세요!">

            <!-- 일기 내용 입력 -->
            <textarea id="diaryContent" placeholder="오늘 있었던 일이나 감정을 적어보세요 :)"></textarea><br>

            <!-- 버튼 -->
            <button id="saveBtn" onclick="saveDiary()">일기 등록하기</button>
            <button id="updateBtn" onclick="updateDiary()" style="display: none;">일기 수정 완료</button>
        </div>

        <!-- 상세 조회 뷰 -->
        <div id="diaryViewSection" style="display: none;">
            <!-- 날짜 -->
            <p><strong>TODAY:</strong> <span id="viewDiaryDate"></span></p>


            <!-- 이모지 선택 (비활성화) -->
            <div>
                <span id="view-🙂" class="emoji-option readonly" onclick="selectEmoji('🙂')">🙂</span>
                <span id="view-😢" class="emoji-option readonly" onclick="selectEmoji('😢')">😢</span>
                <span id="view-😡" class="emoji-option readonly" onclick="selectEmoji('😡')">😡</span>
                <span id="view-😆" class="emoji-option readonly" onclick="selectEmoji('😆')">😆</span>
                <span id="view-🥰" class="emoji-option readonly" onclick="selectEmoji('🥰')">🥰</span>
            </div>
            <!-- 타이틀 출력 -->
            <div id="viewDiaryTitle">오늘의 타이틀</div>

            <!-- 일기 내용 출력 -->
            <div id="viewDiaryContent" class="diary-content-view"></div>

            <!-- 수정/삭제 버튼 -->
            <button id="editBtn" class="btn-edit" onclick="switchToEditMode()">일기 수정하기</button>
            <button id="deleteBtn" class="btn-delete" onclick="deleteDiary()">일기 삭제하기</button>
        </div>
    </div>
</div>

<!-- ✅ 오늘의 감정 점수 모달 -->
<div id="emotionScoreModal" class="modal-overlay" style="display: none;">
    <div class="modal-container">
        <h2>오늘 하루는 어땠나요?</h2>
        <p>점수를 입력해 주세요! (10점 ~ 100점)</p>
        <div class="score-slider-container">
            <input type="range" id="emotionScoreInput" min="10" max="100" value="50" step="10"
                   oninput="updateScoreValue(this.value)">
            <div class="score-value"><span id="scoreDisplay">50</span> 점</div>
        </div>
        <div class="modal-buttons">
            <button id="confirmBtn" class="btn-confirm" onclick="saveEmotionScore()">저장하기</button>
            <button id="cancelBtn" class="btn-cancel" onclick="closeEmotionModal()">취소</button>
        </div>
    </div>
</div>