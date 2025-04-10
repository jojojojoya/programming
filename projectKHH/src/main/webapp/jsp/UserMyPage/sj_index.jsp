<%--
  Created by IntelliJ IDEA.
  User: jien9
  Date: 2025-01-14
  Time: 오후 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/userMyPage/userMyPage.css">
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="header">header</div>
    <div class="nav">nav   <a href="UserC">mypage</a></div>
    <div class="content">
        <jsp:include page="${content}"></jsp:include>

    </div>
    <div class="footer">footer</div>
</div>
</body>
</html>
