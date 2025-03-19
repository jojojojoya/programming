document.addEventListener("DOMContentLoaded", function () {

    // 모달
    const noticeBtn = document.getElementById("notice");
    const modal = document.getElementById("notice-modal");
    const closeBtn = document.querySelector(".close-btn");


    noticeBtn.addEventListener("click", function (event) {
        event.stopPropagation(); // 다른 클릭 이벤트 방지(이벤트 버블링 방지)
        modal.style.display = modal.style.display === "block" ? "none" : "block";
        // modal.style.display => css 파일의 style 가져올 수 없으므로 빈 문자열 출력
        // 삼항 연산자 실행시 false가 되기 때문에 block 설정하게 됨 => 모달 열기
        // 이후부터는 js에서 설정한 값을 가지고 보여주는 것.
    });


    closeBtn.addEventListener("click", function () {
        modal.style.display = "none";
    });


    document.addEventListener("click", function (event) {
        if (!modal.contains(event.target) && event.target !== noticeBtn) {
            modal.style.display = "none";
        }
        // event.target => 사용자가 클릭한 요소(html 태그)
        // 즉 클릭한 요소가 모달 내부에 있지 않거나(모달 창 밖이거나), 공지사항 버튼이 아니면
        // 모달창 닫기.
    });


    // 체크리스트
    const taskInput = document.getElementById("task-input");
    const addTaskBtn = document.getElementById("add-task-btn");
    const taskList = document.getElementById("task-list");

    addTaskBtn.addEventListener("click", function () {
        const taskText = taskInput.value.trim(); // trim = 앞 뒤 공백 제거 => 공백이 유효값이 되는 걸 방지
        if (taskText === "") return; // trim으로 인해 값이 없어지므로 추가 되는 것이 방지됨.

        // 리스트 아이템 생성
        const listItem = document.createElement("li");
        listItem.classList.add("task-item");

        // 체크박스 생성
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";

        // 라벨 생성
        const label = document.createElement("label");
        label.textContent = taskText;

        // 체크박스 체크 시 줄 긋기 효과
        checkbox.addEventListener("change", function () {
            if (checkbox.checked) {
                listItem.classList.add("completed"); // 줄 긋기
            } else {
                listItem.classList.remove("completed"); // 원래 상태로
            }
        });

        // 리스트 아이템 구성
        listItem.appendChild(checkbox);
        listItem.appendChild(label);
        taskList.appendChild(listItem);

        // 입력 필드 비우기
        taskInput.value = "";
    });

});
