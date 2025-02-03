<%--
  Created by IntelliJ IDEA.
  User: MiNN
  Date: 2025-01-14(화)
  Time: 오전 9:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title> 로그인 정보 입력 | KHH </title>
        <link rel="stylesheet" type="text/css" href="../css/loginStyles.css">
    </head>

    <body>
        <div class="login-container">
            <h2>로그인</h2>
            <form action="login" method="post">
                <div class="loginBox">
                    <label for="iD">아이디</label>
                    <input type="text" id="iD" name="iD" required>
                </div>
                <div class="loginBox">
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="loginBox">
                    <button type="submit">로그인</button>
                </div>
                <div class="loginHelp">
                    <a href="forgotPassword.jsp">비밀번호 찾기</a> |
                    <a href="register.jsp">회원가입</a>
                </div>
            </form>
        </div>
    </body>
</html>