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
//1. 값 받기.
int a = Integer.parseInt(request.getParameter("a"));
int b = Integer.parseInt(request.getParameter("b"));

int c= a+b;
request.setAttribute("ccc", c);


/*
값 
(1). parameter 
html에서 만든 값이다. - input name / url (get 요청 시 만든 문자열)
자료형 String, String[] _chkbox
그런 값이 request 객체에 실려서 전송

(2). attribute
java에서 만든 값
Object object(객체)면 다됨 
request 객체에 실려서 전송 

자동 이동 시리즈 3개
1. redirect - 공사중 / 작업완료 

2. forward 
first에서 second로 넘어올때 요청한 정보를 third까지 보내줌

forward를 주로 쓰는데, first에서 second으로 넘어온 request까지 third로 보내줌
redirect는 그냥 넘기는거  (원하는 값을 명시해서 보내는 건 가능)

foward는 request에 값이 실려서 넘어오는건데
그 request를 같이 넘기니까 third가 그 값을 쓸 수 있음

3. include(어떤 jsp속에 jsp)
관심없음.. 나중에 위치 조절되는 include 쓸꺼

*/ 

// response.sendRedirect("third.jsp");
RequestDispatcher rd  = request.getRequestDispatcher("third.jsp");
// rd.forward(request, response);


%>
<h1>second page</h1>

<%=c %>
<% rd.include(request,response); %>

</body>
</html>