<%@ page import="com.koyoi.main.vo.AdminMypageVO" %>
<%@ page import="com.koyoi.main.vo.UserMyPageVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    UserMyPageVO user = (UserMyPageVO)  request.getAttribute("user");
    String imgPath = (user != null && user.getUser_img() != null)
            ? user.getUser_img()
            : "/static/imgsource/testprofile.png"; // 기본 이미지
%>
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
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sawarabi+Maru&family=M+PLUS+Rounded+1c:wght@100;300;400;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="/static/css/usermypage/usermypage.css">
</head>
<body>

        <main class="content">
            <div class="top-section">
                <div class="profile_table">
                    <div class="profile_content">
                        <div class="profile_img">
                            <img src="${user.user_img}" onerror="this.onerror=null; this.src='/imgsource/userProfile/default.png'" alt="프로필 이미지">
                        </div>
                        <div class="profile_info">
                            <div class="profile_item">
                                <img src="/static/imgsource/profile/personicon.png" alt="">
                                <span>ID: ${user.user_id}</span>
                            </div>

                            <input type="hidden" id="hiddenUserId" value="${user.user_id}"> <!-- 🔥 여기에 추가 -->

                            <div class="profile_item">
                                <img src="/static/imgsource/profile/lockicon.png" alt="비밀번호">
                                <span> PW: ******** </span>
                            </div>
                            <div class="profile_item">
                                <img src="/static/imgsource/profile/personicon.png" alt="">
                                <span id="nicknameDisplay">닉네임: ${user.user_nickname} </span>
                            </div>
                            <button class="profile_edit_btn" id="openPasswordCheckModal">프로필 수정하기</button>
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
                            <div class="chatbot_list"> 챗봇 이용 내역이 없습니다.</div>
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


                <!-- 상담 영역 -->
                <div class="counseling_wrapper">
                    <!-- 상담 내역 없는 경우 -->

                    <c:if test="${empty reservations}">
                    <div class="counseling_no_reservation">
                        <div class="nonreserved_counseling_table">
                            <!-- 갈색 배경 안에 텍스트 포함 -->
                            <img src="/static/imgsource/background/padoo2.png">

                            <div class="nonreserved_counseling_table_comment">
                                <div><img style="width: 70px" src="/static/imgsource/background/shining5.png"></div>
                                <p>現在、予定されている相談はありません。<br>お話ししましょうか？</p>
                                <button class="reservation_submit_btn" onclick="location.href='/livechatreservation'">상담
                                    예약하기
                                </button>
                            </div>
                        </div>
                    </div>
                    </c:if>


                    <c:if test="${not empty reservations}">
                    <div class="counseling_table">
                        <div class="reserved_counseling_table_comment">
                            <div> 予約された相談 </div>
                            <button class="reservation_submit_btn" onclick="location.href='/livechatreservation'">추가상담
                                예약
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


                                        <div><strong>[상담일시] </strong></div>
                                        <fmt:formatDate value="${reservation.counseling_date}" pattern="yyyy년 MM월 dd일"/>
                                            ${reservation.counseling_time}시 00분

                                        <div><strong>[상담 카테고리] </strong>${reservation.category}</div>
                                        <div><strong>[상담사 ID] </strong>${reservation.counselor_id}</div>
                                        <div class="counseling_status"><strong>[상담 상태] </strong>${reservation.status}
                                        </div>

                                        <c:choose>
                                            <c:when test="${reservation.status eq '대기'}">
                                                <button type="button" class="enter_counseling_btn">상담 입장하기</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="view_counseling_btn">상담 내용보기</button>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </c:forEach>


                            </div>
                        </div>
                    </div>
                    </c:if> <!-- ✅ 닫음 -->
        </main>
<%--    </div>--%>
<%--</div>--%>

<!-- 🔥 여기에 모달 추가 -->
<div id="passwordCheckModal" class="modal" style="display: none;">
    <div class="modal-content">
        <p>비밀번호를 입력하세요:</p>
        <input type="password" id="passwordCheck" autocomplete="off">
        <button id="checkPasswordBtn">확인</button>
        <button class="close">닫기</button>
        <p id="passwordErrorMsg" style="display: none; color: red;">비밀번호가 틀렸습니다.</p>
    </div>
</div>
<!-- 프로필 수정 모달 -->
<div id="profileModal" class="modal" style="display: none;">
    <div class="modal-content">
        <h3>프로필 수정</h3>

        <!-- 기존 프로필 이미지 -->
        <div class="profile_img">
            <img src="${user.user_img}" alt="프로필 이미지" onerror="this.src='/imgsource/userProfile/default.png'">
        </div>

        <label> 사진 선택</label>
        <input type="file" id="editProfileImg" accept="image/*">
        <br>

        <label>아이디 </label>
        <input type="text" id="editId" readonly>
        <br>


        <label>새 비밀번호</label>
        <input type="password" id="editPw" placeholder="새 비밀번호 입력">
        <br>

        <label>닉네임</label>
        <input type="text" id="editNickname">
        <br>

        <button id="saveProfileBtn">저장</button>
        <button class="close">닫기</button>
    </div>
</div>


</body>
<script src="/static/js/usermypage/usermypage.js">
    <script>
        // 달력 전체 클릭 시 /diary로 이동
        document.querySelector(".calendar-container").addEventListener("click", function () {
        window.location.href = "/diary";
    });
</script>

</script>
</html>