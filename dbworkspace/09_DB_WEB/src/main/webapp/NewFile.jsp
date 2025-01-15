<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function deleteMember(nooo){
	let ok = confirm("really?");
	if(ok){
		location.href='DeleteMemberC?no=' + nooo;
	}


}

</script>
</head>
<body>

	<c:forEach items="${members }" var="m" varStatus="st">
		<h1>${st.count } ) ${m.name } - ${m.age} <button onclick="deleteMember(${m.no});"> delete </button> </h1>
	</c:forEach>

	

</body>
</html>