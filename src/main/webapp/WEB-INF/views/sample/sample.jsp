<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/product.css">
    <script src="/resources/js/product.js"></script>
</head>
<body>
<h1> -Product List- </h1>

            <div class="no">${product.p_no}</div>
            <div class="name">${product.p_name}</div>
            <div class="price">${product.p_price}</div>


</body>
</html>