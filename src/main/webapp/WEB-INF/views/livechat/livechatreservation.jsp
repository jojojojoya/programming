<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sawarabi+Maru&family=M+PLUS+Rounded+1c:wght@100;300;400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="/static/css/livechat/livechatreservation.css">
</head>
<body>
        <main class="content">

            <div class="top-section">
                <div class="livechat_table">
                    <div style="color: #D8D2C2; font-size: 20px;"  > 전문가와의 상담 예약을 원하시나요? </div>
                    <div class="livechat_info">

                        <div id="livechat_step1" class="show">
                            <div class="livechat_list"> 상담하고 싶은 일정을 선택해주세요. </div>
                            <input type="date" id="livechat_reserve_date" onchange="showNext('step2')">
                        </div>

                        <div id="step2">
                            <div class="livechat_list"> 해당 일자에 가장 코요이한 시간을 선택해주세요. </div>
                            <select id="livechat_reserve_time" onchange="showNext('step3')">
                                <option value="">시간 선택</option>
                                <option value="10:00">10:00</option>
                                <option value="11:00">11:00</option>
                                <option value="12:00">12:00</option>
                                <option value="13:00">13:00</option>
                                <option value="14:00">14:00</option>
                                <option value="15:00">15:00</option>
                                <option value="16:00">16:00</option>
                                <option value="17:00">17:00</option>
                                <option value="18:00">18:00</option>
                                <option value="19:00">19:00</option>
                                <option value="20:00">20:00</option>
                                <option value="21:00">21:00</option>
                                <option value="22:00">22:00</option>
                            </select>


                        </div>


                        <div id="step3">
                            <div class="livechat_list"> 상담하고 싶은 분야를 선택해주세요. </div>
                            <select id="livechat_reserve_category" onchange="showNext('reserveSection')">
                                <option value="">카테고리 선택</option>
                                <option value="건강">건강</option>
                                <option value="미래">미래</option>
                                <option value="인간관계">인간관계</option>
                                <option value="기타고민">기타고민</option>
                            </select>
                        </div>

                        <div id="reserveSection">
                            <button id="livechat_reserve_btn"> 예약하기 </button>
                        </div>

                        <div id="conformation">
                            <div id="conformation_text" class="livechat_list" style="display: none;">
                                해당 문의를 조합해 적합한 상담사님을 배정해드렸어요.
                            </div>
                            <button id="livechat_exit_btn" class="hide">나가기</button>
                        </div>


                    </div>
                </div>
            </div>
        </main>
<script src="/static/js/livechat/livechatreservation.js"></script>

</body>
</html>