
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bmi/calc.css" />

</head>
<body>


		
	<div class="bmi-container">
		<div class="bmi-header">BMI 검사</div>
		<div class="bmi-body">
			<div> <img src="uploadFile/${bb.fName }
			"> </div>
		</div>
		<div class="bmi-body1">
			<div>이름</div>
			<div>${bb.name }</div>
		</div>
		<div class="bmi-body1">
			<div>키</div>
			<div>${bb.height }</div>
		</div>
		<div class="bmi-body1">
			<div>체중</div>
			<div>${bb.weight }</div>
		</div>
		<div class="bmi-body1">
			<div>BMi</div>
			<div>${bb.bmi } %></div>
		</div>
		<div class="bmi-body1">
			<div>결과</div>
			<div>${bb.status} %></div>
		</div>

		</div>
	</div>
	</div>
</body>
</html>