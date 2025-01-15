<%@page import="com.mz.conv.Vo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="NewFile.css">
</head>
<body>

<% 

	Vo v = (Vo)request.getAttribute("vv");

%>


	<div class="container" style="background-color : <%=v.getColor()%>">
            <div class="contents">
                <div class="title">
                   변환 결과</div>
                <div class="input">
                    <%=v.getI() %>
                    <span style="color : <%=v.getColor()%>">
                    <%=v.getLabel() %></span>
                </div>
                <label>↓</label>
                <div class="input">
                    <span><%=v.getStr() %></span><span  style="color : <%=v.getColor2()%>"><%=v.getLabel2() %></span>
                </div>
                <a href="http://localhost/08_JSP_Unit/NewFile.html">돌아가기</a>
            </div>
        </div>



