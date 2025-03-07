document.addEventListener("DOMContentLoaded", function () {
    const noticeBtn = document.getElementById("notice"); // 버튼
    const modal = document.getElementById("notice-modal"); // 모달
    const closeBtn = document.querySelector(".close-btn"); // 닫기 버튼

    // 버튼 클릭 시 모달 표시/숨김
    noticeBtn.addEventListener("click", function (event) {
        event.stopPropagation(); // 다른 클릭 이벤트 방지
        modal.style.display = modal.style.display === "block" ? "none" : "block";
    });

    // 닫기 버튼 클릭 시 모달 닫기
    closeBtn.addEventListener("click", function () {
        modal.style.display = "none";
    });

    // 문서 클릭 시 모달 닫기
    document.addEventListener("click", function (event) {
        if (!modal.contains(event.target) && event.target !== noticeBtn) {
            modal.style.display = "none";
        }
    });
});
