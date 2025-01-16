<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div>
상품가격 : 
${rrr.price }</div>

<div> 
잔돈 : 
${rrr.ex }
</div>


 <div>지불금액 : 
${rrr.money }
</div>
<div> 
멘트 : 
${rrr.say }
</div>

1. 모자를때만 버튼이 나오게. [얼마 모자른데요?]
버튼을 누르면 output3.jsp로 가서 모자른 금액 안내 




<a href="output3.jsp?howmuch=${rrr.price - rrr.money}">
    <button>얼마 모자른데요????</button>
</a>

<form action="output3.jsp">
    <input name="howmuch" hidden="1" value="${rrr.money*-1 }">
    <button>얼마 모자른데요?????</button>
</form>

<hr>

    <form action="ExController" method="post">
        <button name="asd" value="${rrr.ex * -1}"
               > post 요청 (데이터 완성)</button>
    </form>

    <form action="ExController" method="post">
        <button name="asd" value="${rrr.ex}"
               > post 요청 (데이터 미완성)</button>
    </form>
    
    
    <a href="ExController?ex222=<%=request.getAttribute("ex2")%>">ex2 test</a>


</body>
</html>