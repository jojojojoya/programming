<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">

    <title> KOYOI </title>
    <link rel="stylesheet" href="/static/css/sidebar.css">
</head>
<body>

<!-- 전체 컨테이너 -->
<div class="container">
    <header class="header-bar">
        <h1 class="logo">
            <img class="logo-icon" src="/static/imgsource/layout/logo.png" alt="로고 아이콘">

        </h1>
        <!-- 우측 상단 헤더 아이콘 컨테이너 -->
        <div class="header-icons">
            <button class="header-btn">
                <img src="/static/imgsource/layout/chat.png" alt="채팅">
            </button>
            <button class="header-btn">
                <img src="/static/imgsource/layout/settingss.png" alt="설정">
            </button>
            <button class="header-btn">
                <img src="/static/imgsource/layout/logout.png" alt="로그아웃">
            </button>
            <img class="profile-img" src="/static/imgsource/layout/testprofile.png" alt="프로필">
        </div>

    </header>

    <!-- 메인 콘텐츠 영역 -->
    <main class="content">
        <p>메인 콘텐츠 영역</p>
    </main>
    <div class="bbiyak">
        <img src="/static/imgsource/layout/layout/bbiyak.png">
    </div>
    <!-- 파도 추가 -->
    <div class="wave-container"></div>
    <!-- 사이드바 추가 -->
    <aside class="sidebar">
        <nav class="sidebar-menu">
            <button class="sidebar-btn"><img src="/static/imgsource/smile.png" alt="프로필"></button>
            <button class="sidebar-btn"><img src="/static/imgsource/calander.png" alt="캘린더"></button>
            <button class="sidebar-btn"><img src="/static/imgsource/layout/pencil.png" alt="메모"></button>
            <button class="sidebar-btn"><img src="/static/imgsource/layout/chat.png" alt="채팅"></button>
            <button class="sidebar-btn"><img src="/static/imgsource/layout/settingss.png" alt="설정"></button>
        </nav>
    </aside>


</div>

</body>
</html>
