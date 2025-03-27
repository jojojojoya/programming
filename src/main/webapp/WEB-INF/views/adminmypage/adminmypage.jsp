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
            : "/imgsource/usermypage_profiletest.jpg"; // 기본 이미지
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
<body
        data-user-total="${userTotal}" data-user-page="${userPage}"
        data-counselor-total="${counselorTotal}" data-counselor-page="${counselorPage}"
        data-announcement-total="${announcementTotal}" data-announcement-page="${announcementPage}">

<div class="container">

    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn" id="user">
                    <img src="/static/imgsource/profile/user.png" alt="user">
                </button>
                <button class="sidebar-btn" id="counselor">
                    <img src="/static/imgsource/profile/counselor.png" alt="counselor">
                </button>
                <button class="sidebar-btn" id="announcement">
                    <img src="/static/imgsource/announcements.png" alt="announcement">
                </button>
            </nav>
        </aside>
    </div>

    <div class="right-container">
        <header class="header-bar">
            <div class="brand-title">
                <a href="/main"> <img class="logo-icon" src="/static/imgsource/layout/logo.png" alt="KOYOI"> </a>
            </div>

            <div class="header-icons">
                <button class="header-btn">
                    <a href="/logout"> <img src="/static/imgsource/layout/logout.png" alt="logout"> </a>
                </button>
                <img class="profile-img" src="/static<%=imgPath%>" alt="profile">
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
                <c:forEach var="user" items="${users}" varStatus="status">
                    <div class="user-row user-detail-btn" data-user-id="${user.user_id}">
                        <div class="cell col-num">${userTotal - ((userPage - 1) * 5 + status.index)}</div>
                        <div class="cell col-id">${user.user_id}</div>
                        <div class="cell col-name">${user.user_name}</div>
                        <div class="cell col-nickname">${user.user_nickname}</div>
                        <div class="cell col-email">${user.user_email}</div>
                        <div class="cell col-date">${user.formattedCreatedAt}</div>
                    </div>
                </c:forEach>
              <%--  <c:set var="totalUsers" value="${fn:length(users)}" />
                <c:forEach var="user" items="${users}" varStatus="status">
                    <div class="user-row user-detail-btn" data-user-id="${user.user_id}">
                        <div class="cell col-num">${totalUsers - status.index}</div>
                        <div class="cell col-id">${user.user_id}</div>
                        <div class="cell col-name">${user.user_name}</div>
                        <div class="cell col-nickname">${user.user_nickname}</div>
                        <div class="cell col-email">${user.user_email}</div>
                        <div class="cell col-date">${user.formattedCreatedAt}</div>
                    </div>
                </c:forEach>--%>
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
                <c:forEach var="counselor" items="${counselors}" varStatus="status">
                    <div class="user-row user-detail-btn" data-user-id="${counselor.user_id}">
                        <div class="cell col-num">${counselorTotal - ((counselorPage - 1) * 5 + status.index)}</div>
                        <div class="cell col-id">${counselor.user_id}</div>
                        <div class="cell col-name">${counselor.user_name}</div>
                        <div class="cell col-nickname">${counselor.user_nickname}</div>
                        <div class="cell col-email">${counselor.user_email}</div>
                        <div class="cell col-date">${counselor.formattedCreatedAt}</div>
                    </div>
                </c:forEach>
                <%--<c:set var="totalCounselors" value="${fn:length(counselors)}" />
                <c:forEach var="counselor" items="${counselors}" varStatus="status">
                    <div class="user-row user-detail-btn" data-user-id="${counselor.user_id}" data-type="counselor">
                        <div class="cell col-num">${totalCounselors - status.index}</div>
                        <div class="cell col-id">${counselor.user_id}</div>
                        <div class="cell col-name">${counselor.user_name}</div>
                        <div class="cell col-nickname">${counselor.user_nickname}</div>
                        <div class="cell col-email">${counselor.user_email}</div>
                        <div class="cell col-date">${counselor.formattedCreatedAt}</div>
                    </div>
                </c:forEach>--%>
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

            <%-- 공지사항 목록 --%>
            <div id="announcementTable" class="announcement-board" style="display: none;">
                <button class="announcement-create-btn"> Create </button>
                <div class="announcement-board-header">
                    <div class="col col-announcement-num"> No </div>
                    <div class="col col-announcement-id"> Admin Id </div>
                    <div class="col col-announcement-title"> Title </div>
                    <div class="col col-announcement-created"> Created </div>
                </div>
                <c:forEach var="announcement" items="${announcements}" varStatus="status">
                    <div class="announcement-row announcement-detail-btn" data-user-id="${announcement.announcement_id}">
                        <div class="cell col-announcement-num">${announcementTotal - ((announcementPage - 1) * 5 + status.index)}</div>
                        <div class="cell col-announcement-id">${announcement.admin_id}</div>
                        <div class="cell col-announcement-title">${announcement.title}</div>
                        <div class="cell col-announcement-created">${announcement.formattedCreatedAt}</div>
                    </div>
                </c:forEach>
                <%--<c:set var="totalAnnouncements" value="${fn:length(announcements)}" />
                <c:forEach var="announcement" items="${announcements}" varStatus="status">
                <div class="announcement-row announcement-detail-btn" data-user-id="${announcement.announcement_id}">
                    <div class="cell col-announcement-num">${totalAnnouncements - status.index}</div>
                    <div class="cell col-announcement-id">${announcement.admin_id}</div>
                    <div class="cell col-announcement-title">${announcement.title}</div>
                    <div class="cell col-announcement-created">${announcement.formattedCreatedAt}</div>
                </div>
                </c:forEach>--%>
            </div>

            <%-- 공지사항 상세 모달 --%>
            <div id="announcementModal" class="modal">
                <div class="modal-content">
                    <span class="modal-close-btn">&times;</span>
                    <div class="modalTitle">Announcement Detail</div>
                    <div class="announcement-modal scrollable">
                        <div class="announcement-info-grid">
                            <div class="field">
                                <label>Title</label>
                                <input type="text" id="modalAnnouncementTitle" class="value" />
                            </div>
                            <div class="field">
                                <label>Admin ID</label>
                                <div id="modalAnnouncementAdminId" class="value"></div>
                            </div>
                            <div class="field">
                                <label>Created At</label>
                                <div id="modalAnnouncementCreated" class="value"></div>
                            </div>
                            <div class="field">
                                <label>Content</label>
                                <textarea id="modalAnnouncementContent" class="value" rows="8"></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="modal-buttons">
                        <button id="updateAnnouncement" data-announcement-id=""> Update </button>
                        <button id="deleteAnnouncement" data-announcement-id=""> Delete </button>
                    </div>
                </div>
            </div>

            <%-- 공지사항 작성 모달 --%>
            <div id="announcementCreateModal" class="modal">
                <div class="modal-content">
                    <span class="modal-close-btn create-close">&times;</span>
                    <div class="modalTitle">Create Announcement</div>
                    <div class="announcement-modal scrollable">
                        <div class="announcement-info-grid">
                            <div class="field">
                                <label>Title</label>
                                <input type="text" id="createAnnouncementTitle" class="value" />
                            </div>
                            <div class="field">
                                <label>Content</label>
                                <textarea id="createAnnouncementContent" class="value" rows="8"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-buttons">
                        <button id="submitAnnouncement"> Submit </button>
                    </div>
                </div>
            </div>
            <div id="commonPagination" class="pagination"></div>

        </main>
    </div>
</div>


</body>
</html>