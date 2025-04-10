<%--
  Created by IntelliJ IDEA.
  User: svyet
  Date: 2025/01/14
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
    <title>Title</title>
    <%--<link rel="stylesheet" type="text/css" href="../../css/index_ver.0.4.css?v=1.0">
    <link rel="stylesheet" href="../../css/signup/signup_ver.0.2.css">--%>
    <link rel="stylesheet" href="../../css/signup/signUp.css">
    <link rel="stylesheet" href="../../css/main.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <style>
        @font-face {
            font-family: 'BMJUA';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        .header{
            background-color: rgb(205, 187, 173);
        }
        p{
            font-family: 'BMJUA',serif;
        }
        body{
            margin-left: 200px;
        }
        button{
            padding-right: 10px;
            width: 100px;
            height: 50px;
            margin: 20px;
            background-color: rgb(205, 187, 173);
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 15px;
            border: 3px rgb(70, 44, 32) solid;
            font-size: 20px;
            font-family: 'BMJUA',serif;
            font-weight: 70;
            font-style: normal;
            color: rgb(70, 44, 32);
        }
        button:hover{
            padding-right: 10px;
            width: 100px;
            height: 50px;
            margin: 20px;
            background-color: rgb(70, 44, 32);
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 15px;
            border: 3px rgb(205, 187, 173) solid;
            font-size: 20px;
            font-family: 'BMJUA',serif;
            font-weight: 70;
            font-style: normal;
            color: rgb(205, 187, 173);
        }
    </style>
</head>

<body>

<% request.setCharacterEncoding("UTF-8");%>

<h1>입력 확인 페이지</h1>

<form action="/SignUpC" method="POST">
    <% String email = request.getParameter("email-domain") + "@" + request.getParameter("email-service"); %>

    <p>이름: <%= request.getParameter("name")%>
    </p>
    <input type="hidden" name="userName" value="<%= request.getParameter("name") %>">


    <p>이메일: <%= email %>
    </p>
    <input type="hidden" name="userEmail" value="<%= email%>">

    <p>닉네임: <%= request.getParameter("nickname") %>
    </p>
    <input type="hidden" name="nickname" value="<%= request.getParameter("nickname") %>">

    <p>전화번호: <%= request.getParameter("tel") %>
    </p>
    <input type="hidden" name="tel" value="<%= request.getParameter("tel") %>">

    <p>성별: <%= request.getParameter("gender") %>
    </p>
    <input type="hidden" name="gender" value="<%= request.getParameter("gender") %>">

    <p>생년월일:
        <%= request.getParameter("birth-year") %>년&nbsp;
        <%= request.getParameter("birth-month") %>월&nbsp;
        <%= request.getParameter("birth-day") %>일
    </p>
<%--    <input type="hidden" name="birth"--%>
<%--           value="<%= request.getParameter("birth-year") + "-" + request.getParameter("birth-month") + "-" + request.getParameter("birth-day") %>">--%>
    <input type="hidden" id="formatted-birthdate" name="birth" value="<%= request.getParameter("birthdate")%>">

    <p>비밀번호: <%= request.getParameter("password")%>
    </p>
    <input type="hidden" name="password" value="<%= request.getParameter("password") %>">

    <div style="display: flex">
    <button type="submit" name="userType" value="user">회원 등록</button>
    <button type="button" onclick="history.back();">수정하기</button>
    </div>
</form>
</body>
</html>
