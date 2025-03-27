let currentMode = "daily"; // 초기 모드는 Daily
let currentDate = new Date();
let emotionMap = {}; // 감정 데이터를 저장

document.getElementById("prevCalendar").addEventListener("click", switchCalendar);
document.getElementById("nextCalendar").addEventListener("click", switchCalendar);

// Daily <-> Weekly 전환
function switchCalendar() {
    if (currentMode === "daily") {
        document.getElementById("calendar-title").innerText = "ウィークリー";
        currentMode = "weekly";
    } else {
        document.getElementById("calendar-title").innerText = "デイリー";
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
        .catch(error => console.error("감정 데이터를 불러오지 못했습니다.", error));
}

function generateCalendar() {
    let calendarEl = document.getElementById("calendar-grid");
    let monthYearEl = document.getElementById("calendar-month-year");

    const monthNames = [
        "1月", "2月", "3月", "4月", "5月", "6月",
        "7月", "8月", "9月", "10月", "11月", "12月"
    ];

    let year = currentDate.getFullYear();
    let month = currentDate.getMonth() + 1;
    let formattedMonth = `${year}-${String(month).padStart(2, '0')}`;

    monthYearEl.innerText = `${year}年 ${monthNames[month - 1]}`;
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

        dayDiv.dataset.data = formattedDate;

        if (currentMode === "daily" && emotionMap[formattedDate]) {
            dayDiv.innerHTML = `<span>${emotionMap[formattedDate]}</span>`;
        } else {
            dayDiv.innerText = date;
        }

        if (formattedDate === todayFormatted) {
            dayDiv.classList.add("today");
        }

        if (currentMode === "daily") {
            dayDiv.addEventListener("click", function () {
                let selectedDate = new Date(`${formattedDate}T00:00:00`); // 클릭한 날짜 객체 변환

                if (selectedDate > today) {
                    alert("未来の日付には日記を記入できません。");
                    return;
                }

                fetch('/diary/setSelectedDate', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ date: formattedDate })
                })
                .then(() => {
                    window.location.href = '/diary';
                })
                .catch(err => {
                    console.error("서버 세션 저장 실패:", err);
                });

            });
        } else if (currentMode === "weekly") {
            dayDiv.addEventListener("click", function () {
                let selectedDate = new Date(`${formattedDate}T00:00:00`);

                document.querySelectorAll(".calendar-day").forEach(el => {
                    el.classList.remove("selected-date");
                });

                // 현재 클릭한 날짜에만 추가
                this.classList.add("selected-date");
                selectedCalendarDate = this;

                if (selectedDate) {
                    let weekRange = getWeekRange(selectedDate);
                    fetchWeeklyMoodScores(selectedDate);
                    console.log("클릭한 날짜:", formattedDate);
                    loadChecklistByDate(formattedDate);
                } else {
                    console.log("선택한 날짜가 없습니다 (undefined)");
                }
            })
        }

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

document.addEventListener("DOMContentLoaded", function () {
    fetchEmotions(); // 감정 데이터 불러오기
    let today = new Date();
    today = new Date(today.getFullYear(), today.getMonth(), today.getDate()); // Date에서 시간 정보를 제거
    let { start, end } = getWeekRange(today);
    fetchWeeklyMoodScores(today);
});

// 클릭한 날짜가 속한 주 계산하기
function getWeekRange(selectedDate) {
    let date = new Date(selectedDate); // 선택한 날짜를 Date 객체로 변환
    let dayOfWeek = date.getDay(); // 요일 가져오기 (0: 일요일, 1: 월요일, ..., 6: 토요일)

    let monday = new Date(date);
    monday.setDate(date.getDate() - (dayOfWeek === 0 ? 5 : dayOfWeek -2));

    let sunday = new Date(monday);
    sunday.setDate(monday.getDate() + 6);

    console.log("선택한 날짜가 포함된 주의 월요일:", monday.toISOString().split("T")[0]);
    console.log("선택한 날짜가 포함된 주의 일요일:", sunday.toISOString().split("T")[0]);

    return {
        start: monday.toISOString().split("T")[0], // YYYY-MM-DD 형식 반환
        end: sunday.toISOString().split("T")[0]
    };
}

// 기분 점수 가져오기
function fetchWeeklyMoodScores(selectedDate) {
    let { start, end } = getWeekRange(selectedDate);

    console.log(`선택한 날짜: ${selectedDate}, 주간 범위: ${start} ~ ${end}`);

    fetch(`/mood/scores?start=${start}&end=${end}`)
        .then(response => response.json())
        .then(moodData => {
            let moodScores = new Array(7).fill(0); // 월요일부터 시작하는 배열
            moodData.forEach(entry => {
                let date = new Date(entry.recorded_at);
                let dayIndex = date.getDay();

                if (dayIndex === 0) dayIndex = 6; // 일요일(0)을 배열 마지막으로 이동
                else dayIndex -= 1; // 요일 인덱스 맞추기 (월~일: 0~6)

                moodScores[dayIndex] = entry.emotion_score; // 점수 저장
            });
            updateMoodChart(moodScores);
        })
        .catch(error => console.error("주간 기분 점수를 불러오는 데 실패했습니다.", error));
}

// 무드 그래프 업데이트
function updateMoodChart(moodScores) {
    let chartElement = document.getElementById('moodChart');

    if (!chartElement) {
        console.error("moodChart 요소를 찾을 수 없습니다.");
        return;
    }

    const ctx = chartElement.getContext('2d');

    // 기존 차트가 `<canvas>` 요소인지 확인 후 초기화
    if (window.moodChart && !(window.moodChart instanceof Chart)) {
        window.moodChart = null;
    }

    if (window.moodChart instanceof Chart) {
        window.moodChart.destroy();
    }

    // 새로운 차트 생성
    window.moodChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['月', '火', '水', '木', '金', '土', '日'],
            datasets: [{
                label: '今週の気分',
                data: moodScores,
                tension: 0.2,
                fill: true,
                backgroundColor: 'rgba(233, 215, 233, 0.3)',
                borderColor: '#A1887F',
                pointBackgroundColor: '#C8E3D4',
                pointBorderColor: '#D2C4D6',
                pointRadius: 4,
                pointHoverRadius: 8,
                pointHoverBackgroundColor: '#EAD4C2',
                pointHoverBorderColor: '#5D4037',
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: '#7B6651'
                    },
                    grid: {
                        display: false
                    }
                },
                y: {
                    beginAtZero: true,
                    max: 100,
                    ticks: {
                        color: '#A1887F'
                    },
                    grid: {
                        color: '#f3e7db'
                    }
                }
            }
        }
    });

    console.log("차트가 정상적으로 업데이트되었습니다.");
}
