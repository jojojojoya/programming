<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<link rel="stylesheet" href="/css/userScrapPage.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>

<div class="mypage">
	<h1 style="margin-inline-end: auto;">좋아요한 가게</h1>
</div>

			<%--<div class="like-list" style="border: none;">
				<h2 style="margin-inline-end: auto;">좋아요한 가게</h2>
			</div>--%>

	<c:forEach items="${scraps}" var="scrap">
			<div class="like-place-list-field">
				<div class="like-place-list">
					<img src="image/${scrap.shop_image}" alt="place-name">
					<div style="text-align: left; width: 63%">
						<h1 style="text-align: left">😋&nbsp;${scrap.shop_name}  </h1>
						<div>
							<p>[가게 주소] </p>
							${scrap.shop_addr}
							<p>[가게 연락처]</p>
							${scrap.shop_tel}
							<p>[가게 소개] </p>
							${scrap.shop_content}
						</div>
					</div>
				</div>
				<div class="like-place-list-btn" style="border: none; align-content: center;">
					<input type="button" value="가게 정보 보기">
				</div>
			</div>
	</c:forEach>

			<div class="bottom-list-num" style="border: none;">
				<a href="UserScrapPageC?p=1"> << </a>
				<c:forEach begin = "1" end = "${pageCount }" var = "i">
					<a href="UserScrapPageC?p=${i }">[${i }] </a>
				</c:forEach>
				<a href="UserScrapPageC?p=${pageCount }"> >> </a>
			</div>

			<div class="bottom-return-btn"
				 style="border: none; align-content: center;">
				<input onclick="location.href='UserC'" type="button" value="마이페이지로 돌아가기">
			</div>

</body>
</html>