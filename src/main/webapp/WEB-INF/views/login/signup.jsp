<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>KOYOI - Sign Up</title>
    <!-- CSS 연결-->
    <link rel="stylesheet" href="/static/css/login/signup.css">
    <!-- Swiper CSS Lib -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">

</head>
<body>

<!-- 전체 컨테이너 -->
<div class="container">

    <header class="header-bar">
        <div class="header-logo-center">
            <img src="/static/imgsource/layout/logo.png" alt="KOYOI 로고" class="header-logo">
        </div>
    </header>

    <!-- 메인 콘텐츠 -->
    <main class="content">
        <div class="signup-wrapper">

            <!-- 왼쪽 슬라이드 -->
            <div class="signup-left">
                <div class="swiper mySwiper">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide"><img src="/static/imgsource/login/test1.jpeg" alt="슬라이드1"/></div>
                        <div class="swiper-slide"><img src="/static/imgsource/login/test2.jpeg" alt="슬라이드2"/></div>
                        <div class="swiper-slide"><img src="/static/imgsource/login/test3.jpeg" alt="슬라이드3"/></div>
                    </div>

                    <!-- 페이지네이션 -->
                    <div class="swiper-pagination"></div>

                    <!-- 이전/다음 버튼 -->
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-button-next"></div>
                </div>
            </div>

            <!-- 오른쪽 회원가입 폼 -->
            <div class="signup-right">
                <div class="title">Sign Up</div>
                <p class="subtitle">Create your KOYOI account</p>

                <form action="/signup" method="post" enctype="multipart/form-data" class="signup-form">

                    <!-- ID -->
                    <div class="form-group">
                        <label for="user_id">ID</label>
                        <div class="check-wrapper">
                            <input type="text" id="user_id" name="user_id" placeholder="Enter your ID" required>
                            <button type="button" id="checkIdBtn" class="check-btn small">Check</button>
                        </div>
                        <div id="id-error" class="error-message"></div>
                    </div>

                    <!-- PW -->
                    <label for="user_pw">Password</label>
                    <input type="password" id="user_pw" name="user_pw" placeholder="Enter your password" required>

                    <label for="user_pw_confirm">Confirm Password</label>
                    <input type="password" id="user_pw_confirm" name="user_pw_confirm"
                           placeholder="Confirm your password" required>
                    <div id="pw-error" class="error-message"></div>

                    <!-- User Type -->
                    <div class="form-group">
                        <label>User Type</label>
                        <div class="user-type-selector">
                            <button type="button" class="user-type-btn" data-type="1">User</button>
                            <button type="button" class="user-type-btn" data-type="2">Counselor</button>
                        </div>
                        <!-- 실제 전송할 값 -->
                        <input type="hidden" name="user_type" id="user_type" value="" required>
                        <div id="user-type-error" class="error-message"></div>
                    </div>

                    <!-- Name -->
                    <div class="form-group">
                        <label for="user_name">User Name</label>
                        <input type="text" id="user_name" name="user_name" placeholder="Enter your name" required>
                        <div id="name-error" class="error-message"></div>
                    </div>

                    <!-- Nickname -->
                    <div class="form-group">
                        <label for="user_nickname">Nickname</label>
                        <div class="check-wrapper">
                            <input type="text" id="user_nickname" name="user_nickname" placeholder="Enter your nickname" required>
                            <button type="button" id="checkNicknameBtn" class="check-btn small">Check</button>
                        </div>
                        <div id="nickname-error" class="error-message"></div>
                    </div>

                    <!-- Email -->
                    <div class="form-group">
                        <label for="user_email">Email</label>
                        <div class="check-wrapper">
                            <input type="email" id="user_email" name="user_email" placeholder="Enter your email" required>
                            <button type="button" id="checkEmailBtn" class="check-btn small">Check</button>
                        </div>
                        <div id="email-error" class="error-message"></div>
                    </div>


                    <!-- Profile -->
                    <div class="form-group">
                        <label for="user_img">Profile Image</label>
                        <div class="file-upload-wrapper">
                            <input type="file" id="user_img" name="user_img" accept="image/*">
                            <!-- 썸네일 미리보기 -->
                            <div class="img-preview">
                                <img id="img-preview" src="#" alt="Image Preview" style="display: none;" />
                            </div>
                        </div>
                    </div>

                    <button type="submit" class="signup-btn">Sign Up</button>

                    <div class="login-option">
                        <p>Already have an account? <a href="/login">Login here</a></p>
                    </div>
                </form>
            </div>
        </div>
    </main>

    <!-- 파도 영역 -->
    <div class="wave-container"></div>
</div>

<!-- Swiper JS -->
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<!-- 아이디 중복체크 & 비밀번호 일치 확인 JS -->
<script src="/static/js/login/signup.js"></script>

</body>
</html>
