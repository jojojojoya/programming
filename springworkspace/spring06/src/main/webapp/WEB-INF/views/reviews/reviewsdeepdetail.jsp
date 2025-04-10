<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<link rel="stylesheet" href="/resources/css/reviewsdetail.css">
<script src ="/resources/js/review.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>- Update Review -</h1>
<hr>

<form action="reviews-update-complete" method="post">


    <div>글 번호 : ${review.r_no}</div>
    <div>글 제목 : <input type="text" name="r_title" value="${review.r_title}"></div>
    <div>글 내용 : <textarea name="r_txt" rows="5" cols="50">${review.r_txt}</textarea></div>
    <div>작성일 : <input type="text" name="r_date" readonly> ${review.r_date}</div>

    <button type="submit">Update</button>
    <button type="button" onclick="location.href='reviews'">Go Back</button>
</form>


<hr>
</body>
</html>