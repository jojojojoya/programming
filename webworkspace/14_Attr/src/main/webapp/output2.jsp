<%@page import="com.mz.alt.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<% 
Result r =  (Result)request.getAttribute("rrr");
%>

</head>
<body>


<div>
상품가격 : <%=r.getPrice()%></div>

<div> 
잔돈 : <%=r.getEx()%>
</div>


 <div>지불금액 : <%=r.getMoney()%></div>
<div> 
멘트 : <%=r.getSay()%>
</div>

1. 모자를때만 버튼이 나오게. [얼마 모자른데요?]
버튼을 누르면 output3.jsp로 가서 모자른 금액 안내 



<!-- 使える場面: ボタンを押しただけで別の処理に進む簡易なケース。 -->
<%if(r.getEx() < 0){ %>
    <button>얼마 모자른데요?</button>
<%} %>

<!-- 使える場面: 次のページで追加の計算や表示をしない場合. -->
<% if (r.getPrice() > r.getMoney()) { %>
<a href="output3.jsp"><button>얼마 모자른데요??</button></a> 
<%} %>


<!-- 使える場面: 不足金額の詳細を次のページで表示する場合。 -->
 <% if (r.getPrice() > r.getMoney()) { 
int howmuch = r.getPrice() - r.getMoney();
%>
<a href="output3.jsp?howmuch=<%=howmuch%>"><button>얼마 모자른데요???</button></a>
<% } %>

<a href="output3.jsp?howmuch=<%=r.getEx() * -1 %>">
    <button>얼마 모자른데요????</button>
</a>

<form action="output3.jsp">
    <input name="howmuch" hidden="1" value="<%=r.getEx() * -1 %>">
    <button>얼마 모자른데요?????</button>
</form>

<!-- 위에서 한건 다 나가릿＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠ -->
<hr>
    <form action="ExController">
        <input hidden value="<%=r.getEx() * -1%>" name="howmuch">
        <button>얼마 모자른데요?</button>
    </form>

    <form action="ExController">
        <button name="howmuch" value="<%=r.getEx() * -1 %>">얼마 모자른데요??</button>
    </form>

	<a href="ExController?howmuch=<%=r.getEx() *-1 %>">얼마? mvc</a>
<hr>

    <form action="ExController" method="post">
        <button name="asd" value="<%=r.getEx() * -1 %>"
               > post 요청 (데이터 완성)</button>
    </form>

    <form action="ExController" method="post">
        <button name="asd" value="<%=r.getEx() %>"
               > post 요청 (데이터 미완성)</button>
    </form>
    
    
    <a href="ExController?ex222=<%=request.getAttribute("ex2")%>">ex2 test</a>


</body>
</html>