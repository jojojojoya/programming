<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <%--<link rel="stylesheet" type="text/css" href="../../css/index_ver.0.4.css?v=1.0">--%>
    <%--<link rel="stylesheet" href="../../css/signup/signup_ver.0.2.css">--%>
    <link rel="stylesheet" href="../../css/signup/signUp.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

</head>

<body>

<%--<header class="header">
    <h1 style="display: flex; justify-content: center">
        <a href="/HomeC">KHH</a>
    </h1>
</header>--%>

<h1>점주 회원가입</h1>

<div class="main-body">
    <%--    이메일, 닉네임 중복체크--%>

    <div class="signup-flow" style="display: flex; justify-content: center;">
        <ul>
            <div class="signup-flow-no">1</div>
            <p class="signup-flow-title">정보 입력</p>
        </ul>
        <ul>
            <div class="signup-flow-no">2</div>
            <p class="signup-flow-title">입력 정보 확인</p>
        </ul>
        <ul>
            <div class="signup-flow-no">3</div>
            <p class="signup-flow-title">등록 완료</p>
        </ul>
    </div>

    <div class="signup-form" style="background-color:white; width: 90%; margin: auto; height:auto">

        <p style="text-align: center">필요한 정보 입력 후에 다음 버튼을 입력해주세요.</p>

        <form action="/CheckC" method="post">

            <!-- 회원 유형 선택 -->
            <div class="form-group">
                <div class="input-wrapper">
                    <div class="signup-title"><label for="name"><b>이름</b></label></div>
                    <div class="signup-input"><input type="text" id="name" name="name" placeholder="이름" required></div>
                </div>

                <div class="input-wrapper">
                    <div class="signup-title"><label for="email-domain"><b>이메일</b></label></div>
                    <div class="signup-input" style="display: flex">
                        <input type="text" id="email-domain" name="email-domain" placeholder="example" required>&nbsp;@&nbsp;
                        <label for="email-service"></label>
                        <select name="email-service" id="email-service" required>
                            <option value="naver.com">naver.com</option>
                            <option value="yahoo.co.jp">yahoo.co.jp</option>
                            <option value="google.com">google.com</option>
                            <option value="daum.net">daum.net</option>
                            <option value="hotmail.com">hotmail.com</option>
                            <option value="hanmail.net">hanmail.net</option>
                        </select>
                        &nbsp;&nbsp;&nbsp;
                        <button class="check-btn" type="button" id="email-check-button">중복체크</button>
                    </div>
                    <span id="email-message" class="email-message" style="display: none;"></span>
                </div>

                <div class="input-wrapper">
                    <div class="signup-title"><label for="nickname"><b>닉네임</b></label></div>
                    <div class="signup-input" style="display: flex">
                        <input type="text" id="nickname" name="nickname" placeholder="닉네임" required>
                        &nbsp;&nbsp;&nbsp;
                        <button class="check-btn" type="button" id="nickname-check-button">중복체크</button>
                    </div>
                    <span id="nickname-message" class="nickname-message" style="display: none"></span>
                </div>

                <div class="input-wrapper">
                    <div class="signup-title"><label for="tel"><b>전화번호</b></label></div>
                    <div class="signup-input"><input type="text" id="tel" name="tel" placeholder="휴대폰 번호" required></div>
                </div>

                <div class="input-wrapper">
                    <div class="signup-title"><span><b>생년월일</b></span></div>
                    <div class="signup-input" style="display: flex">
                        <select name="birth-year" id="birth-year" required>
                            <option value="">-- --</option>
                        </select>
                        <label for="birth-year">&nbsp;년</label>&nbsp;
                        <select name="birth-month" id="birth-month" required>
                            <option value="">-- --</option>
                        </select>
                        <label for="birth-month">&nbsp;월</label>&nbsp;
                        <select name="birth-day" id="birth-day" required>
                            <option value="">-- --</option>
                        </select>
                        <label for="birth-day">&nbsp;일</label>&nbsp;

                    </div>
                    <input type="hidden" id="formatted-birthdate" name="birthdate">
                </div>
                <p style="text-align: center">*** 한번 입력하면 수정할 수 없습니다. ***</p>

                <div class="password-wrapper">
                    <div class="signup-title"><label for="password"><b>비밀번호</b></label></div>
                    <div class="signup-input"><input type="text" id="password" name="password" placeholder="영문+숫자 8~16자 이내" required></div>
                </div>
                <div class="password-wrapper">
                    <div class="signup-title"><label for="passwordCheck"><b>비밀번호 확인</b></label></div>
                    <div class="signup-input"><input type="text" id="passwordCheck" name="passwordCheck" placeholder="한　번 더 입력해주세요" required></div>
                </div>

                <div class="btn-wrapper">
                    <button type="submit"
                            style=""
                            class="util-btn"
                            name="submit">입력 확인
                    </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="util-btn" type="button" onclick="history.back();">
                        유저 유형 다시 선택
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="../../js/signup/signup.js"></script>
</body>
</html>
