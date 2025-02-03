<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../../css/ownerMyPage/ownerAddRestaurant.css">
</head>
<body>
<div class="container">

        <form action="AddRestaurantC" method="post" enctype="multipart/form-data">
            <div class="mypage" style="border: none;">
               <%-- <a href="MyPage.jsp" style="text-decoration: none; color: black">--%>
                    <h1>가게 등록하기</h1>
                <%--</a>--%>
            </div>

           <%-- <div class="myShop-list" style="border: none;">
                <h1>추가하실 가게의 정보</h1>
            </div>--%>

            <div class="myShop_information_list_field">
                <div class="myShop_information">
                    <%--<div class="shopImageBtnDiv">

                        &lt;%&ndash;<img id="previewImage1"
                            src="https://d12zq4w4guyljn.cloudfront.net/750_750_20230827024509017_photo_408d3d3f6fc0.jpg"
                            alt="place-name" width="480" height="320"> <input
                            type="file" id="fileInput1" style="display: none;"
                            onchange="changeImage(event, 'previewImage1')">
                        <img
                            id="previewImage2"
                            src="https://d12zq4w4guyljn.cloudfront.net/750_750_20230827024509661_photo_408d3d3f6fc0.jpg"
                            alt="place-name" width="480" height="320"> <input
                            type="file" id="fileInput2" style="display: none;"
                            onchange="changeImage(event, 'previewImage2')">&ndash;%&gt;
                                <label for="mainimage">
                                    <div class="btn-upload">메인 이미지 업로드하기</div>
                                </label>
                        <input accept=".jpg,.jpeg,.png" type="file" id="mainimage" name="main" required>

                        &lt;%&ndash;<input type="file" id="fileInput1" onchange="updateButtonText(this)">
                        <button class="add_image_button"
                                onclick="triggerFileInput('fileInput1');">
                            메인이미지 선택
                        </button>
                        <input hidden="hidden" accept=".jpg,.jpeg,.png" type="file" id="fileInput1"
                               onchange="updateButtonText(this)">
                        <button class="add_image_button"
                                onclick="triggerFileInput('fileInput2');">
                            서브이미지 선택
                        </button>
                        <input multiple accept=".jpg,.jpeg,.png" type="file" id="fileInput2" hidden="hidden"
                               onchange="updateButtonText(this)">&ndash;%&gt;
                    </div>--%>

                    <div style="border: none; width: 300px;">
                        <div>
                            <div style="margin-bottom: 10px;">
                                <p class="myShop_info_input_field">가게이름</p>
                                <input name="addName" style="width: 300px">
                            </div>
                            <div style="padding-top: 10px;">
                                <p class="myShop_info_input_field">주인이름</p>
                                <input name="addOwner" style="width: 300px">
                            </div>
                            <div style="padding-top:20px;">
                                <p class="myShop_info_input_field">주소</p>
                                <select name="addRegion" style="width: 150px;">
                                    <option value="도오">도오</option>
                                    <option value="도난">도난</option>
                                    <option value="도토">도토</option>
                                    <option value="도호쿠">도호쿠</option>
                                </select>
                            </div>
                            <p class="myShop_info_input_field">상세주소</p>
                            <input name="addAddr" style="width: 300px">
                            <p class="myShop_info_input_field">영업시간</p>
                            <select name="addWeek" style="width: 150px;">
                                <option value="연중무휴">연중무휴</option>
                                <option value="평일">평일</option>
                                <option value="주말">주말</option>
                            </select>
                            <br>
                            <select name="addOpenHour" id="start-hour-select" style="width: 66px;"></select>
                            <select name="addOpenMinute" id="start-minute-select" style="width: 66px;"></select>
                            ~
                            <select name="addCloseHour" id="end-hour-select" style="width: 66px;"></select>
                            <select name="addCloseMinute" id="end-minute-select" style="width: 66px;"></select>

                            <script>
                                const startHourSelect = document.getElementById("start-hour-select");
                                for (let i = 1; i <= 24; i++) {
                                    const option = document.createElement("option");
                                    option.value = i;
                                    option.textContent = i + "시";
                                    startHourSelect.appendChild(option);
                                }

                                const startMinuteSelect = document.getElementById("start-minute-select");
                                for (let i = 0; i < 60; i += 5) {
                                    const option = document.createElement("option");
                                    option.value = i;
                                    option.textContent = i + "분";
                                    startMinuteSelect.appendChild(option);
                                }
                                const endHourSelect = document.getElementById("end-hour-select");
                                for (let i = 1; i <= 24; i++) {
                                    const option = document.createElement("option");
                                    option.value = i;
                                    option.textContent = i + "시";
                                    endHourSelect.appendChild(option);
                                }

                                const endMinuteSelect = document.getElementById("end-minute-select");
                                for (let i = 0; i < 60; i += 5) {
                                    const option = document.createElement("option");
                                    option.value = i;
                                    option.textContent = i + "분";
                                    endMinuteSelect.appendChild(option);
                                }
                            </script>
                            <p class="myShop_info_input_field">전화번호</p>
                            <input name="addPhoneNum1" style="width: 80px; text-align: center">
                            -
                            <input name="addPhoneNum2" style="width: 90px; text-align: center">
                            -
                            <input name="addPhoneNum3" style="width: 90px; text-align: center">
                            <p class="myShop_info_input_field">가게에 대한 설명</p>
                            <textarea name="addExplain" style="resize: none; width: 100%; height: 150px;"></textarea>
                        </div>
                        <div class="shopImageBtnDiv">
                            <div class="mainBtnDiv">
                                <label for="mainimage">
                                    <div class="btn-upload">메인 이미지 업로드하기</div>
                                </label>
                                <input accept=".jpg,.jpeg,.png" type="file" id="mainimage" name="main" required>
                            </div>
                            <div class="subBtnDiv">
                                <label for="subimage">
                                    <div class="btn-upload">서브 이미지 업로드하기</div>
                                </label>
                                <input accept=".jpg,.jpeg,.png" type="file" id="subimage" name="sub" multiple required>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <input hidden="hidden" name="no" value="">
            <div class="myShop_button_box">
                <button onclick="location.href='ShowAllRestaurantC'" id="cancel_info">목록으로</button>
                <button type="submit" id="complete_info">등록하기</button>
            </div>
        </form>

