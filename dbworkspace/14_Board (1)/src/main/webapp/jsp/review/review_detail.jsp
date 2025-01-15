<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/review.css">
<script type="text/javascript" src="js/review.js"></script>
</head>
<body>
	
	<div class="review-wrap">
		<div>
			<div>
				<div class="review-reg-col">Title</div>
				<div class="review-reg-col2">
					<input name="title" value="${review.title }" disabled="disabled">
				</div>
			</div>
			<div>
				<div class="review-reg-col">Text</div>
				<div class="review-reg-col2">
					<textarea name="txt" maxlength="200" disabled="disabled">${review.txt }</textarea>
					<br><span id="cntSpan">0</span> / 200
				</div>
			</div>
			<div>
				<div>
					Posted at
					<fmt:formatDate value="${review.date }"/>
				</div>
			</div>
			<div style="position: relative; bottom : -50px;">
				<button class="review-reg-btn" onclick="location.href='ReviewUpdC?no=${review.no}'" >update</button>
				<button class="review-reg-btn" onclick="deleteReview(${review.no});">delete</button>
				<button class="review-reg-btn" onclick="location.href='ReviewController'">list</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
    const textarea = document.querySelector("textarea[name='txt']");
    const cntSpan = document.querySelector("#cntSpan");
    textarea.addEventListener('input', ()=>{
        const len = textarea.value.length;
        cntSpan.innerText = len;
    });
	</script>
	
</body>
</html>