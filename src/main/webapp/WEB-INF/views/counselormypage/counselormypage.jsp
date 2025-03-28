<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

<link href="https://fonts.googleapis.com/css2?family=Sawarabi+Maru&family=M+PLUS+Rounded+1c:wght@100;300;400;700&display=swap"
      rel="stylesheet">
<link rel="stylesheet" href="/static/css/counselormypage/counselormypage.css">

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
                    <img src="/static/imgsource/profile/lockicon.png" alt="비밀번호">
                    <span> PW: ******** </span>
                </div>
                <div class="profile_item">
                    <img src="/static/imgsource/profile/personicon.png" alt="">
                    <span id="nicknameDisplay">ニックネーム: ${user.user_nickname} </span>
                </div>
                <button class="profile_edit_btn" id="openPasswordCheckModal">프로필 수정하기</button>
            </div>

        </div>
    </div>

    <div class="chatbot_table">
        <div class="chatbot_title">チャットボットとのやりとり</div>
        <div class="chatbot_info">
            <c:if test="${not empty chats}">
                <c:forEach var="chat" items="${chats}">
                    <div class="chatbot_list">${chat.chat_summary}</div>
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
        <div class="calendar-iframe-wrapper" style="position: relative;">
            <iframe src="/maincalendar" frameborder="0"
                    style="width: 100%; height: 450px; border: none;"></iframe>
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

    <!-- 상담 영역 (상담사용) -->
    <div class="counseling_wrapper">
        <div class="counseling_table">
            <div class="reserved_counseling_table_comment">
                <div>談予約された相模リスト</div>
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
                            <div><strong>[ユーザーID] </strong>
                                <br>${reservation.user_id}</div>
                            <div class="counseling_status"><strong>[状況] </strong>${reservation.status}
                            </div>

                            <c:choose>
                                <c:when test="${reservation.status eq '待機中'}">
                                    <button type="button" class="enter_counseling_btn">今すぐ入室</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="view_counseling_btn">内容を確認する</button>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 패스워드 체크 모달 -->
<div id="passwordCheckModal" class="modal" style="display: none;">
    <div class="modal-content">
        <p> パスワードを入力してください </p>
        <input type="password" id="passwordCheck" autocomplete="off">
        <button id="checkPasswordBtn">確認</button>
        <button class="close">閉じる</button>
        <p id="passwordErrorMsg" style="display: none; color: red;">パスワードが正しくありません。</p>
    </div>
</div>

<%--        챗봇 내역 열람 모달 --%>
<div id="chatbotDetailModal" class="modal" style="display: none">
    <div class="modal-content">
        <div class="chatbot-detail-title"> チャットボットのタイトル </div>
        <div class="chatbot-detail-text"> チャットボットの内容 </div>
        <button class="close">閉じる</button>
    </div>

</div>

<!-- 프로필 수정 모달 -->
<div id="profileModal" class="modal" style="display: none;">
    <div class="modal-content">
        <h3>プロフィールを編集する</h3>

        <div class="profile_img">
            <img src="${user.user_img}" onerror="this.onerror=null; this.src='/imgsource/userProfile/default.png'">
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
        <button class="close">閉じる</button>
    </div>
</div>

<script src="/static/js/counselormypage/counselormypage.js">

</script>
