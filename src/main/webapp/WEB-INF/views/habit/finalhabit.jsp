<%--<%@ page language="java" contentType="text/html; charset=utf-8"--%>
<%--pageEncoding="utf-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"--%>
<%--prefix="c" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="ko">--%>
<%--  <head>--%>
<%--    <meta charset="UTF-8" />--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0" />--%>
<%--    <link--%>
<%--      href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap"--%>
<%--      rel="stylesheet"--%>
<%--    />--%>
<%--    <link rel="stylesheet" href="/static/css/habit/habit.css" />--%>
<%--  </head>--%>

<%--  <body>--%>
<%--    <!-- 전체 컨테이너 -->--%>
<%--    <div class="container">--%>
<%--      <!-- 🟠 왼쪽 컨테이너 (사이드바) -->--%>
<%--      <div class="left-container">--%>
<%--        <aside class="sidebar">--%>
<%--          <nav class="sidebar-menu">--%>
<%--            <button class="sidebar-btn">--%>
<%--              <img src="/static/imgsource/layout/home.png" alt="홈" />--%>
<%--            </button>--%>
<%--            <button class="sidebar-btn">--%>
<%--              <img src="/static/imgsource/layout/calandar.png" alt="목록" />--%>
<%--            </button>--%>
<%--            <button class="sidebar-btn">--%>
<%--              <img src="/static/imgsource/layout/pencil.png" alt="채팅" />--%>
<%--            </button>--%>
<%--            <button class="sidebar-btn">--%>
<%--              <img src="/static/imgsource/layout/chat.png" alt="공유" />--%>
<%--            </button>--%>
<%--            <button class="sidebar-btn">--%>
<%--              <img src="/static/imgsource/layout/settingss.png" alt="설정" />--%>
<%--            </button>--%>
<%--            <div class="bbiyak">--%>
<%--              <img src="/static/imgsource/layout/bbiyak.png" />--%>
<%--            </div>--%>
<%--          </nav>--%>
<%--        </aside>--%>
<%--      </div>--%>

<%--      <!-- 🟣 오른쪽 컨테이너 (헤더바 + 콘텐츠) -->--%>
<%--      <div class="right-container">--%>
<%--        <header class="header-bar">--%>
<%--          <div class="brand-title">--%>
<%--            <img src="/static/imgsource/layout/logo.png" alt="KOYOI 로고" />--%>
<%--          </div>--%>
<%--          <div class="header-icons">--%>
<%--            <img--%>
<%--              class="profile-img"--%>
<%--              src="/static/imgsource/layout/testprofile.png"--%>
<%--              alt="프로필"--%>
<%--            />--%>
<%--          </div>--%>
<%--        </header>--%>

<%--        <main class="content">--%>
<%--          <div class="habit-page">--%>
<%--            <!-- ✅ 1행: 내 습관 / 캘린더 / 주간 이력 -->--%>
<%--            <div class="widget-box habit-list">--%>
<%--              <div class="myhabit">習慣リスト</div>--%>
<%--              <div class="myhabit-list">--%>
<%--                <c:forEach var="habit" items="${habits}">--%>
<%--                  <div id="habit-box-${habit.habit_id}">--%>
<%--                    <input type="checkbox" id="habit-${habit.habit_id}" />--%>
<%--                    <label for="habit-${habit.habit_id}"--%>
<%--                      >${habit.habit_name}</label--%>
<%--                    >--%>
<%--                    <button--%>
<%--                      class="delete-btn"--%>
<%--                      onclick="deleteHabit(${habit.habit_id})"--%>
<%--                    >--%>
<%--                      삭제--%>
<%--                    </button>--%>
<%--                  </div>--%>
<%--                </c:forEach>--%>
<%--              </div>--%>
<%--              <div>--%>
<%--                <input--%>
<%--                  type="text"--%>
<%--                  id="habitInput"--%>
<%--                  placeholder="習慣の追加はこちら"--%>
<%--                />--%>
<%--                <button id="addHabitBtn">+</button>--%>
<%--              </div>--%>
<%--            </div>--%>

<%--            <div class="widget-box habit-calendar">--%>
<%--              <div class="calendar">--%>
<%--                <div class="calendar-header">--%>
<%--                  <button id="prevMonth">&lt;</button>--%>
<%--                  <span id="monthYear"></span>--%>
<%--                  <button id="nextMonth">&gt;</button>--%>
<%--                </div>--%>
<%--                <div class="calendar-days">--%>
<%--                  <div class="day-name">日</div>--%>
<%--                  <div class="day-name">月</div>--%>
<%--                  <div class="day-name">火</div>--%>
<%--                  <div class="day-name">水</div>--%>
<%--                  <div class="day-name">木</div>--%>
<%--                  <div class="day-name">金</div>--%>
<%--                  <div class="day-name">土</div>--%>
<%--                </div>--%>
<%--                <div id="calendarBody" class="calendar-body"></div>--%>
<%--                <div--%>
<%--                  id="selectedDateDisplay"--%>
<%--                  style="margin-top: 10px; font-weight: bold"--%>
<%--                >--%>
<%--                  日付 ： 未選択--%>
<%--                </div>--%>
<%--              </div>--%>
<%--            </div>--%>

