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

<h1> - Product Reg -</h1>
    <div>
        <div> Name <input type="text" id="name" name="p_name"> </div>
        <div> Price <input type="text" id="price" name="p_price"> </div>
        <div> <button id="add"> Add </button> </div>
    </div>

<hr>

<h1> - product update - </h1>
    <input type="text" hidden name="_method" value="put">
    <select name="p_no">
    </select>

    <div> <input type="text" id= "up-name" name="p_name" placeholder="name"> </div>
    <div> <input type="text" id="up-price" name="p_price" placeholder="price"> </div>
    <div> <button id="update-btn"> Update </button> </div>

<hr>

<h1> - product delete -</h1>
    <div> <input type="text" name="pk"> </div>
    <div> <button id="del-btn"> Delete </button> </div>

<hr>

<h1> -Product List- </h1>

<div>
    <div class="item">
        <div> No. </div>
        <div> Name. </div>
        <div> Price. </div>
    </div>
<%--첨부하길 원하는 템플릿--%>

        <div class="item temp">
            <div class="no">${p.p_no}</div>
            <div class="name">${p.p_name}</div>
            <div class="price">${p.p_price}</div>
            <div class="delete">
                <button> Delete </button> </div>
        </div>
</div>

<div id = "product-list"></div>
<button id="openModal">Open Modal</button>

<!-- dialog 태그로 모달 정의 -->
<dialog id="myModal">
    <h2>Product Modal</h2>
    <div>
        <div class="modal-no"></div>
        <div class="modal-name"></div>
        <div class="modal-price"></div>
    </div>
    <button id="closeModal">Close</button>
</dialog>


<script>

</script>
</body>
</html>