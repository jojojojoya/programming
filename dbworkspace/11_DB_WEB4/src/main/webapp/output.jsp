<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>




<style type="text/css">
.wrap {
    width: 300px;
    border: 2px solid black;
    border-radius: 10px;
    text-align: center;
    font-size: 15pt;
}
.wrap > div{
    border-bottom: 1px dotted; 
    height: 35px;
}
.wrap > div:hover{
    background-color: #eca6ec59;
    cursor: pointer;
}
.name {
    width: 50%;
    border-right: 1px solid;
}

.age {
    width: 50%;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript">

function deleteMem(no) {
	let ok = confirm("really");
	if (ok) { 
		location.href = 'DeleteMemberC?noo=' + no;
		
	}
	
}

function updateMem(name,num) {
	
	let nnn = prompt("이름 변경 무엇으로",name);
	if (nnn != null, nnn !="") {
		location.href = 'UpdateMemberC?name=' + nnn + '&num=' + num;
		
	}
	
	
}
</script>


</head>
<body>

	<div class="wrap">

		<div style="display: flex;">
			<div class="name">NAME</div>
			<div class="age">AGE</div>
		</div>
	<c:forEach var="m" items="${members }">
		<div style="display: flex;">
			<div class="name" onclick="updateMem('${m.m_name }','${m.m_no }')">${m.m_name }</div>
			<div class="age">${m.m_age }</div>
				<button onclick="deleteMem('${m.m_no }')"> 삭제 </button>
		</div>
	</c:forEach>

	</div>

</body>
</html>