<%--            <div class="widget-box habit-week">--%>
<%--              <h3>WEEKLY習慣履歴</h3>--%>
<%--              <table class="week-table">--%>
<%--                <thead>--%>
<%--                  <tr>--%>
<%--                    <th>習慣名</th>--%>
<%--                    <th>日</th>--%>
<%--                    <th>月</th>--%>
<%--                    <th>火</th>--%>
<%--                    <th>水</th>--%>
<%--                    <th>木</th>--%>
<%--                    <th>金</th>--%>
<%--                    <th>土</th>--%>
<%--                  </tr>--%>
<%--                </thead>--%>
<%--                <tbody id="weeklyHabitBody">--%>
<%--                  <!-- 자바스크립트에서 데이터로 채워짐 -->--%>
<%--                </tbody>--%>
<%--              </table>--%>
<%--            </div>--%>

<%--            <!-- ✅ 2행: 추천 습관 / 격려의 말 / 메모 -->--%>
<%--            <div class="widget-box habit-recommend">--%>
<%--              <div class="habit-tabs">--%>
<%--                <div--%>
<%--                  id="habit-tab-신체건강"--%>
<%--                  class="habit-tab habit-active"--%>
<%--                  onclick="habitShowTab('신체건강')"--%>
<%--                >--%>
<%--                  신체건강--%>
<%--                </div>--%>
<%--                <div--%>
<%--                  id="habit-tab-정신건강"--%>
<%--                  class="habit-tab"--%>
<%--                  onclick="habitShowTab('정신건강')"--%>
<%--                >--%>
<%--                  정신건강--%>
<%--                </div>--%>
<%--              </div>--%>

<%--              <div id="habit-신체건강" class="habit-content">--%>
<%--                <div style="float: left; width: 25%">--%>
<%--                  <p id="exercise">✅ 運動</p>--%>
<%--                  <p id="meal">🍽️ 食事</p>--%>
<%--                  <p id="diet">🏋️ 体重管理</p>--%>
<%--                </div>--%>
<%--                <div style="float: left; width: 25%">--%>
<%--                  <p id="supplement">✅ サプリ</p>--%>
<%--                  <p id="water">🍽️ お水</p>--%>
<%--                  <p id="posture">🏋️ 正しい姿勢</p>--%>
<%--                </div>--%>
<%--                <div style="float: left; width: 25%">--%>
<%--                  <p id="sunshine">✅ 日差し</p>--%>
<%--                  <p id="rest">🍽️ 休憩</p>--%>
<%--                  <p id="stretch">🏋️ ストレッチ</p>--%>
<%--                </div>--%>
<%--              </div>--%>

<%--              <div--%>
<%--                id="habit-정신건강"--%>
<%--                class="habit-content habit-hidden"--%>
<%--                onclick="habitShowTab('정신건강')"--%>
<%--              >--%>
<%--                <div class="habit-정신건강-part">--%>
<%--                  <p id="walk">散歩</p>--%>
<%--                  <p id="meal">食事</p>--%>
<%--                  <p id="talk">お喋り</p>--%>
<%--                  <p id="friend">友達</p>--%>
<%--                  <p id="exercise">運動</p>--%>
<%--                  <p id="book">読書</p>--%>
<%--                  <p id="game">ゲーム</p>--%>
<%--                  <p id="water">お水</p>--%>
<%--                  <p id="movie">映画</p>--%>
<%--                </div>--%>
<%--              </div>--%>
<%--            </div>--%>

<%--            <div class="widget-box habit-rate">--%>
<%--              <h3>励ましの言葉</h3>--%>
<%--              <ul id="encouragementList"></ul>--%>
<%--            </div>--%>

<%--            <div class="widget-box habit-memo">--%>
<%--              <h3>주간 회고 메모</h3>--%>
<%--              <textarea--%>
<%--                id="weeklyMemoText"--%>
<%--                rows="6"--%>
<%--                placeholder="이번 주를 돌아보는 나의 생각을 적어보세요..."--%>
<%--                style="width: 100%; resize: none"--%>
<%--              ></textarea>--%>
<%--              <button id="saveMemoBtn">저장하기</button>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </main>--%>
<%--      </div>--%>
<%--    </div>--%>

<%--    <script src="https://kit.fontawesome.com/a076d05399.js"></script>--%>
<%--    <script src="/static/js/habit/habit.js"></script>--%>

<%--  </body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
                                            prefix="c" %>

<style>
  @font-face {
    font-family: 'MyFont';
    src: url('/static/fonts/Boku2-Regular.otf') format('opentype');
  }

  body {
    font-family: 'MyFont', sans-serif;
    font-size: 32px;
    color: black;
  }
