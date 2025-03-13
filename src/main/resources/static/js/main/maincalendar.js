let currentDate = new Date();

function generateCalendar() {
    let calendarEl = document.getElementById("calendar");
    let monthYearEl = document.getElementById("month-year");

    let year = currentDate.getFullYear();
    let month = currentDate.getMonth() + 1; // 0부터 시작.
    let formattedMonth = `${year}-${String(month).padStart(2, '0')}`;

    monthYearEl.innerText = `${year}년 ${month}월`;

    // 첫 번째 날과 마지막 날 계산

    // new Date(year, month, day)에서 day가 0이면 이전 달의 마지막 날 의미.
    // .getDate()를 호출해서 값 반환.

    // .getDay() => 그 날짜의 요일을 반환(0 : 일요일, 6 : 토요일)
    let firstDay = new Date(year, month - 1, 1).getDay();
    let lastDate = new Date(year, month, 0).getDate(); // 이번 달 마지막 날짜
    let prevLastDate = new Date(year, month - 1, 0).getDate(); // 이전 달의 마지막 날짜

    calendarEl.innerHTML = ""; // 기존 날짜 초기화
    let totalCells = 0; // 전체 날짜 셀 개수 저장

    let today = new Date();
    let todayYear = today.getFullYear();
    let todayMonth = today.getMonth() + 1;
    let todayDate = today.getDate();

    // 감정 데이터 불러오기
    let emotionMap = {};

    fetch(`/calendar/emotions`)
        .then(function (response) {
            return response.json();
        })
        .then(function (emotionData) {

            // 현재 달(`YYYY-MM`)에 해당하는 데이터만 필터링 <= 데이터가 많아지는 것을 방지
            emotionData.forEach(function (event) {
                if (event.recorded_at) {
                    let formattedDate = event.recorded_at.substring(0, 10); // "YYYY-MM-DD" 형식으로 변환
                    emotionMap[formattedDate] = event.emotion_emoji;
                }
            });

            drawCalendar(firstDay, lastDate, prevLastDate, formattedMonth, emotionMap);
        })
        .catch(function(error) {
            console.error("감정 데이터를 불러오지 못했습니다.", error);
        });
}

// 감정 데이터를 받은 후, 달력을 생성하는 함수
function drawCalendar(firstDay, lastDate, prevLastDate, formattedMonth, emotionMap) {
    let calendarEl = document.getElementById("calendar");
    let totalCells = 0;

    let today = new Date();
    let todayYear = today.getFullYear();
    let todayMonth = today.getMonth() + 1;
    let todayDate = today.getDate();

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
        let formattedDate = `${formattedMonth}-${String(date).padStart(2, '0')}`;

        // 감정 데이터가 있으면 이모지를 추가
        if (emotionMap[formattedDate]) {
            dayDiv.innerHTML = `<span>${emotionMap[formattedDate]}</span>`;
        } else {
            dayDiv.innerText = date;
        }

        let selectedDate = new Date(`${formattedDate}T00:00:00`);

        // 오늘의 날짜와 같으면 true => 배경색 표시
        if (todayYear === selectedDate.getFullYear() &&
            todayMonth === selectedDate.getMonth() + 1 &&
            todayDate === selectedDate.getDate()) {
            dayDiv.style.backgroundColor = "#FFD27A";
        }

        dayDiv.style.cursor = "pointer";

        // 날짜 클릭 이벤트 추가
        dayDiv.addEventListener("click", function () {
            if (selectedDate > today) {
                alert("미래의 일기는 작성할 수 없습니다.");
            } else {
                window.location.href = `/diary?date=${formattedDate}`;
            }
        });

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
