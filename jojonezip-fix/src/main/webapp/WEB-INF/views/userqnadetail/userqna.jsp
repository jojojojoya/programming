<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP </title>

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
			<div class="usermp-qna-header">
				<span>문의내역</span>

				<!-- 구분선 -->
				<div class="section-divider"></div>

				<!-- 문의 내역 목록 -->
				<div class="usermp-qnalistbox">
					<c:choose>
						<c:when test="${empty qnaList}">
							<div class="usermp-null-notice"> 문의 내역이 없습니다. </div>	
						</c:when>
						<c:otherwise>
					<c:forEach var="qna" items="${qnaList}">
						<a href="/userqnadetail?qnaId=${qna.qna_id}" class="qna-link">
	
						<div class="usermp-qnalist-item">
							<div class="usermp-qnalist-prname">
								[${qna.product_name}] ${qna.qna_title}
							</div>
							<div class="usermp-qnalist-iddate">
								<div class="usermp-qnalist-id">${qna.user_id}&nbsp;${qna.qna_date}</div>
							</div>
						</div>
						</a>
						
					</c:forEach>
				</c:otherwise>
					</c:choose>
				</div>

			</div>
		</div> <!-- .usermp-header end -->

	</div> <!-- .usermp-container end -->

	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>
</body>


</html>