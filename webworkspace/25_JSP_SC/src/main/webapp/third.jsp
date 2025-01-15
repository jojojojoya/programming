<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> third page</h1>

파라미터 : ${param.a } <br>
어트리뷰트 : ${b } ${param.b }<br>
<h1> 세션 : ${c } </h1><br>
<h1> 세션 : ${sessionScope.c } </h1><br> 
<h1> 쿠키 : ${cookie.d.value } </h1><br> 




</body>
</html>