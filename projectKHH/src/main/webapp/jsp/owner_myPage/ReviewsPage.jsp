<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="../../css/ownerMyPage/ownerReviewsPage.css">
</head>
<body>
	<div class="container">
		<div class="mypage" style="border: none;">
			<h1 style="margin-inline-end: auto;">작성한 맛리뷰</h1>
		</div>

			<%--<div class="review-comment" style="border: none;">
				<span>🦀🍴</span> [ 맛리뷰 예시 ] 가게명 / 제목 or 텍스트 일부 / 게시일
			</div>--%>

			<div class="review-comment" style="border: none;">
				<span>🦀🍴</span> 
				<a href="https://kr.savorjapan.com/contents/discover-oishii-japan/if-you-re-looking-for-a-good-drink-in-shinjuku-these-are-the-places-to-be-10-izakaya/"> 홋카이도넘버원 (北海道ナンバーワン) | 찾았습니다. 존맛탱 홍게집....! 홍게 속살이 꽉
				차있는 숨은 골목 .. | 2025.01.10</a>
				<!-- 글주소 (편집 가능한)  -->
			</div>
			
			<div class="review-comment" style="border: none;">
				<span>🦀🍴</span> <a href="#">우유빙수 (ミルクかき氷) | 산처럼 쌓인 우유 빙수가 인상적인 디저트 맛집입니다. 만족만족
				대만족! | 2025.01.10</a>
			</div>

			<div class="review-comment" style="border: none;">
				<span>🦀🍴</span> <a href="#">우유빙수 (ミルクかき氷) | 산처럼 쌓인 우유 빙수가 인상적인 디저트 맛집입니다. 만족만족
				대만족! | 2025.01.10</a>
			</div>
			
			<!--  글리스트 넘길수있게  -->

		<div class="bottom-list-num" style="border: none;">
			<a href="#"> <<  1</a>
			<c:forEach begin = "1" end = "${pageCount }" var = "i">
				<a href="#">[${i }] </a>
			</c:forEach>
			<a href="#"> >>  </a>
		</div>

		<div class="bottom-return-btn">
			<input onclick="location.href='ShowOwnerMyPageController'" type="button" value="마이페이지로 돌아가기">
		</div>
	</div>
</body>
</html>