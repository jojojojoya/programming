// ✅ habit.js 최종 통합 버전

// ✅ 주간 달성이력 + 격려 데이터 로딩
function loadWeeklySummary() {
    const selectedDate = getSelectedDate();

    fetch(`/habit/week/status?date=${selectedDate}`)
        .then(res => res.json())
        .then(data => {
            renderWeeklyMemo(data);        // .habit-week
            renderEncouragement(data);    // .habit-rate
        });
}

// ✅ 주간 이력 테이블 출력
function renderWeeklyMemo(data) {
    const tbody = document.getElementById("weeklyHabitBody");
    tbody.innerHTML = "";

    data.forEach(habit => {
        const tracking = habit.tracking;
        let row = `<tr><td>${habit.habit_name}</td>`;

        for (let i = 0; i < 7; i++) {
            row += `<td>${tracking[i] ? 'O' : 'X'}</td>`;
        }

        row += "</tr>";
        tbody.innerHTML += row;
    });
}

// ✅ 격려 문구 출력
function renderEncouragement(data) {
    const list = document.getElementById("encouragementList");
    list.innerHTML = "";

    data.forEach(habit => {
        list.innerHTML += `<li><strong>${habit.habit_name}</strong>: ${habit.encouragement}</li>`;
    });
}

// ✅ 날짜 클릭 시 호출할 함수 (달력 이벤트 연결 시 활용 가능)
function onCalendarDateClick(dateStr) {
    document.getElementById('selectedDateDisplay').textContent = `선택한 날짜: ${dateStr}`;

    loadTrackingStatus();
    attachCheckboxEvents();
    loadWeeklySummary(); // 🔥 추가된 부분
}

// ✅ 본문 스크립트 실행


document.addEventListener("DOMContentLoaded", function () {
    console.log("📌 JavaScript 로드 완료!");

    let selectedDate = null;
    let currentMonth = new Date().getMonth();
    let currentYear = new Date().getFullYear();

    function addHabit() {
        const habitName = document.getElementById("habitInput").value.trim();
        if (!habitName) {
            alert("습관 이름을 입력해주세요.");
            return;
        }

        const data = {
            habit_name: habitName,
            user_id: "user1"
        };

        fetch("/habit/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                if (data.habit_id || data.status === "success") {
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

    function addClickListener() {
        const addHabitBtn = document.getElementById("addHabitBtn");
        if (addHabitBtn && !addHabitBtn.dataset.listenerAttached) {
            addHabitBtn.setAttribute("type", "button");
            addHabitBtn.addEventListener("click", addHabit);
            addHabitBtn.dataset.listenerAttached = "true";
        }
    }

    addClickListener();

    const observer = new MutationObserver(mutations => {
        mutations.forEach(mutation => {
            if (mutation.addedNodes.length) {
                addClickListener();
            }
        });
    });
    observer.observe(document.body, { childList: true, subtree: true });

    function habitShowTab(tab) {
        const allTabs = document.querySelectorAll('.habit-content');
        const allTabButtons = document.querySelectorAll('.habit-tab');

        allTabs.forEach(tabContent => tabContent.classList.add('habit-hidden'));
        allTabButtons.forEach(button => button.classList.remove('habit-active'));

        document.getElementById('habit-' + tab).classList.remove('habit-hidden');
        document.getElementById('habit-tab-' + tab).classList.add('habit-active');
    }

    habitShowTab('신체건강');

    const habitItems = document.querySelectorAll('.habit-recommend p');
    habitItems.forEach(item => {
        item.addEventListener('click', function () {
            const habitName = item.innerText;
            addHabitToDatabase(habitName);
        });
    });

    function addHabitToDatabase(habitName) {
        const requestData = {
            userId: 'user1',
            habitName: habitName
        };

        fetch('/habit/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.habit_id || data.status === "success") {
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

    function generateCalendar(month, year) {
        const calendarBody = document.getElementById("calendarBody");
        calendarBody.innerHTML = "";

        const monthYear = document.getElementById("monthYear");
        monthYear.textContent = `${year}년 ${month + 1}월`;

        const firstDay = new Date(year, month, 1).getDay();
        const daysInMonth = new Date(year, month + 1, 0).getDate();

        for (let i = 0; i < firstDay; i++) {
            const emptyCell = document.createElement("div");
            emptyCell.classList.add("calendar-day");
            emptyCell.innerHTML = "&nbsp;";
            calendarBody.appendChild(emptyCell);
        }

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

                loadTrackingStatus();
                loadWeeklySummary(); // ✅ 추가된 호출
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

    function loadTrackingStatus() {
        if (!selectedDate) return;

        fetch(`/habit/tracking/status?date=${selectedDate}`)
            .then(response => response.json())
            .then(result => {
                console.log("✅ 서버 응답:", result);
                const trackedHabitIds = Array.isArray(result) ? result : result.data;

                document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
                    const habitId = parseInt(checkbox.id.split('-')[1]);
                    checkbox.checked = trackedHabitIds.includes(habitId);
                });

                attachCheckboxEvents();
            })
            .catch(error => {
                console.error("습관 상태 로드 실패:", error);
            });
    }

    function attachCheckboxEvents() {
        document.querySelectorAll('input[type="checkbox"]').forEach(oldCheckbox => {
            const newCheckbox = oldCheckbox.cloneNode(true);
            oldCheckbox.replaceWith(newCheckbox);
        });

        document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
            checkbox.addEventListener("change", function () {
                const habitId = parseInt(this.id.split('-')[1]);
                const isChecked = this.checked ? 1 : 0;

                if (!selectedDate) {
                    alert("먼저 날짜를 선택해주세요!");
                    this.checked = !this.checked;
                    return;
                }

                const formattedDate = new Date(selectedDate).toISOString().split("T")[0];

                const payload = {
                    habit_id: habitId,
                    completed: isChecked,
                    tracking_date: formattedDate,
                    user_id: "user1"
                };
                console.log("📤 서버로 보낼 payload:", payload);

                fetch("/habit/tracking", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(payload)
                })
                    .then(response => {
                        if (!response.ok) {
                            alert("습관 상태 저장 실패");
                            this.checked = !isChecked;
                        }
                    })
                    .catch(error => {
                        console.error("저장 에러:", error);
                        this.checked = !isChecked;
                    });
            });
        });
    }

    // ✅ 여기 마지막에 주간 데이터 호출 추가
    loadWeeklySummary();
});