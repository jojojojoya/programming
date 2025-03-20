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

//습관추가

document.addEventListener("DOMContentLoaded", function() {
    // habit-recommend의 각 <p> 태그에 클릭 이벤트 추가
    const habitItems = document.querySelectorAll('.habit-recommend p');
    habitItems.forEach(item => {
        item.addEventListener('click', function() {
            const habitName = item.innerText;  // 클릭된 습관 이름
            addHabitToDatabase(habitName);  // 데이터베이스에 추가하는 함수 호출
        });
    });

    // AJAX를 사용하여 서버로 습관 추가 요청을 보냄
    function addHabitToDatabase(habitName) {
        const userId = 'current_user_id';  // 현재 사용자의 ID로 대체 필요 (예: 세션에서 가져오기)

        const requestData = {
            userId: userId,
            habitName: habitName
        };

        fetch('/habit/add', {  // '/habit/add'는 서버에서 습관을 추가하는 엔드포인트
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    // 습관 추가 성공 시, myhabit-list에 새 습관 추가
                    updateHabitList(data);
                } else {
                    alert('습관 추가 실패');
                }
            })
            .catch(error => console.error('Error:', error));
    }

    // 습관 리스트를 동적으로 업데이트하는 함수
    function updateHabitList(habit) {
        const habitList = document.querySelector('.myhabit-list');
        const newHabitDiv = document.createElement('div');
        newHabitDiv.id = `habit-${habit.habit_id}`;
        newHabitDiv.innerHTML = `
            <input type="checkbox" id="habit-${habit.habit_id}" />
            <label for="habit-${habit.habit_id}">${habit.habit_name}</label>
            <button class="delete-btn" onclick="deleteHabit(${habit.habit_id})">삭제</button>
        `;
        habitList.appendChild(newHabitDiv);
    }
});











// habit.js


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





