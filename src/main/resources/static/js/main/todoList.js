    document.addEventListener("DOMContentLoaded", function () {
        loadTodoList(); // 페이지 로드 시 오늘 날짜 체크리스트
    });

    const taskList = document.getElementById("task-list");

    // 오늘 날짜의 체크리스트 불러오기
    function loadTodoList() {
        fetch(`/habit-tracking/list/today`)
            .then(response => response.json())
            .then(data => {
                console.log("서버 응답 데이터:", data);
                renderChecklist(data.hasHabits, data.todayHabits);
            })
            .catch(error => console.error("데이터 불러오기 실패:", error));
    }

    // 특정 날짜의 체크리스트 불러오기
    function loadChecklistByDate(dateString) {
        fetch(`/habit-tracking/list/by-date?date=${dateString}`)
            .then(response => response.json())
            .then(data => {
                renderChecklist(true, data); // renderChecklist는 날짜별 목록을 그려주는 함수
            })
            .catch(error => console.error("체크리스트 불러오기 실패:", error));
    }

    // 체크리스트 렌더링 함수 (공통)
    function renderChecklist(hasHabits, habitList) {
        taskList.innerHTML = "";

        if (!hasHabits) {
            taskList.innerHTML = `
                <div class="no-habit-box">
                    <p class="no-habit-text"> 아직 등록한 습관이 없습니다. </p>
                    <button id="go-to-habit-page" class="register-btn"> 습관 등록하러 가기 </button>
                </div>
            `;
            document.getElementById("go-to-habit-page").addEventListener("click", () => {
                window.location.href = "/habit";
            });
            return;
        }

        if (!Array.isArray(habitList) || habitList.length === 0) {
            taskList.innerHTML = `
                <div class="no-habit-box">
                    <p class="no-habit-text">이 날짜에 수행할 습관이 없습니다.</p>
                </div>
            `;
            return;
        }

        habitList.forEach(habit => {
            const listItem = document.createElement("li");
            listItem.classList.add("task-item");

            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.checked = habit.completed === 1;

            // 날짜 선택으로 보는 경우라면 체크박스 비활성화
            if (habit.tracking_id === undefined) {
                checkbox.disabled = true;
            } else {
                // 완료 상태 변경 허용
                checkbox.addEventListener("change", function () {
                    toggleHabit(habit.tracking_id, checkbox.checked);
                    listItem.classList.toggle("completed", checkbox.checked);
                });
            }

            const label = document.createElement("label");
            label.textContent = habit.habit_name;

            if (checkbox.checked) {
                listItem.classList.add("completed");
            }

            listItem.appendChild(checkbox);
            listItem.appendChild(label);
            taskList.appendChild(listItem);
        });
    }

    // 체크박스 클릭 시 완료 상태 업데이트
    function toggleHabit(trackingId, completed) {
        fetch(`/habit-tracking/toggle/${trackingId}`, {
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
