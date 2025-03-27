<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.koyoi.main.vo.AdminMypageVO" %>
<%@ page import="com.koyoi.main.vo.UserMyPageVO" %>
<%
    // 세션 체크
    HttpSession session1 = request.getSession(false);
    String userId = null;

    if (session1 != null) {
        userId = (String) session1.getAttribute("userId");
    }

    if (userId == null) {
        response.sendRedirect("/login");
        return;
    }

    // user 객체에서 이미지 경로 추출 (usermypage에 인클루드할 상단 우측 프로필 작은 이미지 추출용)
    Object userObj = request.getAttribute("user");
    String imgPath = "/imgsource/testprofile.png"; // 기본 이미지

    if (userObj instanceof AdminMypageVO) {
        AdminMypageVO user = (AdminMypageVO) userObj;
        if (user.getUser_img() != null) {
            imgPath = user.getUser_img();
        }
    } else if (userObj instanceof UserMyPageVO) {
        UserMyPageVO user = (UserMyPageVO) userObj;
        if (user.getUser_img() != null) {
            imgPath = user.getUser_img();
        }
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/finalindex.css">
</head>
<body>
<!-- 전체 컨테이너 -->
<div class="container">
    <!-- 🟠 왼쪽 컨테이너 (사이드바) -->
    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <a href="/main" class="sidebar-btn">
                    <img src="/static/imgsource/layout/home.png" alt="홈">
                </a>
                <a href="/diary" class="sidebar-btn"><img src="/static/imgsource/layout/calandar.png" alt="캘린더"></a>
                <a href="/habit" class="sidebar-btn"><img src="/static/imgsource/layout/pencil.png" alt="습관"></a>
                <a href="/livechatreservation" class="sidebar-btn"><img src="/static/imgsource/layout/chat.png" alt="라챗"></a>
                <a href="/chat"><div class="bbiyak">
                    <img src="/static/imgsource/layout/bbiyak.png" alt="챗봇삐약잉">
                </div></a>
            </nav>
        </aside>
    </div>

    <!-- 🟣 오른쪽 컨테이너 (헤더바 + 콘텐츠) -->
    <div class="right-container">
        <header class="header-bar">
            <!-- 🌙 중앙 브랜드 로고 -->
            <div class="brand-title">
                <a href="/main"><img src="/static/imgsource/layout/logo.png" alt="KOYOI 로고"></a>
            </div>
            <!-- 🟡 우측 상단 아이콘 -->
            <div class="header-icons">
                <img class="profile-img" src="<%= imgPath %>" alt="프로필" onerror="this.src='/imgsource/testprofile.png'">
            </div>
        </header>

        <!-- 🔵 실제 콘텐츠 영역 -->
        <main class="content">
            <c:if test="${not empty diaryContent}">
                <jsp:include page="${diaryContent}" />
            </c:if>
            <c:if test="${not empty announcementList}">
                <jsp:include page="${announcementList}" />
            </c:if>
            <c:if test="${not empty announcementDetail}">
                <jsp:include page="${announcementDetail}" />
            </c:if>
            <c:if test="${not empty usermypage}">
                <jsp:include page="${usermypage}" />
            </c:if>
            <c:if test="${not empty livechatdetail}">
                <jsp:include page="${livechatdetail}" />
            </c:if>
            <c:if test="${not empty livechatreservation}">
                <jsp:include page="${livechatreservation}" />
            </c:if>
        </main>
    </div>
</div>
</body>
</html>
