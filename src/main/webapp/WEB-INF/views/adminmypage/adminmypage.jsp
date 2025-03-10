<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>KOYOI</title>
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/adminmypage/adminmypage.css">
    <script src="/static/js/adminmypage/adminmypage.js"></script>
</head>
<body>

<div class="container">

    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn" id="user"><img src="/static/imgsource/user.png" alt="user"></button>
                <button class="sidebar-btn" id="counselor"><img src="/static/imgsource/counselor.png" alt="counselor"></button>
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
            <h2 id="table-title"> 회원 목록 </h2>

            <%-- 회원 목록 --%>
            <table id="userTable">
                <thead>
                    <tr>
                        <td> 번호 </td>
                        <td> 이름 </td>
                        <td> 닉네임 </td>
                        <td> 이메일 </td>
                        <td> 가입일 </td>
                    </tr>
                </thead>
                <tbody id="userTableBody">
                    <tr>
                        <td></td>
                    </tr>
                </tbody>
            </table>

            <!-- 상담사 목록 (초기에는 숨김 처리) -->
            <table id="counselorTable" style="display: none;">
                <thead>
                <tr>
                    <th> 번호 </th>
                    <th> 이름 </th>
                    <th> 이메일 </th>
                    <th> 등록일 </th>
                </tr>
                </thead>
                <tbody id="counselorTableBody">
                <c:forEach var="counselor" items="${counselorList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>
                            <a href="/admin/counselorDetail?id=${counselor.id}" class="user-link">
                                    ${counselor.name}
                            </a>
                        </td>
                        <td>${counselor.email}</td>
                        <td>${counselor.registerDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </main>
    </div>

</div>


</body>
</html>