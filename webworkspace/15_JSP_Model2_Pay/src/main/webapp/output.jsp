<%@page import="com.mz.pay.Result"%>
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
Result r = (Result)
request.getAttribute("rr"); // 객체
%>
	
		<div>
			<div>번돈 :<%= r.getEarn() %></div>
		</div>
		<div>
			<div>쓴돈 :<%= r.getSpend() %></div>
		</div>
		<div>
			<div> 남은돈? :<%= r.getRemain() %> </div>
		</div>
		

	
</body>
</html>