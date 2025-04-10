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
<h1> - reviews contetents - </h1>
<hr>


    <div class="item">
        <div> 글 번호 : ${review.r_no}</div>
        <div> 글 제목 : ${review.r_title}</div>
        <div> 글 내용 : ${review.r_txt}</div>
        <div> 작성일 : ${review.r_date}</div>


        <button onclick="location.href='reviews'"> go back</button>
        <button onclick="location.href='reviews-update?no=${review.r_no}'"> update </button>
        <button onclick="location.href='reviews-delete?no=${review.r_no}'">  delete </button>
        <!-- location.href = url -->
    </div>


<hr>
</body>
<script>
    function deleteReviews() {
        let ok =  confirm("really?");
        if (ok) {
            location.href='review-delete?no='+pk
        }
    }

</script>
</html>