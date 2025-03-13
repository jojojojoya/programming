document.addEventListener("DOMContentLoaded", function () {

    // 유저, 상담사 목록 토글
    const userBtn = document.getElementById("user");
    const counselorBtn = document.getElementById("counselor");
    const userTable = document.getElementById("userTable");
    const counselorTable = document.getElementById("counselorTable");

    userBtn.addEventListener("click", function () {
        userTable.style.display = "table";   // 유저 목록 보이기
        counselorTable.style.display = "none"; // 상담사 목록 숨기기
    });

    counselorBtn.addEventListener("click", function () {
        userTable.style.display = "none";   // 유저 목록 숨기기
        counselorTable.style.display = "table"; // 상담사 목록 보이기
    });

    // 상세 정보 모달
    const modal = document.getElementById("userDetailModal");
    const closeBtn = document.querySelector(".close");

    // 모든 유저 상세 버튼에 클릭 이벤트 추가
    document.querySelectorAll(".user-detail-btn").forEach(btn => {
        btn.addEventListener("click", function () {

                    modal.style.display = "block";
                });


        closeBtn.addEventListener("click", function () {
            modal.style.display = "none";
        });

        window.addEventListener("click", function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        });

    });

});