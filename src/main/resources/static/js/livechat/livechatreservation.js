

document.addEventListener("DOMContentLoaded", function () {
    let today = new Date().toISOString().split("T")[0];
    document.getElementById("livechat_reserve_date").setAttribute("min", today);
});

// ✅ 기존 showNext 함수 유지
function showNext(nextStep) {
    let nextElement = document.getElementById(nextStep);
    if (nextElement) {
        nextElement.classList.add("show");
    } else {
        console.error("Error: Element with ID '" + nextStep + "' not found.");
    }
}

// ✅ 날짜 및 시간 검증 함수
function validateDateTime() {
    let selectedDate = document.getElementById("livechat_reserve_date").value;
    let now = new Date();
    let selectedDateTime = new Date(selectedDate + "T00:00:00");

    if (selectedDateTime < now.setHours(0, 0, 0, 0)) {
        alert("현재 날짜보다 이전 날짜는 선택할 수 없습니다!");
        document.getElementById("livechat_reserve_date").value = "";
        return;
    }

    updateAvailableTimes(selectedDate);
}

// ✅ 선택 날짜에 따라 사용 가능한 시간 동적 변경
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

// ✅ 날짜 선택 시 이벤트 추가
document.getElementById("livechat_reserve_date").addEventListener("change", validateDateTime);
//
// document.getElementById("livechat_reserve_btn").addEventListener("click", function () {
//     let reserveBtn = document.getElementById("livechat_reserve_btn");
//     let confirmationText = document.getElementById("conformation_text");
//     let exitBtn = document.getElementById("livechat_exit_btn");
//     let conformationDiv = document.getElementById("conformation");
//
//     fetch("/livechatreservation", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify({
//             livechatreservedate: document.getElementById("livechat_reserve_date").value,
//             livechatreservetime: document.getElementById("livechat_reserve_time").value,
//             livechatcategory: document.getElementById("livechat_reserve_category").value
//         })
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log("📌 [클라이언트] 서버 응답 데이터:", data);
//             if (data.success) {
//                 alert("예약이 완료되었습니다!");
//
//                 // ✅ 1. 예약 버튼 숨기기
//                 reserveBtn.style.display = "none";
//
//                 // ✅ 2. 컨포메이션 영역 자체를 보여줌
//                 conformationDiv.style.display = "flex";
//                 conformationDiv.style.opacity = "0";
//                 conformationDiv.style.visibility = "visible";
//                 conformationDiv.style.transition = "opacity 1s ease-in-out, visibility 1s ease-in-out";
//
//                 setTimeout(() => {
//                     conformationDiv.style.opacity = "1";
//                 }, 200);
//
//                 // ✅ 3. 상담사 배정 메시지 표시
//                 confirmationText.style.display = "block";
//                 confirmationText.style.opacity = "0";
//                 confirmationText.style.visibility = "visible";
//                 confirmationText.style.transition = "opacity 1s ease-in-out, visibility 1s ease-in-out";
//
//                 setTimeout(() => {
//                     confirmationText.style.opacity = "1";
//                 }, 400);
//
//                 // ✅ 4. 나가기 버튼도 1.5초 후 표시
//                 setTimeout(() => {
//                     exitBtn.style.display = "block";
//                     exitBtn.style.opacity = "0";
//                     exitBtn.style.visibility = "visible";
//                     exitBtn.style.transition = "opacity 1s ease-in-out, visibility 1s ease-in-out";
//
//                     setTimeout(() => {
//                         exitBtn.style.opacity = "1";
//                     }, 200);
//                 }, 1500);
//             } else {
//                 alert("예약 실패: " + data.message);
//             }
//         })
//         .catch(error => console.error("🚨 예약 중 오류 발생:", error));
// });
//
// document.getElementById("livechat_reserve_btn").addEventListener("click", function () {
//     let reserveBtn = document.getElementById("livechat_reserve_btn");
//     let confirmationText = document.getElementById("conformation_text");
//     let exitBtn = document.getElementById("livechat_exit_btn");
//     let conformationDiv = document.getElementById("conformation");
//
//     fetch("/livechatreservation", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify({
//             livechatreservedate: document.getElementById("livechat_reserve_date").value,
//             livechatreservetime: document.getElementById("livechat_reserve_time").value,
//             livechatcategory: document.getElementById("livechat_reserve_category").value
//         })
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log("📌 [클라이언트] 서버 응답 데이터:", data);
//             if (data.success) {
//                 alert("예약이 완료되었습니다!");
//
//                 // ✅ 1. 상담 ID를 sessionStorage에 저장 (나가기 버튼 클릭 시 사용)
//                 sessionStorage.setItem("counseling_id", data.counseling_id);
//
//                 // ✅ 2. 예약 버튼 숨기기
//                 reserveBtn.style.display = "none";
//
//                 // ✅ 3. 컨포메이션 영역 자체를 보여줌
//                 conformationDiv.style.display = "flex";
//                 conformationDiv.style.opacity = "0";
//                 conformationDiv.style.visibility = "visible";
//                 conformationDiv.style.transition = "opacity 1s ease-in-out, visibility 1s ease-in-out";
//
//                 setTimeout(() => {
//                     conformationDiv.style.opacity = "1";
//                 }, 200);
//
//                 // ✅ 4. 상담사 배정 메시지 표시
//                 confirmationText.style.display = "block";
//                 confirmationText.style.opacity = "0";
//                 confirmationText.style.visibility = "visible";
//                 confirmationText.style.transition = "opacity 1s ease-in-out, visibility 1s ease-in-out";
//
//                 setTimeout(() => {
//                     confirmationText.style.opacity = "1";
//                 }, 400);
//
//                 // ✅ 5. 나가기 버튼도 1.5초 후 표시
//                 setTimeout(() => {
//                     exitBtn.style.display = "block";
//                     exitBtn.style.opacity = "0";
//                     exitBtn.style.visibility = "visible";
//                     exitBtn.style.transition = "opacity 1s ease-in-out, visibility 1s ease-in-out";
//
//                     setTimeout(() => {
//                         exitBtn.style.opacity = "1";
//                     }, 200);
//                 }, 1500);
//             } else {
//                 alert("예약 실패: " + data.message);
//             }
//         })
//         .catch(error => console.error("🚨 예약 중 오류 발생:", error));
// });
document.addEventListener("DOMContentLoaded", function () {
    let today = new Date().toISOString().split("T")[0];
    document.getElementById("livechat_reserve_date").setAttribute("min", today);
});

