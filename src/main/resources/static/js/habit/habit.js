document.addEventListener("DOMContentLoaded", function () {
    console.log("📌 JavaScript 로드 완료!");

    // ✅ 전역 변수
    let selectedDate = null;
    let currentMonth = new Date().getMonth();
    let currentYear = new Date().getFullYear();

    // ✅ 습관 추가 함수 (날짜 포함)
    function addHabit() {
        const habitName = document.getElementById("habitInput").value.trim();

        if (!habitName) {
            alert("습관 이름을 입력해주세요.");
            return;
        }

        if (!selectedDate) {
            alert("날짜를 선택해주세요.");
            return;
        }
        // ✅ 날짜를 YYYY-MM-DD 형식으로 가공
        const formattedDate = new Date(selectedDate).toISOString().split("T")[0];
        const data = {
            habit_name: habitName,
            user_id: "user1", // 임시 고정값
            tracking_date: selectedDate
        };
        // ✅ 바로 여기!
        console.log("📤 서버로 보낼 전체 data:", data);
        fetch("/habit/addHabitWithTracking", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                if (data.status === "success") {
                    alert("습관 추가 성공!");
                    location.reload();
                } else {
                    alert("습관 추가 실패: " + data.message);
                }
            })
            .catch(error => {
                console.error("요청 실패:", error);
                alert("서버 오류 발생!");
            });
    }

    // ✅ 습관 추가 버튼 이벤트 등록
    function addClickListener() {
        let addHabitBtn = document.getElementById("addHabitBtn");

        if (addHabitBtn) {
            addHabitBtn.removeEventListener("click", addHabit); // 중복 방지
            addHabitBtn.addEventListener("click", addHabit);
        } else {
            console.error("❌ [ERROR] addHabitBtn 버튼을 찾을 수 없습니다.");
        }
    }

    addClickListener();

    // ✅ 동적 요소 추가 감지 시 리스너 재등록
    const observer = new MutationObserver(function (mutations) {
        mutations.forEach(function (mutation) {
            if (mutation.addedNodes.length) {
                addClickListener();
            }
        });
    });

    observer.observe(document.body, { childList: true, subtree: true });

    // ✅ 탭 기능
    function habitShowTab(tab) {
        const allTabs = document.querySelectorAll('.habit-content');
        const allTabButtons = document.querySelectorAll('.habit-tab');

        allTabs.forEach(tabContent => tabContent.classList.add('habit-hidden'));
        allTabButtons.forEach(button => button.classList.remove('habit-active'));

        document.getElementById('habit-' + tab).classList.remove('habit-hidden');
        document.getElementById('habit-tab-' + tab).classList.add('habit-active');
    }

    habitShowTab('신체건강');

    // ✅ 추천 습관 클릭 시 DB에 추가
    const habitItems = document.querySelectorAll('.habit-recommend p');
    habitItems.forEach(item => {
        item.addEventListener('click', function () {
            const habitName = item.innerText;
            addHabitToDatabase(habitName);
        });
    });

    function addHabitToDatabase(habitName) {
        const userId = 'current_user_id'; // 세션 연동 시 수정

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

    // ✅ 달력 생성 함수
    function generateCalendar(month, year) {
        const calendarBody = document.getElementById("calendarBody");
        calendarBody.innerHTML = "";

        const monthYear = document.getElementById("monthYear");
        monthYear.textContent = `${year}년 ${month + 1}월`;

        const firstDay = new Date(year, month, 1).getDay();
        const daysInMonth = new Date(year, month + 1, 0).getDate();

        console.log("✅ 달력 생성 시작:", year, month + 1);
        console.log("📌 첫째 날 요일:", firstDay);
        console.log("📌 날짜 수:", daysInMonth);

        // 빈 셀 생성
        for (let i = 0; i < firstDay; i++) {
            const emptyCell = document.createElement("div");
            emptyCell.classList.add("calendar-day");
            emptyCell.innerHTML = "&nbsp;";
            calendarBody.appendChild(emptyCell);
        }

        // 날짜 셀 생성
        for (let day = 1; day <= daysInMonth; day++) {
            const dateCell = document.createElement("div");
            dateCell.classList.add("calendar-day");
            dateCell.textContent = day;

            const formattedMonth = (month + 1).toString().padStart(2, "0");
            const formattedDay = day.toString().padStart(2, "0");
            dateCell.dataset.date = `${year}-${formattedMonth}-${formattedDay}`;

            dateCell.addEventListener("click", function () {
                document.querySelectorAll(".calendar-day").forEach(cell => cell.classList.remove("selected"));
                this.classList.add("selected");
                selectedDate = this.dataset.date;
                document.getElementById("selectedDateDisplay").innerText = `선택한 날짜: ${selectedDate}`;
                // ✅ 로그 확인
                console.log("✅ 선택된 날짜 (selectedDate):", selectedDate);
            });

            calendarBody.appendChild(dateCell);
            console.log("📆 날짜 셀 추가:", dateCell.dataset.date);
        }

        console.log("✅ 셀 개수:", calendarBody.children.length);
    }

    // ✅ 달력 이전/다음 버튼
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

    // ✅ 초기 달력 생성
    generateCalendar(currentMonth, currentYear);
});
