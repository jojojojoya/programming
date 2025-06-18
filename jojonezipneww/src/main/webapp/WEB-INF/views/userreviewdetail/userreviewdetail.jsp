<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP</title>

<link rel="stylesheet" href="/css/usermypage.css">
</head>
	<body>
	  <div class="page-wrapper">
	    <jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>

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
		    <div class="usermp-subpage-header">
		      <div class="usermp-review-header">
		        <span>리뷰목록</span>
		        <div class="section-divider"></div>

		        <div class="usermp-reviewlistbox">
		          <div class="usermp-reviewlist-item">
		            <div class="usermp-reviewlist-prname">
		              ${reviewDetail.product_name}&nbsp;|&nbsp;${reviewDetail.review_title}
		            </div>
		            <div class="usermp-reviewlist-iddate">
		              <span class="usermp-reviewlist-id">${reviewDetail.user_id}&nbsp;${reviewDetail.review_date}</span>
		            </div>
		          </div>
		          <div class="usermp-reviewlist-text">
					<img src="/img/review/${reviewDetail.review_image}?v=${reviewDetail.review_id}" class="review-img" />
					<div>					
		            ${reviewDetail.review_text}
		          </div>
		          </div>
				<c:if test="${not empty reviewDetail.review_answer}">
					<div class="usermp-reviewlist-prnam">&nbsp;ㄴ&nbsp;${reviewDetail.review_answer} </div>
								   <div class="usermp-answerlist-text"> admin | 관리자 </div>		
			</c:if>
				  <div class="usermp-reviewlist-btn">
				    <button type="button" onclick="location.href='/review/updateForm?reviewId=${reviewDetail.review_id}'" class="review-update-btn">수정</button>
					<form method="post" action="/review/delete" onsubmit="return confirm('정말 삭제하시겠습니까?');">
					  <input type="hidden" name="reviewId" value="${reviewDetail.review_id}">
					  <button type="submit">삭제</button>
					</form>
				    <button type="button" class="review-back-btn" onclick="location.href='/userreview'">목록으로 가기</button>
				  </div>
				  </form>

		        </div>
		      </div>
		    </div>
		  </div> <!-- ✅ usremp-main-content 끝 -->

		</div> <!-- ✅ usermp-container 끝 -->
		
			   <jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>
			  </div>
			</body>


</html>