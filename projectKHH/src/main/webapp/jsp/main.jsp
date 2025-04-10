<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/main.css?v=1.0">
</head>
<body>
<div class="header-container">
    <header>
        <a href="HomeC"><img alt="" src="../image/logo_header.png"></a>
    </header>
    <nav>
        <ul>
            <li><a href="ShopC">맛집 소개</a></li>
            <%--<li><a href="RankingListController">이달의 맛집</a></li>--%>
            <li><a href="CommunityListController">커뮤니티</a></li>
            <li><a href="LoginC">로그인</a></li>
            <li><a href="UserC">마이 페이지(유저)</a></li>
            <li><a href="ShowOwnerMyPageController">마이 페이지(점주)</a></li>
            <%--            <li><a href="/LogoutC">로그아웃</a></li>--%>
        </ul>
    </nav>
</div>

<div class="content">
    <jsp:include page="${content }"/>
</div>

<div class="fixed-nav">
    <div class="btn-page-top"></div>
    <span class="btn-page-top-span" onclick="window.scrollTo(0,0);">TOP</span>
</div>

<div class="footer">
    <div class="footer-logo" style="padding-bottom: 5px">
        <img alt="" src="../image/logo_header.png" style="width: 80px;">
    </div>
    <div class="footer-links">
        <a href="">프로젝트 소개</a> |
        <a href="">이용 약관</a> |
        <a href="">개인정보 처리방침</a>
    </div>
    <div class="footer-social" style="padding-top:10px; padding-bottom: 5px;">
        <a href="" target="_blank">Facebook |</a>
        <a href="" target="_blank">Twitter |</a>
        <a href="" target="_blank">Instagram</a>
    </div>
        <span>© 2025 KHHCompany. All Rights Reserved.</span>
</div>

</body>
</html>
