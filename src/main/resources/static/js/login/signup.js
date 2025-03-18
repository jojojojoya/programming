document.addEventListener("DOMContentLoaded", function () {
    // ✅ Swiper 초기화
    const swiper = new Swiper(".mySwiper", {
        loop: true,
        autoplay: {
            delay: 2500,
            disableOnInteraction: false
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev"
        },
        effect: 'fade',
        speed: 1000
    });

    // ✅ 폼 제출 시 검증 연결
    const signupForm = document.querySelector('.signup-form');
    signupForm.addEventListener('submit', function (event) {
        if (!validateSignupForm()) {
            event.preventDefault(); // 검증 실패 시 폼 전송 막기
        }
    });

    // ✅ ID 중복 체크 버튼 연결
    const checkIdBtn = document.getElementById("checkIdBtn");
    checkIdBtn.addEventListener("click", checkDuplicateId);
});

// ✅ 회원가입 폼 검증
function validateSignupForm() {
    let isValid = true;

    const userId = document.getElementById("user_id").value.trim();
    const userPw = document.getElementById("user_pw").value.trim();
    const userPwConfirm = document.getElementById("user_pw_confirm").value.trim();
    const userName = document.getElementById("user_name").value.trim();
    const userNickname = document.getElementById("user_nickname").value.trim();
    const userEmail = document.getElementById("user_email").value.trim();

    // 에러 요소 초기화
    document.getElementById("id-error").textContent = "";
    document.getElementById("pw-error").textContent = "";
    document.getElementById("pw-confirm-error").textContent = "";
    document.getElementById("name-error").textContent = "";
    document.getElementById("nickname-error").textContent = "";
    document.getElementById("email-error").textContent = "";

    // ✅ 검증 로직
    if (!userId) {
        document.getElementById("id-error").textContent = "Please enter your ID.";
        isValid = false;
    }

    if (!userPw) {
        document.getElementById("pw-error").textContent = "Please enter your password.";
        isValid = false;
    }

    if (userPw !== userPwConfirm) {
        document.getElementById("pw-confirm-error").textContent = "Passwords do not match.";
        isValid = false;
    }

    if (!userName) {
        document.getElementById("name-error").textContent = "Please enter your name.";
        isValid = false;
    }

    if (!userNickname) {
        document.getElementById("nickname-error").textContent = "Please enter your nickname.";
        isValid = false;
    }

    if (!userEmail) {
        document.getElementById("email-error").textContent = "Please enter your email.";
        isValid = false;
    }

    return isValid; // true면 폼 전송, false면 전송 막힘
}

// ✅ ID 중복 체크 (Ajax로 변경 예정)
function checkDuplicateId() {
    const userId = document.getElementById("user_id").value.trim();

    if (!userId) {
        alert("Please enter an ID to check.");
        return;
    }

    // ⭐ 서버와 통신할 경우엔 여기에 Ajax 추가!
    // ⭐ 지금은 임시 alert!
    alert("This ID is available.");
}
