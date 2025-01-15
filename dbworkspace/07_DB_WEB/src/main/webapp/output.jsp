<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>



	<table border="1">
	
	<tr>
	<td> 이름 </td>
	<td> 나이 </td>
	</tr>
	
	<c:forEach var="h" items= "${humans }"> 
	<tr>
	<td>${h.name } </td>
	<td>${h.age } </td>
	</tr>
	</c:forEach> 
	
	
	</table>	
</body>
</html>