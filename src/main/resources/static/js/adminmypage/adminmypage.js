document.addEventListener("DOMContentLoaded", function () {
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
});