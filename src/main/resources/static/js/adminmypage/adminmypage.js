document.addEventListener("DOMContentLoaded", function () {
    // 목록 토글 처리
    const views = {
        user: {
            button: document.getElementById("user"),
            table: document.getElementById("userTable"),
            title: "Member List",
            label: "Users"
        },
        counselor: {
            button: document.getElementById("counselor"),
            table: document.getElementById("counselorTable"),
            title: "Member List",
            label: "Counselors"
        },
        announcement: {
            button: document.getElementById("announcement"),
            table: document.getElementById("announcementTable"),
            title: "Announcements",
            label: "Announcement List"
        }
    };

    const tableTitle = document.getElementById("table-title");
    const memberTypeLabel = document.getElementById("memberTypeLabel");

    function showView(viewName) {
        Object.values(views).forEach(view => view.table.style.display = "none");
        views[viewName].table.style.display = "table";
        tableTitle.textContent = views[viewName].title;
        memberTypeLabel.textContent = views[viewName].label;
        sessionStorage.setItem("lastView", viewName);
    }

    const lastView = sessionStorage.getItem("lastView") || "user";
    showView(lastView);

    Object.entries(views).forEach(([name, view]) => {
        view.button.addEventListener("click", () => showView(name));
    });

    // 공통 모달 처리 함수
    function setupModal(detailButtons, modal, closeBtn, apiUrl, fillModalData) {
        detailButtons.forEach(button => {
            button.addEventListener("click", function (event) {
                event.stopPropagation();
                const id = this.getAttribute("data-user-id");
                fetch(`${apiUrl}${id}`)
                    .then(res => res.json())
                    .then(data => {
                        fillModalData(data);
                        modal.style.display = "block";
                        setTimeout(() => {
                            const scrollable = modal.querySelector(".scrollable");
                            if (scrollable) scrollable.scrollTop = 0;
                        }, 0);
                    })
                    .catch(err => console.error("Error:", err));
            });
        });

        closeBtn.addEventListener("click", () => modal.style.display = "none");
        window.addEventListener("click", e => {
            if (e.target === modal) modal.style.display = "none";
        });
    }

    // 유저 상세 모달 설정
    setupModal(
        document.querySelectorAll(".user-detail-btn"),
        document.getElementById("userDetailModal"),
        document.querySelector("#userDetailModal .close"),
        "/admin/userDetail?userId=",
        data => {
            document.getElementById("modalUserId").textContent = data.user_id || "";
            document.getElementById("modalUserPassword").value = data.user_password || "";
            document.getElementById("modalUserName").textContent = data.user_name || "";
            document.getElementById("modalUserNickname").value = data.user_nickname || "";
            document.getElementById("modalUserEmail").value = data.user_email || "";
            document.getElementById("modalUserType").textContent = data.user_type === 2 ? "Counselor" : "User";
            document.getElementById("modalCreatedAt").textContent = data.formattedCreatedAt || "";
            const modalUserImg = document.getElementById("modalUserImg");
            modalUserImg.src = data.user_img ? `/static/${data.user_img}` : "/static/imgsource/layout/testprofile.png";
        }
    );

    // 공지사항 상세 모달 설정
    setupModal(
        document.querySelectorAll(".announcement-detail-btn"),
        document.getElementById("announcementModal"),
        document.querySelector("#announcementModal .modal-close-btn"),
        "/admin/announcementDetail/",
        data => {
            document.getElementById("modalAnnouncementTitle").value = data.title || "";
            document.getElementById("modalAnnouncementAdminId").textContent = data.admin_id || "";
            document.getElementById("modalAnnouncementCreated").textContent = data.formattedCreatedAt || "";
            document.getElementById("modalAnnouncementContent").value = data.content || "";
            document.getElementById("announcementModal").setAttribute("data-user-id", data.announcement_id);
        }
    );
});

