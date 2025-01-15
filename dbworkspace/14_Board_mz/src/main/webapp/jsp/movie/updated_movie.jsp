<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="DetailUpdateMovieC" method="post">

	<h1> - Movie Update- </h1>
	
	<div style="display: flex; justify-content: center;">
	<div class="movie-detail">
	<div>	
	<div class="col-1">No.</div>
	<div class="col-2"> <input  value="${movie.m_no }" disabled ></div>
	</div>
	<div>	
	<div><img alt="" src="jsp/movie/movieFile/${movie.m_img }" ></div>
	</div>
	<div>	
	<div class="col-1">Title.</div>
	<div class="col-2"> <input name="title" value = "${movie.m_title }"></div>
	</div>
	<div>	
	<div class="col-1">Actor.</div>
	<div class="col-2"> <input  name="actor" value = "${movie.m_actor }"></div>
	</div>
	<div>	
	<div class="col-1">Story.</div>
	<div class="col-2"> <textarea  name="story" rows="" cols=""> ${movie.m_story }</textarea></div>
	
	</div>
	<button name="no" value="${movie.m_no }" >done</button>
	</div>
	</div>
</form>
</body>
</html>