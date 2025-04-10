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

String id = request.getParameter("id");
String pw = request.getParameter("pw");

%>

ID : <%=id%> <br>
PW : <%=pw%> <br>
<% 
	//로그인 판정
	
	if(id.equals("sj")) {
	if(pw.equals("1004")) {
%>
			<h1>로그인 성공 </h1>
<% } else { %>

			<h1>비밀번호 오류 </h1>

<% } }else { %> 
			<h1>존재하지 않는 id  </h1>
<% }%>


</body>
</html>