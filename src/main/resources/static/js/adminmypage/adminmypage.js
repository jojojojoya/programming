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

                    if (data.user_img) {
                        modalUserImg.src = data.user_img.startsWith("/")
                            ? location.origin + data.user_img
                            : data.user_img;
                    } else {
                        modalUserImg.src = "/static/imgsource/testprofile.png"; // 기본 이미지
                    }

                    if (data) {
                        modalUserId.textContent = data.user_id || "";
                        modalUserPassword.value = data.user_password || "";
                        modalUserName.value = data.user_name || "";
                        modalUserEmail.value = data.user_email || "";
                        modalUserType.textContent = (data.user_type === "counselor") ? "상담사" : "회원";
                        modalCreatedAt.textContent = data.formattedCreatedAt || "";

                        modal.style.display = "block";
                    }
                })
                .catch(error => console.error("Error fetching user details:", error));
        });
    });

    // 유저 삭제
    deleteUser.addEventListener("click", function () {
        const userId = modalUserId.textContent; // 모달에서 현재 유저 ID 가져오기

        if (!userId) {
            alert("삭제할 사용자 ID가 없습니다.");
            return;
        }

        if (!confirm("정말 삭제하시겠습니까?")) {
            return; // 취소 버튼을 누르면 삭제 중단
        }

        // API 호출하여 사용자 삭제 요청 보내기
        fetch(`/admin/deleteUser?userId=${userId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("삭제 실패");
                }
                return response.json();
            })
            .then(data => {
                alert(data.message || "사용자가 삭제되었습니다.");
                modal.style.display = "none"; // 모달 닫기
                location.reload(); // 페이지 새로고침
            })
            .catch(error => {
                console.error("Error deleting user:", error);
                alert("삭제 중 오류가 발생했습니다.");
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
