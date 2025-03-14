    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>KOYOI - Login</title>
        <link rel="stylesheet" href="/static/css/login/login.css">
        <!-- Swiper CSS CDN -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
        <script src="/static/js/login/login.js"></script>
    </head>
    <body>

    <!-- 전체 컨테이너 -->
    <!-- 실패 여부를 data 속성으로 전달 -->
    <div class="container" data-login-failed="${loginFailed}">

        <header class="header-bar">
            <div class="header-logo-center">
                <img src="/static/imgsource/logo.png" alt="KOYOI 로고" class="header-logo">
            </div>
        </header>

        <!-- 메인 콘텐츠 영역 -->
        <main class="content">
            <!-- 로그인 폼 -->
            <div class="login-wrapper">
                    <div class="login-left">
                        <div class="swiper mySwiper">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <img src="/static/imgsource/login/test1.jpeg" alt="슬라이드1" />
                                </div>
                                <div class="swiper-slide">
                                    <img src="/static/imgsource/login/test2.jpeg" alt="슬라이드1" />
                                </div>
                                <div class="swiper-slide">
                                    <img src="/static/imgsource/login/test3.jpeg" alt="슬라이드1" />
                                </div>
                            </div>

                            <!-- 페이지네이션 -->
                            <div class="swiper-pagination"></div>

                            <!-- 이전/다음 버튼 -->
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                        </div>
                    </div>

                <div class="login-right">

                    <div class="title">KOYOI</div>
                    <p class="subtitle"> Login </p>
                    <p class="login-cont">Welcome back. Please login to your account.</p>

                    <form action="/login" method="post" class="login-form" onsubmit="return validateForm()">
                    <%--                    <form action="/login" method="post" class="login-form">--%>
                        <label for="user_id">ID</label>
                        <input type="text" id="user_id" name="user_id" placeholder="Enter your ID">
                        <span id="id-error" class="error-message"></span>

                        <label for="user_pw">Password</label>
                        <input type="password" id="user_pw" name="user_pw" placeholder="Enter your password">
                        <span id="pw-error" class="error-message"></span>

                        <button type="submit" class="login-btn">Login</button>

                        <div class="signup-option">
                            <p>Don't have an account? <a href="/signup">Sign Up</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </main>

        <!-- 파도는 content 바로 밑에 -->
        <div class="wave-container"></div>

    </div>

    <div id="loginFailModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <p> The ID or password you entered is incorrect. Please try again. </p>
        </div>
    </div>

    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
    <!-- Login JS -->
    <script src="/static/js/login/login.js"></script>

    </body>
    </html>
