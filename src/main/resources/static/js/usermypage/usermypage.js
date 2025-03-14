document.addEventListener("DOMContentLoaded", function () {
    console.log("🚀 페이지 로드 완료!");

    const passwordCheckModal = document.getElementById("passwordCheckModal");
    const profileModal = document.getElementById("profileModal");
    const openPasswordCheckModal = document.getElementById("openPasswordCheckModal");
    const checkPasswordBtn = document.getElementById("checkPasswordBtn");
    const passwordCheckInput = document.getElementById("passwordCheck");
    const passwordErrorMsg = document.getElementById("passwordErrorMsg");
    const editPwInput = document.getElementById("editPw");
    const editIdInput = document.getElementById("editId");
    const editNicknameInput = document.getElementById("editNickname");
    const saveProfileBtn = document.getElementById("saveProfileBtn");

    let userId = document.getElementById("hiddenUserId").value || "user5";
    console.log("🔍 현재 user_id:", userId);

    openPasswordCheckModal.addEventListener("click", function () {
        console.log("🔓 프로필 수정 버튼 클릭됨!");
        passwordCheckModal.style.display = "block";
    });

    checkPasswordBtn.addEventListener("click", function () {
        const enteredPassword = passwordCheckInput.value;

        if (!enteredPassword) {
            console.error("🚨 [오류] 비밀번호 입력이 없습니다!");
            return;
        }

        fetch("/checkPassword", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ user_id: userId, password: enteredPassword })
        })
            .then(response => response.json())
            .then(data => {
                if (data.valid) {
                    console.log("✅ 비밀번호 확인 성공! 프로필 수정 모달 열기");
                    passwordCheckModal.style.display = "none";
                    profileModal.style.display = "block";
                    editIdInput.value = userId; // 아이디 유지
                } else {
                    console.warn("⚠️ 비밀번호 불일치!");
                    passwordErrorMsg.style.display = "block";
                }
            })
            .catch(error => console.error("🚨 API 요청 오류:", error));
    });

    saveProfileBtn.addEventListener("click", function () {
        const newNickname = editNicknameInput.value.trim();
        const newPassword = editPwInput.value.trim();

        if (!newNickname) {
            alert("닉네임을 입력해주세요.");
            return;
        }

        fetch("/profileupdate", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                user_id: userId,
                user_nickname: newNickname,
                user_password: newPassword || null // 비밀번호 변경 없을 시 null
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.updated) {
                    alert("✅ 프로필이 성공적으로 수정되었습니다!");

                    // 🌟 변경된 닉네임을 화면에 즉시 반영
                    document.getElementById("nicknameDisplay").innerText = `닉네임: ${newNickname}`;

                    profileModal.style.display = "none"; // 모달 닫기
                } else {
                    alert("❌ 프로필 수정 실패! 다시 시도해주세요.");
                }
            })
            .catch(error => console.error("🚨 프로필 업데이트 오류:", error));
    });

    // 모달 닫기 이벤트 추가
    document.querySelectorAll(".close").forEach(button => {
        button.addEventListener("click", function () {
            passwordCheckModal.style.display = "none";
            profileModal.style.display = "none";
            passwordErrorMsg.style.display = "none";
            passwordCheckInput.value = "";
        });
    });

    window.addEventListener("click", function (event) {
        if (event.target === profileModal) profileModal.style.display = "none";
        if (event.target === passwordCheckModal) passwordCheckModal.style.display = "none";
    });
});
