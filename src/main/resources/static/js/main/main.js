document.addEventListener("DOMContentLoaded", function () {


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



});
