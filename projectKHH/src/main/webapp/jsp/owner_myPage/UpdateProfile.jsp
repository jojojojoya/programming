<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
            height: 2000px;
            border: none;
        }

        .footer {
            height: 200px;
        }

        .profile-field {
            display: flex;
            margin: 30px;
            height: 250px;
        }

        .profile-circle {
            display: flex;
            flex-direction: column;
        }

        .profile-circle img {
            border-radius: 50%;
            width: 170px;
            height: 170px;
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

        .change_profile {
            height: 50px;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            color: #ffffff;
            background-color: #007bff;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s, transform 0.2s;
        }

        .change_profile:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
            transform: scale(1.05); /* 약간 확대 */
        }

        .myProfile_button_box {
            display: flex;
            justify-content: center;
            gap: 10px;
            border: none;
            margin-top: 10px;
        }

        .myProfile_button_box > button {
            height: 50px;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            color: #ffffff;
            background-color: #007bff;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s, transform 0.2s;
        }

        #complete_info:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
            transform: scale(1.05); /* 약간 확대 */
        }

        #cancel_info:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
            transform: scale(1.05); /* 약간 확대 */
        }

        .nameCheck {
            padding: 7px 10px;
            font-size: 16px;
            font-weight: bold;
            color: #ffffff;
            background-color: #000000;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s, transform 0.2s;
        }
        .nameCheck:hover {
            background-color: #9d0de6; /* 호버 시 배경색 변경 */
            transform: scale(1.05); /* 약간 확대 */
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">header</div>
    <div class="nav">nav</div>
    <div class="content">
        <form action="/Edit_profileC" method="post">
        <div class="mypage" style="border: none;">
            <h1>MyPage</h1>
            <hr>
            <h2>프로필 편집</h2>
        </div>

        <div class="profile">

            <div class="profile-field" style="border: none;">
                <div class="profile-circle" style="border: none;">
                    <img
                            id="previewImage"
                            src="/jsp/owner_myPage/profilePhoto/${res.picture}"
                            alt="place-name" width="480" height="320">
                        <input name="picture"
                        type="file" id="fileInput" style="display: none;"
                        onchange="changeImage(event, 'previewImage')">
                    <br>
                    <button class="add_image_button" type="button"
                            onclick="document.getElementById('fileInput').click();">
                        프로필사진을 선택
                    </button>
                </div>
                <div
                        style="border: none; align-content: baseline; margin-left: 30px">
                    <p style="margin-bottom: 8px">
                        이름을 입력 <br>
                    </p>
                    <input name="nickname" value="${res.nickname}">
                    <button type="button" class="nameCheck">중복체크</button>

                    <br>
                    <p>
                        상태메시지
                    </p>
                    <textarea style="resize: none; height: 100px" name="message" id="" cols="30" rows="10">${res.message}</textarea> <br> <br>
                </div>

            </div>
        </div>
        <div class="myProfile_button_box" style="border: none;">
            <button type="button" onclick="location.href='ShowOwnerMyPageController'" id="cancel_info">취소</button>
            <button type="submit" id="complete_info" name="email" value="${res.email}">수정완료</button>
        </div>


        <script type="text/javascript">
            function changeImage(event, previewImageId) {
                const input = event.target;
                const previewImage = document.getElementById(previewImageId);

                if (input.files && input.files[0]) {
                    const reader = new FileReader();

                    reader.onload = function (e) {
                        previewImage.src = e.target.result; // 이미지 소스를 선택한 파일로 변경
                    };

                    reader.readAsDataURL(input.files[0]); // 파일 읽기
                }
            }
        </script>
        </form>
    </div>

    <div class="footer">footer</div>
</div>
</body>
</html>