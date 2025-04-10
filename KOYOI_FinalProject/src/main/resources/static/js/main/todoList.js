    document.addEventListener("DOMContentLoaded", function () {
        loadTodoList(); // 페이지 로드 시 오늘 날짜 체크리스트
    });

    const taskList = document.getElementById("task-list");

    /* 오늘 날짜의 체크리스트 불러오기 */
    function loadTodoList() {
        fetch(`/habit-tracking/list/today`)
            .then(response => response.json())
            .then(data => {
                console.log("서버 응답 데이터:", data);
                renderChecklist(data.hasHabits, data.todayHabits);
            })
            .catch(error => console.error("데이터 불러오기 실패:", error));
    }

    /* 특정 날짜의 체크리스트 불러오기 */
    function loadChecklistByDate(dateString) {
        fetch(`/habit-tracking/list/by-date?date=${dateString}`)
            .then(response => response.json())
            .then(data => {
                renderChecklist(true, data, dateString); // renderChecklist는 날짜별 목록을 그려주는 함수
            })
            .catch(error => console.error("체크리스트 불러오기 실패:", error));
    }

    /* 체크리스트 렌더링 함수(공통) */
    function renderChecklist(hasHabits, habitList, dateString) {
        taskList.innerHTML = "";

        // 습관이 없는 경우
        if (!hasHabits) {
            taskList.innerHTML = `
        <div class="no-habit-box">
            <p class="no-habit-text"> まだ習慣が登録されていません。 </p>
            <button id="go-to-habit-page" class="register-btn"> 習慣を登録する </button>
        </div>
    `;
            document.getElementById("go-to-habit-page").addEventListener("click", () => {
                window.location.href = "/habit";
            });
            return;
        }

        // 선택한 날짜에 습관이 없는 경우
        if (!Array.isArray(habitList) || habitList.length === 0) {
            taskList.innerHTML = `
        <div class="no-habit-box">
            <p class="no-habit-text"> この日の習慣はありません。 </p>
        </div>
    `;
            return;
        }

        // 날짜 유효성 검사
        const isToday = (dateString) => {
            const today = new Date().toISOString().split("T")[0];
            return dateString === today;
        };

        const isPast = (dateString) => {
            const today = new Date().toISOString().split("T")[0];
            return dateString < today;
        };

        const isFuture = (dateString) => {
            const today = new Date().toISOString().split("T")[0];
            return dateString > today;
        };


        habitList.forEach(habit => {
            const listItem = document.createElement("li");
            listItem.classList.add("task-item");

            const label = document.createElement("label");
            label.classList.add("custom-checkbox");

            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.checked = habit.completed === 1;

            const checkmark = document.createElement("span");
            checkmark.classList.add("checkmark");

            label.appendChild(checkbox);
            label.appendChild(checkmark);
            label.appendChild(document.createTextNode(habit.habit_name));

            // 체크박스 비활성화 조건 처리
            if (isPast(dateString) || isFuture(dateString)) {
                checkbox.disabled = true;
                checkbox.classList.add("disabled-checkbox");
            } else {
                checkbox.disabled = false;
                checkbox.addEventListener("change", function () {
                    toggleHabit(habit.habit_id, checkbox.checked);
                    listItem.classList.toggle("completed", checkbox.checked);
                });
            }

            if (checkbox.checked) {
                listItem.classList.add("completed");
            }

            listItem.appendChild(label);
            taskList.appendChild(listItem);
        });

        const spacing = document.createElement("div");
        spacing.style.height = "30px";
        taskList.appendChild(spacing);

        const habitPageLinkBox = document.createElement("div");
        habitPageLinkBox.classList.add("habit-page-link-box");

        // 습관 페이지로 이동
        const habitButton = document.createElement("button");
        habitButton.classList.add("habit-link-btn");
        habitButton.textContent = "習慣ページへ";
        habitButton.onclick = function () {
            window.location.href = "/habit";
        };

        habitPageLinkBox.appendChild(habitButton);
        taskList.appendChild(habitPageLinkBox);
    }


    /* 체크박스 클릭 시 완료 상태 업데이트 */
    function toggleHabit(habitId, completed) {
        fetch(`/habit-tracking/toggle/${habitId}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ completed: completed ? 1 : 0 })
        })
            .then(response => response.text())
            .then(() => {
                console.log("완료 상태 변경 성공");
                loadTodoList(); // 오늘 체크리스트 다시 로드
            })
            .catch(error => console.error("완료 상태 변경 실패:", error));
    }
