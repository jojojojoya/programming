<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%! 
// 메서드 정의는 <%!안에서 가능
	public int getMok(int a, int b){
	return a /b ;
}
%> 
<% 

	
 // 1. 값 받기 (이 공간 안에서는 java ㅂ코드를 받을 수 있음)
	int x = Integer.parseInt(request.getParameter("xx")); 
	int y = Integer.parseInt(request.getParameter("yy"));
	
	int add = x+y;
	int min = x-y;
	int mul = x*y;
	int div = x/y;
	int div2 = getMok(x,y);
	
%>

<!-- 현재 상태에서 합이 10이 넘는 경우에만 결과화면 나오게 -->


<% if(add > 10) { %>

<h1><%=x %> +  <%=y %> = <%=add %></h1>
<h1><%=x %> -  <%=y %> = <%=min %></h1>
<h1><%=x %> x  <%=y %> = <%=mul %></h1>
<h1><%=x %> /  <%=y %> = <%=div %></h1>

<% } %>


</body>
</html>