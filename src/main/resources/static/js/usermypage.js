document.addEventListener("DOMContentLoaded", function () {
    const passwordCheckModal = document.getElementById("passwordCheckModal");
    const profileModal = document.getElementById("profileModal");

    const checkPasswordBtn = document.getElementById("checkPasswordBtn");
    const passwordCheckInput = document.getElementById("passwordCheck");
    const passwordErrorMsg = document.getElementById("passwordErrorMsg");

    const editPwInput = document.getElementById("editPw"); // 새 비밀번호 입력 필드
    const openPasswordCheckModal = document.getElementById("openPasswordCheckModal");

    if (passwordCheckModal) passwordCheckModal.style.display = "none";
    if (profileModal) profileModal.style.display = "none";

    openPasswordCheckModal.addEventListener("click", function () {
        passwordCheckModal.style.display = "block"; // 비밀번호 확인 모달 띄우기
    });

    checkPasswordBtn.addEventListener("click", function () {
        const userId = document.getElementById("editId").value;
        const enteredPassword = passwordCheckInput.value;

        fetch("/checkPassword", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ user_id: userId, password: enteredPassword })
        })
            .then(response => response.json())
            .then(data => {
                if (data.valid) {
                    passwordCheckModal.style.display = "none"; // 비밀번호 확인 모달 닫기
                    profileModal.style.display = "block"; // 프로필 수정 모달 열기
                    editPwInput.value = ""; // 기존 비밀번호 삭제
                } else {
                    passwordErrorMsg.style.display = "block"; // 오류 메시지 표시
                    passwordCheckInput.value = ""; // 입력 필드 초기화
                }
            })
            .catch(error => console.error("Error:", error));
    });

    document.querySelectorAll(".close").forEach(button => {
        button.addEventListener("click", function () {
            passwordCheckModal.style.display = "none";
            profileModal.style.display = "none";
            passwordErrorMsg.style.display = "none";
            passwordCheckInput.value = ""; // 입력값 초기화
        });
    });

    window.addEventListener("click", function (event) {
        if (event.target === profileModal) {
            profileModal.style.display = "none";
        }
        if (event.target === passwordCheckModal) {
            passwordCheckModal.style.display = "none";
        }
    });
});
