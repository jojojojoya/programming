<%@page import="com.mz.alt.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

p :    <%=request.getParameter("p") %>    <br>
m : <%=request.getParameter("m") %>    <br>
rrr : <%=request.getAttribute("rrr") %>    <br>

<%=request.getParameter("howmuch") %>원 모자라요.
<hr>
<%=request.getParameter("asd") %>원 모자라요.
<hr>
<%=request.getAttribute("asdasd")%>원 모자라요.
<hr>
<%=request.getParameter("ex222")%> 파라미터
<hr>
<%=request.getAttribute("ex2")%> 애트리뷰투
<hr>
<hr>



</body>
</html>

