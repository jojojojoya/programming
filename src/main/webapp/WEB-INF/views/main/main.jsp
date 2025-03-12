<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>KOYOI</title>
    <link href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/main/main.css">
    <script src="/static/js/main/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css">
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

</head>
<body>

<div class="container">

    <header class="header-bar">

        <div class="logo-container">
            <img class="logo-icon" src="/static/imgsource/logo.png" alt="KOYOI">
        </div>
        <div class="header-icons">
            <button class="header-btn" id="notice">
                <img src="/static/imgsource/notice.png" alt="notice">
            </button>
            <%--추후 알림창 설정--%>
<%--            <button class="header-btn" id="message">
                <img src="/static/imgsource/chat.png" alt="message">
            </button>--%>
            <button class="header-btn">
                <img src="/static/imgsource/logout.png" alt="logout">
            </button>
            <img class="profile-img" src="/static/imgsource/testprofile.png" alt="profile">
        </div>

    </header>

    <%-- 공지 모달창 --%>
    <div id="notice-modal" class="modal">
        <div class="modal-content">
            <span class="close-btn"> &times; </span>
            <h3> 공지사항 </h3>
            <ul id="notice-lists">
                <li><a href="#"> 첫 번째 공지사항 </a></li>
                <li><a href="#"> 두 번째 공지사항 </a></li>
                <li><a href="#"> 세 번째 공지사항 </a></li>
            </ul>
        </div>
    </div>

    <main class="main-container">

        <div class="quotes-container">
            <div class="swiper">
                <div class="swiper-wrapper" id="quoteWrapper">
                    <c:forEach var="quote" items="${quotes}">
                        <div class="swiper-slide">
                            ${quote.content}
                        </div>
                    </c:forEach>
                </div>
                <div class="swiper-button-prev"></div>
                <div class="swiper-button-next"></div>
            </div>
        </div>
        <script>

            const swiper = new Swiper('.swiper', {
                direction: 'horizontal',
                loop: true,
                autoplay: {
                    delay: 60000,
                    disableOnInteraction: false, // 슬라이드를 터치해도 오토플레이가 적용됨.
                },

                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                }
            });
        </script>

        <div class="content-wrapper">
            <!-- 왼쪽 영역: 달력 -->
            <div class="left-content">
                <div class="calendar-container">
                    <jsp:include page="maincalendar.jsp"/>
                </div>
            </div>

            <!-- 오른쪽 영역: 체크리스트 + 무드 그래프 + 챗봇 -->
            <div class="right-content">
                <div class="right-inner">
                    <div class="checklist-container">
                        <h3> 체크리스트 </h3>
                        <div class="checklist-input">
                            <input type="text" id="task-input" placeholder="할 일을 입력하세요">
                            <button id="add-task-btn"> 추가 </button>
                        </div>
                        <ul id="task-list"></ul>
                    </div>

                    <div class="right-side">
                        <div class="mood-chart">
                            <h3> Mood Chart </h3>
                            <canvas id="moodChart"></canvas>
                        </div>

                        <script>
                            document.addEventListener("DOMContentLoaded", function () {
                                const ctx = document.getElementById('moodChart').getContext('2d');

                                /*const moodChart = document.getElementById('moodChart');
                                moodChart.width = 220;  // 원하는 너비(px)
                                moodChart.height = 150; // 원하는 높이(px)*/

                                new Chart(ctx, {
                                    type: 'bar',
                                    data: {
                                        labels: ['월', '화', '수', '목', '금', '토', '일'], // X축: 요일
                                        datasets: [{
                                            label: '기분 점수',
                                            data: [7, 8, 6, 5, 9, 8, 7], // 예제 기분 점수
                                            backgroundColor: ['#ff9999', '#ffcc99', '#ffff99', '#99ff99', '#99ccff', '#cc99ff', '#ff99cc'],
                                            borderColor: '#555',
                                            borderWidth: 1
                                        }]
                                    },
                                    options: {
                                        responsive: true, // 반응형 그래프
                                        scales: {
                                            y: {
                                                beginAtZero: true, // Y축 0부터 시작
                                                max: 10 // 최대 기분 점수 10으로 설정
                                            }
                                        }
                                    }
                                });
                            });
                        </script>

                        <div class="chat-connect">
                            <button class="chatbot"> ChatBot</button>
                            <button class="livechat" onclick="location.href='/livechat'"> LiveChat </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </main>

</div>
</body>
</html>