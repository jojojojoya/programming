<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% // 세션 체크 추가 부분 시작
    HttpSession session1 = request.getSession(false); // 기존 세션 가져오기
    String userId = null;

    if (session1 != null) {
        userId = (String) session1.getAttribute("userId"); // 세션에 저장된 userId 값
    }

    if (userId == null) {
        response.sendRedirect("/login"); // 세션 없거나 만료 시 로그인 페이지로 이동
        return;
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
                <a href="/chatbot"><div class="bbiyak">
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
                <img class="profile-img" src="/static/imgsource/layout/testprofile.png" alt="프로필">
            </div>
        </header>
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
        </main>
    </div>
</div>
</body>
</html>
