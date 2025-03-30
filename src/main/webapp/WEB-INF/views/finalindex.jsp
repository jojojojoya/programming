<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.koyoi.main.vo.AdminMypageVO" %>
<%@ page import="com.koyoi.main.vo.UserMyPageVO" %>
<%@ page import="com.koyoi.main.vo.CounselorMyPageVO" %>
<%
    // 세션 체크
    HttpSession session1 = request.getSession(false);
    String userId = null;
    String userType = null;
    String userNickName = "친구";

    if (session1 != null) {
        userId = (String) session1.getAttribute("userId"); // 세션에 저장된 userId 값

        String nicknameFromSession = (String) session1.getAttribute("userNickName");   // session userNickname값
        if (nicknameFromSession != null) {
            userNickName = nicknameFromSession;
        }

        Object userTypeObj = session1.getAttribute("userType"); // int로 저장된 경우
        if (userTypeObj != null) {
            userType = userTypeObj.toString(); // int → String 안전하게 변환
        }
    }

    if (userId == null) {
        response.sendRedirect("/login"); // 세션 없거나 만료 시 로그인 페이지로 이동
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
    }   else if (userObj instanceof CounselorMyPageVO) {
        CounselorMyPageVO user = (CounselorMyPageVO) userObj;
        if (user.getUser_img() != null) {
            imgPath = user.getUser_img();
        }
    }
%>
<script>
    var userType = "<%= userType %>";

    function goToMyPage() {
        if (userType === "1") {
            location.href = "/usermypage";
        } else if (userType === "2") {
            location.href = "/counselormypage";
        }
    }
</script>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/finalindex.css">
</head>
<body>
<!-- 전체 컨테이너 -->
<div class="container">
    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <a href="/main" class="sidebar-btn">
                    <img src="/static/imgsource/layout/homeicon.png" alt="홈">
                </a>
                <a href="/diary" class="sidebar-btn"><img src="/static/imgsource/layout/nitkiicon2.png" alt="캘린더"></a>
                <a href="/habit" class="sidebar-btn"><img src="/static/imgsource/layout/pencilicon.png" alt="습관"></a>
                <% if (!"2".equals(userType)) { %>
                <a href="/livechatreservation" class="sidebar-btn">
                    <img src="/static/imgsource/layout/counicon.png" alt="라이브상담">
                </a>
                <% } %>

                <a href="javascript:void(0);" onclick="openChatModal()">
                    <div class="chatbotlogo">
                        <img src="/static/imgsource/layout/koyoi_moon.png" alt="챗봇">
                    </div>
                </a>
            </nav>
        </aside>
    </div>

    <div class="right-container">
        <header class="header-bar">
            <div class="brand-title">
                <a href="/main"><img src="/static/imgsource/layout/koyoi_name.png" alt="KOYOI 로고"></a>
            </div>
            <div class="header-icons">
                <button class="header-btn">
                    <a href="/logout"> <img src="/static/imgsource/layout/logout.png" alt="logout"> </a>
                </button>
                <button class="header-btn" onclick="goToMyPage()">
                <img class="profile-img" src="<%=imgPath%>" alt="프로필" onerror="this.src='/imgsource/testprofile.png'">
<%--        <img class="profile-img" src="${user.user_img}" alt="프로필 이미지" onerror="this.src='/imgsource/userProfile/default.png'">--%>

                </button>
            </div>
        </header>

        <main class="content">
            <c:if test="${not empty diaryContent}">
                <jsp:include page="${diaryContent}"/>
            </c:if>

            <c:if test="${not empty announcementList}">
                <jsp:include page="${announcementList}"/>
            </c:if>

            <c:if test="${not empty announcementDetail}">
                <jsp:include page="${announcementDetail}"/>
            </c:if>

            <c:if test="${not empty counselormypage}">
                <jsp:include page="${counselormypage}"/>
            </c:if>

            <!-- 유저마이페이지 인클루드 -->
            <c:if test="${not empty usermypage}">
                <jsp:include page="${usermypage}" />
            </c:if>


            <!-- 라이브챗 디테일  인클루드 -->
            <c:if test="${not empty livechatdetail}">
                <jsp:include page="${livechatdetail}"/>
            </c:if>

            <!-- 라이브챗 예약 인클루드 -->

            <% if (!"2".equals(userType)) { %>
            <c:if test="${not empty livechatreservation}">
                <jsp:include page="${livechatreservation}"/>
            </c:if>
            <% } %>
        </main>

        <%-- 상담사 접근 제한 알림 처리 --%>
        <% if ("2".equals(userType) && request.getAttribute("livechatreservation") != null) { %>
        <script>
            alert("このページにはアクセスできません。");
            location.href = "/main";
        </script>
        <% } %>
    </div>
</div>
</body>
<script>
    const userName = "<%= userNickName %>";
</script>
<script src="/static/js/chat/chat-modal.js"></script>
</html>
