<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>관리자 유저 목록</title>
  <link rel="stylesheet" href="/css/adminqnasd.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/main/header.jsp" />

<div class="adminmp-container">

  <!-- 사이드바 -->
  <div class="adminmp-sidebar-container">
    <div class="adminmp-sidebar-list">
      <div onclick="location.href='/adminmypage'">MYPAGE</div>
      <div onclick="location.href='/adminuserlist'">유저관리</div>
      <div onclick="location.href='/adminorder'">주문관리</div>
      <div onclick="location.href='/adminproduct'">상품관리</div>
      <div onclick="location.href='/adminreview'">리뷰관리</div>
      <div onclick="location.href='/adminqna'">문의관리</div>
    </div>
  </div>

  <!-- 본문 -->
  <div class="adminmp-subpage-header">
    <div class="adminmp-qnalist-header">
		<div class="adminmp-qnalist-hhd">
      <div style="font-size:14px; font-weight: bold;">문의 관리</div>
</div>
      <div class="section-divider"></div>
	  <div class="adminmp-qnalist-item">
	    <!-- 헤더 영역 -->
	    <div class="qna-header-row">
	      <div class="qna-title">
	        [${qnaDetail.product_name}] ${qnaDetail.qna_title}
	      </div>
	      <c:choose>
	        <c:when test="${not empty qnaDetail.qna_answer}">
	          <div class="qna-status qna-complete">답변완료</div>
	        </c:when>
	        <c:otherwise>
	          <div class="qna-status qna-pending">답변대기</div>
	        </c:otherwise>
	      </c:choose>
	    </div>

	    <!-- 작성자 및 날짜 -->
	    <div class="qna-meta">
	      ${qnaDetail.user_id} | ${qnaDetail.qna_date}
	    </div>

	    <!-- 구분선 -->
	    <div class="qna-divider"></div>

	    <!-- 리뷰 내용 -->
	    <div class="qna-text">
	      ${qnaDetail.qna_text}
	    </div>


			    <!-- 답변 -->
			    <div class="qna-divider"></div>
				<c:choose>
				  <c:when test="${not empty qnaDetail.qna_answer}">
				    <div class="qna-answer-box">
				      <div>관리자</div>
				      ㄴ&nbsp;${qnaDetail.qna_answer}
				    </div>
				    <div class="answerbtn-class"> 
				      <button type="button" class="answer-update-btn" id="answer-update-btn">답변 수정</button>
				      <button type="button" class="answer-delete-btn" onclick="location.href='/delete/qnaanswer?qnaId=${qnaDetail.qna_id}'">답변 삭제</button>
				    </div>
				  </c:when>
				  <c:otherwise>
				    <div class="qna-answer-box qna-pending" id="qna-no-answer">답변이 없습니다.</div>
				    <button type="button" id="answer-insert-btn" class="answer-insert-btn">답변 등록</button>
				  </c:otherwise>
				</c:choose>

				<!-- ✅ 폼은 공통으로 밖에 배치 -->
				<div class="answer-insert-form" id="answer-insert-form" style="display:none;">
				  <form action="/insert/qnaanswer" method="post">
				    <input type="text" name="qna_answer" class="answer-insert-text" />
				    <input type="hidden" name="qna_id" value="${qnaDetail.qna_id}" />
				    <div class="answer-insert-form-buttons">
				      <button type="submit" class="answer-insert-confirm-btn" style="display:none;">등록</button>
				      <button type="button" class="answer-insert-cancel-btn" id="answer-cancel-btn" style="display:none;">취소</button>
				    </div>
				  </form>
				</div>

		    <div class="adminmp-qnalist-header">
			  </div>

			  </div>


		        </div>
		      </div>
		    </div>
		  </div>
		</div>

		<script>
			document.addEventListener("DOMContentLoaded", function () {
			  const showBtn = document.getElementById("answer-insert-btn");
			  const formContainer = document.getElementById("answer-insert-form");
			  const noMent = document.getElementById("qna-no-answer");
			  const confirmBtn = document.querySelector(".answer-insert-confirm-btn");
			  const updateBtn = document.getElementById("answer-update-btn");
			  const inputBox = document.querySelector(".answer-insert-text");
			  const cancelBtn = document.getElementById("answer-cancel-btn");
			  const answerBox = document.querySelector(".qna-answer-box");

			  // 답변 등록 버튼
			  if (showBtn) {
			    showBtn.addEventListener("click", function () {
			      formContainer.style.display = "block";
			      showBtn.style.display = "none";
			      if (noMent) noMent.style.display = "none";
			      confirmBtn.style.display = "inline-block";
			      cancelBtn.style.display = "inline-block";
			    });
			  }

			  // 답변 수정 버튼
			  if (updateBtn) {
			    updateBtn.addEventListener("click", function () {
			      formContainer.style.display = "block";
			      updateBtn.style.display = "none";

			      const deleteBtn = document.querySelector(".answer-delete-btn");
			      if (deleteBtn) deleteBtn.style.display = "none";

			      if (answerBox) {
			        answerBox.style.display = "none";
			        const answerText = answerBox.innerText.replace("관리자", "").replace("ㄴ ", "").trim();
			        inputBox.value = answerText;
			      }

			      confirmBtn.style.display = "inline-block";
			      cancelBtn.style.display = "inline-block";
			    });
			  }

			  // 취소 버튼
			  if (cancelBtn) {
			    cancelBtn.addEventListener("click", function () {
			      formContainer.style.display = "none";
			      if (showBtn) showBtn.style.display = "block";
			      if (updateBtn) updateBtn.style.display = "inline-block";
			      if (answerBox) answerBox.style.display = "block";
			      if (noMent) noMent.style.display = "block";
			      confirmBtn.style.display = "none";
			      cancelBtn.style.display = "none";

			      // 기존 삭제 버튼 다시 보이게
			      const deleteBtn = document.querySelector(".answer-delete-btn");
			      if (deleteBtn) deleteBtn.style.display = "inline-block";
			    });
			  }
			});

		</script>

<jsp:include page="/WEB-INF/views/main/footer.jsp" />
</body>
</html>
