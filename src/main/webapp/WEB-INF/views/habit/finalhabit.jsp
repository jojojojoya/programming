

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
                                            prefix="c" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link
          href="https://fonts.googleapis.com/css2?family=Inknut+Antiqua&display=swap"
          rel="stylesheet"
  />
  <link rel="stylesheet" href="/static/css/habit/habit.css" />

</head>

<body>
<!-- 全体コンテナ -->
<div class="container">
  <!-- 🟠 左側コンテナ（サイドバー） -->
  <div class="left-container">
    <aside class="sidebar">
      <nav class="sidebar-menu">
        <button class="sidebar-btn">
          <img src="/static/imgsource/layout/home.png" alt="ホーム" />
        </button>
        <button class="sidebar-btn">
          <img src="/static/imgsource/layout/calandar.png" alt="リスト" />
        </button>
        <button class="sidebar-btn">
          <img src="/static/imgsource/layout/pencil.png" alt="チャット" />
        </button>
        <button class="sidebar-btn">
          <img src="/static/imgsource/layout/chat.png" alt="共有" />
        </button>
        <button class="sidebar-btn">
          <img src="/static/imgsource/layout/settingss.png" alt="設定" />
        </button>
        <div class="bbiyak">
          <img src="/static/imgsource/layout/bbiyak.png" />
        </div>
      </nav>
    </aside>
  </div>

  <!-- 🟣 右側コンテナ（ヘッダーバー＋コンテンツ） -->
  <div class="right-container">
    <header class="header-bar">
      <div class="brand-title">
        <img src="/static/imgsource/layout/logo.png" alt="KOYOI ロゴ" />
      </div>
      <div class="header-icons">
        <img
                class="profile-img"
                src="/static/imgsource/layout/testprofile.png"
                alt="プロフィール"
        />
      </div>
    </header>

    <main class="content">
      <div class="habit-page">
        <!-- ✅ 1行目：マイ習慣 / カレンダー / 週間履歴 -->
        <div class="widget-box habit-list">
          <div class="myhabit">習慣リスト</div>
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
                    style="margin-top: 10px; font-weight: bold"
            >
              日付：未選択
            </div>
          </div>
        </div>

        <div class="widget-box habit-week">
          <h3>Weekly習慣履歴</h3>
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

        <!-- ✅ 2行目：おすすめ習慣 / 励まし言葉 / メモ -->
        <div class="widget-box habit-recommend">
          <div class="habit-tabs">
            <div
                    id="habit-tab-신체건강"
                    class="habit-tab habit-active"
                    onclick="habitShowTab('신체건강')"
            >
              体の健康
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
              <p id="exercise">運動</p>
              <p id="meal">食事</p>
              <p id="diet">体重管理</p>
            </div>
            <div style="float: left; width: 25%">
              <p id="supplement">サプリ</p>
              <p id="water">水分摂取</p>
              <p id="posture">姿勢を正す</p>
            </div>
            <div style="float: left; width: 25%">
              <p id="sunshine">日光浴</p>
              <p id="rest">休憩</p>
              <p id="stretch">ストレッチ</p>
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
          <h3>週間振り返りメモ</h3>
          <textarea
                  id="weeklyMemoText"
                  rows="6"
                  placeholder="今週の振り返りを自由に書いてみましょう..."
                  style="width: 100%; resize: none"
          ></textarea>
          <button id="saveMemoBtn">保存する</button>
        </div>
      </div>
    </main>
  </div>
</div>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="/static/js/habit/habit.js"></script>
<script>
  const sessionUserId = '${user.user_id}';
</script>

</body>
</html>

