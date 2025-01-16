<%-- <%@page import="com.mz.el.Student"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- 
<%
	Student st = (Student)request.getAttribute("st");
%>
<body>
이름 : <%= request.getParameter("name")%>
<br>
중간 : <%= request.getParameter("mid") %>
<br>
기말 : <%= st.getLast() %>
<br> 
평점 : <%= st.getAvg() %>
<br> 
등급 : <%= st.getGrade() %>

 --%>
 
 <%-- 
	.jsp 파일 자바를 쓰는 경우
	- 값 읽으려고 
	- 조건문, 반복문,	
	- 출력형식 때문에 
	 
	 
	 EL(Expresstion Language)
	 	- 값 읽을때
	 	- 연산자 사용 가능 
	 	- import가 필요 없음 
	 	- 값이 없으면 넘어감 
	 	
	 	
	 	EL로 
	 	- parameter 읽기 : ${param. 파라미터 이름}
	 	- attribute 읽기 : ${어트리뷰트 이름}
	 	- object 읽기 : ${이름.멤버변수}
	 	 
 
 		기본형 [] or ArrayList <> : ${이름[인덱스]}
 			객체 [] : ${이름[인덱스].멤버변수}
 
  --%>



<hr>
이름 : ${ param.name} <br>
중간 : ${ param.mid} <br>
기말 : ${ st.last } <br>
평점 : ${ st.avg } <br>
등급 :  ${ st.grade } <br>

<hr>


a : ${a }<br>
b : ${b }<br>
c : ${c[2]}<br>
d : ${d[0].name}<br>
d : ${d[0].grade}<br>

d[1] : ${d[1].mid }/
d[1] : ${d[1].last }/
d[1] : ${d[1].avg }/
<hr>
${students}
${students[2].name}
<br>
<!-- 1번학생의 등급 -->

${students[0].grade }
<!-- 2번학생의 평점,이름 -->
${students[1].avg }
${students[1].name }
<!-- 3번학생의 이름,평점,등급 -->
${students[2].name }
${students[2].avg }
${students[2].grade }
<br>
</body>
</html>