<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>달력</title>
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <style>
        .calendar {
            width: 100%;
            max-width: 400px;
            margin: auto;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 10px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column; /* 세로 방향 정렬 */
            align-items: center; /* 중앙 정렬 */
            background-color: #9A6E50;
        }

        .main-calendar_header {
            display: flex; /* ✅ 요소들을 가로로 배치 */
            justify-content: center; /* ✅ 전체 요소를 가로 중앙 정렬 */
            align-items: center; /* ✅ 수직 정렬 */
            gap: 20px; /* ✅ 버튼과 month-year 사이 간격 조정 */
            width: 100%;
            margin-bottom: 10px;
        }

        .main-calendar_header button {
            border: none;
            background: none;
            font-size: 18px; /* 버튼 크기 조정 */
            cursor: pointer;
            padding: 5px;
        }


        .main-calendar_weeks {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            width: 100%;
            font-weight: bold;
            text-align: center;
            padding: 5px 0;
        }

        .main-calendar_days {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 5px;
            text-align: center;
        }

        .calendar-day {
            width: 50px;
            height: 50px;
            line-height: 50px;
            border: 1px solid #ddd;
            cursor: pointer;
            font-size: 16px;
            background-color: #f9f9f9;
        }

        .calendar-day:hover {
            background-color: #e0e0e0;
        }

        .inactive {
            color: #bbb;
        }
    </style>
</head>
<body>

<div class="calendar">
    <div class="main-calendar_header">
        <button id="prevMonth" onclick="prevMonth()"> ◀ </button>
        <h3 id="month-year"></h3>
        <button id="nextMonth" onclick="nextMonth()"> ▶ </button>
    </div>

    <%-- 요일 --%>
    <div class="main-calendar_weeks">
        <div> SUN </div>
        <div> MON </div>
        <div> TUE </div>
        <div> WED </div>
        <div> THU </div>
        <div> FRI </div>
        <div> SAT </div>
    </div>

    <div class="main-calendar_days" id="calendar">
        <%--날짜 생성--%>
    </div>
</div>

<script>
    let currentDate = new Date();

    function generateCalendar() {
        let calendarEl = document.getElementById("calendar");
        let monthYearEl = document.getElementById("month-year");

        if (!calendarEl || !monthYearEl) {
            console.error("❌ month-year 요소를 찾을 수 없음!");
            return;
        }

        let year = currentDate.getFullYear();
        let month = currentDate.getMonth() + 1; // ✅ 1월부터 12월까지 변환

        monthYearEl.innerText = year + "년 " + month + "월";

        // ✅ 첫 번째 날과 마지막 날 계산
        let firstDay = new Date(year, month - 1, 1).getDay(); // 이번 달 1일의 요일 (0: 일요일, 6: 토요일)
        let lastDate = new Date(year, month, 0).getDate(); // 이번 달 마지막 날짜
        let prevLastDate = new Date(year, month - 1, 0).getDate(); // 이전 달의 마지막 날짜

        calendarEl.innerHTML = ""; // 기존 날짜 초기화
        let totalCells = 0; // 총 셀 개수 (6주 = 42개)

        // **1. 이전 달 빈칸에 이전 달 날짜 채우기**
        for (let i = firstDay - 1; i >= 0; i--) {
            let emptyDiv = document.createElement("div");
            emptyDiv.classList.add("calendar-day", "inactive");
            emptyDiv.innerText = prevLastDate - i; // 이전 달의 날짜 표시
            calendarEl.appendChild(emptyDiv);
            totalCells++;
        }

        // **2. 현재 달 날짜 추가**
        for (let date = 1; date <= lastDate; date++) {
            let dayDiv = document.createElement("div");
            dayDiv.classList.add("calendar-day");
            dayDiv.innerText = date;

            let today = new Date();
            if (year === today.getFullYear() && month === today.getMonth() + 1 && date === today.getDate()) {
                dayDiv.style.backgroundColor = "#ffcccb"; // 오늘 날짜 강조
            }

            calendarEl.appendChild(dayDiv);
            totalCells++;
        }

        // **3. 다음 달 빈칸에 다음 달 날짜 채우기 (6주를 유지하도록)**
        let nextMonthDate = 1;
        while (totalCells < 42) {
            let emptyDiv = document.createElement("div");
            emptyDiv.classList.add("calendar-day", "inactive");
            emptyDiv.innerText = nextMonthDate; // ✅ 다음 달 날짜 표시
            calendarEl.appendChild(emptyDiv);
            nextMonthDate++;
            totalCells++;
        }
    }




    function prevMonth() {
        currentDate.setMonth(currentDate.getMonth() - 1);
        generateCalendar();
    }

    function nextMonth() {
        currentDate.setMonth(currentDate.getMonth() + 1);
        generateCalendar();
    }

    // ✅ DOM이 완전히 로드된 후 실행
    document.addEventListener("DOMContentLoaded", function() {
        console.log("📌 DOMContentLoaded 이벤트 발생");
        generateCalendar();
    });

</script>

</body>
</html>
