let currentMode = "daily";
let currentDate = new Date();

document.getElementById("prevCalendar").addEventListener("click", switchCalendar);
document.getElementById("nextCalendar").addEventListener("click", switchCalendar);

function switchCalendar() {
    if (currentMode === "daily") {
        document.getElementById("daily-calendar").style.display = "none";
        document.getElementById("weekly-calendar").style.display = "block";
        document.getElementById("calendar-title").innerText = "Weekly";
        generateWeeklyCalendar();
        currentMode = "weekly";
    } else {
        document.getElementById("daily-calendar").style.display = "block";
        document.getElementById("weekly-calendar").style.display = "none";
        document.getElementById("calendar-title").innerText = "Daily";
        generateDailyCalendar();
        currentMode = "daily";
    }
}

function prevMonth() {
    currentDate.setMonth(currentDate.getMonth() - 1);
    if (currentMode === "daily") {
        generateDailyCalendar();
    } else {
        generateWeeklyCalendar();
    }
}

function nextMonth() {
    currentDate.setMonth(currentDate.getMonth() + 1);
    if (currentMode === "daily") {
        generateDailyCalendar();
    } else {
        generateWeeklyCalendar();
    }
}

function generateDailyCalendar() {
    generateCalendar("daily-calendar-grid", "month-year", true); // 이모지 포함
}

function generateWeeklyCalendar() {
    generateCalendar("weekly-calendar-grid", "week-range", false); // 이모지 없음
}

// 공통 달력
function generateCalendar(calendarId, titleId, loadEmotions) {
    let calendarEl = document.getElementById(calendarId);
    let monthYearEl = document.getElementById(titleId);

    if (!calendarEl || !monthYearEl) {
        console.error(`❌ ${calendarId} 요소를 찾을 수 없습니다.`);
        return;
    }

    let year = currentDate.getFullYear();
    let month = currentDate.getMonth() + 1;
    let formattedMonth = `${year}-${String(month).padStart(2, '0')}`;

    monthYearEl.innerText = `${year}년 ${month}월`;

    let firstDay = new Date(year, month - 1, 1).getDay();
    let lastDate = new Date(year, month, 0).getDate();
    let prevLastDate = new Date(year, month - 1, 0).getDate();

    calendarEl.innerHTML = "";
    let totalCells = 0;
    let today = new Date();
    let emotionMap = {};

    // 감정 데이터 불러오기 (데일리만 실행)
    if (loadEmotions) {
        fetch(`/calendar/emotions`)
            .then(response => response.json())
            .then(emotionData => {
                emotionData.forEach(event => {
                    let formattedDate = event.recorded_at.substring(0, 10);
                    emotionMap[formattedDate] = event.emotion_emoji;
                });

                drawCalendar(firstDay, lastDate, prevLastDate, formattedMonth, emotionMap, calendarEl);
            })
            .catch(error => console.error("❌ 감정 데이터를 불러오지 못했습니다.", error));
    } else {
        drawCalendar(firstDay, lastDate, prevLastDate, formattedMonth, {}, calendarEl);
    }
}

// 달력 UI 생성 함수
function drawCalendar(firstDay, lastDate, prevLastDate, formattedMonth, emotionMap, calendarEl) {
    let totalCells = 0;
    let today = new Date();
    let todayYear = today.getFullYear();
    let todayMonth = today.getMonth() + 1;
    let todayDate = today.getDate();

    // 이전 달 빈칸 채우기
    for (let i = firstDay - 1; i >= 0; i--) {
        let emptyDiv = document.createElement("div");
        emptyDiv.classList.add("calendar-day", "inactive");
        emptyDiv.innerText = prevLastDate - i;
        calendarEl.appendChild(emptyDiv);
        totalCells++;
    }

    // 이번 달 날짜 채우기
    for (let date = 1; date <= lastDate; date++) {
        let dayDiv = document.createElement("div");
        dayDiv.classList.add("calendar-day");
        let formattedDate = `${formattedMonth}-${String(date).padStart(2, '0')}`;

        // 감정 데이터가 있는 경우 이모지 표시 (데일리 달력만 해당)
        if (emotionMap[formattedDate]) {
            dayDiv.innerHTML = `<span>${emotionMap[formattedDate]}</span>`;
        } else {
            dayDiv.innerText = date;
        }

        let selectedDate = new Date(`${formattedDate}T00:00:00`);

        // 오늘 날짜 강조
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
        totalCells++;
    }

    // 다음 달 빈칸 채우기 (달력 칸 6주 유지)
    let nextMonthDate = 1;
    while (totalCells < 42) {
        let emptyDiv = document.createElement("div");
        emptyDiv.classList.add("calendar-day", "inactive");
        emptyDiv.innerText = nextMonthDate;
        calendarEl.appendChild(emptyDiv);
        nextMonthDate++;
        totalCells++;
    }
}

function prevWeek() {
    currentDate.setMonth(currentDate.getMonth() - 1);
    generateWeeklyCalendar();
}

function nextWeek() {
    currentDate.setMonth(currentDate.getMonth() + 1);
    generateWeeklyCalendar();
}

// 페이지 로드 시 자동 실행
document.addEventListener("DOMContentLoaded", function() {
    generateDailyCalendar();
});
