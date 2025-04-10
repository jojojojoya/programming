<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/product.css">
    <script src="/resources/js/product.js"></script>
</head>
<body>
${products}

<h1> - Product Reg -</h1>
<form action="/products" method="post">
    <div>
        <div> Name <input type="text" name="p_name"> </div>
        <div> Price <input type="text" name="p_price"> </div>
        <div> <button> Add </button> </div>
    </div>
</form>

<hr>

<h1> - product update - </h1>
<form action="/products" method="post">
    <input type="text" hidden name="_method" value="put">
    <select name="p_no">
        <c:forEach items="${products}" var="p">
            <option value="${p.p_no}"> No. ${p.p_no}</option>
        </c:forEach>
    </select>
    <div> <input type="text" name="p_name" placeholder="name"> </div>
    <div> <input type="text" name="p_price" placeholder="price"> </div>
    <div> <button> Update </button> </div>
</form>

<hr>

<h1> - product delete -</h1>
<form action="/products" method="post">
    <div> <input type="text" hidden name="_method" value="delete"> </div>
    <div> <input type="text" name="pk"> </div>
    <div> <button> Delete </button> </div>
</form>

<hr>

<h1> -Product List- </h1>

<div>
    <div class="item">
        <div> No. </div>
        <div> Name. </div>
        <div> Price. </div>
    </div>
    <c:forEach items="${products}" var="p">
        <div class="item">
            <div>${p.p_no}</div>
            <div>${p.p_name}</div>
            <div>${p.p_price}</div>
            <div> <button onclick="location.href='/products/getJSON/${p.p_no}'">getJSON</button></div>
            <div> <button onclick="location.href='delete?pk=${p.p_no}'"> Delete </button> </div>
        </div>
    </c:forEach>
</div>

</body>
</html>