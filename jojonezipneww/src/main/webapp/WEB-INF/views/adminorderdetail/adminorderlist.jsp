<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>관리자 주문 목록</title>
  <link rel="stylesheet" href="/css/adminorders.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/main/header.jsp" />

<div class="adminmp-container">

  <!-- 사이드바 -->
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

  <!-- 본문 -->
  <div class="adminmp-subpage-header">
    <div class="adminmp-orderlist-header">
		<div class="adminmp-orderlist-hhd">
      <div style="font-size:14px; font-weight: bold;">주문 관리</div>
</div>
      <div class="section-divider"></div>
	  

	  			<div class="adminmp-orderlistbox">
	  			<c:choose>
	  								<c:when test="${empty orderList}">
	  									<div class="adminmp-null-notice"> 주문한 내역이 없습니다. </div>	
	  									</c:when>
	  									<c:otherwise>
	  								
	  									<c:forEach var="order" items="${orderList}">
	  									    <a href="/adminorderdetail?orderId=${order.order_id}" class="order-link">
	  									        <div class="adminmp-orderlist-item">
	  									            <div class="adminmp-orderlist-prname">
	  									              주문번호&nbsp;${order.order_id}&nbsp;-&nbsp;${order.product_name}
	  									            </div>
	  									            <div class="adminmp-orderlist-iddate">
														<div class="adminmp-orderlist-id">
														  &nbsp;<fmt:formatDate value="${order.order_date}" pattern="yyyy-MM-dd HH:mm:ss" /> 
														  &nbsp;<span class="order-status">${order.order_status}</span>
														</div>
														</div>

	  									        </div>
	  									    </a>
	  									</c:forEach>

	  											</c:otherwise>
	  											</c:choose>
	  		</div>
	  

        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/main/footer.jsp" />

<script>
	document.addEventListener("DOMContentLoaded", function () {
		const statuses = document.querySelectorAll(".order-status");

		statuses.forEach(function (status) {
			const text = status.textContent.trim();
			if (text === "결제완료") {
				status.style.color = "green";
			} else if (text === "주문 취소요청") {
				status.style.color = "red";
			} else if (text === "주문 취소완료") {
				status.style.color = "black";
			} else if (text === "배송 처리완료") {
				status.style.color = "green";
			}
		});
	});

		</script>

</body>
</html>
