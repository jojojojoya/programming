document.addEventListener("DOMContentLoaded", function () {

    // 유저, 상담사 목록 토글
    const userBtn = document.getElementById("user");
    const counselorBtn = document.getElementById("counselor");
    const userTable = document.getElementById("userTable");
    const counselorTable = document.getElementById("counselorTable");

    userBtn.addEventListener("click", function () {
        userTable.style.display = "table";
        counselorTable.style.display = "none";
    });

    counselorBtn.addEventListener("click", function () {
        userTable.style.display = "none";
        counselorTable.style.display = "table";
    });

    // 상세 정보 모달
    const userDetailButtons = document.querySelectorAll(".user-detail-btn");
    const modal = document.getElementById("userDetailModal");
    const closeBtn = document.querySelector(".close");

    // 모달 내부 요소
    const modalUserId = document.getElementById("modalUserId");
    const modalUserPassword = document.getElementById("modalUserPassword");
    const modalUserName = document.getElementById("modalUserName");
    const modalUserEmail = document.getElementById("modalUserEmail");
    const modalUserType = document.getElementById("modalUserType");
    const modalCreatedAt = document.getElementById("modalCreatedAt");

    const saveUserChanges = document.getElementById("saveUserChanges"); // 수정 버튼
    const deleteUser = document.getElementById("deleteUser"); // 삭제 버튼

    // 유저 상세 정보 클릭 이벤트 추가
    userDetailButtons.forEach(button => {
        button.addEventListener("click", function () {
            const userId = this.getAttribute("data-user-id");
            const userType = this.getAttribute("data-type") || "user"; // 기본값은 'user'

            // API 호출하여 사용자 상세 정보 가져오기
            fetch(`/admin/userDetail?userId=${userId}`)
                .then(response => response.json())
                .then(data => {
                    console.log("불러온 이미지 경로:", data.user_img); // 경로 확인용
                    if (data.user_img) {
                        modalUserImg.src = `/static/${data.user_img}`;
                    } else {
                        modalUserImg.src = "/static/imgsource/testprofile.png"; // 기본 이미지
                    }

                    if (data) {
                        modalUserId.textContent = data.user_id || "";
                        modalUserPassword.value = data.user_password || "";
                        modalUserName.textContent = data.user_name || "";
                        modalUserNickname.value = data.user_nickname || "";
                        modalUserEmail.value = data.user_email || "";
                        modalUserType.textContent = (data.user_type === 2 ) ? "상담사" : "회원";
                        modalCreatedAt.textContent = data.formattedCreatedAt || "";

                        modal.style.display = "block";
                    }
                })
                .catch(error => console.error("Error fetching user details:", error));
        });
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

document.addEventListener("click", function (event) {
    // 클릭된 요소가 deleteUser 버튼인지 확인
    if (event.target && event.target.id === "deleteUser") {
        const userId = document.getElementById("modalUserId").textContent;

        if (!confirm("정말로 삭제하시겠습니까?")) {
            return;
        }

        fetch(`/admin/deleteUser?userId=${userId}`, {
            method: "DELETE"
        })
            .then(response => {
                return response.text();
            })
            .then(result => {
                if (result === "1") {
                    alert("삭제 성공했습니다.");
                    location.reload(); // 삭제 성공 시 새로고침
                } else {
                    alert("삭제에 실패했습니다. 다시 시도해주세요.");
                }
            })
            .catch(error => console.error("삭제 요청 실패:", error));
    }
});

