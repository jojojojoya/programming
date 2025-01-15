<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/movie.css"> 
</head>
<body>
	
	<div style="display: flex; justify-content: center;">
		<div class="movie-detail">
			<div>
				<div class="col-1">No.</div>
				<div class="col-2">${movie.no }</div>
			</div>
			<div>
				<div class="col-1">Title</div>
				<div class="col-2">${movie.title }</div>
			</div>
			<div>
				<div class="col-1">Poster</div>
				<div class="col-2"><img style="width:200px" alt="" src="jsp/movie/img/${movie.img }"></div>
			</div>
			<div>
				<div class="col-1">Actor</div>
				<div class="col-2">${movie.actor }</div>
			</div>
			<div>
				<div class="col-1">Story</div>
				<div class="col-2">${movie.story }</div>
			</div>
		</div>
	</div>

</body>
</html>