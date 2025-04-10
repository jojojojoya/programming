<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="index.css">
</head>
<body>
			<div class="container">
			
				<div class="title" style="position: relative;">
					<a href="HC"> Mz's place </a> <span style="color: red;">${result }</span>
					<div style="position: absolute; right: 10px">
						<jsp:include page="${loginPage }"></jsp:include>
					</div>
				</div>
				
				<div class="menu">
					<div> <a href="M1C"> menu1</a></div>
					<div><a href="M2C">menu2 </a></div>
				</div>
				
				<div class="content">
					<jsp:include page="${content }"></jsp:include>
				</div>
			
			
			
			
			</div>




</body>
</html>