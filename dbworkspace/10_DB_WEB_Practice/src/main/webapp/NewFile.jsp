<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript">
function deleteRes(nn) {
	let ok = confirm('really?')
	if (ok) {
		location.href='DelStroeC?num='+nn;
	}
	}
	
	
function UpdStore(val, pk) {
	let name = prompt('수정할 가게명?',val);
	location.href='UpdStoreC?val='+name+'&no='+pk;
	
}
</script>
</head>
<body>




<c:forEach items="${stores }" var="ss" varStatus="st">
<h1>	
${st.count } ] ${ss.name } / ${ss.menu } / ${ss.place }  	
</h1>

		<button onclick="DelStoreC(${ss.name })"> 삭제 </button>
		<button onclick="UpdStore(${ss.name },${st.count })"> update </button>
				
</c:forEach>




</body>
</html>