let currentMode = "daily"; // 초기 모드는 Daily
let currentDate = new Date();
let emotionMap = {}; // 감정 데이터를 저장

document.getElementById("prevCalendar").addEventListener("click", switchCalendar);
document.getElementById("nextCalendar").addEventListener("click", switchCalendar);

// Daily <-> Weekly 전환
function switchCalendar() {
    if (currentMode === "daily") {
        document.getElementById("calendar-title").innerText = "Weekly";
        currentMode = "weekly";
    } else {
        document.getElementById("calendar-title").innerText = "Daily";
        currentMode = "daily";
    }
    generateCalendar();
}

// 이전/다음 달 이동
document.getElementById("prevMonth").addEventListener("click", function () {
    currentDate.setMonth(currentDate.getMonth() - 1);
    generateCalendar();
});

document.getElementById("nextMonth").addEventListener("click", function () {
    currentDate.setMonth(currentDate.getMonth() + 1);
    generateCalendar();
});

// 감정 데이터를 한 번만 가져오기
function fetchEmotions() {
    fetch(`/calendar/emotions`)
        .then(response => response.json())
        .then(emotionData => {
            emotionMap = {};
            emotionData.forEach(event => {
                let formattedDate = event.recorded_at.substring(0, 10);
                emotionMap[formattedDate] = event.emotion_emoji;
            });
            generateCalendar();
        })
        .catch(error => console.error("❌ 감정 데이터를 불러오지 못했습니다.", error));
}

function generateCalendar() {
    let calendarEl = document.getElementById("calendar-grid");
    let monthYearEl = document.getElementById("calendar-month-year");

    let year = currentDate.getFullYear();
    let month = currentDate.getMonth() + 1;
    let formattedMonth = `${year}-${String(month).padStart(2, '0')}`;

    monthYearEl.innerText = `${year}년 ${month}월`;
    calendarEl.innerHTML = "";

    let firstDay = new Date(year, month - 1, 1).getDay();
    let lastDate = new Date(year, month, 0).getDate();
    let prevLastDate = new Date(year, month - 1, 0).getDate();
    let totalCells = 0;

    let today = new Date();
    let todayFormatted = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;

    // 이전 달 빈칸
    for (let i = firstDay - 1; i >= 0; i--) {
        let emptyDiv = document.createElement("div");
        emptyDiv.classList.add("calendar-day", "inactive");
        emptyDiv.innerText = prevLastDate - i;
        calendarEl.appendChild(emptyDiv);
        totalCells++;
    }

    // 이번 달 날짜
    for (let date = 1; date <= lastDate; date++) {
        let dayDiv = document.createElement("div");
        dayDiv.classList.add("calendar-day");
        let formattedDate = `${formattedMonth}-${String(date).padStart(2, '0')}`;

        if (currentMode === "daily" && emotionMap[formattedDate]) {
            dayDiv.innerHTML = `<span>${emotionMap[formattedDate]}</span>`;
        } else {
            dayDiv.innerText = date;
        }
        
        if (formattedDate === todayFormatted) {
            dayDiv.classList.add("today");
        }

        dayDiv.addEventListener("click", function () {
            window.location.href = `/diary?date=${formattedDate}`;
        });

        calendarEl.appendChild(dayDiv);
        totalCells++;
    }

    // 다음 달 빈칸
    while (totalCells < 42) {
        let emptyDiv = document.createElement("div");
        emptyDiv.classList.add("calendar-day", "inactive");
        emptyDiv.innerText = totalCells - lastDate - firstDay + 1;
        calendarEl.appendChild(emptyDiv);
        totalCells++;
    }
}

document.addEventListener("DOMContentLoaded", fetchEmotions);
