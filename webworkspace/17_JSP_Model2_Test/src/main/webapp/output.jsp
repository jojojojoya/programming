<%@page import="com.sz.test.Bean"%>
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
Bean b = (Bean)
request.getAttribute("bb");
%>




<h1>
이름 : <%=b.getName() %>
<br>
나이 : <%=b.getAge() %>
<br>
성별 : <%=b.getGen() %>
<br>
취미 : 
<% for (String s : b.getInter()) { %>
<%= s %> 
<%}%>

</h1>

</body>
</html>