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

int a = Integer.parseInt(request.getParameter("a"));
int b = Integer.parseInt(request.getParameter("b"));
// ccc 받고 쓰기 

// 정석 
Object ccc = request.getAttribute("ccc");
Integer ccc2 = (Integer) ccc;
int ccc3 = ccc2.intValue();


// 오토박싱, 언박싱 
Object o = request.getAttribute("ccc");
int o2 = (Integer) o;



// 위에 부분을 한줄로쓰면 됨
// 최종적으로 담고 싶은데다 그냥 담고 에러나면 왼쪽 자료형의 참조버전 캐스트 처리 (기본형의 wrapper class(클래스타입));

int c = (Integer)request.getAttribute("ccc");

%>
<h1>third page</h1>
<%=a %> + <%=b %> =<%=c %> <br>
<%=a %> + <%=b %> =<%=o2 %> <br>
</body>
</html>