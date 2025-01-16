<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <% String id = request.getParameter("id"); String pw =
    request.getParameter("pw"); String msg = null; if(id.equals("sj")) {
    if(pw.equals("1004")) { msg = "로그인 성공!"; } else { msg = "비번 오류!"; }
    } else { msg = "존재하지 않는 회원 !"; } %> ID : <%=id%> <br />
    PW : <%=pw%> <br />
    <h1><%=msg %></h1>
  </body>
</html>
