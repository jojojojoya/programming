<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link
  href="https://fonts.googleapis.com/css2?family=Sawarabi+Maru&family=M+PLUS+Rounded+1c:wght@100;300;400;700&display=swap"
  rel="stylesheet"
/>
<link rel="stylesheet" href="/static/css/usermypage/usermypage.css" />

<div class="top-section">
  <div class="profile_table">
    <div class="profile_content">
      <div class="profile_img">
        <img
          src="${user.user_img}"
          onerror="this.onerror=null; this.src='/imgsource/userProfile/default.png'"
          alt="프로필 이미지"
        />
      </div>
      <div class="profile_info">
        <div class="profile_item">
          <img src="/static/imgsource/profile/personicon.png" alt="" />
          <span>ID: ${user.user_id}</span>
        </div>

        <input type="hidden" id="hiddenUserId" value="${user.user_id}" />

        <div class="profile_item">
          <img src="/static/imgsource/profile/lockicon.png" alt="비밀번호" />
          <span> PW: ******** </span>
        </div>
        <div class="profile_item">
          <img src="/static/imgsource/profile/personicon.png" alt="" />
          <span id="nicknameDisplay">닉네임: ${user.user_nickname} </span>
        </div>
        <button class="profile_edit_btn" id="openPasswordCheckModal">
          프로필 수정하기
        </button>
      </div>
    </div>
  </div>

  <div class="chatbot_table">
    <div class="chatbot_title">챗봇과의 대화 내역</div>
    <div class="chatbot_info">
      <c:if test="${not empty chats}">
        <c:forEach var="chat" items="${chats}">
          <div class="chatbot_list">${chat.chat_summary}</div>
        </c:forEach>
      </c:if>
      <c:if test="${empty chats}">
        <div class="chatbot_list">챗봇 이용 내역이 없습니다.</div>
      </c:if>
    </div>
  </div>
</div>

<div class="bottom-section">
  <div class="calendar-container">
    <div class="calendar-iframe-wrapper" style="position: relative">
      <iframe
        src="/maincalendar"
        frameborder="0"
        style="width: 100%; height: 450px; border: none"
      ></iframe>
      <a
        href="/diary"
        style="
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          z-index: 10;
          background: transparent;
          cursor: pointer;
        "
      ></a>
    </div>
  </div>

  <!-- 상담 영역 (상담사용) -->
  <div class="counseling_wrapper">
    <div class="counseling_table">
      <div class="reserved_counseling_table_comment">
        <div>📅 상담사로 예약된 상담 목록입니다</div>
      </div>

      <div class="reservation_slider">
        <div class="reservation_list">
          <c:forEach var="reservation" items="${reservations}">
            <div
              class="reserved_reservation_box"
              data-counseling-id="${reservation.counseling_id}"
              data-session-id="${reservation.session_id}"
              data-counseling-date="<fmt:formatDate value='${reservation.counseling_date}' pattern='yyyy-MM-dd'/>"
              data-counseling-time="${reservation.counseling_time}"
              data-status="${reservation.status}"
            >
              <div><strong>[상담일시] </strong></div>
              <fmt:formatDate
                value="${reservation.counseling_date}"
                pattern="yyyy년 MM월 dd일"
              />
              ${reservation.counseling_time}시 00분

              <div>
                <strong>[상담 카테고리] </strong>${reservation.category}
              </div>
              <div><strong>[USER ID] </strong>${reservation.user_id}</div>
              <div class="counseling_status">
                <strong>[상담 상태] </strong>${reservation.status}
              </div>

              <c:choose>
                <c:when test="${reservation.status eq '대기'}">
                  <button type="button" class="enter_counseling_btn">
                    상담 입장하기
                  </button>
                </c:when>
                <c:otherwise>
                  <button type="button" class="view_counseling_btn">
                    상담 내용보기
                  </button>
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
<div id="passwordCheckModal" class="modal" style="display: none">
  <div class="modal-content">
    <p>パスワードを入力してください</p>
    <input type="password" id="passwordCheck" autocomplete="off" />
    <button id="checkPasswordBtn">確認</button>
    <button class="close">閉じる</button>
    <p id="passwordErrorMsg" style="display: none; color: red">
      パスワードが正しくありません。
    </p>
  </div>
</div>

<%-- 챗봇 내역 열람 모달 --%>
<div id="chatbotDetailModal" class="modal" style="display: none">
  <div class="modal-content">
    <div class="chatbot-detail-title">チャットボットのタイトル</div>
    <div class="chatbot-detail-text">チャットボットの内容</div>
    <button class="close">閉じる</button>
  </div>
</div>

<!-- 프로필 수정 모달 -->
<div id="profileModal" class="modal" style="display: none">
  <div class="modal-content">
    <h3>プロフィールを編集する</h3>

    <%-- <label> 写真を選択 </label>--%> <%--
    <input type="file" id="editProfileImg" accept="image/*" />--%> <%--
    <br />--%>
    <div class="profile_img">
      <img
        src="${user.user_img}"
        alt="프로필 이미지"
        onerror="this.src='/imgsource/userProfile/default.png'"
      />
    </div>

    <label for="editProfileImg" id="customFileLabel">ファイルを選択</label>
    <br />
    <input
      type="file"
      id="editProfileImg"
      accept="image/*"
      style="display: none"
    />
    <span id="fileNameDisplay"></span>

    <label> ID : </label>
    <input type="text" id="editId" readonly />
    <br />

    <label>新しいパスワード : </label>
    <input type="password" id="editPw" placeholder="新しいパスワードを入力" />
    <br />

    <label>ニックネーム : </label>
    <input type="text" id="editNickname" />
    <br />

    <button id="saveProfileBtn">保存</button>
    <button class="close">閉じる</button>
  </div>
</div>

<script src="/static/js/counselormypage/counselormypage.js"></script>
