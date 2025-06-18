<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>관리자 유저 목록</title>
  <link rel="stylesheet" href="/css/adminmypage.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/main/header.jsp" />

<div class="adminmp-container">

  <!-- 사이드바 -->
  <div class="adminmp-sidebar-container">
    <div class="adminmp-sidebar-list">
      <div onclick="location.href='/adminmypage'">MYPAGE</div>
      <div onclick="location.href='/adminuserlist'">유저관리</div>
      <div onclick="location.href='/adminorder'">주문관리</div>
      <div onclick="location.href='/adminproduct'">상품관리</div>
      <div onclick="location.href='/adminreview'">리뷰관리</div>
      <div onclick="location.href='/adminqna'">문의관리</div>
    </div>
  </div>

  <!-- 본문 -->
  <div class="adminmp-subpage-header">
    <div class="adminmp-userlist-header">
		<div class="adminmp-userlist-hhd">
      <div style="font-size:14px; font-weight: bold;">유저 목록</div>
      <div class="adminmp-userlist-btn">
        <button type="button" class="userinsert-btn" onclick="location.href='/adduserlist'">유저 등록</button>
      </div></div>
      <div class="section-divider"></div>

      <!-- 등록 버튼 -->

	  <c:forEach var="user" items="${userList}">
	    <div class="adminmp-userlist-item">
	      <div class="adminmp-userlist-info">
	        <div class="adminmp-userlist-prname">
	          ${user.user_id} | ${user.user_nickname}
	        </div>
	      </div>
		  <form action="/delete/userlist" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?')">
			<input type="hidden" name="userId" value="${user.user_id}">
			   <button class="userdelete-btn" type="submit">삭제</button>
		  </form>
	    </div>
	  </c:forEach>

        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/main/footer.jsp" />
</body>
</html>
