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
		  	    <div class="usermp-qna-detail-subpage-header">
		  	      <div class="usermp-qna-detail-header">
					<span>문의 수정</span>
					<div class="qna-section-divider"></div>

					<div class="usermp-qna-updateform"> 
						<div class="qna-form-wrapper">
						  <form method="post" action="/qna/update">
						    <input type="hidden" name="qna_id" value="${qna.qna_id}">

						    <label>문의 제목</label>
						    <input class="qna-input" type="text" name="qna_title" value="${qna.qna_title}" required>

						    <label>문의 내용</label>
						    <textarea class="qna-textarea" name="qna_text" required>${qna.qna_text}</textarea>

						    <div class="qna-section-divider"></div>

						    <div class="qna-update-btn-class">
						      <button type="submit">저장</button>
						      <button type="button" onclick="location.href='/userqnadetail'">취소</button>
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