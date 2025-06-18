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
      <div style="font-size:14px; font-weight: bold;">유저 등록</div>
      </div>
      <div class="section-divider"></div>

      <!-- 등록 버튼 -->

		  <form action="/add/user" method="post" onsubmit="return confirm('최종 등록하시겠습니까?')">
			<div class="useraddment"> 직접 유저를 등록할 수 있습니다. </div>
			<div class="useraddinput">
			ID : <input type="text" name="userId" class="userIdinput"> 
			닉네임 : <input type="text" name="userNickname" class="userNickinput">
			PW : <input type="text" name="userPassword"  class="userPwinput"> 
			   <button class="useradd-btn" type="submit">등록</button>
	    </div>
		  </form>
	    </div>

        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/main/footer.jsp" />
</body>
</html>
