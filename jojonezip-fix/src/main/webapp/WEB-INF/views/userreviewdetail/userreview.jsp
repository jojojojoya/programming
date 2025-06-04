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

	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>

	<div class="usermp-container">

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

				<!-- 구분선 -->
				<div class="section-divider"></div>

				<!-- 리뷰 리스트 박스 -->
				<div class="usermp-reviewlistbox">
					<c:choose>
							<c:when test="${empty reviewList}">
								<div class="usermp-null-notice"> 리뷰 내역이 없습니다. </div>	
							</c:when>
							<c:otherwise>
					<c:forEach var="reviews" items="${reviewList}">
								<a href="/userreviewdetail?reviewId=${reviews.review_id}" class="reviews-link">

						<div class="usermp-reviewlist-item">
							<div class="usermp-reviewlist-prname">
								${reviews.product_name}&nbsp;|&nbsp;${reviews.review_title}
							</div>
							<div class="usermp-reviewlist-iddate">
								<div class="usermp-reviewlist-id">${reviews.user_id}&nbsp;${reviews.review_date}</div>
							</div>
						</div>
						</a>

						</c:forEach>
					</c:otherwise>
						</c:choose>
				</div>

			</div> <!-- .usermp-review-header end -->

		</div> <!-- .usermp-header end -->

	</div> <!-- .usermp-container end -->

	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>

</body>
</html>
