<%@ page import="com.koyoi.main.vo.AdminMypageVO" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    AdminMypageVO user = (AdminMypageVO) request.getAttribute("user");
    String imgPath = (user != null && user.getUser_img() != null)
            ? user.getUser_img()
            : "/static/imgsource/testprofile.png"; // 기본 이미지
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>KOYOI</title>
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="/static/css/announcement/announcement.css">
    <script src="/static/js/adminmypage/adminmypage.js"></script>
</head>
<body>

<div class="container">

    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn" id="user">
                    <img src="/static/imgsource/user.png" alt="user">
                </button>
                <button class="sidebar-btn" id="counselor">
                    <img src="/static/imgsource/counselor.png" alt="counselor">
                </button>
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
                    <a href="/logout"> <img src="/static/imgsource/logout.png" alt="logout"> </a>
                </button>
                <img class="profile-img" src="/static<%=imgPath%>" alt="profile">
            </div>
        </header>

        <main class="content">
            <div class="announcement-view">
                <div class="title">${announcement.title}</div>
                <div class="meta">
                    작성자: ${announcement.admin_id} | 작성일: ${announcement.created_at}
                </div>
                <div class="content">${announcement.content}</div>
            </div>
        </main>
    </div>
</div>


</body>
</html>