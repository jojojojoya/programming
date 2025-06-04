<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>관리자 유저 목록</title>
  <link rel="stylesheet" href="/css/adminreviews.css">
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
    <div class="adminmp-reviewlist-header">
		<div class="adminmp-reviewlist-hhd">
      <div style="font-size:14px; font-weight: bold;">리뷰 관리</div>
</div>
      <div class="section-divider"></div>


	  <c:forEach var="reviews" items="${reviews}">
	    <a href="/adminreviewdetail?reviewId=${reviews.review_id}" class="adminmp-reviewlist-item">
	      <div class="adminmp-reviewlist-info">
	        <div>${reviews.product_name}&nbsp;|&nbsp;${reviews.review_title}</div>
	        <div>${reviews.user_id} | ${reviews.review_date}</div>
	      </div>
	      <c:choose>
	        <c:when test="${not empty reviews.review_answer}">
	          <div class="answer-status answer-complete">답변완료</div>
	        </c:when>
	        <c:otherwise>
	          <div class="answer-status answer-pending">답변대기</div>
	        </c:otherwise>
	      </c:choose>
	    </a>
	  </c:forEach>


        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/main/footer.jsp" />
</body>
</html>
