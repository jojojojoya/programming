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

        /* ✅ 헤더에 Daily/Weekly 버튼 포함 */
        .calendar-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            padding: 10px;
        }

        .calendar-header h3 {
            margin: 0;
        }

        .calendar-header button {
            border: none;
            background: none;
            font-size: 16px;
            cursor: pointer;
            padding: 5px;
        }

        .calendar-weeks {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            width: 100%;
            font-weight: bold;
            text-align: center;
            padding: 5px 0;
        }

        .calendar-days {
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

        .today {
            background-color: #FFD27A !important;
            font-weight: bold;
            border: 2px solid #ff9f1c;
        }
    </style>
</head>
<body>

<div class="calendar">
    <div class="calendar-header">
        <button id="prevMonth">◀</button>
        <h3 id="calendar-month-year"></h3>
        <button id="nextMonth">▶</button>

        <div>
            <button id="prevCalendar">◀</button>
            <span id="calendar-title">Daily</span>
            <button id="nextCalendar">▶</button>
        </div>
    </div>

    <div class="calendar-weeks">
        <div> SUN </div> <div> MON </div> <div> TUE </div>
        <div> WED </div> <div> THU </div> <div> FRI </div> <div> SAT </div>
    </div>
    <div class="calendar-days" id="calendar-grid"></div>
</div>

<script src="/static/js/main/maincalendar.js"></script>
</body>
</html>
