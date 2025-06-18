<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP - Order</title>
<link rel="stylesheet" href="/css/ordercomplete.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/main/header.jsp" />

<div class="cartp-container">
  <div class="cartp-header">
    <span style="font-weight: bold; font-size:18px; margin-bottom: 14px;">주문 완료</span>

    <div>주문이 완료되었습니다. 😊</div>
    <div>* 주문 취소 및 확인은 마이페이지에서 진행해주세요.</div>
    <div class="section-divider"></div>

    <div class="cartp-profile-box">
      <c:forEach var="i" begin="0" end="${fn:length(productIds)-1}">
        <div class="product-in-cart cart-item">
          <div class="cartp-list-info">
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

      <div class="order-summary" style="margin-bottom: 20px;">
        <p style="font-weight: bold; font-size: 16px;">총 합계: ₩ 
          <fmt:formatNumber value="${totalPrice}" type="number" />
        </p>
      </div>
	  
	  <!-- 아래쪽 수령인/전화번호/주소/입금정보 -->
	  <div class="order-info-section">
	    <p><strong>수령인:</strong> ${ordername}</p>
	    <p><strong>전화번호:</strong> ${ordertel}</p>
	    <p><strong>배송지 주소:</strong> ${orderaddress}</p>
	  </div>

	  <div class="bank-info">
	    💳 무통장 입금처: <strong>신한은행 110-494-579660 조수진</strong>
	  </div>

  </div>
</div>
</div>


<jsp:include page="/WEB-INF/views/main/footer.jsp" />
</body>
</html>
