document.addEventListener("DOMContentLoaded", function () {

    // 유저, 상담사 목록 토글
    const userBtn = document.getElementById("user");
    const counselorBtn = document.getElementById("counselor");
    const userTable = document.getElementById("userTable");
    const counselorTable = document.getElementById("counselorTable");
    const announcementBtn = document.getElementById("announcement");
    const announcementTable = document.getElementById("announcementTable");

    const lastView = sessionStorage.getItem("lastView") || "user";
    const tableTitle = document.getElementById("table-title");
    const memberTypeLabel = document.getElementById("memberTypeLabel");

    if (lastView === "counselor") {
        userTable.style.display = "none";
        counselorTable.style.display = "table";
        announcementTable.style.display = "none";
        tableTitle.textContent = "Member List";
        memberTypeLabel.textContent = "Counselors";
    } else if (lastView === "announcement") {
        userTable.style.display = "none";
        counselorTable.style.display = "none";
        announcementTable.style.display = "table";
        tableTitle.textContent = "Announcements";
        memberTypeLabel.textContent = "Announcement List";
    } else {
        userTable.style.display = "table";
        counselorTable.style.display = "none";
        announcementTable.style.display = "none";
        tableTitle.textContent = "Member List";
        memberTypeLabel.textContent = "Users";
    }
    sessionStorage.setItem("lastView", lastView);


    userBtn.addEventListener("click", function () {
        userTable.style.display = "table";
        counselorTable.style.display = "none";
        announcementTable.style.display = "none";
        memberTypeLabel.textContent = "Users";
        tableTitle.textContent = "Member List";
        sessionStorage.setItem("lastView", "user");
    });

    counselorBtn.addEventListener("click", function () {
        userTable.style.display = "none";
        announcementTable.style.display = "none";
        counselorTable.style.display = "table";
        memberTypeLabel.textContent = "Counselors";
        tableTitle.textContent = "Member List";
        sessionStorage.setItem("lastView", "counselor");
    });

    announcementBtn.addEventListener("click", function () {
        userTable.style.display = "none";
        counselorTable.style.display = "none";
        announcementTable.style.display = "table";
        tableTitle.textContent = "Announcements";
        memberTypeLabel.textContent = "Announcement List";
        sessionStorage.setItem("lastView", "announcement");
    });

    // 상세 정보 모달
    const userDetailButtons = document.querySelectorAll(".user-detail-btn");
    const modal = document.getElementById("userDetailModal");
    const closeBtn = document.querySelector(".close");

    const passwordInput = document.getElementById("modalUserPassword");
    const toggleIcon = document.querySelector(".password-toggle");

    toggleIcon.addEventListener("click", function () {
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            toggleIcon.classList.remove("fa-eye");
            toggleIcon.classList.add("fa-eye-slash"); // 눈 감은 아이콘
        } else {
            passwordInput.type = "password";
            toggleIcon.classList.remove("fa-eye-slash");
            toggleIcon.classList.add("fa-eye"); // 눈 뜬 아이콘
        }
    });

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

            document.getElementById("userDetailModal").setAttribute("data-user-id", userId);

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
                        modalUserType.textContent = (data.user_type === 2 ) ? "Counselor" : "User";
                        modalCreatedAt.textContent = data.formattedCreatedAt || "";

                        modal.style.display = "block";

                        // 약간의 지연 후 스크롤 초기화
                        setTimeout(() => {
                            const scrollable = document.querySelector(".user-info-grid.scrollable");
                            if (scrollable) {
                                scrollable.scrollTop = 0;
                            }
                        }, 0); // 또는 50~100ms 정도 줘도 좋아

                    }
                })
                .catch(error => console.error("Error fetching user details:", error));
        });
    });

    // 모달 창 닫기 (X 버튼, 모달 창 바깥 클릭)
    closeBtn.addEventListener("click", function () {
        modal.style.display = "none";
    });

    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });

});

// 유저 정보 삭제
document.addEventListener("click", function (event) {
    // 클릭된 요소가 deleteUser 버튼인지 확인
    if (event.target && event.target.id === "deleteUser") {
        const userId = document.getElementById("modalUserId").textContent;

        if (!confirm("Are you sure you want to delete this member?")) {
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
                    alert("Successfully deleted.");
                    if (userType === "Counselor") {
                        sessionStorage.setItem("lastView", "counselor");
                    } else {
                        sessionStorage.setItem("lastView", "user");
                    }
                    document.getElementById("userDetailModal").style.display = "none";
                    location.reload();
                } else {
                    alert("Failed to delete. Please try again.");
                }
            })
            .catch(error => console.error("삭제 요청 실패:", error));
    }
});

// 유저 정보 수정
document.addEventListener("click", function (event) {
    if (event.target && event.target.id === "updateUser") {
        const userId = document.getElementById("modalUserId").textContent;
        const updatedPassword = document.getElementById("modalUserPassword").value;
        const updatedNickname = document.getElementById("modalUserNickname").value;
        const updatedEmail = document.getElementById("modalUserEmail").value;
        const userType = document.getElementById("modalUserType").textContent.trim();

        if (!confirm("Are you sure you want to update this member?")) {
            return;
        }

        const userData = {
            user_id: userId,
            user_password: updatedPassword,
            user_nickname: updatedNickname,
            user_email: updatedEmail
        };

        fetch(`/admin/updateUser`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userData)
        })
            .then(response => response.text())
            .then(result => {
                if (result === "1") {
                    alert("Successfully updated.");
                    if (userType === "Counselor") {
                        sessionStorage.setItem("lastView", "counselor");
                    } else {
                        sessionStorage.setItem("lastView", "user");
                    }
                    document.getElementById("userDetailModal").style.display = "none";
                    location.reload();
                } else {
                    alert("Failed to update. Please try again.");
                }
            })
            .catch(error => console.error("수정 요청 실패:", error));
    }
});


