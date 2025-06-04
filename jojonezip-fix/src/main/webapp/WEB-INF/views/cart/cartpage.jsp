<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP - Cart</title>

<link rel="stylesheet" href="/css/cart.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>

		<div class="cartp-container">
			<div class="cartp-header">
	  <span style="font-weight: bold; font-size:18px; margin-bottom: 14px;">MY CART</span>
					<div class="section-divider"></div>
				<div class="cartp-profile-box">
					
					<c:choose>
					  <c:when test="${not empty emptyCart}">
					    <div class="cart-null-notice">${emptyCart}</div>
					  </c:when>
					  <c:otherwise>
					<c:forEach var="item" items="${cartList}">
						<div class="product-in-cart cart-item" data-price="${item.productPrice}">
						<div onclick="location.href='/productdetail?productId=${item.productId}'" class="cartp-list-info">
					    <img style="width: 120px;" src="/img/${item.productImage}"> </img>
						<div class="cartp-text-info">
					    <div>${item.productName}</div>
						<div>가격: ₩ <fmt:formatNumber value="${item.productPrice}" type="number" /></div>
						<div>총 가격: ₩ <span class="total-price" style="font-weight:bold">
						  <fmt:formatNumber value="${item.quantity * item.productPrice}" type="number" />
						</span></div>

						</div>
						</div>
						
						<div class="cartp-listbtn">
						  <form action="/cart/updateQuantity" method="post" style="display: flex; align-items: center;">
						    <input type="hidden" name="productId" value="${item.productId}" />
						    
						    <div class="quantity-change" style="display: flex; align-items: center; margin-right: 8px;">
						      <button type="button" class="minus-btn">-</button>
							  <input type="text" 
							         class="quantity-input" 
							         data-product-id="${item.productId}" 
							         value="${item.quantity}" 
							         style="text-align:center; width:30px; margin: 0 5px;" />
						      <button type="button" class="plus-btn">+</button>
						    </div>

						    <button type="submit" class="product-update-btn">수량 변경</button>
						  </form>

						  <form action="/cart/delete" method="post" style="margin-left: 10px;">
						    <input type="hidden" name="productId" value="${item.productId}" />
						    <button type="submit" class="product-delete-btn">상품 삭제</button>
					

						  </form>
						</div>
						</div>

							
					<div class="section-divider"></div>
					</c:forEach>
					<form id="orderForm" action="/orderpage" method="post">
					  <c:forEach var="item" items="${cartList}">
					    <input type="hidden" name="productIds" value="${item.productId}" />
						<input type="number" 
						       name="quantities" 
						       value="${item.quantity}" 
						       data-product-id="${item.productId}" 
						       class="order-qty-input" 
						              style="width:0; height:0; border:0; padding:0; margin:0; position:absolute; left:-9999px;" />

					    <input type="hidden" name="prices" value="${item.productPrice}" />
					    <input type="hidden" name="productNames" value="${item.productName}" />
					    <input type="hidden" name="productImages" value="${item.productImage}" />
					  </c:forEach>
					  <div class="cartp-order-btn-wrapper">
					    <button type="submit" id="order-btn">주문하기</button>
					  </div>
					</form>
				  </c:otherwise>
				</c:choose>
			</div>
			</div>
		</div>



		<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>
	<script type="text/javascript" src="/js/cartpage.js"></script>
</body>


</html>