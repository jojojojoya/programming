// ✅ 전역으로 선언된 함수: 중복 방지를 위해 이벤트 리스너와 동일한 함수 참조 유지
function addHabit() {
    let habitName = document.getElementById("habitInput").value.trim();

    if (habitName === "") {
        alert("습관 이름을 입력하세요!");
        return;
    }

    console.log("📌 서버로 전송할 데이터:", { habitName });

    fetch("/habit/addNewHabit", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ habit_name: habitName }) // ✅ snake_case로 전송
    })
        .then(response => response.json())
        .then(data => {
            console.log("✅ 서버 응답:", data);

            if (data.status === "success") {
                alert("습관이 성공적으로 추가되었습니다.");
                location.reload(); // ✅ 새로고침
            } else {
                alert("습관 추가 실패: " + data.message);
            }
        })
        .catch(error => {
            console.error("❌ 서버 요청 실패:", error);
            alert("서버 오류 발생. 다시 시도해주세요.");
        });
}

document.addEventListener("DOMContentLoaded", function () {
    console.log("📌 JavaScript 로드 완료!");

    // ✅ addHabitBtn 클릭 리스너 등록
    function addClickListener() {
        let addHabitBtn = document.getElementById("addHabitBtn");

        if (addHabitBtn) {
            addHabitBtn.removeEventListener("click", addHabit); // ✅ 이전 리스너 제거
            addHabitBtn.addEventListener("click", addHabit);    // ✅ 새로운 리스너 등록
        } else {
            console.error("❌ [ERROR] addHabitBtn 버튼을 찾을 수 없습니다.");
        }
    }

    addClickListener(); // ✅ 페이지 로드시 초기 등록

    // ✅ 동적 요소 변화 감지
    let observer = new MutationObserver(function (mutations) {
        mutations.forEach(function (mutation) {
            if (mutation.addedNodes.length) {
                addClickListener(); // ✅ 새로 추가된 버튼에도 리스너 등록
            }
        });
    });

    observer.observe(document.body, { childList: true, subtree: true });

    // ✅ 기본 탭 설정
    function habitShowTab(tab) {
        const allTabs = document.querySelectorAll('.habit-content');
        const allTabButtons = document.querySelectorAll('.habit-tab');

        allTabs.forEach(tabContent => tabContent.classList.add('habit-hidden'));
        allTabButtons.forEach(button => button.classList.remove('habit-active'));

        document.getElementById('habit-' + tab).classList.remove('habit-hidden');
        document.getElementById('habit-tab-' + tab).classList.add('habit-active');
    }

    habitShowTab('신체건강');

    // ✅ 습관 추천 클릭 → 추가
    const habitItems = document.querySelectorAll('.habit-recommend p');
    habitItems.forEach(item => {
        item.addEventListener('click', function () {
            const habitName = item.innerText;
            addHabitToDatabase(habitName);
        });
    });

    function addHabitToDatabase(habitName) {
        const userId = 'current_user_id'; // 필요 시 세션 연동

        const requestData = {
            userId: userId,
            habitName: habitName
        };

        fetch('/habit/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    updateHabitList(data);
                } else {
                    alert('습관 추가 실패');
                }
            })
            .catch(error => console.error('Error:', error));
    }

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

    // ✅ 달력 기능
    let selectedDate = null;
    let currentMonth = new Date().getMonth();
    let currentYear = new Date().getFullYear();

    function generateCalendar(month, year) {
        let calendarBody = document.getElementById("calendarBody");
        calendarBody.innerHTML = "";

        let monthYear = document.getElementById("monthYear");
        monthYear.textContent = `${year}년 ${month + 1}월`;

        let firstDay = new Date(year, month, 1).getDay();
        let daysInMonth = new Date(year, month + 1, 0).getDate();

        for (let i = 0; i < firstDay; i++) {
            let emptyCell = document.createElement("div");
            calendarBody.appendChild(emptyCell);
        }

        for (let day = 1; day <= daysInMonth; day++) {
            let dateCell = document.createElement("div");
            dateCell.classList.add("calendar-day");
            dateCell.textContent = day;
            dateCell.dataset.date = `${year}-${(month + 1).toString().padStart(2, "0")}-${day.toString().padStart(2, "0")}`;

            dateCell.addEventListener("click", function () {
                document.querySelectorAll(".calendar-day").forEach(cell => cell.classList.remove("selected"));
                this.classList.add("selected");
                selectedDate = this.dataset.date;
                document.getElementById("selectedDateDisplay").innerText = `선택한 날짜: ${selectedDate}`;
                console.log("✅ 선택된 날짜:", selectedDate);
            });

            calendarBody.appendChild(dateCell);
        }
    }

    document.getElementById("prevMonth").addEventListener("click", function () {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        generateCalendar(currentMonth, currentYear);
    });

    document.getElementById("nextMonth").addEventListener("click", function () {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        generateCalendar(currentMonth, currentYear);
    });

    generateCalendar(currentMonth, currentYear);
});
