<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP</title>

<link rel="stylesheet" href="/css/usermypage.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>

	<div class="usermp-container">
		<div class="usermp-sidebar-container">
			<div class="usermp-sidebar-list">
		<div onclick="location.href='/usermypage'">MYPAGE</div>
				<div onclick="location.href='/userorder'">주문내역</div>
				<div onclick="location.href='/userreview'">리뷰목록</div>
				<div onclick="location.href='/userqna'">문의내역</div>
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
					<p><strong>주문상태:</strong>${orderDetail.orderStatus} </p>
					<p><strong>주문일시:</strong> <fmt:formatDate value="${orderDetail.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></strong></p>
				  <p><strong>수령인:</strong> ${orderDetail.orderName}</p>
				  <p><strong>전화번호:</strong> ${orderDetail.orderTel}</p>
				  <p><strong>배송지 주소:</strong> ${orderDetail.orderAddress}</p>
				</div>

			  <div class="bank-info">
			    💳 무통장 입금처: <strong>신한은행 110-494-579660 조수진</strong>
			  </div>

			  <div class="orderpagebtn">
				<c:if test="${orderDetail.orderStatus == '결제완료'}">
				<button type="button"
				        onclick="if (confirm('취소 요청하시겠습니까?')) location.href='/cancelorder?orderId=${orderDetail.orderId}'">
				  주문 취소 요청
				</button>
				</c:if>
				  <button type="button" onclick="location.href='/userorder'">주문 목록으로 </button>
				</div>
		  </div>
		</div>
		</div>
		</div>
	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>

</body>


</html>