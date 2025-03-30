<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.koyoi.main.vo.UserMyPageVO" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    @font-face {
        font-family: 'MyFont';
        src: url('/static/fonts/Boku2-Regular.otf') format('opentype');
    }

    body {
        font-family: 'MyFont', sans-serif;
        font-size: 32px;
        color: black;
    }
</style>


<%  // 세션 체크 추가 부분 시작
    HttpSession session1 = request.getSession(false); // 기존 세션 가져오기
    String userId = null;
    String userType = null;
    String userImg = null;

    if (session1 != null) {
        userId = (String) session1.getAttribute("userId"); // 세션에 저장된 userId 값
        userImg = (String) session1.getAttribute("userImg"); // 세션에 저장된 userId 값
        Object userTypeObj = session1.getAttribute("userType"); // int로 저장된 경우

        if (userTypeObj != null) {
            userType = userTypeObj.toString(); // int → String 안전하게 변환
        }
    }

    if (userId == null) {
        response.sendRedirect("/login"); // 세션 없거나 만료 시 로그인 페이지로 이동
        return;
    }
%>

<!DOCTYPE html>
<html lang="ja">

    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sawarabi+Maru&family=M+PLUS+Rounded+1c:wght@100;300;400;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="/static/css/usermypage/usermypage.css">


