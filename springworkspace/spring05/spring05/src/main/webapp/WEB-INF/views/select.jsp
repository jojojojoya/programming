<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    <h1> - Menu List - </h1>

    <c:forEach items="${menus}" var="m">
        <h2> 메뉴명 : ${m.m_name} / 가격 : ${m.m_price}
            <button onclick="deleteMenu('${m.m_no}')"> Delete </button>
            <button onclick="updateMenu('${m.m_no}')"> Update(js) </button>
<%--            <button onclick="location.href='menu-update?m_no=${m.m_no}&m_name=${m.m_name}&m_price=${m.m_price}'"> Update(jsp) </button>--%>
            <button onclick="location.href='menu-update?m_no=${m.m_no}'"> Update(jsp) </button>
            <%--<button onclick="location.href='menu-detail?no=${m.m_no}'"> Detail Page </button>--%>
            <button onclick="location.href='menu-detail?m_no=${m.m_no}'"> Detail Page </button>
        </h2>
    </c:forEach>


</body>

<script>
    function deleteMenu(no){
        let ok = confirm('정말 삭제하시겠습니까?');
        if(ok) {
            location.href='menu-del?no=' + no;
        }
    }

    function updateMenu(no){
        const price = prompt('가격 얼마로 수정?');
        location.href='menu-modi?m_price=' + price + '&m_no=' + no;

    }
</script>
</html>