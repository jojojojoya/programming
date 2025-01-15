<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/review.css">
</head>
<body>


	<h1>Review Page</h1>
	
	<div class="review-container">
		<div>
			<div class="review-title">
				Review Page <a href="ReviewRegController">write</a>
			</div>
			<c:forEach items="${reviews }" var="r">
				<div class="review-row">
					<div>
						<span><a href="ReviewDetailController?no=${r.no }">${r.title }</a></span>
					</div>
					<div>
						<fmt:formatDate value="${r.date }"/>
					</div>
				</div>
			</c:forEach>
			
			<div>
			
			<a href="ReviewPageC?p=1">[begin] </a>
			<c:forEach begin = "1" end = "${pageCount }" var = "i"> 
			<a href="ReviewPageC?p=${i }">[${i }] </a>
			</c:forEach>
			<a href="ReviewPageC?p=${pageCount }">[end] </a>
			</div>
			
	
	
	<div>
	 	<input id="search-input">
	 	<button id="search-btn">search</button>
	 	<hr>
	 	<span id = "result"></span>
	
	</div>
	
	</div>
</body>
</html>