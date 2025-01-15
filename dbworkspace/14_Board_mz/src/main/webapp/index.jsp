<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/movie.css">
<link rel="stylesheet" href="css/reviews.css">
<script type="text/javascript" src="js/movie.js"></script>
</head>
<body>

	<div class="container">
		<div class="title">
			<a href="HomeController"> HY's Place </a>
		</div>
		<div class="menu">
			<div> <a href="Menu1Controller"> [Menu 1] </a> </div>
			<div> <a href="MovieController"> [Movie] </a> </div>
			<div> <a href="ReviewController"> [Review] </a> </div>
		</div>
		<div class="content">
			<jsp:include page="${content }"></jsp:include>
		</div>
	</div>



</body>
</html>