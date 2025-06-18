<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP - Details</title>

<link rel="stylesheet" href="/css/productdetail.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>

	<div class="mainpage-container">
	  <!-- 상품 상단 정보 -->
	  <div class="product-container">	
	      <div class="product-thumb-container">
	          <img src="/img/${productdetail.product_image}" alt="상품이미지" class="product-img">
	        </div>
			
	      <div class="product-info-container">
	        <div class="product-name">${productdetail.product_name}</div>
	        <div class="product-price">₩ <fmt:formatNumber value="${productdetail.product_price}" type="number" /></div>
	        <div class="product-detail-text">${productdetail.product_introtext}</div>

	        <div class="product-amount-input-container">
			  <button type="button" class="minus-amount-btn"> - </button>
			  <input type="text" class="amount-input" value="1" style="text-align:center; width: 30px;" />

			  <button type="button" class="plus-amount-btn"> + </button>
			</div>

			<div class="product-act-container">
				<form action="/buydirect" method="post">
				    <input type="hidden" name="productId" value="${productdetail.product_id}" />
				    <input type="hidden" name="productName" value="${productdetail.product_name}" />
				    <input type="hidden" name="productPrice" value="${productdetail.product_price}" />
				    <input type="hidden" name="quantity" id="cart-quantity-input" value="1" />
				    <button type="submit" id="buy-product-btn">구매하기</button>
				</form>

			  <form action="/cart/add" method="post"  id="add-to-cart-form">
			    <input type="hidden" name="productId" value="${productdetail.product_id}" />
				<input type="hidden" name="quantity" id="cart-add-quantity-input" value="1" />
			    <button type="submit" class="cart-icon" id="add-to-cart-btn">
           <img src="/img/addtocart.png" alt="장바구니" />
			    </button>
			  </form>


			</div>
		  </div>
		</div>

	  <!-- 탭 메뉴 -->
	  <div class="product-tab-container">
	    <div class="product-tab-header">
			<a href="/productdetail?productId=${productdetail.product_id}&tab=info" class="tab-btn ${tab eq 'info' ? 'active' : ''}">INFO</a>
			<a href="/productdetail?productId=${productdetail.product_id}&tab=review" class="tab-btn ${tab eq 'review' ? 'active' : ''}">REVIEW</a>
			<a href="/productdetail?productId=${productdetail.product_id}&tab=qna" class="tab-btn ${tab eq 'qna' ? 'active' : ''}">Q&A</a>
	    </div>

	    <!-- 탭 내용 영역 각각 따로 분리해서 출력 -->
	    <div class="product-tab-content ${tab eq 'info' ? 'active' : ''}">
	      <c:if test="${tab eq 'info'}">
	        <jsp:include page="/WEB-INF/views/productdetail/info.jsp"/>
	      </c:if>
	    </div>

	    <div class="product-tab-content ${tab eq 'review' ? 'active' : ''}">
	      <c:if test="${tab eq 'review'}">
	        <jsp:include page="/WEB-INF/views/productdetail/review.jsp"/>
	      </c:if>
	    </div>

	    <div class="product-tab-content ${tab eq 'qna' ? 'active' : ''}">
	      <c:if test="${tab eq 'qna'}">
	        <jsp:include page="/WEB-INF/views/productdetail/qna.jsp"/>
	      </c:if>
	    </div>
	  </div>
	</div>

	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>
	<script type="text/javascript" src="/js/productdetail.js"></script>
</body>
</html>
