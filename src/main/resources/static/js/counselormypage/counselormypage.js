document.addEventListener("DOMContentLoaded", function () {
    mypageLoad();
    reservationHandler();
});


function reservationHandler() {
    console.log("🚀 counselormypage ページのロードが完了しました！");
    function reservationHandler() {
        document.querySelectorAll(".enter_counseling_btn").forEach(button => {
            button.addEventListener("click", () => {
                const sessionId = button.dataset.sessionId;
                const counselingId = button.dataset.counselingId;
                const isCounselor = button.dataset.isCounselor === "true"; // 상담사 여부

                // 상담사일 경우 → 상담사용 채팅 인터페이스 열기
                if (isCounselor) {
                    startCounselorChat(sessionId, counselingId);
                } else {
                    goToLiveChatDetail(sessionId, counselingId, false); // 유저용
                }
            });
        });
    }

    function startCounselorChat(sessionId, counselingId) {
        const url = `/livechatdetail?sessionId=${sessionId}&counselingId=${counselingId}&isCounselor=true`;
        console.log("상담사 채팅방으로 이동:", url);
        window.location.href = url;
    }
    document.querySelectorAll(".reserved_reservation_box").forEach(reservationBox => {
        let counselingId = reservationBox.dataset.counselingId;
        let sessionId = reservationBox.dataset.sessionId;
        let status = reservationBox.dataset.status;
        let counselingDate = reservationBox.dataset.counselingDate;
        let counselingTime = parseInt(reservationBox.dataset.counselingTime); 

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
        let isWithinOneHour = (counselingDate === currentDate) && (counselingTime - currentHour <= 1) && (counselingTime - currentHour >= 0);
    });
}


function goToLiveChatDetail(sessionId, counselingId, isCompleted) {
    let url = `/livechatdetail?sessionId=${sessionId}&counselingId=${counselingId}&isCompleted=${isCompleted}`;
    console.log("📌 遷移先のURL:", url);
    window.location.href = url;
}


function mypageLoad() {
    console.log("🚀 ページのロードが完了しました！");

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
    const chatbotDetailModal = document.getElementById("chatbotDetailModal");
    const chatbotList = document.querySelectorAll(".chatbot_list");

    let userId = document.getElementById("hiddenUserId").value || "user5";
    console.log("🔍 現在のuser_id:", userId);

    openPasswordCheckModal.addEventListener("click", function () {
        console.log("🔓 プロフィール編集ボタンがクリックされました！");
        passwordCheckModal.style.display = "block";
    });

    const chatbotListItems = document.querySelectorAll(".chatbot_list");

    chatbotListItems.forEach(item => {
        item.addEventListener("click", function () {
            console.log("チャットボットリストがクリックされました");

            // 타이틀
            const summaryTitle = this.textContent;
            document.querySelector(".chatbot-detail-title").textContent = summaryTitle;
            // 내용
            const summaryText = this.textContent;
            document.querySelector(".chatbot-detail-text").textContent = summaryText;

            // 모달 열기
            chatbotDetailModal.style.display = "block";
        });
    });


    checkPasswordBtn.addEventListener("click", function () {
        const enteredPassword = passwordCheckInput.value;

        if (!enteredPassword) {
            console.error("🚨[エラー] パスワードが入力されていません！");
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
                    console.log("✅パスワード確認成功！プロフィール編集モーダルを開きます");
                    passwordCheckModal.style.display = "none";
                    profileModal.style.display = "block";
                    editIdInput.value = userId; // 아이디 유지
                } else {
                    console.warn("⚠️ パスワードが一致しません!");
                    passwordErrorMsg.style.display = "block";
                }
            })
            .catch(error => console.error("🚨APIリクエストエラー:", error));
    })



        document.getElementById("editProfileImg").addEventListener("change", function (event) {
            const file = event.target.files[0];
            const fileNameDisplay = document.getElementById("fileNameDisplay");
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    document.querySelector("#profileModal .profile_img img").src = e.target.result;
                };
                reader.readAsDataURL(file);

                fileNameDisplay.textContent = file.name;
            } else {
                fileNameDisplay.textContent = "選択されたファイルはありません";
            }
        });

        // 모달 닫기 이벤트도 '밖에'
        document.querySelectorAll(".close").forEach(button => {
            button.addEventListener("click", function () {
                passwordCheckModal.style.display = "none";
                chatbotDetailModal.style.display = "none";
                profileModal.style.display = "none";
                passwordErrorMsg.style.display = "none";
                passwordCheckInput.value = "";
            });
        });

        saveProfileBtn.addEventListener("click", function () {
            const newNickname = editNicknameInput.value.trim();
            const newPassword = editPwInput.value.trim();
            const profileImgFile = document.getElementById("editProfileImg").files[0];

            if (!newNickname) {
                alert("ニックネームを入力してください。");
                return;
            }

            fetch("/checkNicknameDuplicate", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ nickname: newNickname, user_id: userId }) // userId 추가
            })


                .then(res => res.json())
                .then(data => {
                    if (data.duplicate) {
                        alert("❌  そのニックネームは既に使われています。別のニックネームを入力してください。");
                    } else {
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
                                    alert("✅ プロフィールが正常に更新されました！");
                                    if (data.newImgPath) {
                                        document.querySelector(".profile_img img").src = data.newImgPath;
                                    }
                                    document.getElementById("nicknameDisplay").innerText = `ニックネーム: ${newNickname}`;
                                    profileModal.style.display = "none";
                                } else {
                                    alert("❌ プロフィールの更新に失敗しました。");
                                }
                            })
                            .catch(error => console.error("🚨 プロフィール更新エラー:", error));
                    }
                });
        });
    }