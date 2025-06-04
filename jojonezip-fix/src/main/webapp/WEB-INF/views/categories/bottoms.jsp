<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP - Bottom </title>

<link rel="stylesheet" href="/css/main.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>

	<div class="mainpage-container">
	  <div class="product-container">
		
		
		
			<c:choose> 
				<c:when test="${empty productList}">
				<div class="product-null-notice"> 상품이 없습니다. </div>	
		  </c:when>
		  <c:otherwise>
			<c:forEach var="product" items="${productList}">
						  <div class="product-item">

						    <!-- 상품 상세 링크 -->
						    <a href="/productdetail?productId=${product.product_id}" class="product-link">
						      <div class="product-list">
						        <img src="/img/${product.product_image}" alt="상품이미지" class="product-img">
						      </div>
						      <div class="product-name">${product.product_name}</div>
						    </a>

						    <div class="price-cart-wrap">
								<div class="product-price">₩ <fmt:formatNumber value="${product.product_price}" type="number" /></div>

						      <!-- 카트 추가 폼 -->
						      <form action="/cart/add" method="post" style="display:inline;">
						        <input type="hidden" name="productId" value="${product.product_id}" />
						        <button type="submit" class="cart-icon" style="border:none; background:none; cursor:pointer;">
						          <img src="/img/addtocart.png" alt="장바구니" />
						        </button>
						      </form>
						    </div>
						  </div>
						</c:forEach>
				</c:otherwise>
		  </c:choose>
		
		
	  </div>
	</div>



	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>

</body>


</html>