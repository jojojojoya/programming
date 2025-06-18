<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/login.css">
<title>Login Page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
	
<form action="/login" method="post" class="login-form" autocomplete="off">
	<div class="login-container">

		<div class="login-box-header">Login</div>

		<div class="login-box-body">
			<c:if test="${not empty errorMessage}">
			    <div class="error-message" style="color: red; margin-top: 10px;">
			        ${errorMessage}
			    </div>
			</c:if>
			ID <input name="userId" id="user_id" type="text" > 
			Password <input name="userPassword" id="user_password" type="password"> 
		</div>
		<div class="login-box-btn">			
			<button type="submit" id="login-btn">Login</button>
			<button type="button" id="join-btn" onclick="location.href='/joinpage'">Join</button>
		</div>
	</div>
</form>

	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>
</body>
</html>