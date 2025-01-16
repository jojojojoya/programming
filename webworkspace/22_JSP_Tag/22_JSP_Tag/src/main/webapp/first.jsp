<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--
		.jsp에 자바를 쓰는 경우
		
		- 값 읽으려고		-EL로 해결
		
		-----	Tag	-----
		
		- 조건문, 반복문
		- 출력 형식
		- 이동, 포함
		
		<> DOM 객체		<p> aaa </p>	-html	-실행할때 결국 자바코드로 실행
		
		.jsp만
		
		<접두어: xxx> 내용 </접두어: xxx>
		
		1. JSP 표준 액션 태그 (정품)
						.jsp에서 기본적으로 사용 가능
						
		2. jstl (사제 (기본적으로 못쓴다))				
						기본엔 없음.
-->
<h1>First page</h1>

<%-- 	<jsp:forward page="second.jsp"></jsp:forward> --%>
	<jsp:include page="second.jsp"></jsp:include> <!-- 위치 제어 가능함 -->


</body>
</html>