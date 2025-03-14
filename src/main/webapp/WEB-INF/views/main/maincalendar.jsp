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
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            border: 1px solid #ddd;
            cursor: pointer;
            font-size: 16px;
            background-color: #f9f9f9;
            position: relative;
        }

        .calendar-day span {
            font-size: 24px;
            line-height: 1;
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

<div class="calendar-container">
    <button id="prevCalendar">◀</button>
    <h3 id="calendar-title">Daily</h3>
    <button id="nextCalendar">▶</button>
</div>

<!-- 데일리 달력 -->
<div id="daily-calendar">
    <div class="calendar">
        <div class="main-calendar_header">
            <button id="prevMonth" onclick="prevMonth()"> ◀ </button>
            <h3 id="month-year"></h3>
            <button id="nextMonth" onclick="nextMonth()"> ▶ </button>
        </div>
        <div class="main-calendar_weeks">
            <div> SUN </div> <div> MON </div> <div> TUE </div>
            <div> WED </div> <div> THU </div> <div> FRI </div> <div> SAT </div>
        </div>
        <div class="main-calendar_days" id="daily-calendar-grid"></div>
    </div>
</div>

<!-- 위클리 달력 -->
<div id="weekly-calendar" style="display: none;">
    <div class="calendar">
        <div class="main-calendar_header">
            <button id="prevWeek" onclick="prevWeek()"> ◀ </button>
            <h3 id="week-range"></h3>
            <button id="nextWeek" onclick="nextWeek()"> ▶ </button>
        </div>
        <div class="main-calendar_weeks">
            <div> SUN </div> <div> MON </div> <div> TUE </div>
            <div> WED </div> <div> THU </div> <div> FRI </div> <div> SAT </div>
        </div>
        <div class="main-calendar_days" id="weekly-calendar-grid"></div>
    </div>
</div>

<script src="/static/js/main/maincalendar.js"></script>
</body>
</html>
