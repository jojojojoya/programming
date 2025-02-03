<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>가게 정보와 리뷰</title>
    <link rel="stylesheet" href="../../css/shopinfo/shopdetail.css">
</head>
<body>

<!-- 가게 이름과 이미지 항상 위에 위치 -->
<div class="shop-detail">
    <div class="main-img">
        <img alt="" src="/image/shopImage/${shop.main_image}">
    </div>
    <div class="sub-img">
        <c:forEach var="sub" items="${shop.sub_image}" varStatus="st">
            <img src="/image/shopImage/${sub}" alt="서브사진 ${st.count}">
        </c:forEach>
    </div>

    <div class="shop-name">${shop.shop_name}</div>
    <div class="shop-intro">${shop.shop_content}</div>
</div>

<!-- 탭 메뉴 -->
<div class="tab-menu">
    <button id="tab1" class="tab-button active" onclick="openTab(1)">가게정보</button>
    <%--<button id="tab2" class="tab-button" onclick="openTab(2)">메뉴</button>--%>
    <button id="tab3" class="tab-button" onclick="openTab(3)">리뷰</button>
</div>

<!-- 탭 내용 -->
<!-- 가게정보 -->
<div id="content1" class="tab-content active">
    <div class="info">Information</div>
    <div class="info-content">
        <div style="display: flex; width: 1150px;">${shop.shop_name}</div>
        <div style="display: flex; width: 1150px;">
            <div style="width:250px">주소</div>
            <div style="font-size:20px">${shop.shop_addr}</div>
        </div>
        <div style="display: flex; width: 1150px;">
            <div style="width:250px">TEL</div>
            <div style="font-size:20px">${shop.shop_tel}</div>
        </div>
        <div style="display: flex; width: 1150px;">
            <div style="width:250px">영업시간</div>
            <div style="font-size:20px">${shop.shop_opentime}</div>
        </div>
    </div>
    <div class="reservation-btn">
        <button onclick="servicement()">예약하기</button>
        <button onclick="location.href='ShopC'">목록으로</button>
    </div>
</div>
<script>
    function servicement(){
        alert('서비스 준비중입니다. 조금만 기다려주세요~~');
    }
</script>
<!-- 메뉴 -->
<div id="content2" class="tab-content">
    <c:forEach var="menu" items="${menus}">
        <div class="menu-one">
            <div class="menu-nameprice">
                <div class="menu-name">${menu.menu_name}</div>
                <div>${menu.menu_price}</div>
            </div>
            <div class="menu-type">${menu.menu_type}</div>
        </div>
    </c:forEach>
</div>

<div id="content3" class="tab-content review-section">
        <div class="review">
            <div class="nickname">챈챈</div>
            <div class="stars">
            <c:forEach var="i" begin="1" end="4">
                <span class="star">★</span> <!-- 별을 채운 부분 -->
            </c:forEach>
            <c:forEach var="i" begin="5" end="5">
                <span class="star2">★</span>  <!-- 빈 별 부분 -->
            </c:forEach>
            </div>
            <div class="review-content">너무너무 맛나용~</div>
            <div class="photo-container">
                <img src="/image/img1-1.jpg" alt="review photo 1">
                <img src="/image/img1-2.jpg" alt="review photo 2">
                <img src="/image/img1-3.jpg" alt="review photo 3">
            </div>
            <div class="like-button-div">
            <button class="like-button" onclick="increaseLikeCount(this)">좋아요</button>
            <span class="like-count">0</span>
            </div>
        </div>
</div>

<!-- 리뷰 -->
<%--<div id="content3" class="tab-content review-section">
    <c:forEach var="reviews" items="${review}">
        <div class="review">
            <div class="nickname">${reviews.review_nickname}</div>
            <c:forEach var="i" begin="1" end="${reviews.review_star}">
                <span class="star">★</span> <!-- 별을 채운 부분 -->
            </c:forEach>
            <c:forEach var="i" begin="${reviews.review_star + 1}" end="5">
                <span class="star2">★</span>  <!-- 빈 별 부분 -->
            </c:forEach>
            &lt;%&ndash;<div class="stars">
                <span class="star">&#9733;</span>
                <span class="star">&#9733;</span>
                <span class="star">&#9733;</span>
                <span class="star">&#9733;</span>
                <span class="star">&#9733;</span>
            </div>&ndash;%&gt;
            <div class="content">${reviews.review_content}</div>
            <div class="photo-container">
                <img src="https://via.placeholder.com/150x150" alt="review photo 1">
                <img src="https://via.placeholder.com/150x150" alt="review photo 2">
                <img src="https://via.placeholder.com/150x150" alt="review photo 3">
            </div>
            <button class="like-button" onclick="increaseLikeCount(this)">좋아요</button>
            <span class="like-count">0</span>
        </div>
    </c:forEach>
</div>--%>

<script>
    function openTab(tabNumber) {
        var contents = document.querySelectorAll('.tab-content');
        contents.forEach(function(content) {
            content.classList.remove('active');
        });

        var buttons = document.querySelectorAll('.tab-button');
        buttons.forEach(function(button) {
            button.classList.remove('active');
        });

        document.getElementById('content' + tabNumber).classList.add('active');
        document.getElementById('tab' + tabNumber).classList.add('active');
    }

    function increaseLikeCount(button) {
        var likeCountSpan = button.nextElementSibling;
        var currentCount = parseInt(likeCountSpan.textContent);
        likeCountSpan.textContent = currentCount + 1;
    }
</script>

</body>
</html>
