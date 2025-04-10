<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        @font-face {
            font-family: 'BMJUA';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        /*div {
            border: 1px solid;
        }

        .header {
            height: 100px;
        }

        .nav {
            height: 70px;
        }

        .content {
            margin-top: 100px;
            margin-left: 100px;
            margin-right: 100px;
            height: 3500px;
            border: none;
        }

        .footer {
            height: 200px;
        }*/

        .profile-field {
            display: flex;
        }

        .profile-circle img {
            border-radius: 50%;
            width: 170px;
            height: 170px;
        }


        .reservation-list {
            display: flex;
            justify-content: flex-end;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .reserv-place-list {
            display: flex;
            text-align: center;
            border: none;
            margin: 20px;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .reserv-place-list img {
            border-radius: 50%;
            width: 210px;
            height: 210px;
        }

        .reserv-place-list-field {
            display: flex;
            overflow: hidden;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .calendar-container {
            margin: 20px 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            background-color: #f9f9f9;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .calendar-months > div {
            width: 400px;
            margin: 0;
        }

        .calendar-slider {
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .calendar-months {
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }


        .calendar {
            border: none;
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 5px;
            width: 100%;
            max-width: 400px;
            text-align: center;
            grid-auto-rows: 43px; /* 각 날짜 칸의 높이를 고정 */
        }

        .calendar div {
            padding: 10px 0;
            border-radius: 5px;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .calendar .day-header {
            font-weight: bold;
            background-color: rgb(205, 187, 173);
            color: rgb(70, 44, 32);
        }

        .calendar .day-header:hover {
            font-weight: bold;
            background-color: rgb(70, 44, 32);
            color: white;
        }

        .calendar .day {
            border: 1px solid #ddd;
            background-color: #f1f1f1;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .calendar .day:hover {
            background-color: rgb(70, 44, 32);
            color: white;
        }

        .nav-buttons {
            margin-top: 10px;
            margin-bottom: 10px;
            display: flex;
            justify-content: center;
            gap: 10px;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .nav-buttons button {
            padding: 10px 20px;
            background-color: rgb(205, 187, 173);
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .nav-buttons button:hover {
            background-color: rgb(70, 44, 32);
            font-family: 'BMJUA',serif;
            color: white;
        }

        .month > h3 {
            text-align: center;
        }

        .reserv_button_field {
            margin-left: auto;
            margin-right: 30px;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .reserv_button_field > input {
            padding: 10px 20px;
            background-color: rgb(205, 187, 173);
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-family: 'BMJUA',serif;
            color: rgb(70, 44, 32);
        }

        .reserv_button_field > input:hover {
            background-color: rgb(70, 44, 32);
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
   <%-- <div class="header">header</div>
    <div class="nav">nav</div>--%>


        <div class="mypage" style="border: none;">
            <h1>내 가게 예약확인</h1>
        </div>




        <div class="calendar-container">
            <h3>예약리스트 달력</h3>
            <div class="calendar-slider" style="width: 400px">
                <div class="calendar-months">
                    <!-- JavaScript로 동적으로 채워질 부분 -->
                </div>
                <input hidden="hidden" name="no" value="">
                <div class="nav-buttons" style="border: none">
                    <button onclick="prevSlide()">이전 달</button>
                    <button onclick="nextSlide()">다음 달</button>
                </div>
            </div>
        </div>

        <script>
            const monthsData = [
                {name: "2025년 1월", days: 31},
                {name: "2025년 2월", days: 28}, // 윤년 처리는 필요 시 추가 가능
                {name: "2025년 3월", days: 31},
                {name: "2025년 4월", days: 30},
                {name: "2025년 5월", days: 31},
                {name: "2025년 6월", days: 30},
                {name: "2025년 7월", days: 31},
                {name: "2025년 8월", days: 31},
                {name: "2025년 9월", days: 30},
                {name: "2025년 10월", days: 31},
                {name: "2025년 11월", days: 30},
                {name: "2025년 12월", days: 31},
            ];

            const calendarMonths = document.querySelector(".calendar-months");
            let currentMonthIndex = 0;

            function generateCalendar() {
                monthsData.forEach((month, index) => {
                    const monthDiv = document.createElement("div");
                    monthDiv.classList.add("month");
                    if (index !== 0) monthDiv.style.display = "none"; // 첫 번째 달만 보이도록 설정

                    const header = document.createElement("h3");
                    header.textContent = month.name;

                    const grid = document.createElement("div");
                    grid.classList.add("calendar");

                    // 요일 헤더 추가
                    ["일", "월", "화", "수", "목", "금", "토"].forEach(day => {
                        const dayHeader = document.createElement("div");
                        dayHeader.textContent = day;
                        dayHeader.classList.add("day-header");
                        grid.appendChild(dayHeader);
                    });

                    // 첫 번째 날짜의 요일 계산
                    const firstDay = new Date(2025, index, 1).getDay(); // 2025년 index월 1일의 요일 (0: 일요일, 6: 토요일)

                    // 빈 칸 추가
                    for (let i = 0; i < firstDay; i++) {
                        const emptyDiv = document.createElement("div");
                        grid.appendChild(emptyDiv);
                    }

                    // 날짜 추가
                    for (let i = 1; i <= month.days; i++) {
                        const dayDiv = document.createElement("div");
                        dayDiv.textContent = i;
                        dayDiv.classList.add("day");
                        grid.appendChild(dayDiv);
                    }

                    // 6주 (42일) 칸 맞추기
                    const totalDays = month.days + firstDay;
                    const remainingDays = 42 - totalDays;
                    for (let i = 0; i < remainingDays; i++) {
                        const emptyDiv = document.createElement("div");
                        grid.appendChild(emptyDiv);
                    }

                    monthDiv.appendChild(header);
                    monthDiv.appendChild(grid);
                    calendarMonths.appendChild(monthDiv);
                });
            }

            function updateSlide() {
                const months = document.querySelectorAll(".month");
                months.forEach((month, index) => {
                    month.style.display = index === currentMonthIndex ? "block" : "none";
                });
            }

            function prevSlide() {
                if (currentMonthIndex > 0) {
                    currentMonthIndex--;
                    updateSlide();
                }
            }

            function nextSlide() {
                if (currentMonthIndex < 11) {
                    currentMonthIndex++;
                    updateSlide();
                }
            }

            generateCalendar();
        </script>
       <div class="reservation-list" style="border: none;">
           <h2 style="margin-inline-end: auto;">예약고객 리스트</h2>
       </div>
        <div class="reserv-place-list-field" style="border: solid 1px;">
            <div class="reserv-place-list">
                <img
                        src="../../profilePhoto/bleach_kv.png"
                        alt="place-name">
                <div style="border: none; text-align: left; margin-left: 30px">
                    <h2>예약한 가게명</h2>
                    <h2>예약시간 / 예약인원</h2>
                    <div style="border: none;">
                        <h3>예약자 이름 : 000</h3>
                        <h3>예약자 전화번호 : 000 </h3>

                    </div>
                </div>
            </div>
            <div class="reserv_button_field" style="border: none; align-content: center;">
                <input type="button" value="예약 취소">
            </div>
        </div>


    </div>

   <%-- <div class="footer">footer</div>--%>

</body>
</html>