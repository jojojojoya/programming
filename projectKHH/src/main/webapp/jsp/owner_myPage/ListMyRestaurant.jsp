<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../css/ownerMyPage/ownerRestaurantList.css">
<style>

</style>
</head>
<body>
	<div class="container">
			<div class="mypage">
				<h1>내 가게 리스트</h1>
			</div>

			<div class="myShop">
				<%--<h1 style="margin-inline-end: auto;">내 가게</h1>--%>
				<p>
					<a href="AddRestaurantC" style="text-decoration: none; color: black">
						<button>가게를 추가</button>
					</a>
				</p>
			</div>

		<div class="myShop_information_list_field">
			<c:forEach var="res" items="${res}" varStatus="status">
			<div class="myShop_information_list">
				<a href="ShopDetailC">
					<img class="myShop_information_list_img"
						 src="image/shopImage/${res.image}"
						 alt="place-name">
				</a>

				<div style="border: none;">
					<a href="ShopDetailC?no=${res.id}">
						<h2>${res.name}</h2>
					</a>
				</div>

				<div style="border: none;">
					<h3>${res.address}</h3>
					<h3>${res.opentime}</h3>
					<p>${res.phone}</p>
				</div>

				<div class="myShop_menuBtn">
					<button class="MenuButton" onclick="location.href='Delete_myRestaurantC?id=${res.id}'">
						가게삭제
					</button>
				</div>
			</div>
			</c:forEach>
		</div>

		<div class="service_comment">
			<h2>서비스 준비중입니다(◍•ᴗ•◍)</h2>
		</div>
		<br>
		<div class="service_comment2">
			<h2>얼른 완성시킬게요 (ง •̀_•́)ง</h2>
		</div>

<%--			<c:forEach var="res" items="${res}">
				<div class="myShop_container" style="border: solid 1px;">
					<div class="myShop_place_box">
						<img class="myShop_photo"
							 src="https://kr.savorjapan.com/gg/content_image/t0283_017.jpg"
							 alt="place-name">
						<div style="border: none; text-align: left; margin-left: 30px">
							<h2>
								<span>😋</span>${res.name}
							</h2>
							<div style="border: none;">
								<p>${res.address}</p>
								<p>${res.opentime}</p>
								<p>${res.phone}</p>
								<p>${res.explain}</p>

							</div>
						</div>
					</div>
					<div class="myShop_button_field" style="border: none; align-content: center;">
						<input onclick="location.href='DetailRestaurantC?no=${res.id}'" class="myShop-button" type="button" value="가게 정보 보기">
						<input onclick="location.href='Edit_myRestaurantC?no=${res.id}'" class="myShop-button" type="button" value="수정">
						<input onclick="location.href='Delete_myRestaurantC?no=${res.id}'" class="myShop-button" type="button" value="삭제">
					</div>
				</div>
			</c:forEach>--%>

			<%--<div class="bottom-list-num" style="border: none;">
				<h2>1 2 3 4 (나중에 구현) ></h2>
			</div>--%>

		<div class="bottom-return-btn">
			<input onclick="location.href='ShowOwnerMyPageController'" type="button" value="마이페이지로 돌아가기">
		</div>
	</div>
</body>
</html>