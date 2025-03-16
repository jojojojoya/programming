<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
                        <td> ID </td>
                        <td> 이름 </td>
                        <td> 닉네임 </td>
                        <td> 이메일 </td>
                        <td> 가입일 </td>
                    </tr>
                </thead>
                <tbody id="userTableBody">
                <c:set var="totalUsers" value="${fn:length(users)}" />
                <c:forEach var="user" items="${users}" varStatus="status">
                    <tr class="user-detail-btn" data-user-id="${user.user_id}">
                        <td>${totalUsers - status.index}</td>
                        <td>${user.user_id}
                            <%--<span class="user-detail-btn" data-user-id="${user.user_id}">${user.user_id}</span>--%>
                        </td>
                        <td>${user.user_name}</td>
                        <td>${user.user_nickname}</td>
                        <td>${user.user_email}</td>
                        <td>${user.formattedCreatedAt}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%-- 상담사 목록 --%>
            <table id="counselorTable" style="display: none;">
                <thead>
                <tr>
                    <td> 번호 </td>
                    <td> ID </td>
                    <td> 이름 </td>
                    <td> 닉네임 </td>
                    <td> 이메일 </td>
                    <td> 가입일 </td>
                </tr>
                </thead>
                <tbody id="counselorTableBody">
                <c:set var="totalCounselors" value="${fn:length(counselors)}" />
                <c:forEach var="counselor" items="${counselors}" varStatus="status">
                    <tr class="user-detail-btn" data-user-id="${counselor.user_id}" data-type="counselor">
                        <td>${totalCounselors - status.index}</td>
                        <td>${counselor.user_id}
                            <%--<span class="user-detail-btn" data-user-id="${counselor.user_id}" data-type="counselor">${counselor.user_id}</span>--%>
                        </td>
                        <td>${counselor.user_name}</td>
                        <td>${counselor.user_nickname}</td>
                        <td>${counselor.user_email}</td>
                        <td>${counselor.formattedCreatedAt}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%-- 상세 데이터 모달 --%>
            <div id="userDetailModal" class="modal" style="display: none;">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <h2 id="modalTitle">회원 상세 정보</h2>

                    <div class="profile-container">
                        <img id="modalUserImg" alt="profile">
                    </div>

                    <table>
                        <tr><th>ID</th> <td id="modalUserId"></td></tr>
                        <tr><th>비밀번호</th> <td><input type="password" id="modalUserPassword"></td></tr>
                        <tr><th>이름</th> <td id="modalUserName"></td></tr>
                        <tr><th>닉네임</th> <td><input type="text" id="modalUserNickname"></td></tr>
                        <tr><th>이메일</th> <td><input type="email" id="modalUserEmail"></td></tr>
                        <tr><th>타입</th> <td id="modalUserType"></td></tr>
                        <tr><th>가입일</th> <td id="modalCreatedAt"></td></tr>
                    </table>

                    <div class="modal-buttons">
                        <button id="updateUser"> 수정 </button>
                        <button id="deleteUser"> 삭제 </button>
                    </div>
                </div>
            </div>


        </main>
    </div>
</div>


</body>
</html>