
<%@page import="com.mz.bmi.Bean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="calc.css" />

</head>
<% Bean b = (Bean)
request.getAttribute("bb");
%>
<body>


		
	<div class="bmi-container">
		<div class="bmi-header">BMI 검사</div>
		<div class="bmi-body">
			<div> <img src="uploadFile/<%=b.getfName() %>"> </div>
		</div>
		<div class="bmi-body1">
			<div>이름</div>
			<div><%= b.getName() %></div>
		</div>
		<div class="bmi-body1">
			<div>키</div>
			<div><%= b.getHeight()*100 %></div>
		</div>
		<div class="bmi-body1">
			<div>체중</div>
			<div><%= b.getWeight() %></div>
		</div>
		<div class="bmi-body1">
			<div>BMi</div>
			<div><%=b.getBmi() %></div>
		</div>
		<div class="bmi-body1">
			<div>결과</div>
			<div><%=b.getStatus() %></div>
		</div>

		</div>
	</div>
	</div>
</body>
</html>