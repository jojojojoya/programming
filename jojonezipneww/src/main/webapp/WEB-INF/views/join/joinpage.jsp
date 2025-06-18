<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/join.css">

<title>Join Page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
	
	<form action="/join" method="post" class="join_form" autocomplete="off">
	<div class="joinpage-container">

		<div class="join-box-header">Create Your Account</div>

		<!-- 메시지 -->


		<div class="join-box-body">

			<c:if test = "${not empty joinError}">
				<div class="error">${joinError}</div>
				</c:if>
			Your ID <input name="userId" type="text" id="userid" value="${prevUserId}"> 
			Your Nickname <input  name="userNickname" type="text" value="${prevNickname}"> 
			Your Password <input  name="userPassword" type="password" id="pw1">
			Password Re-Check <input name="userPasswordCheck" type="password" id="pw2"> 
			
		</div>
		<div class="join-box-btn">			
			<button type="submit" id="join-btn"> Create </button>
		</div>
	</div>
	</form>
	
	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>

	
	
	<c:if test="${not empty joinSuccess}">
		<script>
		alert("${joinSuccess}");
		window.location.href = "/";
		</script>
	</c:if>


	<script type="text/javascript" src="/js/join.js"></script>


</body>
</html>