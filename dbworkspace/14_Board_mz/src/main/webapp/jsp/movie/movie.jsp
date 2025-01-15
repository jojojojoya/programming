<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1> - Movie Page - </h1>
	<div style="display: flex; justify-content: center;">
		<form action="MovieAddController" method="post" enctype="multipart/form-data">
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

	<c:forEach var="movie" items="${movies }">
			<div class="movie-wrap">

				<div class="movie-img"> 
					<img alt="" src="jsp/movie/movieFile/${movie.m_img }">
				</div>			
				<div class="movie-title" onclick="location.href='MovieDetailController?no=${movie.m_no}' ">${movie.m_title }</div>			
				<div class="movie-actor">${movie.m_actor }</div>			
				<div>
					<button class="movie-btn" onclick="deleteMovie('${movie.m_no}')">삭제</button>
					<button class="movie-btn" onclick="location.href='DetailUpdateMovieC?no=${movie.m_no}'">수정(jsp)</button>
					<button class="movie-btn" onclick="location.href='DetailUpdateMovieC?no=${movie.m_no}'">수정(img]jsp)</button>
					<button class="movie-btn" onclick="updateMovie('${movie.m_no }','${movie.m_title }','${movie.m_actor }')">수정(js)</button>
				</div>	

			</div>
	</c:forEach>

		</div>

	</div>
			<div>
			
<%-- 			<c:if test="${curPageNum != 1}">
			 <button class="movie-btn pn"  onclick="location.href='MoviePageC?p=${curPageNum - 1}'"> prev </button>
			 </c:if>
			 <c:if test="${curPageNum != pageCount }">
			 <button class="movie-btn pn" onclick="location.href='MoviePageC?p=${curPageNum + 1}'"> next </button>
				</c:if>
--%>				 

<c:choose>
 			<c:when test="${curPageNum != 1}">
			 <button class="movie-btn pn"  onclick="location.href='MoviePageC?p=${curPageNum - 1}'"> prev </button>
			 </c:when>
				<c:otherwise> 
			 <button class="movie-btn pn shake"> prev </button>
			 </c:otherwise>
			 </c:choose>
			 
			 
<c:choose>
 			<c:when test="${curPageNum != pageCount}">
			 <button class="movie-btn pn" onclick="location.href='MoviePageC?p=${curPageNum + 1}'"> next </button>
			 </c:when>
				<c:otherwise> 
			 <button class="movie-btn pn shake"> next </button>
			 </c:otherwise>
			 </c:choose>

				 
				 </div>


</body>
</html>