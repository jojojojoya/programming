<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN PAGE</title>

<link rel="stylesheet" href="/css/adminmypage.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>

	<div class="adminmp-container">
		<div class="adminmp-sidebar-container">
			<div class="adminmp-sidebar-list">
				<div onclick="location.href='/adminmypage'">MYPAGE</div>
				<div onclick="location.href='/adminuserlist'">유저관리</div>
				<div onclick="location.href='/adminorder'">주문관리</div>
				<div onclick="location.href='/adminproduct'">상품관리</div>
				<div onclick="location.href='/adminreview'">리뷰관리</div>
				<div onclick="location.href='/adminqna'">문의관리</div>
			</div>
		</div>



		<div class="adminmp-header">
			MYPAGE
			<div class="adminmp-profile-box">
				<div class="adminmp-welcome-text">관리자님! 오늘도 힘차게 시작해봐요. </div>
				<div class="adminmp-textbox">
				<div>유저수 TOTAL : <Strong>${userCount}명</Strong></div>
				<div>금일 매출액 : <Strong><fmt:formatNumber value="${uriageCount}" pattern="#,###" type="number" />
					원</Strong></div>
				<div>금일 주문수 : <Strong>${orderCount}건</Strong></div>
				<div>금일 문의수 : <Strong>${qnaCount}건</Strong></div>
				<div>금일 리뷰수 : <Strong>${reviewCount}건</Strong></div>
			</div>
			</div>
			
		</div>
	</div>



	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>
<script type="text/javascript" src="/js/adminmypage.js"></script>
</body>


</html>