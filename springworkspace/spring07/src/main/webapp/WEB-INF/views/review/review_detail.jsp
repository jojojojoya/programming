<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="resources/css/review.css">
</head>
<body>

<h1> Review Detail </h1>

<div class="detail-container">
    <div class="detail-box">
        <div class="detail-item"><strong>No:</strong> ${review.r_no}</div>
        <div class="detail-item"><strong>Title:</strong> ${review.r_title}</div>
        <div class="detail-item"><strong>Content:</strong> ${review.r_txt}</div>
        <div class="detail-item">
            <strong>Date:</strong>
            <fmt:formatDate value="${review.r_date}" pattern="yyyy년 MM월 dd일"/></div>
    </div>

    <div class="detail-btn">
        <button class="back-btn" onclick="history.back()"> Back </button>
        <button class="back-btn" onclick="location.href='update?no=${review.r_no}'"> Update </button>
        <button class="back-btn" onclick="deleteReview('${review.r_no}')"> Delete </button>
        <button class="back-btn" onclick="location.href='review'"> List </button>
    </div>
</div>
</body>
<script>
    function deleteReview(no) {
        let ok = confirm("정말 삭제하시겠습니까?")
        if(ok) {
            location.href='delete?no=' + no;
        }
    }


</script>
</html>