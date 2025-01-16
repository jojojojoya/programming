<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="calc.css" />

</head>
<body>

<% 
	
	
	// 1. 경로설정
	//로컬 경로
//	String path = "C:/csj97/dbworkspace/07_BMI";
	//서버상 경로
	String path = request.getServletContext().getRealPath("uploadFile");
	System.out.println(path);
	
	//cos.jar 
	// 2. multipart 객체 생성 => 업로드 기능
	MultipartRequest mr = new MultipartRequest (request, path, 1024*1024*20, // 최대 허용 용량 20mb
			
			"utf-8", new DefaultFileRenamePolicy()); // 중복처리 
		
	
	//3. 값 mr로 박스포장 까서 값 얻어야됨
	String name = mr.getParameter("name");
	double weight = Double.parseDouble(mr.getParameter("weight"));
	double height = Double.parseDouble(mr.getParameter("height"));
	String fName = mr.getFilesystemName("imgFile");
	
	
		if (height > 10){
			//cm로 입력했을 것
			height /= 100;	
		} double bmi = (weight / (height*height));

		System.out.printf("BMI 지수 : %.1f\n",bmi);
		// printf > 자료형 변환  // f > 소수점 // d > 정수
		
String status = "3단계 비만";
 if(bmi < 18.5) {
status = "저체중";
} else if (bmi <= 22.9) {
status = "정상";
} else if (bmi <= 24.9) {
status = "비만전단계";

} else if (bmi <= 29.9) {
status = "1단계 비만";
} else if (bmi <= 34.9) {
status = "2단계 비만";
}
 
String bmi1 =  String.format("%.1f", bmi);

		%>
		
	<div class="bmi-container">
		<div class="bmi-header">BMI 검사</div>
		<div class="bmi-body">
			<div> <img src="uploadFile/<%=fName %>"> </div>
		</div>
		<div class="bmi-body1">
			<div>이름</div>
			<div><%= name %></div>
		</div>
		<div class="bmi-body1">
			<div>키</div>
			<div><%= height*100 %></div>
		</div>
		<div class="bmi-body1">
			<div>체중</div>
			<div><%= weight %></div>
		</div>
		<div class="bmi-body1">
			<div>BMi</div>
			<div><%=bmi1 %></div>
		</div>
		<div class="bmi-body1">
			<div>결과</div>
			<div><%=status %></div>
		</div>

		</div>
	</div>
	</div>
</body>
</html>