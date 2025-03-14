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






