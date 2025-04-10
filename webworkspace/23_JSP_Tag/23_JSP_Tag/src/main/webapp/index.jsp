<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/test_BMI.css" />

<script type="text/javascript" src="js/ZvalidCheck_Study.js"></script>
<script type="text/javascript" src="js/check.js"></script>
</head>
<body>
	
	<table id="site">
		<tr>
			<td id="title"><a href="HC">Mz's place</a></td>		
		</tr>
		<tr>
			<td id="menu">
				<a href="AC">A</a>
				<a href="BC">B</a>
				<a href="CC">C</a>
				<a href="unitC">Unit</a>
				<a href="BMIC">BMI</a>
			</td>		
		</tr>
		<tr>
			<td align="center">
				<jsp:include page="${contentPage}"></jsp:include>
			</td>		
		</tr>
	</table>
	
	
	
	
</body>
</html>