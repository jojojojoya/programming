<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP - Review</title>

<link rel="stylesheet" href="/css/productdetail.css">
</head>
<body>
	
	<div class="mainpage-container">
<!--리뷰 작성 폼-->
<c:if test="${canWriteReview}">
  <div class="show-review-form">
    <div style="margin-right:7px;"> *리뷰 내역 수정 및 삭제는 마이페이지에서 가능합니다.</div>
    <button id="show-review-form-btn">리뷰 작성</button>
  </div>
  <div id="review-form-container" style="display: none; margin-top: 10px;">
    <form method="post" action="/review/insert" enctype="multipart/form-data">
      <input type="hidden" name="product_id" value="${productdetail.product_id}" />
      <input type="hidden" name="user_id" value="${loginUser.user_id}" />
      <label>제목</label>
      <input type="text" name="review_title" required>
      <label>내용</label>
      <textarea name="review_text" rows="5" required></textarea>
      <label>이미지</label>
      <input type="file" name="imageFile" accept="image/*" />
      <div class="review-form-buttons">
        <button type="submit">등록</button>
        <button type="button" onclick="document.getElementById('review-form-container').style.display='none'; document.getElementById('show-review-form-btn').style.display='block';">취소</button>
      </div>
    </form>
  </div>
</c:if>

			
<!--			하단 기존 리뷰 디테일-->
		<div class="review-list">
		  <c:forEach var="reviews" items="${reviewList}">
		    <div class="review-item">
		      <div class="review-title">${reviews.review_title}</div>
		      <div class="review-text">${reviews.review_text}</div>
			  <c:if test="${not empty reviews.review_image}">
				
		      <img src="/img/review/${reviews.review_image}" alt="리뷰 이미지" width="200"/>
		   		</c:if>
			     <div class="review-meta">${reviews.user_id} | ${reviews.review_date}</div>
				
				 <c:if test="${not empty reviews.review_answer}">
					<br>
				   <div class="review-answer">ㄴ&nbsp;${reviews.review_answer} </div>
				   <div class="review-meta"> admin | 관리자 </div>
				 </c:if>

				 
		    </div>
		  </c:forEach>
		</div>

		<c:if test="${empty reviewList}">
		  <div>리뷰가 없습니다.</div>
		</c:if>
	</div>


<script>
	document.addEventListener("DOMContentLoaded",function() {
		const showBtn = document.getElementById("show-review-form-btn");
		const formContainer = document.getElementById("review-form-container");
		
		if(showBtn && formContainer) {
			showBtn.addEventListener("click", function() {
				formContainer.style.display = "block";
				showBtn.style.display = "none";
			})
		}
	});
	</script>

</body>


</html>