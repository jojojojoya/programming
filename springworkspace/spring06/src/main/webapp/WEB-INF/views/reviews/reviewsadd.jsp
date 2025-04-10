<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<link rel="stylesheet" href="/resources/css/addreviews.css">
<script src ="/resources/js/addreview.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1> - add reviews - </h1>
<hr>
<form action="addreview" method="post">
    <div class="item">
        <div> title <input type="text" name="r_title"> </div>
        <div> content <input type="text" name="r_txt"> </div>
        <button> add </button>
    </div>
</form>



<hr>
</body>
</html>