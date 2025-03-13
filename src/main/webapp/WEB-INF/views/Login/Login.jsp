    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>KOYOI - Login</title>
        <link rel="stylesheet" href="/static/css/login/login.css">
        <!-- Swiper CSS CDN -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
    </head>
    <body>

    <!-- 전체 컨테이너 -->
    <div class="container">

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

                            <!-- 페이지네이션/버튼 원하면 추가 -->
                            <div class="swiper-pagination"></div>
                        </div>
                    </div>

                <div class="login-right">
                    <div class="title">KOYOI</div>
                    <p class="subtitle"> Login </p>
                    <p class="login-cont">Welcome back. Please login to your account.</p>

                    <form action="/login" method="post" class="login-form">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" placeholder="Enter your email" required>

                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" placeholder="Enter your password" required>

                        <button type="submit" class="login-btn">Login</button>

                        <div class="signup-option">
                            <p>Don't have an account? <a href="/signup">Sign Up</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </main>

        <!-- ✅ 파도는 content 바로 밑에 -->
        <div class="wave-container"></div>

    </div>
    <script>
        var swiper = new Swiper(".mySwiper", {
            loop: true,
            autoplay: {
                delay: 2500,
                disableOnInteraction: false,
            },
            pagination: {
                el: ".swiper-pagination",
                clickable: true,
            },
            effect: 'fade', // 부드러운 페이드 효과 추천!
        });
    </script>
    </body>
    </html>
