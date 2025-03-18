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
//
// document.addEventListener("DOMContentLoaded", function () {
//     console.log("🚀 usermypage 페이지 로드 완료!");
//
//     const enterButtons = document.querySelectorAll(".enter_counseling_btn");
//
//     enterButtons.forEach(button => {
//         const reservationBox = button.closest(".reserved_reservation_box");
//
//         let counselingId = reservationBox.dataset.counselingId;
//         let counselingDate = reservationBox.dataset.counselingDate;
//         let counselingTime = parseInt(reservationBox.dataset.counselingTime, 10);
//         let statusElement = reservationBox.querySelector(".counseling_status");
//
//         let now = new Date();
//         let counselingDateTime = new Date(`${counselingDate}T${String(counselingTime).padStart(2, '0')}:00:00`);
//         let oneHourBefore = new Date(counselingDateTime.getTime() - 60 * 60 * 1000);
//
//         console.log(`🕒 현재 시간: ${now}`);
//         console.log(`📅 상담 시작 시간: ${counselingDateTime}`);
//         console.log(`⏳ 상담 1시간 전: ${oneHourBefore}`);
//         console.log(`🔍 초기 상담 상태: ${statusElement.textContent}`);
//
//         // 🔥 상담 시작 1시간 이내이면 '대기' 상태로 유지
//         if (statusElement.textContent.trim() === "대기") {
//             console.log(`✅ 상담(${counselingId}) 상태 유지: '대기'`);
//             button.disabled = false;
//             button.style.opacity = "1";
//             button.style.cursor = "pointer";
//             button.style.backgroundColor = "#f7c3c3";
//
//             button.addEventListener("click", function () {
//                 console.log(`📌 상담(${counselingId}) 상세 페이지 이동`);
//                 location.href = `/livechatdetail?reservationId=${counselingId}`;
//             });
//         } else {
//             console.log(`❌ 상담(${counselingId}) 상태 변경 없음 (현재: ${statusElement.textContent})`);
//             button.disabled = true;
//             button.style.opacity = "0.5";
//             button.style.cursor = "not-allowed";
//         }
//     });
// });
document.addEventListener("DOMContentLoaded", function () {
    console.log("🚀 usermypage 페이지 로드 완료!");

    const enterButtons = document.querySelectorAll(".enter_counseling_btn");

    enterButtons.forEach(button => {
        const reservationBox = button.closest(".reserved_reservation_box");

        let counselingId = reservationBox.dataset.counselingId;
        let counselingDate = reservationBox.dataset.counselingDate;
        let counselingTime = parseInt(reservationBox.dataset.counselingTime, 10);

        console.log(`📌 상담 ID: ${counselingId}`);
        console.log(`📅 상담 날짜: ${counselingDate}`);
        console.log(`⏰ 상담 시간: ${counselingTime}`);

        if (!counselingDate || isNaN(counselingTime)) {
            console.error(`🚨 데이터 오류: 상담 날짜 또는 시간이 올바르지 않음! (counselingDate=${counselingDate}, counselingTime=${counselingTime})`);
            return;
        }

        let counselingDateTime = new Date(`${counselingDate}T${String(counselingTime).padStart(2, '0')}:00:00`);
        if (isNaN(counselingDateTime.getTime())) {
            console.error(`🚨 날짜 변환 실패: ${counselingDate} ${counselingTime}`);
            return;
        }

        console.log(`✅ 변환된 상담 시작 시간: ${counselingDateTime}`);

        let now = new Date();
        let oneHourBefore = new Date(counselingDateTime.getTime() - 60 * 60 * 1000);
        let statusElement = reservationBox.querySelector(".counseling_status");

        console.log(`🕒 현재 시간: ${now}`);
        console.log(`⏳ 상담 1시간 전: ${oneHourBefore}`);

        // ✅ 기존 버튼 제거 후, 새로운 버튼 추가할 준비
        let existingViewButton = reservationBox.querySelector(".view_counseling_btn");
        if (existingViewButton) existingViewButton.remove();

        if (now >= oneHourBefore && now < counselingDateTime) {
            console.log(`✅ 상담(${counselingId}) 상태: '대기' (입장 가능)`);
            statusElement.innerHTML = `<strong>[상담 상태] </strong>대기`;

            // ✅ 입장하기 버튼 활성화
            button.disabled = false;

            button.addEventListener("click", function () {
                console.log(`📌 상담(${counselingId}) 상세 페이지 이동`);
                location.href = `/livechatdetail?reservationId=${counselingId}`;
            });

        } else {
            console.log(`❌ 상담(${counselingId}) 상태: '완료'`);
            statusElement.innerHTML = `<strong>[상담 상태] </strong>완료`;

            // ✅ '입장하기' 버튼 숨김
            button.style.display = "none";

            // ✅ '상담 내용보기' 버튼 추가
            let viewButton = document.createElement("button");
            viewButton.classList.add("view_counseling_btn");
            viewButton.textContent = "상담 내용보기";

            viewButton.addEventListener("click", function () {
                console.log(`📌 상담(${counselingId}) 완료 - 상담 내용 보기 이동`);
                location.href = `/livechatdetail?reservationId=${counselingId}&isCompleted=true`;
            });

            reservationBox.appendChild(viewButton);
        }
    });
});
