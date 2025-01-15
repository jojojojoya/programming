<%@page import="org.eclipse.jdt.internal.compiler.ast.IfStatement"%>
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
	String addr = request.getParameter("addr");
			if(addr.equals("seoul")) { %>
		<h1> 당신의 주소는 서울입니다. </h1>
				<% } else if(addr.equals("busan")) { %>
		<h1> 당신의 주소는 부산입니다. </h1>
				<% } else { %>
		<h1> 당신의 주소는 경주입니다. </h1>
<% } %>
			
			
</body>
</html>