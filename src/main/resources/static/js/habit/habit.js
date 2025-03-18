function habitShowTab(tabId) {
    // 모든 탭 콘텐츠 숨기기
    document.getElementById("habit-신체건강").classList.add("habit-hidden");
    document.getElementById("habit-정신건강").classList.add("habit-hidden");

    // 선택한 탭 콘텐츠 보이기
    document.getElementById(`habit-${tabId}`).classList.remove("habit-hidden");

    // 모든 탭 버튼 비활성화
    document.getElementById("habit-tab-신체건강").classList.remove("habit-active");
    document.getElementById("habit-tab-정신건강").classList.remove("habit-active");

    // 선택한 탭 버튼 활성화
    document.getElementById(`habit-tab-${tabId}`).classList.add("habit-active");
}

// 기본적으로 '신체건강' 탭을 활성화
document.addEventListener('DOMContentLoaded', function () {
    habitShowTab('신체건강');
});


function habitShowTab(tab) {
    const allTabs = document.querySelectorAll('.habit-content');
    const allTabButtons = document.querySelectorAll('.habit-tab');

    // 모든 탭 숨기기
    allTabs.forEach(function (tabContent) {
        tabContent.classList.add('habit-hidden');
    });

    // 모든 탭 버튼 비활성화
    allTabButtons.forEach(function (button) {
        button.classList.remove('habit-active');
    });

    // 클릭한 탭 활성화
    document.getElementById('habit-' + tab).classList.remove('habit-hidden');
    document.getElementById('habit-tab-' + tab).classList.add('habit-active');
}

let habitList = document.getElementById('habitList'); // UI 상의 습관 리스트 컨테이너
let maxHabits = 9; // 최대 습관 개수

function addHabit() {
    if (habitList.children.length < maxHabits) {
        const habitName = `습관 ${habitList.children.length + 1}`;

        // 화면에 새로운 습관을 즉시 추가
        let newHabit = document.createElement('div');
        newHabit.classList.add('habit-item');
        newHabit.textContent = habitName; // 습관 이름
        habitList.appendChild(newHabit); // 화면에 즉시 추가

        // 서버로 새 습관을 추가하는 요청
        fetch('/add-habit', {
            method: 'POST',
            body: JSON.stringify({ habitName: habitName }), // 서버로 보낼 데이터
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .catch(error => {
                console.error('서버에 요청하는 중 오류 발생:', error);
                alert('서버와의 연결에 문제가 발생했습니다.');
                // 오류 발생 시 화면에서 추가된 항목을 제거할 수 있습니다
                habitList.removeChild(newHabit); // 실패 시 화면에서 해당 습관을 제거
            });
    } else {
        alert('최대 9개의 습관만 추가할 수 있습니다.');
    }
}











// habit.js

document.addEventListener("DOMContentLoaded", function() {
    const habits = document.querySelectorAll('.habit-content p'); // habit-content 내 p 태그

    habits.forEach(function(habit) {
        habit.addEventListener('click', function() {
            const habitName = this.innerText.trim(); // 클릭한 습관 이름 가져오기
            addHabitToUser(habitName); // 해당 습관을 사용자에 추가하는 함수 호출
        });
    });

    function addHabitToUser(habitName) {
        fetch('/habit/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ habitName: habitName })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("습관이 추가되었습니다!");
                    // 새로 추가된 습관을 myhabit-list에 반영
                    const habitList = document.querySelector('.myhabit-list');
                    const newHabit = document.createElement('div');
                    newHabit.innerHTML = `<input type="checkbox" id="habit-${data.habit_id}" /> <label for="habit-${data.habit_id}">${data.habit_name}</label>`;
                    habitList.appendChild(newHabit);
                } else {
                    alert("습관 추가 실패");
                }
            })
            .catch(error => console.error('Error:', error));
    }
});

const calendarDays = document.querySelector('#calendar-days tbody');
const monthYearText = document.querySelector('#month-year-text');
const prevButton = document.querySelector('#prev');
const nextButton = document.querySelector('#next');

let currentDate = new Date();

function renderCalendar() {
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth();

    monthYearText.textContent = `${year}년 ${month + 1}월`;

    // 첫 번째 날의 요일을 구함 (0: 일요일, 6: 토요일)
    const firstDay = new Date(year, month, 1).getDay();

    // 해당 월의 마지막 날짜를 구함
    const lastDate = new Date(year, month + 1, 0).getDate();

    // 테이블을 비운 후 다시 생성
    calendarDays.innerHTML = '';

    let day = 1;

    for (let i = 0; i < 6; i++) {
        const row = document.createElement('tr');

        for (let j = 0; j < 7; j++) {
            const cell = document.createElement('td');

            if (i === 0 && j < firstDay) {
                row.appendChild(cell);
            } else if (day <= lastDate) {
                cell.textContent = day;
                row.appendChild(cell);
                day++;
            }

            if (day > lastDate) break;
        }

        calendarDays.appendChild(row);
    }
}

// 이전 달로 이동
prevButton.addEventListener('click', () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    renderCalendar();
});

// 다음 달로 이동
nextButton.addEventListener('click', () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    renderCalendar();
});

// 처음 달력 표시
renderCalendar();





