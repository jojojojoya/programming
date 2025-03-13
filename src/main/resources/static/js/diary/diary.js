// ✅ 기본 변수 선언
let selectedEmoji = "🙂"; // 기본 이모지 상태 초기값

// ✅ 페이지 로드 후 캘린더 초기화
document.addEventListener('DOMContentLoaded', function() {
    const calendarEl = document.getElementById('calendar'); // 캘린더 요소 가져오기

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'en',
        height: 400,
        contentHeight: 400,
        expandRows: true,
        fixedWeekCount: true,
        aspectRatio: 1.8, // 셀 크기 비율 조정

        headerToolbar: {
            left: 'prev',
            center: 'title',
            right: 'next today'
        },

        dayMaxEventRows: true, // 셀 안 이벤트가 많을 경우 행 개수 제한

        events: "/diary/events", // 서버에서 이벤트 데이터 가져오기

        eventContent: function(arg) {
            return { html: '<div class="emoji-event">' + arg.event.title + '</div>' }; // 이모지 이벤트 출력
        },

        dateClick: function(info) {
            loadDiary(info.dateStr); // 날짜 클릭 시 해당 일기 로드 함수 실행
        }
    });

    calendar.render(); // 캘린더 렌더링 실행
});

// ✅ 이모지 선택 시 처리 함수
function selectEmoji(emoji) {
    selectedEmoji = emoji; // 선택한 이모지를 전역 변수에 저장
    document.querySelectorAll(".emoji-option").forEach(option => {
        option.classList.remove("selected"); // 모든 이모지 선택 해제
    });
    document.getElementById(emoji).classList.add("selected"); // 선택한 이모지만 활성화
}

// ✅ 특정 날짜의 일기 불러오기 함수
function loadDiary(date) {
    $.ajax({
        url: "/diary/get", // 서버에서 데이터 가져올 경로
        type: "GET", // GET 방식 요청
        data: { date: date }, // 날짜 파라미터 전달
        success: function(response) {
            document.getElementById("diaryDate").innerText = date; // 날짜 표시 변경
            document.getElementById("diaryContent").value = response.content || ""; // 일기 내용 입력창에 반영
            selectedEmoji = response.emoji || "🙂"; // 저장된 이모지 불러오기 또는 기본값

            // 이모지 선택 상태 초기화 후 현재 이모지 선택
            document.querySelectorAll(".emoji-option").forEach(option => {
                option.classList.remove("selected");
            });
            document.getElementById(selectedEmoji).classList.add("selected");
        },
        error: function() {
            alert("일기 데이터를 불러오지 못했습니다."); // 에러 발생 시 알림
        }
    });
}

// ✅ 일기 저장 처리 함수
function saveDiary() {
    const date = document.getElementById("diaryDate").innerText; // 현재 선택된 날짜
    const content = document.getElementById("diaryContent").value; // 작성한 일기 내용

    $.ajax({
        url: "/diary/save", // 서버 저장 엔드포인트
        type: "POST", // POST 방식 요청
        contentType: "application/json", // JSON 타입 지정
        data: JSON.stringify({
            date: date,
            content: content,
            emoji: selectedEmoji
        }),
        success: function() {
            alert("일기가 저장되었습니다!"); // 성공 알림
            location.reload(); // 페이지 새로고침으로 반영
        },
        error: function() {
            alert("일기 저장에 실패했습니다."); // 실패 시 알림
        }
    });
}