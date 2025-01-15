<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>


	<div class="container">
		<div class="title">
			<a href="HC"> Mz's place </a>		 
		 </div>
		<div class="menu"> 
			<div><a href="M1C">[Menu1]</a></div>
			<div><a href="MovieC">[Movie]</a></div>
			<div><a href="ReviewC">[Review]</a></div>
			</div>
		<div class="content"> 
			<jsp:include page="${content }"></jsp:include>
			</div>
	
	</div>
</body>
</html>