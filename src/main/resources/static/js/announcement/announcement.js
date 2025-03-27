document.addEventListener("DOMContentLoaded", function () {

    const urlParams = new URLSearchParams(window.location.search);
    const currentPage = parseInt(urlParams.get("page")) || 1;

    if (currentPage > 1) {
        loadAnnouncementPage(currentPage); // 2페이지 이상이면 fetch
    } else {
        const totalCount = parseInt(document.body.dataset.totalCount);
        renderPagination("announcement", totalCount, currentPage);
    }

    // 이벤트 위임 방식으로 click 이벤트 처리
    document.addEventListener("click", function (e) {
        // 페이지 버튼 클릭 시
        if (e.target.classList.contains("page-btn")) {
            const page = parseInt(e.target.dataset.page);
            loadAnnouncementPage(page);
        }

        // 공지사항 행 클릭 시
        const row = e.target.closest(".announcement-detail-btn");
        if (row) {
            const id = row.dataset.userId;
            if (id) {
                window.location.href = `/announcement/view/${id}`;
            } else {
                console.error("ID 없음!");
            }
        }
    });

    function renderPagination(type, total, currentPage) {
        const pagination = document.getElementById("commonPagination");
        const totalPages = Math.ceil(total / 5);
        let html = "";

        const groupSize = 5;
        const currentGroup = Math.ceil(currentPage / groupSize);
        const startPage = (currentGroup - 1) * groupSize + 1;
        const endPage = Math.min(startPage + groupSize - 1, totalPages);

        if (startPage > 1) {
            html += `<button class="page-btn move-prev" data-type="${type}" data-page="${startPage - 1}">&laquo;</button>`;
        }

        for (let i = startPage; i <= endPage; i++) {
            html += `<button class="page-btn ${i === currentPage ? 'active' : ''}" data-type="${type}" data-page="${i}">${i}</button>`;
        }

        if (endPage < totalPages) {
            html += `<button class="page-btn move-next" data-type="${type}" data-page="${endPage + 1}">&raquo;</button>`;
        }

        pagination.innerHTML = html;
        pagination.style.display = totalPages > 1 ? "block" : "none";
    }

    function loadAnnouncementPage(page) {
        fetch(`/announcement/list?page=${page}&size=5`)
            .then(res => res.json())
            .then(data => {
                const table = document.getElementById("announcementTable");
                table.innerHTML = `
                    <div class="announcement-board-header">
                        <div class="col col-announcement-num"> 番号 </div>
                        <div class="col col-announcement-id"> 管理者ID </div>
                        <div class="col col-announcement-title"> タイトル </div>
                        <div class="col col-announcement-created"> 作成日 </div>
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
});
