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
	if  (num%2 ==1) { %>
<h1 style="color : red;"> 홀 </h1>
<% } else { %>
<h1 style="color : blue;"> 짝 </h1>

<% } %>


</body>
</html>