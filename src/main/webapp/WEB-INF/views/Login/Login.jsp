<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>KOYOI - Login</title>
    <link rel="stylesheet" href="/static/css/login/login.css">
</head>
<body>

<!-- 전체 컨테이너 -->
<div class="container">

    <!-- 헤더 영역 -->
    <header class="header-bar">
    </header>

    <!-- 메인 콘텐츠 영역 -->
    <main class="content">

        <!-- 메인 콘텐츠 안에 로그인 폼 추가 -->
        <div class="login-wrapper">
            <div class="login-left">
              <div> 이미지 슬라이드 영역 </div>
            </div>

            <div class="login-right">
                <h1 class="title">KOYOI</h1>
                <p class="subtitle">Welcome back. Please login to your account.</p>

                <form action="/login" method="post" class="login-form">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>

                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>

                    <div class="options">
                        <label class="remember-me">
                            <input type="checkbox" name="remember"> Remember me
                        </label>
                        <a href="#" class="forgot-password">Forgot password?</a>
                    </div>

                    <button type="submit" class="login-btn">Login</button>

                    <div class="signup-option">
                        <p>Don't have an account? <a href="/signup">Sign Up</a></p>
                    </div>

                    <div class="social-login">
                        <p>Or, login with</p>
                        <div class="social-icons">
                            <a href="#" class="facebook">Facebook</a>
                            <a href="#" class="linkedin">LinkedIn</a>
                            <a href="#" class="google">Google</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </main>

    <!-- 삐약 이미지 -->
    <div class="bbiyak">
        <img src="/static/imgsource/bbiyak.png" alt="bbiyak">
    </div>

    <!-- 파도 효과 -->
    <div class="wave-container"></div>

</div>

</body>
</html>
