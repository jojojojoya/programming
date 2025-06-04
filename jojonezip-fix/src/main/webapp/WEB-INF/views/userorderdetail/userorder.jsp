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


		<div class="usermp-subpage-header">
			<div class="usermp-order-header">
				주문내역
				<!-- 구분선 -->
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
										                <div class="usermp-orderlist-id">
										                    &nbsp;<fmt:formatDate value="${order.order_date}" pattern="yyyy-MM-dd HH:mm:ss" />
										                    &nbsp;<span class="order-status">${order.order_status}</span>
										                </div> 
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

								<script>
									document.addEventListener("DOMContentLoaded", function () {
										const statuses = document.querySelectorAll(".order-status");

										statuses.forEach(function (status) {
											const text = status.textContent.trim();
											if (text === "결제완료") {
												status.style.color = "green";
											} else if (text === "주문 취소요청") {
												status.style.color = "red";
											} else if (text === "주문 취소완료") {
												status.style.color = "black";
											} else if (text === "배송 처리완료") {
												status.style.color = "green";
											}
										});
									});

										</script>
								
								
</body>


</html>