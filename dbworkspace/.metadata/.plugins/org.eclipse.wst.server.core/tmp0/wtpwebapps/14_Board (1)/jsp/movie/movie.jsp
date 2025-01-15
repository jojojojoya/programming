<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/movie.css">
<script type="text/javascript" src="js/movie.js"></script>
</head>
<body>

	<h1>Movie Page</h1>

	<div style="display: flex; justify-content: center;">
		<form action="MovieRegController" method="post" enctype="multipart/form-data">
			<div class="movie-reg">
				<div>
					<div>Title</div>
					<div>
						<input name="title">
					</div>
				</div>
				<div>
					<div>Actor</div>
					<div>
						<input name="actor">
					</div>
				</div>
				<div>
					<div>File</div>
					<div>
						<input type="file" name="file">
					</div>
				</div>
				<div>
					<div>Story</div>
					<div>
						<textarea rows="5" cols="40" name="story"></textarea>
					</div>
				</div>
				<div>
					<div>
						<button class="movie-btn">Add</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<div style="width: 100%; display: flex; justify-content: center;">
		<div class="movie-container">
			<c:forEach items="${movies }" var="m">
				<div class="movie-wrap">
					<div class="movie-img"><img height="260px" alt="" src="jsp/movie/img/${m.img }"></div>
					<div class="movie-title" onclick="location.href='MovieDetailController?no=${m.no}'">${m.title }</div>
					<div class="movie-actor">${m.actor }</div>
					<div>
						<button class="movie-btn" onclick="deleteMovie(${m.no})">삭제</button>
						<button class="movie-btn" onclick="location.href='UpdateMovieController?no=${m.no}'">수정(jsp)</button>
						<button class="movie-btn" onclick="location.href='UpdateMovieController?no=${m.no}'">수정(img/jsp)</button>
						<button class="movie-btn" onclick="updateMovie('${m.no}','${m.title}','${m.actor}')">수정(js)</button>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div style="margin-top: 40px; margin-bottom: 40px">
		<%-- <c:if test="${curPageNum > 1 }">		
			<button class="movie-btn pn" onClick="location.href='MoviePageController?p=${curPageNum - 1}'">prev</button>
		</c:if>
		<c:if test="${curPageNum < endPage }">
			<button class="movie-btn pn" onClick="location.href='MoviePageController?p=${curPageNum + 1}'">next</button>
		</c:if> --%>
		
	<%-- 	<c:if test="${curPageNum > 1 }">
			<button class="movie-btn pn" onclick="location.href='MoviePageController?p=${curPageNum - 1}'">prev</button>
		</c:if>
		<c:if test="${curPageNum == 1 }"><button class="movie-btn pn">prev</button></c:if>
		
		<c:if test="${curPageNum < endPage }">
			<button class="movie-btn pn" onclick="location.href='MoviePageController?p=${curPageNum + 1}'">next</button>
		</c:if>
		<c:if test="${curPageNum == endPage }"><button class="movie-btn pn">next</button></c:if> --%>
		
		<c:choose>
			<c:when test="${curPageNum > 1 }">			
				<button class="movie-btn pn" onclick="location.href='MoviePageController?p=${curPageNum - 1}'">prev</button>
			</c:when>
			<c:otherwise>
				<button class="movie-btn pn shake">prev</button>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${curPageNum < endPage }">
				<button class="movie-btn pn" onclick="location.href='MoviePageController?p=${curPageNum + 1}'">next</button>			
			</c:when>
			<c:otherwise>
				<button class="movie-btn pn shake">next</button>
			</c:otherwise>
		</c:choose>
	</div>
	
	
	
			<div>
			
			<a href="MoviePageController?p=1">[begin] </a>
			<c:forEach begin = "1" end = "${pageCount }" var = "i"> 
			<a href="MoviePageController?p=${i }">[${i }] </a>
			</c:forEach>
			<a href="MoviePageController?p=${pageCount }">[end] </a>
			</div>
			
	
	
	

</body>
</html>