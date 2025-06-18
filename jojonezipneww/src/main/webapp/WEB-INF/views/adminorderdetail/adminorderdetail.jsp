<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>관리자 주문 목록</title>
  <link rel="stylesheet" href="/css/adminordersd.css">
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

	  
	  <div class="cartp-container">
	    <div class="cartp-header">
	      <span style="font-weight: bold; font-size:18px; margin-bottom: 14px;">주문 내역</span>

	      <div class="section-divider"></div>

	      <div class="cartp-profile-box">
	  		<c:forEach var="product" items="${productList}">
	  		  <div class="product-in-cart cart-item">
	  		    <div class="cartp-list-info">
	  		      <img style="width: 120px;" src="/img/${product.product_image}" />
	  		      <div class="cartp-text-info">
	  		        <div><strong>상품명:</strong> ${product.product_name}</div>
	  		        <div><strong>수량:</strong> ${product.product_amount}</div>
	  		        <div><strong>가격:</strong> ₩ <fmt:formatNumber value="${product.product_price}" type="number" /></div>
	  		        <div><strong>총 가격:</strong> ₩ 
	  		          <fmt:formatNumber value="${product.product_amount * product.product_price}" type="number" />
	  		        </div>
	  		      </div>
	  		    </div>
	  		  </div>
	  		  <div class="section-divider"></div>
	  		</c:forEach>
	  		<div class="order-summary" style="margin-bottom: 20px;">
	  		     <p style="font-weight: bold; font-size: 16px;">총 합계: ₩ 
	  		       <fmt:formatNumber value="${totalPrice}" type="number" />
	  		     </p>
	  		   </div>
	  		<!-- 주문 정보 -->
	  		<div class="order-info-section">
				<p><strong>주문상태: </strong> ${orderDetail.orderStatus}</p>
				<p><strong>주문일시:</strong> <fmt:formatDate value="${orderDetail.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></strong></p>
	  		  <p><strong>수령인:</strong> ${orderDetail.orderName}</p>
	  		  <p><strong>전화번호:</strong> ${orderDetail.orderTel}</p>
	  		  <p><strong>배송지 주소:</strong> ${orderDetail.orderAddress}</p>
	  		</div>

	  	  <div class="orderpagebtn">
	  		<c:if test="${orderDetail.orderStatus == '결제완료'}">
	  		<button type="button"
	  		        onclick="if (confirm('배송 처리하시겠습니까?')) location.href='/deliveredorder?orderId=${orderDetail.orderId}'">
	  		  배송 처리
	  		</button>
	  		</c:if>
	  		<c:if test="${orderDetail.orderStatus == '주문 취소요청'}">
	  		<button type="button"
	  		        onclick="if (confirm('취소 승인하시겠습니까?')) location.href='/cancelcomplete?orderId=${orderDetail.orderId}'">
	  		  취소 승인
	  		</button>
	  		</c:if>
	  		  <button type="button" onclick="location.href='/adminorder'">주문 목록으로 </button>
	  		</div>
	    </div>
	  </div>
	  </div>
	  </div>

<jsp:include page="/WEB-INF/views/main/footer.jsp" />

</body>
</html>
