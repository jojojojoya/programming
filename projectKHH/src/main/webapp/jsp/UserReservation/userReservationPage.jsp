<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/css/userReservationPage.css">
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
			<%--<div class="mypage" style="border: none;">
				<h1 onclick="location.href='UserC'">MyPage </h1>
			</div>--%>

			<div class="mypage" style="border: none;">
				<h1 style="margin-inline-end: auto;">예약한 가게</h1>
			</div>

<c:forEach items="${myreservations}" var="myreservation">
	<div class="reservation-place-list-field">
		<div class="reservation-place-list">
			<img src="image/${myreservation.shop_image}" alt="place-name">
			<div style="text-align: left; width: 63%">
				<h1>😋&nbsp;${myreservation.shop_name}</h1>
				<div>
				<p>[가게 주소]</p>
					${myreservation.shop_addr} <!-- h2 태그로 가게 주소 적용 -->
				<p>[가게 연락처]</p>
					${myreservation.shop_tel}
					<p>[예약일정] ${myreservation.reservation_date}</p> <!-- h2 태그로 예약일정 적용 -->
					<p>[예약인원] ${myreservation.reservation_people} 명</p> <!-- h2 태그로 예약인원 적용 -->
					<p>[예약자 이름] ${myreservation.reservation_name}</p>
					<p>[예약자 전화번호] ${myreservation.reservation_tel}</p>
				</div>
			</div>
		</div>
		<div class="reservation-place-list-btn" style="border: none; align-content: center;">
			<input type="button" value="예약 정보 보기">
		</div>
	</div>
</c:forEach>

			<div class="bottom-list-num" style="border: none;">
				<a href="UserReservationPageC?p=1"> <<  </a>
				<c:forEach begin = "1" end = "${pageCount }" var = "i">
					<a href="UserReservationPageC?p=${i }">[${i }] </a>
				</c:forEach>
				<a href="UserReservationPageC?p=${pageCount }"> >> </a>
			</div>

			<div class="bottom-return-btn"
				style="border: none; align-content: center;">
				<input onclick="location.href='UserC'" type="button" value="마이페이지로 돌아가기">
			</div>

</body>
</html>