<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP</title>
<link rel="stylesheet" href="/css/main.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="mainpage-banner">
		<img src="/img/mainb5.jpg" alt="배너 이미지">
	</div>

	<div class="mainpage-container">
	  <div class="product-container">

	    <c:forEach var="product" items="${productList}">
	      <div class="product-item">

	        <a href="/productdetail?productId=${product.product_id}" class="product-link">
	          <div class="product-list">
	            <img src="/img/${product.product_image}" alt="상품이미지" class="product-img">
	          </div>
	          <div class="product-name">${product.product_name}</div>
	        </a>

	        <div class="price-cart-wrap">
	          <div class="product-price">₩ <fmt:formatNumber value="${product.product_price}" type="number" /></div>

	          <form action="/cart/add" method="post" style="display:inline;">
	            <input type="hidden" name="productId" value="${product.product_id}" />
	            <button type="submit" class="cart-icon" style="border:none; background:none; cursor:pointer;">
	              <img src="/img/addtocart.png" alt="장바구니" />
	            </button>
	          </form>
	        </div>

	      </div>
	    </c:forEach>

	  </div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
