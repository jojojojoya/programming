<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/static/css/diary/diary.css">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/static/js/diary/diary.js" defer></script>
<script>window.selectedDate = "${selectedDate}";</script>



<style>
    @font-face {
        font-family: 'MyFont';
        src: url('/static/fonts/Boku2-Regular.otf') format('opentype');
    }

    body {
        font-family: 'MyFont', sans-serif;
        font-size: 32px;
        color: #000000;
    }
</style>



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
<script>
    const userId = "<%= userId %>";
</script>

<!-- calendar & weekly -->
<div class="calendar-container">

    <div id="calendar"></div>

    <div class="weekly-summary">
        <div class="weekly-summary-title">こころの1週間</div>
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

<!-- diary -->
<div class="diary-section-container">
    <div class="diary-wrapper">

        <!-- diary-write-section -->
        <div class="diary-write-section" id="diaryWriteSection" style="display: block;">
            <!-- date -->
            <p><strong style="font-size: 18px; color:#3f2e29; ">今日のこと:</strong> <span id="diaryDate"></span></p>

            <!-- emoji -->
            <div>
                <span id="write-🙂" class="emoji-option" data-emoji="🙂">🙂</span>
                <span id="write-😢" class="emoji-option" data-emoji="😢">😢</span>
                <span id="write-😡" class="emoji-option" data-emoji="😡">😡</span>
                <span id="write-😆" class="emoji-option" data-emoji="😆">😆</span>
                <span id="write-🥰" class="emoji-option" data-emoji="🥰">🥰</span>
            </div>

            <!-- diary title -->
            <input type="text" class="diary-write-title" id="diaryTitle" placeholder="今日はどんな一日だったの？">

            <!-- diary content -->
            <textarea class="diary-write-content" id="diaryContent"
                      placeholder="一日中のことやあなたの気分を書いてみてね :)"></textarea><br>

            <!-- button -->
            <div class="diary-button-wrapper">
                <button id="saveBtn" class="diary-btn" onclick="saveDiary()">こよいを登録する</button>
                <button id="updateBtn" class="diary-btn" onclick="updateDiary()" style="display: none;">編集をおわる
                </button>
            </div>
        </div>

        <!-- diary-view-section -->
        <div class="diary-view-section" id="diaryViewSection" style="display: none;">
            <!-- date -->
            <p><strong>今日のこと:</strong> <span id="viewDiaryDate"></span></p>


            <!-- emoji (Disabled) -->
            <div>
                <span id="view-🙂" class="emoji-option readonly" data-emoji="🙂">🙂</span>
                <span id="view-😢" class="emoji-option readonly" data-emoji="😢">😢</span>
                <span id="view-😡" class="emoji-option readonly" data-emoji="😡">😡</span>
                <span id="view-😆" class="emoji-option readonly" data-emoji="😆">😆</span>
                <span id="view-🥰" class="emoji-option readonly" data-emoji="🥰">🥰</span>
            </div>
            <!-- diary title view -->
            <div class="diary-view-title" id="viewDiaryTitle">タイトル</div>

            <!-- diary title view -->
            <div class="diary-view-content" id="viewDiaryContent" class="diary-content-view"></div>

            <!-- update/delete button -->
            <div class="diary-button-wrapper">
                <button id="editBtn" class="diary-btn" onclick="switchToEditMode()">こよいを編集する</button>
                <button id="deleteBtn" class="diary-btn" onclick="deleteDiary()">こよいを削除する</button>
            </div>
        </div>
    </div>
</div>

<!-- emotion score modal -->
<div id="emotionScoreModal" class="modal-overlay" style="display: none;">
    <div class="modal-container">
        <h2>今日の一日はどうでしたか？</h2>
        <p>スコアを入れてください! (10点 ~ 100点)</p>
        <div class="score-slider-container">
            <input type="range" id="emotionScoreInput" min="10" max="100" value="50" step="10"
                   oninput="updateScoreValue(this.value)">
            <div class="score-value"><span id="scoreDisplay">50</span> 点</div>
        </div>
        <div class="modal-buttons">
            <button id="confirmBtn" class="btn-confirm" onclick="saveEmotionScore()">こよいをまとめる</button>
            <button id="cancelBtn" class="btn-cancel" onclick="closeEmotionModal()">キャンセル</button>
        </div>
    </div>
</div>