<div class="usermypage-form">
            <div class="top-section">
                <div class="profile_table">
                    <div class="profile_content">
                        <div class="profile_img">
                            <img src="${user.user_img}" onerror="this.onerror=null; this.src='/imgsource/userProfile/default.png'">
                        </div>
                        <div class="profile_info">
                            <div class="profile_item">
                                <img src="/static/imgsource/profile/personicon.png" alt="">
                                <span>ID: ${user.user_id}</span>
                            </div>

                            <input type="hidden" id="hiddenUserId" value="${user.user_id}">

                            <div class="profile_item">
                                <img src="/static/imgsource/profile/lockicon.png" alt="パスワード">
                                <span> パスワード: ******** </span>
                            </div>
                            <div class="profile_item">
                                <img src="/static/imgsource/profile/nicknameicon.png" alt="ニックネーム">
                                <span id="nicknameDisplay">ニックネーム: ${user.user_nickname} </span>
                            </div>
                            <button class="profile_edit_btn" id="openPasswordCheckModal">プロフィール編集</button>
                        </div>

                    </div>
                </div>

                <div class="chatbot_table">
                    <div class="chatbot_title">チャットボットとのやりとり
                    <img class="shiningdeco" src="/static/imgsource/background/shining.png">
                    </div>
                    <div class="chatbot_info">
                        <c:if test="${not empty chats}">
                            <c:forEach var="chat" items="${chats}">
                                <div class="chatbot_list" data-title="${chat.chat_title}" data-summary="${chat.chat_summary}">
                                    <strong>${chat.chat_title}</strong>
                                    <button class="view_chat_summary_btn">内容確認</button>
                                </div>
                            </c:forEach>



                        </c:if>
                        <c:if test="${empty chats}">
                            <div class="chatbot_list"> チャットボットの会話履歴はありません。</div>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="bottom-section">
                <div class="calendar-container">
                    <div class="calendar-iframe-wrapper"
                         style="    position: relative; transform: scale(0.7);
    transform-origin: center;    width: 100px;
    height: 133px;">
                        <iframe src="/maincalendar" frameborder="0"
                                style="      width: 440px;
    height: 440px;
    border-radius: 16px;border: none; "></iframe>

                        <img src="/static/imgsource/background/book.png" class="calendar-illust">


                        <a href="/diary" style="
        position: absolute;
        top: 0; left: 0;
        width: 100%; height: 100%;
        z-index: 10;
        background: transparent;
        cursor: pointer;
    "></a>

                    </div>

                </div>


                <div class="counseling_wrapper">

                    <c:if test="${empty reservations}">
                    <div class="counseling_no_reservation">
                        <div class="nonreserved_counseling_table">
                            <img src="/static/imgsource/background/padoo2.png">

                            <div class="nonreserved-hover-layer">
                                <div class="nonreserved_counseling_table_comment">
                                    <div><img style="width: 50px" src="/static/imgsource/background/shining5.png"></div>
                                    <p style="font-size: 16px;">現在、予定されている相談はありません。<br>お話ししましょうか？</p>
                                    <button class="nonreservation_submit_btn" onclick="location.href='/livechatreservation'">相談予約</button>
                                </div>
                            </div>
                        </div>

                    </c:if>


                    <c:if test="${not empty reservations}">
                    <div class="counseling_table">
                        <div class="reserved_counseling_table_comment">
                            <div> ご予約中のライブ相談 </div>

                            <button class="reservation_submit_btn" onclick="location.href='/livechatreservation'"> 追加相談を予約
                            </button>
                        </div>

                        <div class="reservation_slider">
                            <div class="reservation_list">

                                <c:forEach var="reservation" items="${reservations}">
                                    <div class="reserved_reservation_box"
                                         data-counseling-id="${reservation.counseling_id}"
                                         data-session-id="${reservation.session_id}"
                                         data-counseling-date="<fmt:formatDate value='${reservation.counseling_date}' pattern='yyyy-MM-dd'/>"
                                         data-counseling-time="${reservation.counseling_time}"
                                         data-status="${reservation.status}">


                                        <div><strong>[相談日時]</strong></div>
                                        <fmt:formatDate value="${reservation.counseling_date}" pattern="yyyy年MM月dd日"/>
                                            ${reservation.counseling_time}時00分

                                        <div><strong>[カテゴリー] </strong>${reservation.category}</div>
                                        <div><strong>[担当カウンセラーID] </strong>
                                                <br>${reservation.counselor_id}</div>
                                        <div class="counseling_status"><strong>[状況] </strong>${reservation.status}
                                        </div>

                                        <c:choose>
                                            <c:when test="${reservation.status eq '待機中'}">
                                                <button type="button" class="enter_counseling_btn">今すぐ入室</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="view_counseling_btn">内容確認</button>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </c:forEach>


                            </div>
                        </div>
                    </div>
                    </c:if>

                <div id="passwordCheckModal" class="modal" style="display: none;">
                    <div class="modal-content">
                        <p> パスワードを入力してください。</p>
                        <input type="password" id="passwordCheck" autocomplete="off">
                        <button id="checkPasswordBtn">確認</button>
                        <button class="close"> X </button>
                        <p id="passwordErrorMsg" style="display: none; color: red;">パスワードが正しくありません。</p>
                    </div>
                </div>

                    <!-- 모달 부분 -->
                    <div id="chatbotDetailModal" class="modal" style="display: none;">
                        <div class="modal-content">
                            <div class="chatbot-detail-title" style="font-weight:bold; margin-bottom:10px;"></div>
                            <div class="chatbot-detail-text"></div>
                            <button class="close" onclick="closeChatDetail()">X</button>
                        </div>
                    </div>



                    <div id="profileModal" class="modal" style="display: none;">
                    <div class="modal-content">
                        <h3>プロフィール編集</h3>

                        <div class="profile_img">
                            <img src="${user.user_img}" alt="프로필 이미지" onerror="this.src='/imgsource/userProfile/default.png'">
                        </div>

                        <label for="editProfileImg" id="customFileLabel">ファイルを選択</label>
                        <br>
                        <input type="file" id="editProfileImg" accept="image/*" style="display: none;">
                        <span id="fileNameDisplay"></span>

                        <label> ID : </label>
                        <input type="text" id="editId" readonly>
                        <br>


                        <label>新しいパスワード : </label>
                        <input type="password" id="editPw" placeholder="新しいパスワードを入力">
                        <br>

                        <label>ニックネーム : </label>
                        <input type="text" id="editNickname">
                        <br>

                        <button id="saveProfileBtn">保存</button>
                        <button class="close">X</button>
                    </div>
                </div>

            </div>

                <script>
                    document.querySelector(".calendar-container").addEventListener("click", function () {
                    window.location.href = "/diary";
                });

                </script>
                <script src="/static/js/usermypage/usermypage.js"></script>
</div>
</html>