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
    <link rel="stylesheet" href="/static/css/adminmypage/adminmypage.css">
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
                    <img src="/static/imgsource/logout.png" alt="logout">
                </button>
                <img class="profile-img" src="<%=imgPath%>" alt="profile">
            </div>
        </header>

        <main class="content">
            <h2 id="table-title" class="table-title"> Member List </h2>
            <p id="memberTypeLabel" class="sub-title"> Users </p>

            <%-- 회원 목록 --%>
            <div id="userTable" class="user-board">
                <div class="user-board-header">
                    <div class="col col-num"> No </div>
                    <div class="col col-id"> ID </div>
                    <div class="col col-name"> Name </div>
                    <div class="col col-nickname"> Nickname </div>
                    <div class="col col-email"> Email </div>
                    <div class="col col-date"> Joined </div>
                </div>
                <c:set var="totalUsers" value="${fn:length(users)}" />
                <c:forEach var="user" items="${users}" varStatus="status">
                    <div class="user-row user-detail-btn" data-user-id="${user.user_id}">
                        <div class="cell col-num">${totalUsers - status.index}</div>
                        <div class="cell col-id">${user.user_id}</div>
                        <div class="cell col-name">${user.user_name}</div>
                        <div class="cell col-nickname">${user.user_nickname}</div>
                        <div class="cell col-email">${user.user_email}</div>
                        <div class="cell col-date">${user.formattedCreatedAt}</div>
                    </div>
                </c:forEach>
            </div>

            <%-- 상담사 목록 --%>
            <div id="counselorTable" class="user-board" style="display: none;">
                <div class="user-board-header">
                    <div class="col col-num"> No </div>
                    <div class="col col-id"> ID </div>
                    <div class="col col-name"> Name </div>
                    <div class="col col-nickname"> Nickname </div>
                    <div class="col col-email"> Email </div>
                    <div class="col col-date"> Joined </div>
                </div>
                <c:set var="totalCounselors" value="${fn:length(counselors)}" />
                <c:forEach var="counselor" items="${counselors}" varStatus="status">
                    <div class="user-row user-detail-btn" data-user-id="${counselor.user_id}" data-type="counselor">
                        <div class="cell col-num">${totalCounselors - status.index}</div>
                        <div class="cell col-id">${counselor.user_id}</div>
                        <div class="cell col-name">${counselor.user_name}</div>
                        <div class="cell col-nickname">${counselor.user_nickname}</div>
                        <div class="cell col-email">${counselor.user_email}</div>
                        <div class="cell col-date">${counselor.formattedCreatedAt}</div>
                    </div>
                </c:forEach>
            </div>

            <%-- 상세 데이터 모달 --%>
            <div id="userDetailModal" class="modal" style="display: none;">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <h2 id="modalTitle" class="modalTitle"> Details </h2>

                    <div class="profile-container">
                        <img id="modalUserImg" alt="profile">
                    </div>

                    <div class="user-info-grid scrollable">
                        <div class="field">
                            <label> ID </label>
                            <div id="modalUserId" class="value"></div>
                        </div>
                        <div class="field">
                            <label> Password </label>
                            <div class="input-wrap">
                                <div class="input-inner">
                                    <input type="password" id="modalUserPassword">
                                    <i class="fa-solid fa-eye password-toggle" id="passwordToggleIcon"></i>
                                </div>
                            </div>
                        </div>
                        <div class="field">
                            <label> Name </label>
                            <div id="modalUserName" class="value"></div>
                        </div>
                        <div class="field">
                            <label> Nickname </label>
                            <input type="text" id="modalUserNickname">
                        </div>
                        <div class="field">
                            <label> Email </label>
                            <input type="email" id="modalUserEmail">
                        </div>
                        <div class="field">
                            <label> Type </label>
                            <div id="modalUserType" class="value"></div>
                        </div>
                        <div class="field">
                            <label> Joined </label>
                            <div id="modalCreatedAt" class="value"></div>
                        </div>
                    </div>

                    <div class="modal-buttons">
                        <button id="updateUser"> Update </button>
                        <button id="deleteUser"> Delete </button>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>


</body>
</html>