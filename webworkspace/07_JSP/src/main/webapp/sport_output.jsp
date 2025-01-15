<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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

//1. 값 받기 
String [] interest = request.getParameterValues("interest");
String result = "";

for (String inter : interest) {
    System.out.println(inter + " ");
    switch(inter){
    case "basketball":
        result += "농구 ";
        break;
    case "football":
        result += "축구 ";
        break;
    case "badminton":
        result += "배드민턴 ";
        break;
    }
  		
}
%>
<%=result%>

</body>
</html>