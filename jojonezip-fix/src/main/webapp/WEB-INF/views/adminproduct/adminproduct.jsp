<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>상품 목록</title>
  <link rel="stylesheet" href="/css/adminproduct.css">
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
    <div class="adminmp-productlist-header">
		<div class="adminmp-productlist-hhd">
      <div style="font-size:14px; font-weight: bold;">상품 관리</div>
	  <div class="adminmp-productlist-btn">
	    <button type="button" class="productinsert-btn" onclick="location.href='/addproductlist'">상품 등록</button>
	  </div>
  </div>
      <div class="section-divider"></div>

      <!-- 등록 버튼 -->

	  <div class="product-list-wrapper">
	    <c:forEach var="product" items="${product}">
	      <a href="/adminproductdetail?productId=${product.product_id}" class="adminmp-productlist-item">
	        <div class="adminmp-productlist-info">
	          <input type="hidden" name="productCategory" value="${product.product_category}" />
	          <input type="hidden" name="productId" value="${product.product_id}" />
	          <img src="/img/${product.product_image}?v=${product.product_id}" class="product-img" alt="상품 이미지"/>
	          <div>${product.product_name}</div>
	        </div>
	      </a>
	    </c:forEach>
	  </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/main/footer.jsp" />
</body>
</html>
