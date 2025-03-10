<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sawarabi+Maru&family=M+PLUS+Rounded+1c:wght@100;300;400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="/static/css/usermypage/usermypage.css">
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

                <div class="profile_table">
                    <div class="profile_content">
                        <div class="profile_img">
                            <img src="/static/imgsource/usermypage_profiletest.jpg" alt="프로필 이미지">
                        </div>

                        <div class="profile_info">
                            <div class="profile_item">
                                <img src="/static/imgsource/personicon.png" alt="">
                                <span>ID: jojojo9797</span>
                            </div>

                            <div class="profile_item">
                                <img src="/static/imgsource/lockicon.png" alt="">
                                <span>PW: ********</span>
                            </div>
                            <div class="profile_item">
                                <img src="/static/imgsource/personicon.png" alt="">
                                <span>닉네임: 조조 ♥ 클로로 </span>
                            </div>

                            <button class="profile_edit_btn">프로필 수정하기</button>
                        </div>
                    </div>
                </div>

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

            </div>

            <div class="bottom-section">
                <div class="diary_table">
                    <div class="diary_background_img">
                        <img src="/static/imgsource/calandarback.png" alt="달력 이미지">
                    </div>
                </div>
                <div class="counseling_table">
                    <div class="counseling_background_img">
                        <img class="main-img" src="/static/imgsource/padoo2.png" alt="기본 이미지">
                    </div>

                    <div class="counseling_table_comment">
                        <div>
                        <img style="width: 70px" src="/static/imgsource/shining5.png">
                        </div>
                        現在、
                        <br>
                        予定されている相談はありません。
                        <br>
                        お話ししましょうか？

                    </div>

                </div>
            </div>

        </main>
    </div>

</div>

<div id="profileModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>My Profile</h2>
        <form id="profileEditForm">
            <label for="editId">ID:</label>
            <input type="text" id="editId" name="id" value="jojojo9797" required>
            <br>
            <label for="editPw">PW:</label>
            <input type="password" id="editPw" name="password" required>
            <br>
            <label for="editNickname">닉네임:</label>
            <input type="text" id="editNickname" name="nickname" value="조조 ♥ 클로로" required>
            <br>
            <button class="profile-submit-btn" type="submit">수정하기</button>
        </form>
    </div>
</div>