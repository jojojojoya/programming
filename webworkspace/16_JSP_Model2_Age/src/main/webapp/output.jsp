<%@page import="com.mz.age.Bean"%>
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

Bean b = (Bean)request.getAttribute("bb");

%>


<h1> <%=b.getBirth() %>년생 이군요, 당신의 나이는 <%=b.getAge()%>살 입니다.</h1>











</body>
</html>