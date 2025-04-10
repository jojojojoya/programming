<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 기본 스타일 */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Arial', 'Helvetica', sans-serif;
}

/* 컨테이너 스타일 */
.container {
    width: 100%;
    height: 100%;
    background-color: #f7f7f7;
    color: #333;
}

/* 헤더 스타일 */
.header {
    height: 100px;
    background-color: #333;
    color: #fff;
    text-align: center;
    line-height: 100px;
    font-size: 2rem;
}

/* 네비게이션 바 스타일 */
.nav {
    height: 70px;
    background-color: #444;
    color: #fff;
    text-align: center;
    line-height: 70px;
    font-size: 1.5rem;
}

/* 콘텐츠 영역 */
.content {
    margin: 20px 20px;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* 제목 */
.mypage h1 {
    font-size: 2.5rem;
    margin-bottom: 10px;
    font-weight: bold;
}

/* 리뷰 목록 스타일 */
.review-list {
    display: flex;
    justify-content: flex-start;
    margin: 20px 0;
}

.review-list h2 {
    font-size: 1.8rem;
    font-weight: bold;
    color: #333;
}

/* 각 리뷰의 스타일 */
.review-comment {
    font-size: 1.2rem;
    font-weight: normal;
    color: #444;
    margin: 20px 0;
    line-height: 1.6;
}

/* 리뷰에 있는 링크 스타일 */
.review-comment a {
    color: #007bff;
    text-decoration: none;
}

.review-comment a:hover {
    text-decoration: underline;
}

/* 페이지 번호 */
.bottom-list-num {
    text-align: center;
    margin: 30px 0;
}

.bottom-list-num h2 {
    font-size: 1.5rem;
    font-weight: bold;
    color: #333;
}

/* 푸터 스타일 */
.footer {
    height: 200px;
    background-color: #333;
    color: #fff;
    text-align: center;
    line-height: 200px;
    font-size: 1.5rem;
}

/* 구분선 */
hr {
    border: 1px solid #ddd;
    margin: 20px 0;
}

/* 추가적인 버튼 스타일 */
input[type="button"] {
    background-color: #333;
    color: #fff;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    font-size: 1rem;
    border-radius: 5px;
}

input[type="button"]:hover {
    background-color: #555;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header">header</div>
		<div class="nav">nav</div>
		<div class="content">

			<div class="mypage" style="border: none;">
				<h1>MyPage  (누르면 마이페이지 메인으로 이동)</h1>
			</div>
		<br>
			<hr>

			<div class="review-list" style="border: none;">
				<h2 style="margin-inline-end: auto;">작성한 맛리뷰</h2>
			</div>
			
			
			<br>
			<br>
			<br>
			<br>

			<div class="review-comment" style="border: none;">
				<span>🦀🍴</span> [ 맛리뷰 예시 ] 가게명 / 제목 or 텍스트 일부 / 게시일
			</div>
			
		<hr>
			<div class="review-comment" style="border: none;">
				<span>🦀🍴</span> 
				<a href="https://kr.savorjapan.com/contents/discover-oishii-japan/if-you-re-looking-for-a-good-drink-in-shinjuku-these-are-the-places-to-be-10-izakaya/"> 홋카이도넘버원 (北海道ナンバーワン) | 찾았습니다. 존맛탱 홍게집....! 홍게 속살이 꽉
				차있는 숨은 골목 .. | 2025.01.10</a>
				<!-- 글주소 (편집 가능한)  -->
			</div>
					<hr>
			
			<div class="review-comment" style="border: none;">
				<span>🦀🍴</span> 우유빙수 (ミルクかき氷) | 산처럼 쌓인 우유 빙수가 인상적인 디저트 맛집입니다. 만족만족
				대만족! | 2025.01.10
			</div>

		<hr>
			<div class="review-comment" style="border: none;">
				<span>🦀🍴</span> 우유빙수 (ミルクかき氷) | 산처럼 쌓인 우유 빙수가 인상적인 디저트 맛집입니다. 만족만족
				대만족! | 2025.01.10
			</div>
			
			<!--  글리스트 넘길수있게  -->
			
			
			
			
		
			<br> <br>
			<div class="bottom-list-num" style="border: none;">  <h2> 1 2 3 4 (나중에 구현) > </h2>   </div>

	



		</div>
		<div class="footer">footer</div>
	</div>
</body>
</html>