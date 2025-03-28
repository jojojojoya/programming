<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sawarabi+Maru&family=M+PLUS+Rounded+1c:wght@100;300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

    <link rel="stylesheet" href="/static/css/livechat/livechatreservation.css">
</head>
<body>
        <main class="content">

            <div class="top-section">
                <div class="livechat_table">
                    <div style="color: #D8D2C2; font-size: 20px;"  > 専門家との相談を予約しませんか？ </div>
                    <div class="livechat_info">

                        <div id="livechat_step1" class="show">
                            <div class="livechat_list"> ご希望の日付を選択してください。</div>
                            <input type="text" id="livechat_reserve_date" onchange="showNext('step2')">

                            <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
                            <script src="https://npmcdn.com/flatpickr/dist/l10n/ja.js"></script>
                            <script>
                                flatpickr("#livechat_reserve_date", {
                                    locale: "ja",
                                    dateFormat: "Y-m-d", // 서버에서 요구하는 yyyy-MM-dd
                                    minDate: "today"
                                });
                            </script>

                        </div>

                        <div id="step2">
                            <div class="livechat_list"> ご都合のよい時間帯をお選びください。</div>
                            <select id="livechat_reserve_time" onchange="showNext('step3')">
                                <option value="">時間を選択</option>
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
                            <div class="livechat_list"> ご相談されたいジャンルを選んでください。 </div>
                            <select id="livechat_reserve_category" onchange="showNext('reserveSection')">
                                <option value="">カテゴリーを選択</option>
                                <option value="健康">健康</option>
                                <option value="将来">将来</option>
                                <option value="人間関係">人間関係</option>
                                <option value="その他のお悩み">その他のお悩み</option>
                            </select>
                        </div>

                        <div id="reserveSection">
                            <button id="livechat_reserve_btn"> 予約する </button>
                        </div>

                        <div id="conformation">
                            <div id="conformation_text" class="livechat_list" style="display: none;">
                                内容に基づいて、最適なカウンセラーを割り当てました。
                            </div>
                            <button id="livechat_exit_btn" class="hide"> 戻る</button>
                        </div>


                    </div>
                </div>
            </div>
        </main>
<script src="/static/js/livechat/livechatreservation.js"></script>

</body>
</html>