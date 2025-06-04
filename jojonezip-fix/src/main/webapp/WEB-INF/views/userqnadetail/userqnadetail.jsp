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
			<div class="usermp-qna-header">
				<span>문의내역</span>
				<div class="section-divider"></div>

				
				    <div class="usermp-qnalistbox">
				          <div class="usermp-qnalist-item">
				            <div class="usermp-qnalist-prname">
				              [${qnaDetail.product_name}] ${qnaDetail.qna_title}
				            </div>
				            <div class="usermp-qnalist-iddate">
				              <span class="usermp-qnalist-id"> 작성자:&nbsp;${qnaDetail.user_id}&nbsp;작성일시:&nbsp;${qnaDetail.qna_date}</span>
				            </div>
				          </div>
				          <div class="usermp-qnalist-text">
				            ${qnaDetail.qna_text}
				          </div>
						  	<c:if test="${not empty qnaDetail.qna_answer}">
						  		<div class="usermp-qnalist-prnam">&nbsp;ㄴ&nbsp;${qnaDetail.qna_answer} </div>
						  					   <div class="usermp-answerlist-text"> admin | 관리자 </div>		
						  </c:if>
						  <div class="usermp-qnalist-btn">
						    <button type="button" onclick="location.href='/qna/updateForm?qnaId=${qnaDetail.qna_id}'" class="qna-update-btn">수정</button>
							
							<form action="/qna/delete" method="post" style="display: inline;">
							  <input type="hidden" name="qnaId" value="${qnaDetail.qna_id}" />
							  <button type="submit" class="qna-delete-btn" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
							</form>
							<button type="button" class="qna-back-btn" onclick="location.href='/userqna'">목록으로 가기</button>
						</div>

				        </div>
				      </div>
				    </div>
				  </div> <!-- ✅ usremp-main-content 끝 -->

				</div> <!-- ✅ usermp-container 끝 -->
	<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>

</body>


</html>