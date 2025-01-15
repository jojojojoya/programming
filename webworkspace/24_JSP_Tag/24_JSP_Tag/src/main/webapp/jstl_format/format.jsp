<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#snack-tbl tr:hover {
background-color : tomato; 
color: ivory;
}


</style>
</head>
<body>
<h1> jstl format~ </h1>
<hr>
${a }<hr>
<fmt:formatNumber value="${a }"></fmt:formatNumber> <hr>
<fmt:formatNumber value="${a }" type="currency" currencySymbol="￦" ></fmt:formatNumber> <hr>
<fmt:formatNumber value="${a }" type="currency" currencySymbol="$"/> <hr>

<fmt:formatNumber value="${b }" type="percent"/> <hr>
<fmt:formatNumber value="${b }" pattern="0.00000"/><hr>
<fmt:formatNumber value="${b }" pattern="#.#####"/><hr>
<fmt:formatNumber value="${b }" pattern="0,00.000"/> <hr>
<fmt:formatNumber value="${b }" pattern="#,##.###"/> <hr>
<fmt:formatNumber value="${c }" pattern="#,##.00"/> <hr> <!-- 0은 반올림 -->

<fmt:formatDate value="${d }" /><hr>
<fmt:formatDate value="${d }" type="date" dateStyle="short"/><hr>
<fmt:formatDate value="${d }" type="date" dateStyle="long"/><hr>


<fmt:formatDate value="${d }" type="time" timeStyle="short"/><hr>
<fmt:formatDate value="${d }" type="time" timeStyle="long"/><hr>

<fmt:formatDate value="${d }" type="both" dateStyle="short" timeStyle="short"/><hr>
<fmt:formatDate value="${d }" type="both" dateStyle="long" timeStyle="long"/><hr>


<fmt:formatDate value="${d }" pattern="yyyy-MM-dd / hh:mm"/>


<hr>
<hr>
<hr>
	<table id ="snack-tbl" border="1" width="500">
	<tr>
	<th>번호</th>
	<th>과자명</th>
	<th>가격</th>
	<th>제조일</th>
	</tr>
	
	<c:forEach var="snack" items="${snacks }" varStatus = "st">
		<tr>
	<th>${st.count }</th>
	<th>${snack.name}</th>
	<th>
	<fmt:formatNumber value="${snack.price }" type = "currency"  currencySymbol="￦"/>
</th>
	<th>
	<fmt:formatDate value="${snack.jejo }"/></th>
	</tr>
	</c:forEach>
	
	
	</table>



<hr>
<hr>
<hr>

</body>
</html>