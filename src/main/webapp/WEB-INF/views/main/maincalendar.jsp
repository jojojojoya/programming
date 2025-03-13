<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Calendar</title>
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
            flex-direction: column;
            align-items: center;
            background-color: #9A6E50;
        }

        .main-calendar_header {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 20px;
            width: 100%;
            margin-bottom: 10px;
        }

        .main-calendar_header button {
            border: none;
            background: none;
            font-size: 18px;
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

        let year = currentDate.getFullYear();
        let month = currentDate.getMonth() + 1; // 0부터 시작.

        monthYearEl.innerText = year + "년 " + month + "월";

        // 첫 번째 날과 마지막 날 계산

        // new Date(year, month, day)에서 day가 0이면 이전 달의 마지막 날 의미.
        // .getDate()를 호출해서 값 반환.

        // .getDay() => 그 날짜의 요일을 반환(0 : 일요일, 6 : 토요일)
        let firstDay = new Date(year, month - 1, 1).getDay();


        let lastDate = new Date(year, month, 0).getDate(); // 이번 달 마지막 날짜
        let prevLastDate = new Date(year, month - 1, 0).getDate(); // 이전 달의 마지막 날짜

        calendarEl.innerHTML = ""; // 기존 날짜 초기화
        let totalCells = 0; // 전체 날짜 셀 개수 저장

        // 이전 달 빈칸에 이전 달 날짜 채우기
        for (let i = firstDay - 1; i >= 0; i--) {
            let emptyDiv = document.createElement("div");
            emptyDiv.classList.add("calendar-day", "inactive");
            emptyDiv.innerText = prevLastDate - i;
            calendarEl.appendChild(emptyDiv);
            totalCells++;
        }

        // 현재 달 날짜 추가
        for (let date = 1; date <= lastDate; date++) {
            let dayDiv = document.createElement("div");
            dayDiv.classList.add("calendar-day");
            dayDiv.innerText = date;

            // 오늘의 날짜와 같으면 true => 배경색 표시
            let today = new Date();
            if (year === today.getFullYear() && month === today.getMonth() + 1 && date === today.getDate()) {
                dayDiv.style.backgroundColor = "#FFD27A";
            }

            calendarEl.appendChild(dayDiv);
            totalCells++; // 총 날짜 칸을 하나씩 증가
        }

        // 다음 달 빈칸에 다음 달 날짜 채우기 <= 매달 같은 크기를 맞추기 위해 달력 표시일을 6주로 고정.
        let nextMonthDate = 1;

        // 총 셀 수가 42가 될 때까지 반복
        while (totalCells < 42) {
            let emptyDiv = document.createElement("div");
            emptyDiv.classList.add("calendar-day", "inactive");
            emptyDiv.innerText = nextMonthDate;
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
    document.addEventListener("DOMContentLoaded", function() {
        generateCalendar();
    });

</script>

</body>
</html>
