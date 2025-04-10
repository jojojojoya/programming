<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<link rel="stylesheet" href="/resources/css/product.css">
<script src ="/resources/js/product.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
${products}




<h1> - product reg - </h1>
<form action="product" method="post">

<div>name <input type="text" name="p_name"></div>
<div>price <input type="text"name="p_price"></div>
<div> <button> add </button></div>
</form>

<hr>
<h1> - product update - </h1>

<form action="update-product" method="post">

    <div>

        <select name = "p_no">
            <c:forEach var="p" items="${products}">
                <option value="${p.p_no}">no.${p.p_no}</option>
            </c:forEach>
        </select>
    </div>
<%--        <input type="" name="p_no"></div>--%>
    <div>name <input type="text" name="p_name"></div>
    <div>price <input type="text" name="p_price"></div>
    <div> <button> update </button></div>
</form>

<hr>

<form action="delete-product" method ="post">
<h1> - product delete - </h1>
<div> no <input type="text" name="p_no"> </div>
<div> <button> delete </button> </div>
</form>


<hr>


<h1> - product list - </h1>
<hr>

<div>
    <div class="item">
        <div>no.</div>
        <div>name</div>
        <div>price</div>
    </div>
</div>

<c:forEach items="${products}" var="p">
    <div class="item">
        <div>${p.p_no}</div>
        <div>${p.p_name}</div>
        <div>${p.p_price}</div>
        <div> <button onclick="location.href='delete-product?no=${p.p_no}'">del</button> </div>
    </div>
</c:forEach>



<hr>
</body>
</html>