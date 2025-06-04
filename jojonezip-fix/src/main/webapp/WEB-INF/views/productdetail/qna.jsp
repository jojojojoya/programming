<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조조네집 JOJONEZIP - info</title>

<link rel="stylesheet" href="/css/productdetail.css">
</head>
<body>

	<div class="mainpage-container">
		
		
		<div class="qna-list">
			<c:if test="${loginUser != null}">
				<div class="show-qna-form">
					<div style="margin-right:7px;"> *문의 내역 수정 및 삭제는 마이페이지에서 가능합니다.</div>

				  <button id="show-qna-form-btn">문의 작성</button>
				</div>
				<div id="qna-form-container" style="display: none; margin-top: 10px;">
				  <form method="post" action="/qna/insert">
					<input type="hidden" name="product_id" value="${productdetail.product_id}" />
					<input type="hidden" name="user_id" value="${loginUser.user_id}" />

				    <label>제목</label>
				    <input type="text" name="qna_title" required>

				    <label>내용</label>
				    <textarea name="qna_text" rows="5" required></textarea>

				    <div class="qna-form-buttons">
				      <button type="submit">등록</button>
				      <button type="button" onclick="document.getElementById('qna-form-container').style.display='none'; document.getElementById('show-qna-form-btn').style.display='block';">취소</button>
				    </div>
				  </form>
				</div>

				</c:if>
			  
			  
		  <c:forEach var="qna" items="${qnaList}">
		    <div class="qna-item">
		      <div class="qna-title">${qna.qna_title}</div>
		      <div class="qna-text">${qna.qna_text}</div>
		      <div class="qna-meta">${qna.user_id} | ${qna.qna_date}</div>
			  <br>
			  <c:choose>
			  <c:when test="${empty qna.qna_answer}">
		      <div class="qna-answer">등록된 답변이 없습니다.</div>
			  </c:when>
			  <c:otherwise>
		      <div class="qna-answer">ㄴ&nbsp;${qna.qna_answer} </div>
		      <div class="qna-meta"> admin | 관리자 </div>
			  </c:otherwise>
			  </c:choose>
		    </div>
		  </c:forEach>
		</div>

		
		<c:if test="${empty qnaList}">
		  <div> 문의 내역이 없습니다.</div>
		</c:if>
	</div>

	<script>
	  document.addEventListener("DOMContentLoaded", function() {
	    const showBtn = document.getElementById("show-qna-form-btn");
	    const formContainer = document.getElementById("qna-form-container");

	    if (showBtn && formContainer) {
	      showBtn.addEventListener("click", function() {
	        formContainer.style.display = "block";
	        showBtn.style.display = "none"; // 버튼은 숨기고 폼만 보이게
	      });
	    }
	  });
	</script>

</body>
</html>