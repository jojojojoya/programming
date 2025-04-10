<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<form id="myForm" action="HC" method="post">
		<select name="birth">
			<% for(int i = 1925; i < 2025; i++){ %>
			<option value="<%=i %>"><%=i %>년</option>
			<% } %>
		</select>
		
		<button>나이 확인</button>
	</form>
	
	

</body>
</html>