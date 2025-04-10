<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    <h1> Update Page </h1>

    ${menu}
    <hr>
    ${menuDTO}
    <form action="menu-update" method="post">
    <hr>

    <div>
        <div> No. ${menuDTO.m_no} </div>
        <div> Name. <input type="text" name="m_name" value="${menuDTO.m_name}"> </div>
        <div> Price. <input type="text" name=m_price value="${menuDTO.m_price}"> </div>
        <div> <button name="m_no" value="${menuDTO.m_no}"> update!</button></div>
    </div>
    </form>



    <hr>

    <div>
        <div> No. ${menu.m_no} </div>
        <div> Name. <input type="text" name="" value="${menu.m_name}"> </div>
        <div> Price. <input type="text" name="" value="${menu.m_price}"> </div>
    </div>


</body>
</html>