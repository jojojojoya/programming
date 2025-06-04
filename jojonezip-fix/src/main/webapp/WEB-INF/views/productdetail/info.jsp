<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP - info</title>

<link rel="stylesheet" href="/css/productdetail.css">
</head>
<body>

	
	<div class="mainpage-container">
		<div class="info-detail">
		  <c:forEach var="info" items="${info}">
		    <div class="info-item">
		      <div class="info-content">${info.info_content}</div>
		    </div>
		  </c:forEach>
		</div>

		<c:if test="${empty info}">
		  <div> 상품 정보가 없습니다.</div>
		</c:if>
	</div>

</html>