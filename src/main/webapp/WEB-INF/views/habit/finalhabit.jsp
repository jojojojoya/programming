<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/habit/habit.css">
    <script src="/static/js/habit/habit.js"></script>
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
                <!-- ✅ 1행: 내 습관 / 캘린더 / 주간 이력 -->
                <div class="widget-box habit-list">
                    <div class="myhabit">내 습관 </div>
                    <div class="myhabit-list">
                        <c:forEach var="habit" items="${habits}">
                            <div id="habit-box-${habit.habit_id}">
                                <input type="checkbox" id="habit-${habit.habit_id}" />
                                <label for="habit-${habit.habit_id}">${habit.habit_name}</label>
                                <button class="delete-btn" onclick="deleteHabit(${habit.habit_id})">삭제</button>
                            </div>
                        </c:forEach>
                    </div>
                    <div>
                        <input type="text" id="habitInput" placeholder="새로운 습관 추가">
                        <button id="addHabitBtn">+</button>
                    </div>
                </div>

                <div class="widget-box habit-calendar">
                    <div class="calendar">
                        <div class="calendar-header">
                            <button id="prevMonth">&lt;</button>
                            <span id="monthYear"></span>
                            <button id="nextMonth">&gt;</button>
                        </div>
                        <div class="calendar-days">
                            <div class="day-name">일</div>
                            <div class="day-name">월</div>
                            <div class="day-name">화</div>
                            <div class="day-name">수</div>
                            <div class="day-name">목</div>
                            <div class="day-name">금</div>
                            <div class="day-name">토</div>
                        </div>
                        <div id="calendarBody" class="calendar-body"></div>
                        <div id="selectedDateDisplay" style="margin-top: 10px; font-weight: bold;">
                            선택한 날짜: 없음
                        </div>
                    </div>
                </div>

                <div class="widget-box habit-week">
                    <h3>주간 습관 이력</h3>
                    <table class="week-table">
                        <thead>
                        <tr>
                            <th>습관명</th>
                            <th>일</th>
                            <th>월</th>
                            <th>화</th>
                            <th>수</th>
                            <th>목</th>
                            <th>금</th>
                            <th>토</th>
                        </tr>
                        </thead>
                        <tbody id="weeklyHabitBody">
                        <!-- 자바스크립트에서 데이터로 채워짐 -->
                        </tbody>
                    </table>
                </div>

                <!-- ✅ 2행: 추천 습관 / 격려의 말 / 메모 -->
                <div class="widget-box habit-recommend">
                    <div class="habit-tabs">
                        <div id="habit-tab-신체건강" class="habit-tab habit-active" onclick="habitShowTab('신체건강')">신체건강</div>
                        <div id="habit-tab-정신건강" class="habit-tab" onclick="habitShowTab('mental')">정신건강</div>
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

                    <div id="habit-mental" class="habit-content habit-hidden" onclick="habitShowTab('mental')">
                        <div class="habit-mental-part">
                            <p id="walk">산책</p>
                            <p id="meal">식사</p>
                            <p id="talk">수다</p>
                            <p id="friend">친구</p>
                            <p id="exercise">운동</p>
                            <p id="book">책</p>
                            <p id="game">게임</p>
                            <p id="water">물</p>
                            <p id="movie">영화</p>
                        </div>
                    </div>
                </div>

                <div class="widget-box habit-rate">
                    <h3>격려의 말</h3>
                    <ul id="encouragementList"></ul>
                </div>

                <div class="widget-box habit-memo">
                    habit-memo
                </div>
            </div>

        </main>
    </div>

</div>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="/static/js/habit/habit.js"></script>
<script>
    // 기존 삭제 로직 유지
    function deleteHabit(habit_id) {
        if (confirm("정말로 삭제하시겠습니까?")) {
            fetch('/habit/delete/' + habit_id, {
                method: 'DELETE',
                headers: { 'Content-Type': 'application/json' }
            })
                .then(response => {
                    if (response.ok) {
                        document.getElementById('habit-box-' + habit_id).remove();
                    } else {
                        alert('삭제 실패');
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    }

    // ✅ 캘린더에서 선택한 날짜 가져오는 함수
    function getSelectedDate() {
        const display = document.getElementById('selectedDateDisplay');
        const text = display.textContent || display.innerText;
        const dateStr = text.replace('선택한 날짜: ', '').trim(); // "2025-03-25" 형태
        return dateStr || new Date().toISOString().split('T')[0]; // 기본값: 오늘
    }

    // ✅ 페이지 로드시 또는 날짜 선택 시 호출 → 해당 날짜의 완료된 습관 목록을 불러옴
    function loadTrackingStatus() {
        const selectedDate = getSelectedDate(); // 선택된 날짜 가져오기

        fetch(`/habit/tracking/status?date=${selectedDate}`)
            .then(response => response.json())
            .then(trackedHabitIds => {
                document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
                    const habitId = parseInt(checkbox.id.split('-')[1]);
                    checkbox.checked = trackedHabitIds.includes(habitId); // 완료된 습관은 체크 처리
                });
            });
    }

    // ✅ 체크박스에 이벤트 바인딩: 변경 시 서버로 상태 전송
    function attachCheckboxEvents() {
        document.querySelectorAll('input[type="checkbox"]').forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                const habitId = this.id.split('-')[1]; // habit-3 → 3
                const isChecked = this.checked ? 1 : 0; // 1: 체크됨, 0: 해제됨
                const selectedDate = getSelectedDate(); // 선택한 날짜 가져오기

                fetch('/habit/tracking', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        habit_id: habitId,
                        completed: isChecked,
                        tracking_date: selectedDate
                    })
                })
                    .then(response => {
                        if (!response.ok) {
                            alert('습관 저장 실패');
                            this.checked = !isChecked; // 실패하면 원래대로 돌림
                        }
                    });
            });
        });
    }

    // ✅ 페이지 로드되면 한 번 실행
    document.addEventListener('DOMContentLoaded', function () {
        attachCheckboxEvents();  // 체크박스 이벤트 등록
        loadTrackingStatus();    // 해당 날짜 기준 체크 상태 불러오기
    });

    // ✅ 이후에 날짜 클릭 시에도 이 함수 2개 호출 필요!
    // 👉 달력 클릭 시: loadTrackingStatus(), attachCheckboxEvents() 호출 잊지 마!

</script>
</body>
</html>