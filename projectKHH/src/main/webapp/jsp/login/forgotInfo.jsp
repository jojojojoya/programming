<%--
  Created by IntelliJ IDEA.
  User: MiNN
  Date: 2025-01-20(월)
  Time: 오후 1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title> ID / PW 찾기 | KHH </title>
  <link rel="stylesheet" type="text/css" href="../../css/forgot.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h2>아이디 / 비밀번호 찾기</h2>

<form id="idSearch" method="post">
  <div class="forgot_mail_mailInput">
    <div class="forgot_mail_nameLabel"><label for="idName"> 사용자 이름 </label></div>
    <input class="idInfo" id="idName" name="idName" required />
  </div>

  <div class="forgot_mail_NicknameInput">
    <div class="forgot_mail_NicknameLabel"><label for="idNickName"> 사용자 닉네임 </label></div>
    <input class="idInfo" id="idNickName" name="idNickName" required />
  </div>

  <div class="forgot_mail_submit">
    <input type="submit" value="이메일 찾기"/>
  </div>
</form>

<hr>

<form id="pwSearch" method="post">
  <div class="forgot_pw_mailInput">
    <div class="forgot_pw_mailLabel"><label for="pwMail">사용자 메일 </label></div>
    <input class="pwInfo" id="pwMail" name="pwMail" type="email" required />
  </div>

  <div class="forgot_pw_nameInput">
    <div class="forgot_pw_nameLabel"><label for="pwName">사용자 이름 </label></div>
    <input class="pwInfo" id="pwName" name="pwName" required />
  </div>

  <div class="forgot_pw_submit">
    <input type="submit" value="비밀번호 찾기"/>
  </div>
</form>

<div class="forgot_result">
  <p id="resultMessage"></p>
</div>

<script type="text/javascript">
  $(document).ready(function() {
    $('#idSearch').on('submit', function(event) {
      event.preventDefault();

      var formData = $(this).serialize();

      $.ajax({
        url: '/ForgotInfoC',
        type: 'POST',
        data: formData,
        dataType: 'json',
        success: function(response) {
          if (response.result) {
            $('#resultMessage').text(response.result);
          } else {
            $('#resultMessage').text('아이디를 찾을 수 없습니다.');
          }
        },
        error: function() {
          $('#resultMessage').text('서버 오류가 발생했습니다.');
        }
      });
    });

    $('#pwSearch').on('submit', function(event) {
      event.preventDefault();

      var formData = $(this).serialize();

      $.ajax({
        url: '/ForgotInfoC',
        type: 'POST',
        data: formData,
        dataType: 'json',
        success: function(response) {
          if (response.result) {
            $('#resultMessage').text(response.result);
          } else {
            $('#resultMessage').text('비밀번호를 찾을 수 없습니다.');
          }
        },
        error: function() {
          $('#resultMessage').text('서버 오류가 발생했습니다.');
        }
      });
    });
  });
</script>
</body>
</html>
