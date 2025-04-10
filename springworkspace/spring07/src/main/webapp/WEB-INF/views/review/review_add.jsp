<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="resources/css/review.css">
</head>
<body>

<div class="form-container">
    <form action="create" method="post">
        <label for="title"> Title </label>
        <input type="text" id="title" name="r_title" placeholder="Enter Title">

        <label for="content"> Content </label>
        <input type="text" id="content" name="r_txt" placeholder="Enter Content">

        <button class="submit-btn"> Submit </button>
    </form>
</div>
</body>
</html>