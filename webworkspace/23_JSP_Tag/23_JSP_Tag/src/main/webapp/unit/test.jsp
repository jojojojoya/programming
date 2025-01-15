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
		margin: 5px;
		background-color: white;
		display: flex;
		justify-content: center;
		align-items: center;
		color:black;
	}
	.menu{
		width:100px;
		height:50px;
		margin: 5px;
		background-color: cornflowerblue;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.num{
		width:150px;
		height:50px;
		margin: 5px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	select{
		width:150px;
		height:50px;
		margin: 5px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.button{
		width:250px;
		height:50px;
		margin: 5px;
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
		color:black;
	}
	
	input{
		width: 150px;
		height: 50px;
	}
</style>

<script type="text/javascript">
	function valid(){
		const inputEl = document.querySelector("input[name='number']");
		if (inputEl.value == ""){
			alert("값을 입력하세요!")
			return false;
		}
	}
</script>
</head>
<body>

<form action="unitC" method="post" onsubmit="return valid()">
	<div class="container">
		<div class="title"> 단위변환 </div>
		<div class="menu"> 변환할 값 </div>
		<div class="num"> <input name="number" id="number"> </div>
		<div class="menu"> 단위 </div>
		<div class="select"> <select name="select" id="">
			<option selected disabled>단위를 선택 하세요</option>
			<option value="cm">cm → inch</option>
			<option value="m2">㎡ → 평</option>
			<option value="cf">℃ → ℉</option>
			<option value="km">km/h → mi/h</option>
		</select></div>
		<div class="button"><button> 변 환 </button></div>
		
		
		
	</div>
</form>

</body>
</html>