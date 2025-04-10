<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Shop List</title>
    <link rel="stylesheet" href="../../css/shopinfo/shoplist.css">
</head>
<body>
<h1>맛집 정보</h1>
<div class="container">
    <c:forEach var="shop" items="${shops}" begin="1" end="3">
        <!-- 주소 타입 표시 -->
        <div class="shop-card">
            <!-- 메인 사진 -->
            <img class="main-img" src="/image/shopImage/${shop.main_image}" alt="메인사진">

            <!-- 서브 사진 6개 -->
            <div class="sub-img">
                <c:forEach var="sub" items="${shop.sub_image}" varStatus="st">
                    <img src="/image/shopImage/${sub}" alt="서브사진 ${st.count}">
                </c:forEach>
            </div>

            <!-- 상점 정보 -->
            <div class="shop-info">
                <div class="shop-name" onclick="location.href='ShopDetailC?no=${shop.shop_no}'">${shop.shop_name}</div>
                <div class="shop-description">${shop.shop_content}</div>
                <div class="shop-details">
                    <div class="shop-time">영업시간: ${shop.shop_opentime}</div>
                    <div class="shop-access">위치: [${shop.shop_addrtype}]${shop.shop_addr}</div>
                </div>
            </div>
        </div>
    </c:forEach>
	
</div>

</body>
</html>
