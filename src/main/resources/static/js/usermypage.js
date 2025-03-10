    document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("profileModal");
    const editButton = document.querySelector(".profile_edit_btn");
    const closeButton = document.querySelector(".close");
    const form = document.getElementById("profileEditForm");

    // 처음 로드될 때 모달을 숨김
    if (modal) {
    modal.style.display = "none";
}

    // "프로필 수정하기" 버튼 클릭 시 모달 열기
    if (editButton) {
    editButton.addEventListener("click", function () {
    modal.style.display = "flex";
});
}

    // 닫기 버튼 클릭 시 모달 닫기
    if (closeButton) {
    closeButton.addEventListener("click", function () {
    modal.style.display = "none";
});
}

    // 모달 바깥 영역 클릭 시 닫기
    window.addEventListener("click", function (event) {
    if (event.target === modal) {
    modal.style.display = "none";
}
});

    // 폼 제출 (AJAX 요청)
    if (form) {
    form.addEventListener("submit", function (event) {
    event.preventDefault();

    const id = document.getElementById("editId").value;
    const password = document.getElementById("editPw").value;
    const nickname = document.getElementById("editNickname").value;

    fetch("/profile/update", {
    method: "POST",
    headers: {
    "Content-Type": "application/json",
},
    body: JSON.stringify({ id, password, nickname }),
})
    .then(response => response.json())
    .then(data => {
    alert("프로필이 수정되었습니다!");
    modal.style.display = "none";
    location.reload(); // 페이지 새로고침
})
    .catch(error => console.error("Error:", error));
});
}
});