</div>

<%--<script>
    // 파일 입력 클릭을 트리거하는 함수
    function triggerFileInput(inputId) {
        document.getElementById(inputId).click();
    }

    // 파일 선택 후 버튼 텍스트 업데이트
    function updateButtonText(inputElement) {
        const fileName = inputElement.files[0]?.name || "메인이미지 선택";
        document.querySelector(".add_image_button").textContent = fileName;
    }

    function changeButtonTextAndClickInput() {
        const fileButton = document.getElementById('fileButton');

        // 버튼 텍스트를 "파일 선택 중..."으로 바꿈
        fileButton.innerText = '파일 선택 중...';

        // 파일 input을 클릭하여 파일 탐색기 열기
        document.getElementById('fileInput').click();
    }

    function changeButtonText() {
        const fileInput = document.getElementById('fileInput');
        const fileButton = document.getElementById('fileButton');

        // 파일이 선택되면 버튼 텍스트 변경
        if (fileInput.files.length > 0) {
            const maxLength = 20; // 최대 길이
            let fileName = fileInput.files[0].name;
            if (fileName.length > maxLength) {
                fileName = fileName.substring(0, maxLength) + '...';
            }
            fileButton.innerText = '파일 선택됨: ' + fileName;
        } else {
            fileButton.innerText = '파일을 선택해주세요';
        }
    }

    function changeImage(event, previewImageId) {
        const input = event.target;
        const previewImage = document.getElementById(previewImageId);

        if (input.files && input.files[0]) {
            const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
            const maxSize = 5 * 1024 * 1024; // 5MB

            if (!allowedTypes.includes(input.files[0].type)) {
                alert('이미지 파일만 업로드 가능합니다.');
                return;
            }

            if (input.files[0].size > maxSize) {
                alert('파일 크기는 5MB를 초과할 수 없습니다.');
                return;
            }

            const reader = new FileReader();
            reader.onload = function (e) {
                previewImage.src = e.target.result; // 이미지 소스를 선택한 파일로 변경
            };
            reader.readAsDataURL(input.files[0]); // 파일 읽기
        }
    }
</script>--%>
</body>
</html>