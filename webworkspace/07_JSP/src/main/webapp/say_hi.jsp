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

	int age = Integer.parseInt(request.getParameter("age"));
	if  (age >= 20) { %>
<h1> 안녕하세요 </h1>
<% } else { %>
<h1> 안녕 </h1>

<% } %>

</body>
</html>