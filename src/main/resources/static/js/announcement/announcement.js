document.addEventListener("DOMContentLoaded", function () {
    const rows = document.querySelectorAll(".announcement-detail-btn");

    rows.forEach(row => {
        row.addEventListener("click", function () {
            const id = this.dataset.userId;
            if (id) {
                window.location.href = `/announcement/view/${id}`;
            } else {
                console.error("ID 없음!");
            }
        });
    });
});
