
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>

<body>


 <div class="container">
        <div class="title">BMI 결과</div>
        
        
        <div class="items">
            <div> <img style="width:100px" src="bmi/uploadFile/${bb.fName}"> </div>
        </div>
        <div class="items">
            <div class="item1"><span>이름</span></div>
            <div class="item2">${bb.name}</div>
        </div>
        <div class="items">
            <div class="item1"><span>키</span></div>
            <div class="item2">${bb.height}</div>
        </div>
        <div class="items">
            <div class="item1"><span>체중</span></div>
            <div class="item2">${bb.weight}</div>
        </div>
        <div class="items">
            <div class="item1"><span>BMI</span></div>
            <div class="item2">${bb.bmi2}</div>
        </div>
        <div class="items">
            <div class="item1"><span>결과</span></div>
            <div class="item2">${bb.status}</div>
        </div>
       
      
    </div>

</body>
</html>