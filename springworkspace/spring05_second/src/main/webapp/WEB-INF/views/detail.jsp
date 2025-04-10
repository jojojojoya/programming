<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    <h1> Detail Page </h1>

    ${menu}
    <hr>
    ${menuDTO}
    <hr>

    <div>
        <div> No. ${menu.m_no} </div>
        <div> Name. ${menu.m_name} </div>
        <div> Price. ${menu.m_price} </div>
    </div>






</body>
</html>