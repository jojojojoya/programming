<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="resources/css/review.css">
</head>
<body>

<h1>Review Page</h1>
<div class="board-container">
    <button onclick="location.href='create'"> Write </button>
    <table>
        <thead>
        <tr>
            <th>No</th>
            <th>Title</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reviews}" var="r" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
<%--                <td> <span onclick="location.href='review-detail?no=${r.r_no}'"> ${r.r_title} </span> </td>--%>
                <td> <span onclick="location.href='detail2?no=${r.r_no}'"> ${r.r_title} </span> </td>
                <td><fmt:formatDate value="${r.r_date}" pattern="yyyy년 MM월 dd일" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>