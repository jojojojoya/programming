<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="UTF-8">

<link rel="stylesheet" href="/css/header.css">
<div class="header">
<div class="header-text" onclick="location.href='/'">
JOJONEZIP
</div>

<div class="login-area"> 
<c:choose>
	<c:when test="${not empty sessionScope.loginUser}">	
<div onclick="location.href='/cartpage'"> 
	<img src="/img/headercart.png" class="header-cart-icon" alt="장바구니">
</div>
<div onclick="location.href='/?logout=true'"> 로그아웃</div>


<c:choose>
<c:when test="${loginUser.user_type ==1}">
<div onclick="location.href='/usermypage'">마이페이지</div>
</c:when>
<c:when test="${loginUser.user_type ==2}">
<div onclick="location.href='/adminmypage'">마이페이지</div>
</c:when>

</c:choose>

</c:when>
<c:otherwise>
<div onclick="location.href='/loginpage'"> 로그인</div>
</c:otherwise>
</c:choose>
</div>


</div>
<div class="nav-bar">
	<div class="nav-bar-text">
		<div onclick="location.href='/outers'">아우터</div>
		<div onclick="location.href='/tops'">상의</div>
		<div onclick="location.href='/bottoms'">하의</div>
		<div onclick="location.href='/accs'">액세서리</div>
		<div onclick="location.href='/sales'">SALE</div>
	</div>
</div>
