<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP - Order</title>
<link rel="stylesheet" href="/css/order.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/main/header.jsp" />

<div class="cartp-container">
  <div class="cartp-header">
    <span style="font-weight: bold; font-size:18px; margin-bottom: 14px;">주문 확인</span>
    <div class="section-divider"></div>

    <div class="cartp-profile-box">
      <c:forEach var="i" begin="0" end="${fn:length(productIds)-1}">
        <div class="product-in-cart cart-item">
          <div class="cartp-list-info">
            <!-- 이미지가 있으면 여기에 표시 -->
            <img style="width: 120px;" src="/img/${productImages[i]}" />
            <div class="cartp-text-info">
              <div><strong>상품명:</strong> ${productNames[i]}</div>
              <div><strong>수량:</strong> ${quantities[i]}</div>
              <div><strong>가격:</strong> ₩ <fmt:formatNumber value="${prices[i]}" type="number" /></div>
              <div><strong>총 가격:</strong> ₩ 
                <fmt:formatNumber value="${quantities[i] * prices[i]}" type="number" />
              </div>
            </div>
          </div>
        </div>
        <div class="section-divider"></div>
      </c:forEach>

      <!-- 총합 표시 -->
      <div class="order-summary" style="margin-bottom: 20px;">
        <p style="font-weight: bold; font-size: 16px;">총 합계: ₩ 
          <fmt:formatNumber value="${totalPrice}" type="number" />
        </p>
      </div>

      <!-- 배송지 입력 + 결제 버튼 -->
      <form action="/completeorder" id="orderForm" method="post" style="margin-top: 20px;">
        <c:forEach var="i" begin="0" end="${fn:length(productIds)-1}">
          <input type="hidden" name="productNames" value="${productNames[i]}" />
          <input type="hidden" name="productImages" value="${productImages[i]}" />
          <input type="hidden" name="productIds" value="${productIds[i]}" />
          <input type="hidden" name="quantities" value="${quantities[i]}" />
          <input type="hidden" name="prices" value="${prices[i]}" />
        </c:forEach>
		

        <div class="address-section" style="display: flex; flex-direction: column; gap: 10px;">
        <c:if test="${not empty errorMsg}">
			<div class="error-msg" style="color:red; margin-bottom:10px;">
			${errorMsg}	
			</div>
		</c:if>  
		<label for="ordername">수령인:</label>
          <input type="text" name="ordername" id="ordername" required />
          <label for="ordertel">전화번호:</label>
          <input type="text" name="ordertel" id="ordertel" required />
          <label for="address">배송지 주소:</label>
          <input type="text" name="orderaddress" id="orderaddress" required />
        </div>

        <div class="cartp-order-btn-wrapper" style="margin-top: 20px;">
          <button type="submit" id="order-btn">결제하기</button>
        </div>
      </form>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/main/footer.jsp" />
</body>
</html>
