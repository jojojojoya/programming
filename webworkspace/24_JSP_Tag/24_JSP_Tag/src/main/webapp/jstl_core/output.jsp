<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{
text-align: center;
}

.item {
width: 50%;
border-bottom: 1px solid black;
}
.item-row:hover{
background-color: tomato;
cursor: pointer;
}
</style>
</head>
<body>
<!-- 

		20살이 넘으면 어서오세요
		미만이면 안녕
		
		if문이 필요한데
		
		1. jsp 표준 액션태그 : include
		
		2. jstl (사제) : jstl.jar		:사실상 기본
		(jsp standard tag lib)
		
 -->
 	<h1>당신의 나이는 ${age }</h1>
 	<!-- if문 -->
 	<c:if test="${age >= 20 }">
 		<h1>안녕하세요</h1>
 	</c:if>
 	<c:if test="${age < 20 }">
 		<h1>안녕</h1>
 	</c:if>
<%--  	<c:otherwise>????</c:otherwise> --%>
 	
 	<!-- if문, else if문 -->
 	<c:choose>
 		<c:when test="${age > 20 }">어서오세요</c:when>
 		<c:when test="${age > 10 }">어서와</c:when>
 	<c:otherwise>안녕 아가야</c:otherwise>
 	</c:choose>
 	
 	<!-- 반복문 -->
 	
 	<c:forEach begin="1" end="3" step="2">
 	<h1>ㅎㅅㅎ</h1>
 	</c:forEach>
 	
 	 <c:forEach var="aa" begin="1" end="${age }" step="3">
 		${aa }
 	</c:forEach>
 	
 	<!-- 역순 안됨 (step에 음수 x) 다른 방법 쓰면 됨 -->
 	<%-- <c:forEach var="aa" begin="${age }" end="1" step="-1">
 		${aa }
 	</c:forEach> --%>
 	
 	<hr>
 	<c:forEach var="aa" items="${ar }">
 		${aa }
 	</c:forEach>
 		<hr>
 	<c:forEach var="menu" items="${ menus}">
 		${menu.name } / ${menu.price } <br>
 	</c:forEach>
 	
 	<div style="border: 2px solid red; width: 300px">
 		<div style="display: flex;">
 			<div class="item name">번호</div>
 			<div class="item name">메뉴명</div>
 			<div class="item price">가격</div>
 		
 		</div>
 		<c:forEach var="m" items="${menus }" varStatus="st">
 		<div class="item-row" style="display: flex;">
<%--  			<div class="item index">${st.count }</div> --%>
 			<div class="item index">${st.index }</div>
 			<div class="item name">${m.name }</div>
 			<div class="item price">${m.price }</div>
 		</div>
 		</c:forEach>
 	</div>
 	
 	<hr>
 	
 	<c:forEach var="i" begin="1" end="10" step="1">
 		<c:set var="v" value="${11-i }"></c:set>
 		<c:out value="${v }"></c:out>
 	</c:forEach>
 	<hr>
 	<c:forEach var="i" begin="1" end="10" varStatus="st">
<%--  		${11-i } --%>
			${st.end+1 - i }
 	</c:forEach>
 	
 	<c:forEach var="i" begin="20" end="30" varStatus="st">
 				[ ${st.current } / ${i } ]
 	</c:forEach>
 	
 	
 	
</body>
</html>