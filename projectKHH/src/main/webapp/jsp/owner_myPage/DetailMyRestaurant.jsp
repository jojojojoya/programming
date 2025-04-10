<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        div {
            border: 1px solid;
        }

        .header {
            height: 100px;
        }

        .nav {
            height: 70px;
        }

        .content {
            margin-top: 100px;
            margin-left: 100px;
            margin-right: 100px;
            height: 3500px;
            border: none;
        }

        .footer {
            height: 200px;
        }

        .profile-field {
            display: flex;
        }

        .profile-circle img {
            border-radius: 50%;
            width: 170px;
            height: 170px;
        }

        .myShop-list {
            display: flex;
            justify-content: center;
        }

        .myShop_information {
            display: flex;
            text-align: center;
            border: none;
            margin: 20px;
            width: 850px;
        }

        #previewImage1 img {
            width: 100%;
            height: 320px;
            border-radius: 12px; /* 모서리 둥글게 */
            border: 2px solid #ddd; /* 얇은 테두리 */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
            transition: transform 0.3s, box-shadow 0.3s; /* 애니메이션 효과 */
        }

        #previewImage1:hover img {
            transform: scale(1.05); /* 호버 시 확대 효과 */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* 호버 시 그림자 강조 */
        }

        #previewImage2 img {
            width: 100%;
            height: 320px;
            border-radius: 12px; /* 모서리 둥글게 */
            border: 2px solid #ddd; /* 얇은 테두리 */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
            transition: transform 0.3s, box-shadow 0.3s; /* 애니메이션 효과 */
        }

        #previewImage2:hover img {
            transform: scale(1.05); /* 호버 시 확대 효과 */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* 호버 시 그림자 강조 */
        }

        .add_image_button {
            height: 50px;
            padding: 10px 20px; /* 버튼 내부 여백 */
            font-size: 16px; /* 텍스트 크기 */
            font-weight: bold; /* 텍스트 두께 */
            color: #ffffff; /* 텍스트 색상 */
            background-color: #007bff; /* 버튼 배경색 */
            border: none; /* 테두리 제거 */
            border-radius: 8px; /* 모서리 둥글게 */
            cursor: pointer; /* 마우스 오버 시 포인터 표시 */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 약간의 그림자 */
            transition: background-color 0.3s, transform 0.2s; /* 애니메이션 효과 */
        }

        .add_image_button:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
            transform: scale(1.05); /* 약간 확대 */
        }

        .myShop_info_input_field {
            margin-bottom: 6px;
        }

        .myShop_button_box {
            display: flex;
            justify-content: center;
            gap: 10px; /* 버튼 간격 설정 (선택사항) */
            border: none; /* 필요 시 테두리 제거 */
            margin-top: 10px;
        }

        #delete_info {
            height: 50px;
            padding: 10px 20px; /* 버튼 내부 여백 */
            font-size: 16px; /* 텍스트 크기 */
            font-weight: bold; /* 텍스트 두께 */
            color: #ffffff; /* 텍스트 색상 */
            background-color: #007bff; /* 버튼 배경색 */
            border: none; /* 테두리 제거 */
            border-radius: 8px; /* 모서리 둥글게 */
            cursor: pointer; /* 마우스 오버 시 포인터 표시 */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 약간의 그림자 */
            transition: background-color 0.3s, transform 0.2s; /* 애니메이션 효과 */
        }

        #delete_info:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
            transform: scale(1.05); /* 약간 확대 */
        }

        #update_info {
            height: 50px;
            padding: 10px 20px; /* 버튼 내부 여백 */
            font-size: 16px; /* 텍스트 크기 */
            font-weight: bold; /* 텍스트 두께 */
            color: #ffffff; /* 텍스트 색상 */
            background-color: #007bff; /* 버튼 배경색 */
            border: none; /* 테두리 제거 */
            border-radius: 8px; /* 모서리 둥글게 */
            cursor: pointer; /* 마우스 오버 시 포인터 표시 */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 약간의 그림자 */
            transition: background-color 0.3s, transform 0.2s; /* 애니메이션 효과 */
        }

        #update_info:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
            transform: scale(1.05); /* 약간 확대 */
        }

        .myShop_information_list_field {
            display: flex;
            overflow: hidden;
            height: 730px;
            background-color: rgb(198, 232, 242);
        }

        .bottom-list-num {
            text-align: center;
            item-align: center;
        }

        .text-box {
            margin-top: 20px;
            margin-left: 20px;
            margin-bottom: 40px;
        }

        .bottom-list-num {
            text-align: center;
            item-align: center;
        }

        .bottom-return-btn {
            text-align: center;
            item-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">header</div>
    <div class="nav">nav</div>
    <div class="content">

        <div class="mypage" style="border: none;">
            <h1>MyPage (누르면 마이페이지 메인으로 이동)</h1>
        </div>
        <br>
        <hr>

        <div class="myShop-list" style="border: none;">
            <h1>가게의 정보</h1>
        </div>
        <br>


        <div class="myShop_information_list_field" style="border: solid 1px;">
            <div class="myShop_information">
                <div style="width: 480px; border: none;">

                    <img id="previewImage1"
                         src="https://d12zq4w4guyljn.cloudfront.net/750_750_20230827024509017_photo_408d3d3f6fc0.jpg"
                         alt="place-name" width="480" height="320"> <input
                        type="file" id="fileInput1" style="display: none;"
                        onchange="changeImage(event, 'previewImage1')">
                    <img
                            id="previewImage2"
                            src="https://d12zq4w4guyljn.cloudfront.net/750_750_20230827024509661_photo_408d3d3f6fc0.jpg"
                            alt="place-name" width="480" height="320"> <input
                        type="file" id="fileInput2" style="display: none;"
                        onchange="changeImage(event, 'previewImage2')">
                    <br>

                </div>

                <div
                        style="border: none; text-align: left; margin-left: 30px; width: 300px">
                    <div style="border: none;">
                        <p class="myShop_info_input_field">${res.name}</p> <br>

                        <br>
                        <p class="myShop_info_input_field">${res.owner}</p> <br>

                        <br>
                        <p class="myShop_info_input_field">${res.address}</p> <br>

                        <br>
                        <p class="myShop_info_input_field">${res.opentime}</p> <br>

                        <br>
                        <p class="myShop_info_input_field">${res.phone}</p> <br>

                        <br>
                        <p class="myShop_info_input_field">${res.explain}</p>

                    </div>
                </div>
            </div>
        </div>
        <input hidden="hidden" name="no" value="">
        <div class="myShop_button_box" style="border: none;">
            <form action="Edit_myRestaurantC">
                <button id="update_info">수정</button>
            </form>
            <form action="Delete_myRestaurantC" method="post">
                <button id="delete_info">삭제</button>
            </form>
        </div>

    </div>


    <div class="footer">footer</div>
</div>
</body>
</html>