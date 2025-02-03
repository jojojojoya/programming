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
    <link rel="stylesheet" type="text/css" href="../../css/loginStyles.css">
</head>
<div class="goToMainPageImg"><a href="/HomeC"><img src="../../image/logo_header.png"></a></div>
<body>
<div class="login-flex">
    <div class="login-container">
        <h2>로그인</h2>
        <form action="" method="post">
            <div class="loginBox">
                <label for="iD">아이디</label>
                <input type="email" id="iD" name="iD" required>
            </div>
            <div class="loginBox">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="loginBox">
                <button type="submit">로그인</button>
            </div>
        </form>
        <div class="loginHelp">
            <a href="javascript:void(0)" onclick="openModal()"> ID / PW 찾기 </a>&nbsp;&nbsp;&nbsp;
            <a href="/SignUpC"> 회원가입</a>
        </div>
    </div>
</div>
<div id="forgotModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <!-- forgot.jsp 내용이 여기에 로드됨 -->
        <iframe id="forgotFrame" src="/jsp/login/forgotInfo.jsp" width="100%" height="470px"
                style="border: none;"></iframe>
    </div>
</div>
<script type="text/javascript">
    function openModal() {
        const modal = document.getElementById("forgotModal");
        modal.style.display = "block";

        const closeModal = document.querySelector(".close");
        closeModal.addEventListener("click", () => {
            modal.style.display = "none";
        });

        window.addEventListener("click", (event) => {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        });
    }
</script>
</body>
</html>