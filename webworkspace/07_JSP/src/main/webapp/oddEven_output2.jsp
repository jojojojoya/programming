<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<%
	//1. 값 받기 

	int num = Integer.parseInt(request.getParameter("num"));
	String result = "짝";
	String color = "blue";
	if (num % 2 == 1) {
		result = "홀";
		color = "red";
	}
	%>

	<h1 style="color : <%=color%>">
		<%=result%></h1>

</body>
</html>