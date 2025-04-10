<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/review.css">
</head>
<body>

	<form action="ReviewRegController" method="post">
		<div class="review-wrap">
			<div>
				<div>
					<div class="review-reg-col">Title</div>
					<div class="review-reg-col2">
						<input name="title">
					</div>
				</div>
				<div>
					<div class="review-reg-col">Text</div>
					<div class="review-reg-col2">
						<textarea name="txt" maxlength="200"></textarea>
						<br><span id="cntSpan">0</span> / 200
					</div>
				</div>
				<div>
					<button class="review-reg-btn">Post</button>
				</div>
			</div>
		</div>
	</form>

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