// ✅ 상담 예약 버튼 클릭 시 처리
document.getElementById("livechat_reserve_btn").addEventListener("click", function () {
    let reserveBtn = document.getElementById("livechat_reserve_btn");
    let confirmationText = document.getElementById("conformation_text");
    let exitBtn = document.getElementById("livechat_exit_btn");
    let conformationDiv = document.getElementById("conformation");

    let selectedDate = document.getElementById("livechat_reserve_date").value;
    let selectedTime = document.getElementById("livechat_reserve_time").value;
    let selectedCategory = document.getElementById("livechat_reserve_category").value;

    if (!selectedDate || !selectedTime || !selectedCategory) {
        alert("❌ 날짜, 시간, 카테고리를 모두 선택해주세요.");
        return;
    }

    console.log("📌 [클라이언트] 예약 요청:", {
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
            console.log("📌 [클라이언트] 서버 응답 데이터:", data);
            if (data.success) {
                alert("✅ 상담 예약이 완료되었습니다!");

                // ✅ 1. 상담 ID를 sessionStorage에 저장
                sessionStorage.setItem("counseling_id", data.counseling_id);
                console.log("✅ [세션 저장] counseling_id:", data.counseling_id);

                // ✅ 2. 예약 버튼 숨기기
                reserveBtn.style.display = "none";

                // ✅ 3. 컨포메이션 메시지 표시
                conformationDiv.style.display = "flex";
                setTimeout(() => { conformationDiv.style.opacity = "1"; }, 200);

                confirmationText.style.display = "block";
                setTimeout(() => { confirmationText.style.opacity = "1"; }, 400);

                // ✅ 4. 나가기 버튼 1.5초 후 활성화
                setTimeout(() => {
                    exitBtn.style.display = "block";
                    setTimeout(() => { exitBtn.style.opacity = "1"; }, 200);
                }, 1500);
            } else {
                alert("❌ 예약 실패: " + data.message);
            }
        })
        .catch(error => {
            console.error("🚨 예약 중 오류 발생:", error);
            alert("❌ 예약 요청 중 오류가 발생했습니다.");
        });
});

