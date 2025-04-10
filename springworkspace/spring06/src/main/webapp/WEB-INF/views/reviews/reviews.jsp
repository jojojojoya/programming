<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<link rel="stylesheet" href="/resources/css/reviews.css">
<script src ="/resources/js/review.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1> - reviews list - </h1>
<hr>

<button onclick="location.href='reviews-add'">Write</button>
<c:forEach items="${reviews}" var="r">
    <div class="item">
        <div>${r.r_no}</div>
        <div onclick="location.href='reviews-detail?no=${r.r_no}'">${r.r_title}</div>
        <div>${r.r_date}</div>
    </div>
</c:forEach>



<hr>
</body>
</html>