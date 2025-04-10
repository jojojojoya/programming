<%--
  Created by IntelliJ IDEA.
  User: svyet
  Date: 2025/01/14
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/signup/signUp.css">


</head>
<body>
<h1 style="display: flex; justify-content: center"><b>회원 유형</b></h1>

<form action="/ChooseTypeC">
    <div class="form-wrapper-userType" style="display: flex; align-items: center; justify-content: space-between;">
        <button type="submit" name="userType" value="user">일반 회원</button>
        <button type="submit" name="userType" value="owner">점주 회원</button>
        <%--        <button type="button" onclick="ToSignUp('customer')">일반 회원</button>--%>
        <%--        <button type="button" onclick="ToSignUp('owner')">점주 회원</button>--%>
    </div>
</form>
</body>
</html>