// ✅ 나가기 버튼 클릭 시 상담 상태를 '대기'로 업데이트 후 usermypage 이동
document.getElementById("livechat_exit_btn").addEventListener("click", function () {
    let counselingId = sessionStorage.getItem("counseling_id");

    if (!counselingId) {
        alert("❌ 상담 ID를 찾을 수 없습니다.");
        return;
    }

    console.log("📌 [클라이언트] 상담 ID 확인:", counselingId);

    fetch("/livechat/updateStatus", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            counseling_id: parseInt(counselingId, 10),
            status: "대기" // ✅ 상담 상태를 '대기'로 변경
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log("📌 [클라이언트] 상담 상태 업데이트 응답:", data);

            if (data.success) {
                alert("✅ 상담 상태가 '대기'로 설정되었습니다.");
                sessionStorage.removeItem("counseling_id"); // ✅ 세션에서 삭제
                window.location.href = "/usermypage"; // ✅ 마이페이지로 이동
            } else {
                alert("❌ 상담 상태 업데이트 중 오류가 발생했습니다.");
            }
        })
        .catch(error => {
            console.error("🚨 상담 상태 업데이트 중 오류 발생:", error);
            alert("❌ 서버 요청 오류 발생.");
        });
});

//
// // ✅ 나가기 버튼 클릭 시 상담 상태 업데이트 후 이동
// document.getElementById("livechat_exit_btn").addEventListener("click", function () {
//     const counselingId = sessionStorage.getItem("counseling_id");
//
//     if (!counselingId) {
//         alert("예약 정보를 찾을 수 없습니다.");
//         return;
//     }
//
//     fetch("/livechat/updateStatus", {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json",
//         },
//         body: JSON.stringify({
//             counseling_id: counselingId,
//             status: "취소됨",
//         }),
//     })
//         .then(response => response.json())
//         .then(data => {
//             if (data.success) {
//                 alert("예약이 취소되었습니다.");
//                 window.location.href = "/usermypage";
//             } else {
//                 alert("예약 취소 중 오류가 발생했습니다.");
//             }
//         })
//         .catch(error => console.error("Error:", error));
// });
document.getElementById("livechat_exit_btn").addEventListener("click", function () {
    let counselingId = sessionStorage.getItem("counseling_id");

    if (!counselingId) {
        alert("❌ 상담 ID를 찾을 수 없습니다.");
        return;
    }

    fetch("/livechat/updateStatus", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            counseling_id: parseInt(counselingId, 10),
            status: "대기", // ✅ 상담 상태를 "대기"로 변경
        }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("✅ 상담이 '대기' 상태로 설정되었습니다.");
                sessionStorage.removeItem("counseling_id"); // ✅ 세션에서 삭제
                window.location.href = "/usermypage"; // ✅ 마이페이지로 이동
            } else {
                alert("❌ 상담 상태 업데이트 중 오류가 발생했습니다.");
            }
        })
        .catch(error => {
            console.error("🚨 서버 요청 중 오류 발생:", error);
            alert("❌ 서버 요청 오류 발생.");
        });
});
