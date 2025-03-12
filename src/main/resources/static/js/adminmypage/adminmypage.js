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
    const userDetailButtons = document.querySelectorAll(".user-detail-btn")
    const modal = document.getElementById("userDetailModal");
    const closeBtn = document.querySelector(".close");

    // 모달 내부 요소
    const modalUserId = document.getElementById("modalUserId");
    const modalUserPassword = document.getElementById("modalUserPassword");
    const modalUserName = document.getElementById("modalUserName");
    const modalUserEmail = document.getElementById("modalUserEmail");
    const modalUserType = document.getElementById("modalUserType");
    const modalCreatedAt = document.getElementById("modalCreatedAt");

    userDetailButtons.forEach(button => {
        button.addEventListener("click", function () {
            const userId = this.getAttribute("data-user-id");
            const userType = this.getAttribute("data-type") || "user"; // 기본값은 'user'

            // API 호출하여 사용자 상세 정보 가져오기
            fetch(`/admin/userDetail?userId=${userId}`)
                .then(response => response.json())
                .then(data => {
                    if (data) {
                        modalUserId.textContent = data.user_id;
                        modalUserPassword.textContent = data.user_password;
                        modalUserName.textContent = data.user_name;
                        modalUserEmail.textContent = data.user_email;
                        modalUserType.textContent = (data.user_type === "counselor") ? "상담사" : "회원";
                        modalCreatedAt.textContent = data.formattedCreatedAt;

                        modal.style.display = "block";
                    }
                })
                .catch(error => console.error("Error fetching user details:", error));
        });
    });

    // 모달 닫기 기능
    closeBtn.addEventListener("click", function () {
        modal.style.display = "none";
    });

    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });

});