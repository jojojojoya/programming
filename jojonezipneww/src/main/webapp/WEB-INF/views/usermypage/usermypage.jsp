<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
		<div class="usermp-sidebar-container">
			<div class="usermp-sidebar-list">
				<div onclick="location.href='/usermypage'">MYPAGE</div>
				<div onclick="location.href='/userorder'">주문내역</div>
				<div onclick="location.href='/userreview'">리뷰목록</div>
				<div onclick="location.href='/userqna'">문의내역</div>
			</div>
		</div>


		<div class="usermp-header">
  <span style="font-weight: bold; font-size:18px;">MYPAGE</span>
			<div class="usermp-profile-box">
				
<div class="usermp-profile-info"> 
	<div>${loginUser.user_id} (${loginUser.user_nickname})님 반갑습니다. </div>
	<div>
		지금까지 ${loginUser.user_nickname}님은 총 
		<strong>${totalCount}</strong>벌의 옷을 구매하고, 
		<strong><fmt:formatNumber value="${totalPrice}" type="number" />원</strong>을 사용하셨어요.
	</div>
</div>
				<button type="button" id="nickname-change-btn">닉네임 변경하기</button>
			</div>

			<div class="usermp-order-header">
					<div class="usermp-order-header-row"> 
						
				<span>주문목록</span>
				<a onclick="location.href='/userorder'"> → </a>
			</div>
				
			<div class="section-divider"></div>

			<div class="usermp-orderlistbox">

					<c:choose>
						<c:when test="${empty orderList}">
							<div class="usermp-null-notice"> 주문한 내역이 없습니다. </div>	
							</c:when>
							<c:otherwise>
						
							<c:forEach var="order" items="${orderList}">
							    <a href="/userorderdetail?orderId=${order.order_id}" class="order-link">
							        <div class="usermp-orderlist-item">
							            <div class="usermp-orderlist-prname">
							              주문번호&nbsp;${order.order_id}&nbsp;-&nbsp;${order.product_name}
							            </div>
							            <div class="usermp-orderlist-iddate">
							                <div class="usermp-orderlist-id">&nbsp;${order.order_date} &nbsp;<span class="order-status">${order.order_status}</span></div>
							            </div>
							        </div>
							    </a>
							</c:forEach>

									</c:otherwise>
									</c:choose>
				</div>
			</div>

			<div class="usermp-review-header">
					<div class="usermp-review-header-row"> 
						
				<span>리뷰목록</span>
				<a onclick="location.href='/userreview'"> → </a>
			</div>
				
			<div class="section-divider"></div>

			<div class="usermp-reviewlistbox">
				<c:choose>
				<c:when test="${empty reviewList}">
					<div class="usermp-null-notice"> 리뷰한 내역이 없습니다. </div>	
					</c:when>
					<c:otherwise>
				
							<c:forEach var="reviews" items="${reviewList}">
										<a href="/userreviewdetail?reviewId=${reviews.review_id}" class="reviews-link">

								<div class="usermp-reviewlist-item">
									<div class="usermp-reviewlist-prname">
										[${reviews.product_name}] ${reviews.review_title}
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
				</div>

			<div class="usermp-qna-header">
					<div class="usermp-qna-header-row"> 
				<span>문의내역</span>
				<a onclick="location.href='/userqna'"> → </a></div>
							<div class="section-divider"></div>
				
				<div class="usermp-qnalistbox">
					<c:choose>
							<c:when test="${empty qnaList}">
								<div class="usermp-null-notice"> 문의 내역이 없습니다. </div>	
								</c:when>
								<c:otherwise>
					<c:forEach var="qna" items="${qnaList}">
						<a href="/userqnadetail?qnaId=${qna.qna_id}" class="qna-link">	
										<div class="usermp-qnalist-item">
								<div class="usermp-qnalist-prname">[${qna.product_name}] ${qna.qna_title}</div>
										
											<div class="usermp-qnalist-iddate">
											<div class="usermp-qnalist-id">${qna.user_id}  ${qna.qna_date}</div>
											</div>
										</div>
										</a>
									</c:forEach>
													</c:otherwise>
													</c:choose>
				</div>
			</div>

		</div>
	</div>



	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>
<script type="text/javascript" src="/js/usermypage.js">
	
</script>

</body>


</html>