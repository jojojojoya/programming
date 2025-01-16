<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>
<table id="site-title">
        <tr>
            <td> <a href="HC"> mz's place</a></td>
        </tr>
    </table>
    <table id="site-content">
        <tr>
            <td>
                <jsp:include page="${content }"></jsp:include>
            </td>
        </tr>
    </table>
    <table id="site-menu">
        <tr>
            <td><a href="jstlc">JSTL-Core</a></td>
            <td><a href="jstl_fc">JSTL-Format</a></td>
        </tr>
    </table>
</body>
</html>