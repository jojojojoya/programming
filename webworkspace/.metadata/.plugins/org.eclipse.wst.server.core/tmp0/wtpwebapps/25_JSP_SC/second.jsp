<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> second page</h1>

파라미터 : ${param.a } <br>
어트리뷰트: ${b } <br>
 
 
 세션 : ${c } <br>
 쿠키 : ${cookie.d.value }<br>
 
 		<a href="ThirdC"> a 태그 (ThirdC로 get 방식 요청)</a>
 		
 		
 		<hr> 
 		<form action="ThirdC">
 			<input name ="a" value= "${param.a }" readonly>
 			<button> 고고! </button>
 		</form>
 		
 		<form action="ThirdC">
 			<input name ="b" value= "${b }">
 			<button> 고고! </button>
 		</form>
 		
</body>
</html>