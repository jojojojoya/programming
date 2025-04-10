<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BMI 계산</title>

</head>
<body>
<form id="myform" action="BMIC" method="post" enctype="multipart/form-data">
    <div class="container">
        <div class="title">BMI 계산</div>
        <div class="items">
            <div class="item1"><span>이름</span></div>
            <div class="item2"><input name="name" type="text" placeholder="이름 입력" /></div>
        </div>
        <div class="items">
            <div class="item1"><span>키</span></div>
            <div class="item2"><input name="height" type="text" placeholder="키 입력 (cm)" /></div>
        </div>
        <div class="items">
            <div class="item1"><span>체중</span></div>
            <div class="item2"><input name="weight" type="text" placeholder="체중 입력 (kg)" /></div>
        </div>
        <div class="items">
            <div class="item1"><span>사진</span></div>
            <div class="item2"><input type="file" name="imgFile" /></div>
        </div>
        <div class="button"><button type="button" onclick="check()">계산</button></div>
    </div>
</form>


</body>
</html>
