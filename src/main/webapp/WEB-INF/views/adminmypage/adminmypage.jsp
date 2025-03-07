<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>KOYOI</title>
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/adminmypage/adminmypage.css">
</head>
<body>

<div class="container">

    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn"><img src="/static/imgsource/user.png" alt="user"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/counselor.png" alt="counselor"></button>
            </nav>
        </aside>
    </div>

    <div class="right-container">
        <header class="header-bar">
            <div class="brand-title">
                <img src="/static/imgsource/logo.png" alt="KOYOI">
            </div>

            <div class="header-icons">
                <button class="header-btn">
                    <img src="/static/imgsource/logout.png" alt="logout">
                </button>
                <img class="profile-img" src="/static/imgsource/testprofile.png" alt="profile">
            </div>
        </header>

        <main class="content">
            유저 목록 조회 게시판 만들면 됩니다.
        </main>
    </div>

</div>


</body>
</html>