// 유저 삭제 및 수정 이벤트 위임 처리 유지 가능
document.addEventListener("click", event => {
    const modalUserId = document.getElementById("modalUserId").textContent;
    const userType = document.getElementById("modalUserType").textContent.trim();

    if (event.target.id === "deleteUser") {
        if (!confirm("Are you sure you want to delete this member?")) return;

        fetch(`/admin/deleteUser?userId=${modalUserId}`, { method: "DELETE" })
            .then(res => res.text())
            .then(result => {
                if (result === "1") {
                    alert("Successfully deleted.");
                    sessionStorage.setItem("lastView", userType === "Counselor" ? "counselor" : "user");
                    document.getElementById("userDetailModal").style.display = "none";
                    location.reload();
                } else {
                    alert("Failed to delete. Please try again.");
                }
            })
            .catch(err => console.error("삭제 요청 실패:", err));
    }

    if (event.target.id === "updateUser") {
        const userData = {
            user_id: modalUserId,
            user_password: document.getElementById("modalUserPassword").value,
            user_nickname: document.getElementById("modalUserNickname").value,
            user_email: document.getElementById("modalUserEmail").value
        };

        if (!confirm("Are you sure you want to update this member?")) return;

        fetch(`/admin/updateUser`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userData)
        })
            .then(res => res.text())
            .then(result => {
                if (result === "1") {
                    alert("Successfully updated.");
                    sessionStorage.setItem("lastView", userType === "Counselor" ? "counselor" : "user");
                    document.getElementById("userDetailModal").style.display = "none";
                    location.reload();
                } else {
                    alert("Failed to update. Please try again.");
                }
            })
            .catch(err => console.error("수정 요청 실패:", err));
    }
});

// 공지사항 업데이트
document.addEventListener("click", function (event) {
    if (event.target && event.target.id === "updateAnnouncement") {
        const announcementId = document.getElementById("announcementModal").getAttribute("data-user-id");
        console.log("announcement_id:", announcementId);
        const updatedTitle = document.getElementById("modalAnnouncementTitle").value;
        const updatedContent = document.getElementById("modalAnnouncementContent").value;

        if (!confirm("Are you sure you want to update this announcement?")) {
            return;
        }

        const announcementData = {
            announcement_id: announcementId,
            title: updatedTitle,
            content: updatedContent
        };

        fetch(`/admin/updateAnnouncement`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(announcementData)
        })
            .then(response => response.text())
            .then(result => {
                if (result === "1") {
                    alert("Successfully updated.");
                    document.getElementById("announcementModal").style.display = "none";
                    location.reload();
                } else {
                    alert("Failed to update. Please try again.");
                }
            })
            .catch(error => console.error("업데이트 요청 실패:", error));
    }
});

// 공지사항 삭제
document.addEventListener("click", function (event) {
    if (event.target && event.target.id === "deleteAnnouncement") {
        const announcementId = document.getElementById("announcementModal").getAttribute("data-user-id");

        if (!confirm("Are you sure you want to delete this announcement?")) {
            return;
        }

        fetch(`/admin/deleteAnnouncement?announcement_id=${announcementId}`, {
            method: "DELETE"
        })
            .then(response => response.text())
            .then(result => {
                if (result === "1") {
                    alert("Successfully deleted.");
                    document.getElementById("announcementModal").style.display = "none";
                    location.reload();
                } else {
                    alert("Failed to delete. Please try again.");
                }
            })
            .catch(error => console.error("삭제 요청 실패:", error));
    }
});

/* 공지사항 작성 모달 */
document.addEventListener("DOMContentLoaded", function () {
    const createBtn = document.querySelector(".announcement-create-btn");
    const createModal = document.getElementById("announcementCreateModal");
    const createCloseBtn = document.querySelector(".create-close");

    createBtn.addEventListener("click", function () {
        createModal.style.display = "flex";
    });

    createCloseBtn.addEventListener("click", function () {
        createModal.style.display = "none";
    });

    window.addEventListener("click", function (e) {
        if (e.target === createModal) {
            createModal.style.display = "none";
        }
    });

    // 작성 완료 버튼 클릭 시 POST 요청
    document.getElementById("submitAnnouncement").addEventListener("click", function () {
        const title = document.getElementById("createAnnouncementTitle").value.trim();
        const content = document.getElementById("createAnnouncementContent").value.trim();

        if (!title || !content) {
            alert("Please enter both the title and content.");
            return;
        }

        const announcementData = {
            title: title,
            content: content
        };

        fetch("/admin/createAnnouncement", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(announcementData)
        })
            .then(response => response.text())
            .then(result => {
                if (result === "1") {
                    alert("Announcement has been successfully created.");
                    createModal.style.display = "none";
                    location.reload();
                } else {
                    alert("Failed to create announcement. Please try again.");
                }
            })
            .catch(err => console.error("작성 요청 실패:", err));
    });
});
