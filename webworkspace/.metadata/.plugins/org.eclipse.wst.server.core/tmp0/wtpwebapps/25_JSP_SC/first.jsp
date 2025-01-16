<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> first page</h1>

<a href="SecondC"> a 태그 / SecondC로 get 요청 </a>
<hr>

<form action="SecondC">
<input name="a">
<button> SecondC로 get/ post 방식 요청</button>	
</form>
<hr>

<h2 onclick="location.href='SecondC?a=ㅎㅅㅎ'"> (JS) SecondC로 get 방식 요청 </h2>
</body>
</html>