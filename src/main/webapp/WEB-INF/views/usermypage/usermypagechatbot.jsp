<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sawarabi+Maru&family=M+PLUS+Rounded+1c:wght@100;300;400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="/static/css/usermypage/usermypagechatbot.css">
    <script src="/static/js/usermypage.js"></script>
</head>
<body>

<div class="container">

    <div class="left-container">
        <aside class="sidebar">
            <nav class="sidebar-menu">
                <button class="sidebar-btn">
                    <img src="/static/imgsource/home.png" alt="홈">
                </button>
                <button class="sidebar-btn"><img src="/static/imgsource/calandar.png" alt="목록"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/pencil.png" alt="채팅"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/chat.png" alt="공유"></button>
                <button class="sidebar-btn"><img src="/static/imgsource/settingss.png" alt="설정"></button>
                <div class="bbiyak">
                    <img src="/static/imgsource/bbiyak.png">
                </div>
            </nav>
        </aside>
    </div>

    <div class="right-container">
        <header class="header-bar">
            <div class="brand-title">
                <img src="/static/imgsource/logo.png" alt="KOYOI 로고">
            </div>

            <div class="header-icons">
                <img class="myprofile-img" src="/static/imgsource/testprofile.png" alt="프로필">
            </div>
        </header>

        <main class="content">

            <div class="top-section">
                <div class="chatbot_table">
                    <!--foreach로 정보구현-->
                    <div> 챗봇과의 대화 </div>
                    <div class="chatbot_info">
                        <div class="chatbot_list"> foreach로</div>
                        <div class="chatbot_list"> 챗봇 컨텐츠 내용</div>
                        <div class="chatbot_list"> 챗봇 컨텐츠 내용</div>
                        <div class="chatbot_list"> 챗봇 컨텐츠 내용</div>
                    </div>
                </div>

                <div class="chatbot_detail_table">
                    <!--foreach로 정보구현-->
                    <div> 챗봇과의 대화 상세 </div>
                    <div class="chatbot_info">
                        <div class="chatbot_list"> foreach로</div>
                        <div class="chatbot_list"> 챗봇 컨텐츠 내용</div>
                        <div class="chatbot_list"> 챗봇 컨텐츠 내용</div>
                        <div class="chatbot_list"> 챗봇 컨텐츠 내용</div>
                    </div>

            </div>

    </div>
</div>
</div>