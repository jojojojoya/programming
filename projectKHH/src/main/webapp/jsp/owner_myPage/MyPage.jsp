<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../../css/ownerMyPage/ownerMyPage.css">
</head>
<body>

<div class="mypage" style="border: none;">
    <h1>MyPage</h1>
</div>

<div class="profile-title"><h2>내 프로필</h2></div>
<div class="profile">
    <div class="profile-field" style="border: none;">
        <div class="profile-circle">
            <img
                    src="https://i.namu.wiki/i/d2I9NNVyw8e1AywBghx9PFHo7MGmrOL-GGm5Tke4vMPYTOanPjc3JU2K0lc1w4eIyunljvGHHYD-qh9-XwmyZZpeKpyWANSMOhpVqQgXZqpJeNIvlL0an4ukij80FyQNl_zkMbSTSRZPXcNRrVRDFw.webp"
                    alt="profile-img">
        </div>
        <div class="profile-message">
						<span>
							<h2>고로상</h2>
							<h2>
                            急に腹が減ってきた(KHH)
                            </h2>
						</span>
        </div>
        <div class="profile-updateBtn" style="align-content: center;">
            <form action="/Edit_profileC" method="post">
                <button class="change_profile" value="프로필 수정">프로필 수정</button>
            </form>

        </div>
    </div>
</div>

<div class="myshop-title">
    <h2>내 가게 정보</h2>

    <button class="myshop_more" onclick="location.href='ShowAllRestaurantC'">더보기</button>
</div>
<%-- <div class="myShop_information" style="border: none;">
     <a href="ShowAllRestaurantC" style="text-decoration: none; color: black">
         <h2 style="margin-inline-end: auto;">내 가게 정보</h2>
     </a>
 </div>--%>


<div class="myShop_information_list_field">

    <div class="myShop_information_list">
        <a href="DetailRestaurantC">
            <img class="myShop_information_list_img"
                 src="https://mblogthumb-phinf.pstatic.net/MjAxODA3MDVfNDAg/MDAxNTMwNzU3MjEyNjQ0.RNprD67cJ2AOWI2GmRVprWVv7qtNI-d3WsN-XhRLTLIg.hpd6lxFA6mkoZ-78fN-zmiLtxAv9H3iP_Jxs6bF7XCEg.PNG.witchstudio/image.png?type=w800"
                 alt="place-name">
        </a>
        <div style="border: none;">
            <a href="DetailRestaurantC">
                <h2>오이시 스시야</h2>
            </a>
            <%--<div style="border: none;">
                <a href="DetailRestaurantC?no=${res.id}" style="text-decoration: none; color: black">
                    <h2>오이시 스시야</h2>
                </a>
            </div>--%>
            <%--<div style="border: none;">
                <h3>도쿄도 시나가와구</h3>
                <h3>10:00 ~ 22:00</h3>
            </div>
            <div style="border: none; margin-bottom: 7px;">
                <button class="MenuButton" onclick="location.href=''">
                    메뉴추가
                </button>
                <button class="MenuButton" onclick="location.href=''">
                    메뉴변경
                </button>
            </div>--%>
        </div>
        <div style="border: none;">
            <h3>도쿄도 시나가와구</h3>
            <h3>10:00 ~ 22:00</h3>
        </div>
        <div class="myShop_menuBtn">
            <button class="MenuButton" onclick="location.href=''">
                메뉴추가
            </button>
            <button class="MenuButton" onclick="location.href=''">
                메뉴변경
            </button>
        </div>
    </div>

    <div class="myShop_information_list">
        <img class="myShop_information_list_img"
             src="https://kr.savorjapan.com/gg/content_image/t0283_017.jpg"
             alt="place-name">
        <div style="border: none;">
            <h2>무라카미 하코다테 본점</h2>
        </div>
        <div style="border: none;">
            <h3>도쿄도 세타가야구</h3>
            <h3>10:00 ~ 15:00</h3>
        </div>
        <div class="myShop_menuBtn">
            <button class="MenuButton" onclick="location.href=''">
                메뉴추가
            </button>
            <button class="MenuButton" onclick="location.href=''">
                메뉴변경
            </button>
        </div>
    </div>
    <div class="myShop_information_list">
        <img class="myShop_information_list_img"
             src="https://kr.savorjapan.com/gg/content_image/t0283_005.jpg"
             alt="place-name">
        <div style="border: none;">
            <h2>라멘 삿포로 이치류안</h2>
        </div>
        <div style="border: none;">
            <h3>도쿄도 시부야</h3>
            <h3>10:00 ~ 24:00</h3>
        </div>
        <div class="myShop_menuBtn">
            <button class="MenuButton" onclick="location.href=''">
                메뉴추가
            </button>
            <button class="MenuButton" onclick="location.href=''">
                메뉴변경
            </button>
        </div>
    </div>
    <div class="myShop_information_list">
        <img class="myShop_information_list_img"
             src="https://kr.savorjapan.com/gg/content_image/t0283_015.jpg"
             alt="place-name">
        <div style="border: none;">
            <h2>해산물 로바타야키</h2>
        </div>
        <div style="border: none;">
            <h3>도쿄도 시나가와구</h3>
            <h3>10:00 ~ 22:00</h3>
        </div>
        <div class="myShop_menuBtn">
            <button class="MenuButton" onclick="location.href=''">
                메뉴추가
            </button>
            <button class="MenuButton" onclick="location.href=''">
                메뉴변경
            </button>
        </div>
    </div>
</div>

<div class="shopReview-title">
    <h2>내 가게 예약</h2>
    <button class="shopReview_more" onclick="location.href='ReviewListC'">더보기</button>
</div>

<%--<div class="review-list" style="border: none;">
    <h2 style="margin-inline-end: auto;">예약리스트</h2>
    <div style="border: none;">
        <h3>
            <a href="http://localhost/18_KHH_Test/ReviewsPage.jsp"> 더보기 ></a>
        </h3>
    </div>
</div>--%>

<div class="shopReview-list">
    <div class="review-comment" style="border: none;">
        <span>🦀🍴</span> [ 예약정보 예시 ] 가게명 / 고객이름,인수 / 날짜,시간 / 전화번호
    </div>
    <div class="review-comment" style="border: none;">
        <span>🦀🍴</span> [ 예약정보 예시 ] 가게명 / 고객이름,인수 / 날짜,시간 / 전화번호
    </div>
    <div class="review-comment" style="border: none;">
        <span>🦀🍴</span> [ 예약정보 예시 ] 가게명 / 고객이름,인수 / 날짜,시간 / 전화번호
    </div>
</div>

</body>
</html>
