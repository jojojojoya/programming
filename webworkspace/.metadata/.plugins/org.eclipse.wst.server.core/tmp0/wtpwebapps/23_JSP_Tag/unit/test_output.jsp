
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	body{
		padding: 0;
		margin: 0;
		color : black;
	}
	.container{
		width:300px;
		height:300px;
		background-color: lightskyblue;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		
	}
	.title{
		width:290px;
		height:50px;
		margin: 10px;
		background-color: white;
		display: flex;
		justify-content: center;
		align-items: center;
		color : black;
	}
	.result{
		width:100px;
		height:50px;
		margin: 10px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.arrow{
		width:150px;
		height:50px;
		margin: 10px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.result2{
		width:150px;
		height:50px;
		margin: 10px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.button{
		width:250px;
		height:50px;
		margin: 10px;
		background-color: white;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	button{
		width:250px;
		height:50px;
		border: 0;
		background-color: transparent;
		color : black;
	}
	.container.cm {
		background-color: #D8BFF1;
	}
	.container.m2 {
		background-color: #CEF4E5;
	}
	.container.cf {
		background-color: #EFF1BF;
	}
	.container.km {
		background-color: pink;
	}
	
	/* span 색상 클래스 추가 */
	span.cm {
		color: #FB8BD5;
	}
	span.m2 {
		color: green;
	}
	span.cf {
		color: #9D9B6D;
	}
	span.km {
		color: #FCD1F9;
	}
	
</style>

</head>
<body>


<form action="test.jsp">
<div class="${vo.containerClass}">
		<div class="title"> 변환결과 </div>
		<div class="result${vo.val}">${vo.num} <span class="${vo.spanClass}">${vo.unitFrom}</span></div>
		<div class="arrow"> ↓ </div>
		<div class="result2${vo.val}">${vo.result} <span class="${vo.spanClass}">${vo.unitTo}</span></div>
		<div class="button"><button> 돌아가기 </button></div>
		<!-- <div class="button"><button onclick="history.back()"> 돌아가기 </button></div> -->
	</div>
</form>
</body>
</html>