

document.addEventListener("DOMContentLoaded", function () {
    let today = new Date().toISOString().split("T")[0];
    document.getElementById("livechat_reserve_date").setAttribute("min", today);
});

// 기존 showNext 함수 유지
function showNext(nextStep) {
    let nextElement = document.getElementById(nextStep);
    if (nextElement) {
        nextElement.classList.add("show");
    } else {
        console.error("Error: Element with ID '" + nextStep + "' not found.");
    }
}

// 날짜 및 시간 검증 함수
function validateDateTime() {
    let selectedDate = document.getElementById("livechat_reserve_date").value;
    let now = new Date();
    let selectedDateTime = new Date(selectedDate + "T00:00:00");

    if (selectedDateTime < now.setHours(0, 0, 0, 0)) {
        alert("本日以前の日付は選択できません！");
        document.getElementById("livechat_reserve_date").value = "";
        return;
    }

    updateAvailableTimes(selectedDate);
}

// 선택 날짜에 따라 사용 가능한 시간 동적 변경
function updateAvailableTimes(selectedDate) {
    let now = new Date();
    let selectedDateTime = new Date(selectedDate + "T00:00:00");

    let timeOptions = document.querySelectorAll("#livechat_reserve_time option");
    timeOptions.forEach(option => {
        let optionTime = new Date(selectedDate + "T" + option.value + ":00");

        if (selectedDateTime.toDateString() === now.toDateString() && optionTime < now) {
            option.disabled = true;
        } else {
            option.disabled = false;
        }
    });
}

// 날짜 선택 시 이벤트 추가
document.getElementById("livechat_reserve_date").addEventListener("change", validateDateTime);

document.addEventListener("DOMContentLoaded", function () {
    let today = new Date().toISOString().split("T")[0];
    document.getElementById("livechat_reserve_date").setAttribute("min", today);
});

// 상담 예약 버튼 클릭 시 처리
document.getElementById("livechat_reserve_btn").addEventListener("click", function () {
    let reserveBtn = document.getElementById("livechat_reserve_btn");
    let confirmationText = document.getElementById("conformation_text");
    let exitBtn = document.getElementById("livechat_exit_btn");
    let conformationDiv = document.getElementById("conformation");

    let selectedDate = document.getElementById("livechat_reserve_date").value;
    let selectedTime = document.getElementById("livechat_reserve_time").value;
    let selectedCategory = document.getElementById("livechat_reserve_category").value;

    if (selectedCategory === "sonota") {
        selectedCategory = "その他のお悩み";
    }

    if (!selectedDate || !selectedTime || !selectedCategory) {
        alert("日付・時間・カテゴリーをすべて選択してください。");
        return;
    }

    console.log("[client] 予約リクエスト:", {
        livechatreservedate: selectedDate,
        livechatreservetime: selectedTime,
        livechatcategory: selectedCategory
    });

    fetch("/livechatreservation", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            livechatreservedate: selectedDate,
            livechatreservetime: selectedTime,
            livechatcategory: selectedCategory
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log("[client] サーバーからの応答データ:", data);
            if (data.success) {
                alert("相談の予約が完了しました！");

                sessionStorage.setItem("counseling_id", data.counseling_id);
                console.log("[セッション保存] counseling_id:", data.counseling_id);

                reserveBtn.style.display = "none";

                conformationDiv.style.display = "flex";
                setTimeout(() => { conformationDiv.style.opacity = "1"; }, 200);

                confirmationText.style.display = "block";
                setTimeout(() => { confirmationText.style.opacity = "1"; }, 400);

                setTimeout(() => {
                    exitBtn.style.display = "block";
                    setTimeout(() => { exitBtn.style.opacity = "1"; }, 200);
                }, 1500);
            } else {
                alert("予約に失敗しました：" + data.message);
            }
        })
        .catch(error => {
            console.error("予約処理中にエラーが発生しました:", error);
            alert(" 予約リクエスト中にエラーが発生しました。");
        });
});

// 나가기 버튼 클릭 시 상담 상태를 '대기'로 업데이트 후 usermypage 이동
document.getElementById("livechat_exit_btn").addEventListener("click", function () {
    let counselingId = sessionStorage.getItem("counseling_id");

    if (!counselingId) {
        alert("相談IDが見つかりません。");
        return;
    }

    console.log("[client] 相談IDの確認:", counselingId);

    fetch("/livechat/updateStatus", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            counseling_id: parseInt(counselingId, 10),
            status: "待機中"
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log("[client] 相談ステータス更新の応答:", data);

            if (data.success) {
                alert("相談ステータスが「待機」に設定されました。");
                sessionStorage.removeItem("counseling_id");
                window.location.href = "/usermypage"; //
            }
            else {
            alert("ステータスの更新中にエラーが発生しました。");
            }
        })
        .catch(error => {
            console.error("相談ステータスの更新中にエラーが発生しました:", error);
            alert(" サーバーへのリクエストでエラーが発生しました。");
        });
});