</style>

<!DOCTYPE html>
<html lang="ja">
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link
          href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap"
          rel="stylesheet"
  />
  <link rel="stylesheet" href="/static/css/habit/habit.css" />



      <div class="habit-page">
        <!-- ✅ 1行目：マイ習慣 / カレンダー / 週間履歴 -->
        <div class="widget-box habit-list">
          <div class="myhabit">マイ習慣</div>
          <div class="myhabit-list">
            <c:forEach var="habit" items="${habits}">
              <div id="habit-box-${habit.habit_id}">
                <input type="checkbox" id="habit-${habit.habit_id}" />
                <label for="habit-${habit.habit_id}"
                >${habit.habit_name}</label
                >
                <button
                        class="delete-btn"
                        onclick="deleteHabit(${habit.habit_id})"
                >
                  削除
                </button>
              </div>
            </c:forEach>
          </div>
          <div>
            <input
                    type="text"
                    id="habitInput"
                    placeholder="習慣を追加してください"
            />
            <button id="addHabitBtn">＋</button>
          </div>
        </div>

        <div class="widget-box habit-calendar">
          <div class="calendar">
            <div class="calendar-header">
              <button id="prevMonth">&lt;</button>
              <span id="monthYear"></span>
              <button id="nextMonth">&gt;</button>
            </div>
            <div class="calendar-days">
              <div class="day-name">日</div>
              <div class="day-name">月</div>
              <div class="day-name">火</div>
              <div class="day-name">水</div>
              <div class="day-name">木</div>
              <div class="day-name">金</div>
              <div class="day-name">土</div>
            </div>
            <div id="calendarBody" class="calendar-body"></div>
            <div
                    id="selectedDateDisplay"
                    style="margin-top: 10px;font-weight: bold;font-size: 18px;"
            >
              日付：未選択
            </div>
          </div>
        </div>

        <div class="widget-box habit-week">
          <h3 style="    font-size: 18px;">週間習慣履歴</h3>
          <table class="week-table">
            <thead>
            <tr>
              <th>習慣名</th>
              <th>日</th>
              <th>月</th>
              <th>火</th>
              <th>水</th>
              <th>木</th>
              <th>金</th>
              <th>土</th>
            </tr>
            </thead>
            <tbody id="weeklyHabitBody">
            <!-- JavaScriptでデータ挿入 -->
            </tbody>
          </table>
        </div>

        <div class="widget-box habit-recommend">
          <div class="habit-tabs">
            <div
                    id="habit-tab-신체건강"
                    class="habit-tab habit-active"
                    onclick="habitShowTab('신체건강')"
            >
              身体の健康
            </div>
            <div
                    id="habit-tab-정신건강"
                    class="habit-tab"
                    onclick="habitShowTab('정신건강')"
            >
              心の健康
            </div>
          </div>

          <div id="habit-신체건강" class="habit-content">
            <div style="float: left; width: 25%">
              <p id="exercise">✅ 運動</p>
              <p id="meal">🍽️ 食事</p>
              <p id="diet">🏋️ 体重管理</p>
            </div>
            <div style="float: left; width: 25%">
              <p id="supplement">✅ サプリメント</p>
              <p id="water">🍽️ 水分摂取</p>
              <p id="posture">🏋️ 姿勢を正す</p>
            </div>
            <div style="float: left; width: 25%">
              <p id="sunshine">✅ 日光浴</p>
              <p id="rest">🍽️ 休憩</p>
              <p id="stretch">🏋️ ストレッチ</p>
            </div>
          </div>

          <div
                  id="habit-정신건강"
                  class="habit-content habit-hidden"
                  onclick="habitShowTab('정신건강')"
          >
            <div class="habit-정신건강-part">
              <p id="walk">散歩</p>
              <p id="meal">食事</p>
              <p id="talk">おしゃべり</p>
              <p id="friend">友達</p>
              <p id="exercise">運動</p>
              <p id="book">読書</p>
              <p id="game">ゲーム</p>
              <p id="water">水分摂取</p>
              <p id="movie">映画鑑賞</p>
            </div>
          </div>
        </div>

        <div class="widget-box habit-rate">
          <h3>励ましの言葉</h3>
          <ul id="encouragementList"></ul>
        </div>

        <div class="widget-box habit-memo">
          <h3 style="
          font-size: 18px;
          "> 週間振り返りメモ</h3>
          <textarea
                  id="weeklyMemoText"
                  rows="6"
                  placeholder="今週の振り返りを自由に書いてみましょう..."
                  style="width: 100%;resize: none;border-radius: 12px;border: 3px solid #f7efe7;padding: 11px;"
          ></textarea>
          <button id="saveMemoBtn">保存する</button>
        </div>
      </div>
  </div>
</div>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="/static/js/habit/habit.js"></script>
</html>

