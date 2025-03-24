document.addEventListener("DOMContentLoaded", function () {
    mypageLoad();
    reservationHandler();
});


function reservationHandler() {
    console.log("🚀 usermypage 페이지 로드 완료!");

    document.querySelectorAll(".reserved_reservation_box").forEach(reservationBox => {
        let counselingId = reservationBox.dataset.counselingId;
        let sessionId = reservationBox.dataset.sessionId;
        let status = reservationBox.dataset.status;
        let counselingDate = reservationBox.dataset.counselingDate;
        let counselingTime = parseInt(reservationBox.dataset.counselingTime); // 상담 시간 (24시간제)

        // console.log(`🔍 상담 ID: ${counselingId}, 세션 ID: ${sessionId}, 상태: ${status}, 날짜: ${counselingDate}, 시간: ${counselingTime}`);

        let enterButton = reservationBox.querySelector(".enter_counseling_btn");
        let viewButton = reservationBox.querySelector(".view_counseling_btn");
        console.log(enterButton)
        console.log(viewButton)
        enterButton?.addEventListener("click", () => {
            console.log("aaa")
            goToLiveChatDetail(sessionId, counselingId, false);

        })

        viewButton?.addEventListener("click", () => {
            goToLiveChatDetail(sessionId, counselingId, true);
        });
        // 🕒 현재 시간 가져오기
        let now = new Date();
        let currentDate = now.toISOString().split("T")[0]; // YYYY-MM-DD 형식
        let currentHour = now.getHours(); // 현재 시간 (24시간제)

        // ✅ '대기' 상태이며 상담 시간이 현재 시각 기준 1시간 이내인지 확인
        let isWithinOneHour = (counselingDate === currentDate) && (counselingTime - currentHour <= 1) && (counselingTime - currentHour >= 0);
    });
}


function goToLiveChatDetail(sessionId, counselingId, isCompleted) {
    let url = `/livechatdetail?sessionId=${sessionId}&counselingId=${counselingId}&isCompleted=${isCompleted}`;
    console.log("📌 이동할 URL:", url);
    window.location.href = url;
}


function mypageLoad() {
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
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({user_id: userId, password: enteredPassword})
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
    //
    // saveProfileBtn.addEventListener("click", function () {
    //     const newNickname = editNicknameInput.value.trim();
    //     const newPassword = editPwInput.value.trim();
    //     const profileImgFile = document.getElementById("editProfileImg").files[0];
    //
    //     if (!newNickname) {
    //         alert("닉네임을 입력해주세요.");
    //         return;
    //     }
    //
    //     fetch("/profileupdate", {
    //         method: "POST",
    //         headers: {"Content-Type": "application/json"},
    //         body: JSON.stringify({
    //             user_id: userId,
    //             user_nickname: newNickname,
    //             user_password: newPassword || null // 비밀번호 변경 없을 시 null
    //         })
    //     })
    //         .then(response => response.json())
    //         .then(data => {
    //             if (data.updated) {
    //                 alert("✅ 프로필이 성공적으로 수정되었습니다!");
    //
    //                 // 🌟 변경된 닉네임을 화면에 즉시 반영
    //                 document.getElementById("nicknameDisplay").innerText = `닉네임: ${newNickname}`;
    //
    //                 profileModal.style.display = "none"; // 모달 닫기
    //             } else {
    //                 alert("❌ 프로필 수정 실패! 다시 시도해주세요.");
    //             }
    //         })
    //         .catch(error => console.error("🚨 프로필 업데이트 오류:", error));
    // });
    saveProfileBtn.addEventListener("click", function () {
        const newNickname = editNicknameInput.value.trim();
        const newPassword = editPwInput.value.trim();
        const profileImgFile = document.getElementById("editProfileImg").files[0];

        if (!newNickname) {
            alert("닉네임을 입력해주세요.");
            return;
        }

        const formData = new FormData();
        formData.append("user_id", userId);
        formData.append("user_nickname", newNickname);
        formData.append("user_password", newPassword || "");
        if (profileImgFile) {
            formData.append("user_img", profileImgFile);
        }

        fetch("/profileupdatewithimg", {
            method: "POST",
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                if (data.updated) {
                    alert("✅ 프로필이 성공적으로 수정되었습니다!");

                    // 프로필 이미지도 즉시 반영
                    if (data.newImgPath) {
                        document.querySelector(".profile_img img").src = data.newImgPath;
                    }

                    document.getElementById("nicknameDisplay").innerText = `닉네임: ${newNickname}`;
                    profileModal.style.display = "none";
                } else {
                    alert("❌ 프로필 수정 실패!");
                }
            })
            .catch(error => console.error("🚨 프로필 업데이트 오류:", error));
    });

    document.getElementById("editProfileImg").addEventListener("change", function (event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.querySelector("#profileModal .profile_img img").src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
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
}