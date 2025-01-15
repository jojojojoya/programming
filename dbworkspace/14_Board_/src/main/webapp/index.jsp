<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css">
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="js/review.js"></script>

</head>
<body>

	<div class="container">
		<div class="title">
			<a href="HomeController">Ce's place</a>
		</div>
		<div class="menu">
			<div><a href="Menu1Controller">[Menu1]</a></div>
			<div><a href="MovieController">[Movie]</a></div>
			<div><a href="ReviewController">[Review]</a></div>
		</div>
		<div class="content">
			<jsp:include page="${content }"></jsp:include>
		</div>
	</div>

</body>
</html>