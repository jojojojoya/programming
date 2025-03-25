document.addEventListener("DOMContentLoaded", function () {
    const views = {
        user: { button: document.getElementById("user"), table: document.getElementById("userTable"), title: "Member List", label: "Users" },
        counselor: { button: document.getElementById("counselor"), table: document.getElementById("counselorTable"), title: "Member List", label: "Counselors" },
        announcement: { button: document.getElementById("announcement"), table: document.getElementById("announcementTable"), title: "Announcements", label: "Announcement List" }
    };

    const tableTitle = document.getElementById("table-title");
    const memberTypeLabel = document.getElementById("memberTypeLabel");

    function showView(viewName) {
        Object.values(views).forEach(view => view.table.style.display = "none");
        views[viewName].table.style.display = "block";
        tableTitle.textContent = views[viewName].title;
        memberTypeLabel.textContent = views[viewName].label;
        sessionStorage.setItem("lastView", viewName);
    }

    const lastView = sessionStorage.getItem("lastView") || "user";
    showView(lastView);
    if (lastView === "user") loadUserPage(1);
    if (lastView === "counselor") loadCounselorPage(1);
    if (lastView === "announcement") loadAnnouncementPage(1);

    Object.entries(views).forEach(([name, view]) => {
        view.button.addEventListener("click", () => {
            showView(name);
            if (name === "user") loadUserPage(1);
            if (name === "counselor") loadCounselorPage(1);
            if (name === "announcement") loadAnnouncementPage(1);
        });
    });

    function renderPagination(type, total, currentPage) {
        const pagination = document.getElementById("commonPagination");
        const totalPages = Math.ceil(total / 5);
        let html = "";
        for (let i = 1; i <= totalPages; i++) {
            html += `<button class="page-btn ${i === currentPage ? 'active' : ''}" data-type="${type}" data-page="${i}">${i}</button>`;
        }
        pagination.innerHTML = html;
        pagination.style.display = totalPages > 1 ? "block" : "none";
    }

    document.addEventListener("click", function (event) {
        if (event.target.classList.contains("page-btn")) {
            const page = parseInt(event.target.getAttribute("data-page"));
            const type = event.target.getAttribute("data-type");
            if (type === "user") loadUserPage(page);
            if (type === "counselor") loadCounselorPage(page);
            if (type === "announcement") loadAnnouncementPage(page);
        }
    });

    function loadUserPage(page) {
        fetch(`/admin/userList?page=${page}&size=5`)
            .then(res => res.json())
            .then(data => {
                const table = document.getElementById("userTable");
                table.innerHTML = `
                    <div class="user-board-header">
                        <div class="col col-num"> No </div>
                        <div class="col col-id"> ID </div>
                        <div class="col col-name"> Name </div>
                        <div class="col col-nickname"> Nickname </div>
                        <div class="col col-email"> Email </div>
                        <div class="col col-date"> Joined </div>
                    </div>
                `;
                data.list.forEach((user, index) => {
                    const no = data.total - ((page - 1) * 5 + index);
                    const row = document.createElement("div");
                    row.className = "user-row user-detail-btn";
                    row.setAttribute("data-user-id", user.user_id);
                    row.innerHTML = `
                        <div class="cell col-num">${no}</div>
                        <div class="cell col-id">${user.user_id}</div>
                        <div class="cell col-name">${user.user_name}</div>
                        <div class="cell col-nickname">${user.user_nickname}</div>
                        <div class="cell col-email">${user.user_email}</div>
                        <div class="cell col-date">${user.formattedCreatedAt}</div>
                    `;
                    table.appendChild(row);
                });
                renderPagination("user", data.total, page);
            });
    }

    function loadCounselorPage(page) {
        fetch(`/admin/counselorList?page=${page}&size=5`)
            .then(res => res.json())
            .then(data => {
                const table = document.getElementById("counselorTable");
                table.innerHTML = `
                    <div class="user-board-header">
                        <div class="col col-num"> No </div>
                        <div class="col col-id"> ID </div>
                        <div class="col col-name"> Name </div>
                        <div class="col col-nickname"> Nickname </div>
                        <div class="col col-email"> Email </div>
                        <div class="col col-date"> Joined </div>
                    </div>
                `;
                data.list.forEach((user, index) => {
                    const no = data.total - ((page - 1) * 5 + index);
                    const row = document.createElement("div");
                    row.className = "user-row user-detail-btn";
                    row.setAttribute("data-user-id", user.user_id);
                    row.innerHTML = `
                        <div class="cell col-num">${no}</div>
                        <div class="cell col-id">${user.user_id}</div>
                        <div class="cell col-name">${user.user_name}</div>
                        <div class="cell col-nickname">${user.user_nickname}</div>
                        <div class="cell col-email">${user.user_email}</div>
                        <div class="cell col-date">${user.formattedCreatedAt}</div>
                    `;
                    table.appendChild(row);
                });
                renderPagination("counselor", data.total, page);
            });
    }

    function loadAnnouncementPage(page) {
        fetch(`/admin/announcementList?page=${page}&size=5`)
            .then(res => res.json())
            .then(data => {
                const table = document.getElementById("announcementTable");
                table.innerHTML = `
                    <button class="announcement-create-btn">Create</button>
                    <div class="announcement-board-header">
                        <div class="col col-announcement-num">No</div>
                        <div class="col col-announcement-id">Admin Id</div>
                        <div class="col col-announcement-title">Title</div>
                        <div class="col col-announcement-created">Created</div>
                    </div>
                `;
                data.list.forEach((a, index) => {
                    const no = data.total - ((page - 1) * 5 + index);
                    const row = document.createElement("div");
                    row.className = "announcement-row announcement-detail-btn";
                    row.setAttribute("data-user-id", a.announcement_id);
                    row.innerHTML = `
                        <div class="cell col-announcement-num">${no}</div>
                        <div class="cell col-announcement-id">${a.admin_id}</div>
                        <div class="cell col-announcement-title">${a.title}</div>
                        <div class="cell col-announcement-created">${a.formattedCreatedAt}</div>
                    `;
                    table.appendChild(row);
                });
                renderPagination("announcement", data.total, page);
            });
    }

    // 공통 이벤트 위임: 상세 모달
    document.addEventListener("click", function (event) {
        if (event.target.closest(".user-detail-btn")) {
            const id = event.target.closest(".user-detail-btn").getAttribute("data-user-id");
            fetch(`/admin/userDetail?userId=${id}`)
                .then(res => res.json())
                .then(data => {
                    document.getElementById("modalUserId").textContent = data.user_id;
                    document.getElementById("modalUserPassword").value = data.user_password;
                    document.getElementById("modalUserName").textContent = data.user_name;
                    document.getElementById("modalUserNickname").value = data.user_nickname;
                    document.getElementById("modalUserEmail").value = data.user_email;
                    document.getElementById("modalUserType").textContent = data.user_type === 2 ? "Counselor" : "User";
                    document.getElementById("modalCreatedAt").textContent = data.formattedCreatedAt;
                    document.getElementById("modalUserImg").src = data.user_img ? `/static/${data.user_img}` : "/static/imgsource/layout/testprofile.png";
                    document.getElementById("userDetailModal").style.display = "block";
                });
        }

        if (event.target.closest(".announcement-detail-btn")) {
            const id = event.target.closest(".announcement-detail-btn").getAttribute("data-user-id");
            fetch(`/admin/announcementDetail/${id}`)
                .then(res => res.json())
                .then(data => {
                    document.getElementById("modalAnnouncementTitle").value = data.title;
                    document.getElementById("modalAnnouncementAdminId").textContent = data.admin_id;
                    document.getElementById("modalAnnouncementCreated").textContent = data.formattedCreatedAt;
                    document.getElementById("modalAnnouncementContent").value = data.content;
                    document.getElementById("announcementModal").setAttribute("data-user-id", data.announcement_id);
                    document.getElementById("announcementModal").style.display = "block";
                });
        }

        if (event.target.classList.contains("close")) {
            document.getElementById("userDetailModal").style.display = "none";
        }

        if (event.target.classList.contains("modal-close-btn")) {
            document.getElementById("announcementModal").style.display = "none";
        }

        if (event.target.id === "deleteUser") {
            const userId = document.getElementById("modalUserId").textContent;
            if (confirm("Are you sure you want to delete this member?")) {
                fetch(`/admin/deleteUser?userId=${userId}`, { method: "DELETE" })
                    .then(res => res.text())
                    .then(result => {
                        if (result === "1") {
                            alert("Deleted.");
                            location.reload();
                        }
                    });
            }
        }

        if (event.target.id === "updateUser") {
            const data = {
                user_id: document.getElementById("modalUserId").textContent,
                user_password: document.getElementById("modalUserPassword").value,
                user_nickname: document.getElementById("modalUserNickname").value,
                user_email: document.getElementById("modalUserEmail").value
            };
            if (confirm("Update this member?")) {
                fetch("/admin/updateUser", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(data)
                })
                    .then(res => res.text())
                    .then(result => {
                        if (result === "1") {
                            alert("Updated.");
                            location.reload();
                        }
                    });
            }
        }

        if (event.target.id === "updateAnnouncement") {
            const id = document.getElementById("announcementModal").getAttribute("data-user-id");
            const data = {
                announcement_id: id,
                title: document.getElementById("modalAnnouncementTitle").value,
                content: document.getElementById("modalAnnouncementContent").value
            };
            if (confirm("Update this announcement?")) {
                fetch("/admin/updateAnnouncement", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(data)
                })
                    .then(res => res.text())
                    .then(result => {
                        if (result === "1") {
                            alert("Updated.");
                            location.reload();
                        }
                    });
            }
        }

        if (event.target.id === "deleteAnnouncement") {
            const id = document.getElementById("announcementModal").getAttribute("data-user-id");
            if (confirm("Delete this announcement?")) {
                fetch(`/admin/deleteAnnouncement?announcement_id=${id}`, { method: "DELETE" })
                    .then(res => res.text())
                    .then(result => {
                        if (result === "1") {
                            alert("Deleted.");
                            location.reload();
                        }
                    });
            }
        }
    });

    // 공지사항 등록 모달 열기 / 닫기 / 작성
    document.addEventListener("click", function (e) {
        const createModal = document.getElementById("announcementCreateModal");

        // 작성 버튼 클릭 → 모달 열기 (버튼이 동적 생성되므로 위임 방식)
        if (e.target && e.target.classList.contains("announcement-create-btn")) {
            createModal.style.display = "flex";
        }

        // 닫기 버튼 클릭
        if (e.target && e.target.classList.contains("create-close")) {
            createModal.style.display = "none";
        }

        // 바깥 영역 클릭 시 모달 닫기
        if (e.target === createModal) {
            createModal.style.display = "none";
        }

        // 제출 버튼 클릭
        if (e.target && e.target.id === "submitAnnouncement") {
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
                .then(res => res.text())
                .then(result => {
                    if (result === "1") {
                        alert("Announcement has been created.");
                        createModal.style.display = "none";
                        location.reload();
                    } else {
                        alert("Failed to create announcement. Please try again.");
                    }
                })
                .catch(err => {
                    console.error("작성 오류:", err);
                    alert("An error occurred while creating the announcement.");
                });
        }
    });

    // 비밀번호 토글 기능
    document.addEventListener("click", function (e) {
        if (e.target.classList.contains("password-toggle")) {
            const input = document.getElementById("modalUserPassword");
            const icon = e.target;
            const isVisible = input.type === "text";

            input.type = isVisible ? "password" : "text";
            icon.classList.toggle("fa-eye", isVisible);
            icon.classList.toggle("fa-eye-slash", !isVisible);
        }
    });

// 모달 외부 클릭 시 닫기 (공통 처리)
    window.addEventListener("click", function (e) {
        const userModal = document.getElementById("userDetailModal");
        const announcementModal = document.getElementById("announcementModal");
        const createModal = document.getElementById("announcementCreateModal");

        if (e.target === userModal) {
            userModal.style.display = "none";
        }
        if (e.target === announcementModal) {
            announcementModal.style.display = "none";
        }
        if (e.target === createModal) {
            createModal.style.display = "none";
        }
    });


});
