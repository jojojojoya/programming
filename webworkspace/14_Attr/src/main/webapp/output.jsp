<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>계산 결과 </h1>

<div>
<div>
상품가격 : <%= request.getParameter("p") %>
</div>
<div>
지불금액 : <%= request.getParameter("m") %>
</div>
<div>
상품가격 : <%= request.getParameter("price") %>
</div>
<div>
지불금액 : <%= request.getParameter("money") %>
</div>


<div> 
잔돈 : <%= request.getAttribute("exchange") %>
</div>
<div> 
멘트 : <%= request.getAttribute("say") %>
</div>

</div>






</body>
</html>