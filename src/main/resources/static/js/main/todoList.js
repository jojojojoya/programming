document.addEventListener("DOMContentLoaded", function () {
    loadTodoList(); // 페이지 로드 시 서버에서 데이터 가져옴
});

const taskList = document.getElementById("task-list");

// 서버에서 할 일 목록 가져오기
function loadTodoList() {
    fetch(`/habit-tracking/list`)
        .then(response => response.json())
        .then(data => {
            console.log("서버 응답 데이터:", data); // 응답 데이터 확인

            if (!Array.isArray(data)) {
                console.error("올바른 데이터 형식이 아닙니다:", data);
                return;
            }

            taskList.innerHTML = ""; // 기존 목록 초기화

            data.forEach(habit => {
                const listItem = document.createElement("li");
                listItem.classList.add("task-item");

                const checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.checked = habit.completed === 1;
                checkbox.addEventListener("change", function () {
                    toggleHabit(habit.trackingId, checkbox.checked);
                });

                const label = document.createElement("label");
                label.textContent = habit.habitName;

                // 체크박스 체크 시 줄 긋기 효과
                if (checkbox.checked) {
                    listItem.classList.add("completed");
                }

                checkbox.addEventListener("change", function () {
                    if (checkbox.checked) {
                        listItem.classList.add("completed"); // 줄 긋기
                    } else {
                        listItem.classList.remove("completed"); // 원래 상태로
                    }
                });

                listItem.appendChild(checkbox);
                listItem.appendChild(label);
                taskList.appendChild(listItem);
            });
        })
        .catch(error => console.error("데이터 불러오기 실패:", error));
}

// 체크박스 클릭 시 완료 상태 업데이트
/*function toggleHabit(trackingId, completed) {
    fetch(`/habit-tracking/toggle/${trackingId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ completed: completed ? 1 : 0 })
    })
        .then(response => response.json())
        .then(() => loadTodoList()) // 상태 변경 후 목록 다시 불러오기
        .catch(error => console.error("완료 상태 변경 실패:", error));
}*/
