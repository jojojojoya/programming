<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/css/userReviewsPage.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--<div class="mypage" style="border: none;">
	<h1 onclick="location.href='UserC'">MyPage </h1>
</div>--%>
			<div class="mypage" style="border: none;">
				<h1 style="margin-inline-end: auto;">작성한 맛리뷰</h1>
			</div>

<%--		<hr>--%>

				<c:forEach items="${reviews}" var="review">
			<div class="review-comment" style="border: none;">

					<span>🦀🍴</span>
				<a href="https://kr.savorjapan.com/contents/discover-oishii-japan/if-you-re-looking-for-a-good-drink-in-shinjuku-these-are-the-places-to-be-10-izakaya/"> ${review.shop_name} |
					${review.review_content} | ${review.review_date} |  ${review.review_nickname} </a>

				<!-- 글주소 (편집 가능한)  -->
			</div>
				</c:forEach>
			<!--  글리스트 넘길수있게  -->

			<br> <br>
<div class="bottom-list-num" style="border: none;">
	<a href="UserReviewPageC?p=1"> <<  </a>
	<c:forEach begin = "1" end = "${pageCount }" var = "i">
		<a href="UserReviewPageC?p=${i }">[${i }] </a>
	</c:forEach>
	<a href="UserReviewPageC?p=${pageCount }"> >>  </a>
</div>


<div class="bottom-return-btn">
	<input onclick="location.href='UserC'" type="button" value="마이페이지로 돌아가기">
</div>


</body>
</html>