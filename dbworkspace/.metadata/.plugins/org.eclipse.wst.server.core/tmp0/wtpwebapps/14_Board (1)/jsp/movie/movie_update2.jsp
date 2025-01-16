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
	<form action="UpdateMovieController" method="post" enctype="multipart/form-data">
		<div style="display: flex; justify-content: center;">
			<div class="movie-detail">
				<div>
					<div class="col-1">No.</div>
					<div class="col-2">
						<input value="${movie.no }" disabled="disabled">
						<input type="hidden" name="no" value="${movie.no }">
					</div>
				</div>
				<div>
					<div class="col-1">Poster</div>
					<div class="col-2">
						<img style="width:200px" alt="" src="jsp/movie/img/${movie.img }"><br>
						<input name="newImg" type="file"><input type="hidden" name="oldImg" value="${movie.img }">
					</div>
				</div>
				<div>
					<div class="col-1">Title</div>
					<div class="col-2"><input name="title" value="${movie.title }"></div>
				</div>
				<div>
					<div class="col-1">Actor</div>
					<div class="col-2"><input name="actor" value="${movie.actor }"></div>
				</div>
				<div>
					<div class="col-1">Story</div>
					<div class="col-2"><textarea name="story" rows="" cols="">${story2 }</textarea></div>
				</div>
				<div style="justify-content: center"><button>Done</button></div>
			</div>
		</div>
	</form>
</body>
</html>