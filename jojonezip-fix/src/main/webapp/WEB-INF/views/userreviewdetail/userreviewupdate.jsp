<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP</title>

<link rel="stylesheet" href="/css/usermypage.css">
</head>
	<body>
		<div class="page-wrapper">
		  <jsp:include page="/WEB-INF/views/main/header.jsp" />
		  
		  <div class="usermp-detail-container">

		  	  <!-- ✅ 새로운 래퍼로 사이드바와 본문 묶기 -->
		  	  <div class="usermp-main-content" style="display: flex; flex: 1;">
		  	    <!-- 사이드바 -->
		  	    <div class="usermp-sidebar-container">
		  	      <div class="usermp-sidebar-list">
		  	        <div onclick="location.href='/usermypage'">MYPAGE</div>
		  	        <div onclick="location.href='/userorder'">주문내역</div>
		  	        <div onclick="location.href='/userreview'">리뷰목록</div>
		  	        <div onclick="location.href='/userqna'">문의내역</div>
		  	      </div>
		  	    </div>

		  	    <!-- 본문 -->
		  	    <div class="usermp-review-detail-subpage-header">
		  	      <div class="usermp-review-detail-header">
					<span>리뷰 수정</span>
					<div class="review-section-divider"></div>

					<div class="usermp-review-updateform"> 
						<div class="review-form-wrapper">
						
							  <form method="post" action="/review/update" enctype="multipart/form-data">
						    <input type="hidden" name="review_id" value="${review.review_id}">

						    <label>리뷰 제목</label>
						    <input class="review-input" type="text" name="review_title" value="${review.review_title}" required>

						    <label>리뷰 내용</label>
						    <textarea class="review-textarea" name="review_text" required>${review.review_text}</textarea>
							

							<c:if test="${not empty review.review_image}">
							  <div>
							    <p>현재 등록된 이미지:</p>
							    <img src="/img/review/${review.review_image}" alt="기존 리뷰 이미지" width="200" />
							  </div>
							</c:if>

							<label>새 이미지 선택 (선택 사항)</label>
							<input type="file" name="imageFile" accept="image/*" />

							
							
							
						    <div class="review-section-divider"></div>

							
							
						    <div class="review-update-btn-class">
						      <button type="submit">저장</button>
						      <button type="button" onclick="location.href='/userreviewdetail'">취소</button>
						    </div>
						  </form>
						</div>
		        </div>
		      </div>
		    </div> <!-- ✅ usremp-main-content 끝 -->

		  </div> <!-- ✅ usermp-container 끝 -->
		  <jsp:include page="/WEB-INF/views/main/footer.jsp" />
		</div>
			</body>


</html>