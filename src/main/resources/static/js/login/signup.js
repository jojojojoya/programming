// 전역 변수로 선언 (모든 함수에서 접근 가능)
let pwInput, pwConfirmInput, pwError;
let userImgInput, imgPreview;

document.addEventListener("DOMContentLoaded", function () {

    // Swiper 초기화
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

    // 패스워드 인풋 초기화
    pwInput = document.getElementById("user_pw");
    pwConfirmInput = document.getElementById("user_pw_confirm");
    pwError = document.getElementById("pw-error");

    // 패스워드 실시간 검증 연결
    pwInput.addEventListener("input", checkPasswordMatch);
    pwConfirmInput.addEventListener("input", checkPasswordMatch);

    // 중복 체크 버튼 이벤트 연결
    document.getElementById("checkIdBtn").addEventListener("click", function () {
        checkDuplicate("id", document.getElementById("user_id").value);
    });

    document.getElementById("checkNameBtn").addEventListener("click", function () {
        checkDuplicate("name", document.getElementById("user_name").value);
    });

    document.getElementById("checkNicknameBtn").addEventListener("click", function () {
        checkDuplicate("nickname", document.getElementById("user_nickname").value);
    });

    // 이미지 업로드 초기화
    userImgInput = document.getElementById("user_img");
    imgPreview = document.getElementById("img-preview");

    // 이미지 업로드 및 미리보기
    userImgInput.addEventListener("change", handleImagePreview);

    // 폼 제출 시 검증
    const signupForm = document.querySelector('.signup-form');
    signupForm.addEventListener('submit', function (event) {
        if (!validateSignupForm()) {
            event.preventDefault(); // 검증 실패 시 폼 전송 막기
        }
    });
});


// 중복 체크 함수 (id, name, nickname)
function checkDuplicate(type, value) {

    if (!value.trim()) {
        console.log(`값 없음! ${type}: `, value);
        return;
    }

    fetch(`/check?type=${type}&value=${encodeURIComponent(value)}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                alert(`This ${type} is already in use.`);
            } else {
                alert(`This ${type} is available!`);
            }
        })
        .catch(error => {
            console.error(`Error checking ${type}:`, error);
            alert(`Failed to check ${type}.`);
        });
}


// 패스워드 실시간 검증 함수
function checkPasswordMatch() {
    const pw = pwInput.value.trim();
    const pwConfirm = pwConfirmInput.value.trim();

    if (!pw || !pwConfirm) {
        pwError.textContent = "";
        return;
    }

    if (pw !== pwConfirm) {
        pwError.textContent = "Passwords do not match.";
        pwError.style.color = "red";
    } else {
        pwError.textContent = "Passwords match!";
        pwError.style.color = "green";
    }
}


// 이미지 업로드 및 미리보기 함수
function handleImagePreview(event) {
    const file = event.target.files[0];

    if (!file) {
        imgPreview.src = "#";
        imgPreview.style.display = "none";
        return;
    }

    // 파일 크기 제한 (5MB)
    if (file.size > 5 * 1024 * 1024) {
        alert("File size must be less than 5MB");
        userImgInput.value = ""; // 파일 초기화
        imgPreview.src = "#";
        imgPreview.style.display = "none";
        return;
    }

    const reader = new FileReader();

    reader.onload = function (e) {
        imgPreview.src = e.target.result;
        imgPreview.style.display = "block";
    };

    reader.readAsDataURL(file);
}


// 폼 최종 검증 함수
function validateSignupForm() {
    let isValid = true;

    const userId = document.getElementById("user_id").value.trim();
    const userPw = pwInput.value.trim();
    const userPwConfirm = pwConfirmInput.value.trim();
    const userName = document.getElementById("user_name").value.trim();
    const userNickname = document.getElementById("user_nickname").value.trim();
    const userEmail = document.getElementById("user_email").value.trim();

    // 에러 초기화
    document.getElementById("id-error").textContent = "";
    pwError.textContent = "";
    document.getElementById("pw-confirm-error").textContent = "";
    document.getElementById("name-error").textContent = "";
    document.getElementById("nickname-error").textContent = "";
    document.getElementById("email-error").textContent = "";

    // 필수값 검증
    if (!userId) {
        document.getElementById("id-error").textContent = "Please enter your ID.";
        isValid = false;
    }

    if (!userPw) {
        pwError.textContent = "Please enter your password.";
        pwError.style.color = "red";
        isValid = false;
    }

    if (userPw !== userPwConfirm) {
        pwError.textContent = "Passwords do not match.";
        pwError.style.color = "red";
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

    // 이미지 파일 유효성 검사 (옵션)
    const file = userImgInput.files[0];
    if (file && file.size > 5 * 1024 * 1024) {
        alert("File size must be less than 5MB");
        isValid = false;
    }

    return isValid;
}
