// 기본 변수 선언
let selectedEmoji = "🙂"; // 기본 이모지 상태 초기값
let currentDiaryId = null; // 일기 ID 저장 (점수 입력 시 필요)

// 페이지 로드 후 캘린더 초기화
document.addEventListener('DOMContentLoaded', function() {
    const calendarEl = document.getElementById('calendar');

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'en',
        height: 400,
        contentHeight: 400,
        expandRows: true,
        fixedWeekCount: true,
        aspectRatio: 1.8,

        headerToolbar: {
            left: 'prev',
            center: 'title',
            right: 'next today'
        },

        dayMaxEventRows: false, // +1 more xx
        eventDisplay: 'block',

        events: "/diary/events", // 이모지 이벤트 불러오기

        // 날짜 클릭 시 (신규 작성 포함)
        dateClick: function(info) {
            const clickedDate = info.dateStr;

            // 날짜 표시
            document.getElementById("diaryDate").innerText = clickedDate;

            // 신규 작성 모드 초기화
            currentDiaryId = null;
            document.getElementById("diaryContent").value = "";
            selectedEmoji = "🙂";

            document.querySelectorAll(".emoji-option").forEach(option => {
                option.classList.remove("selected");
            });
            document.getElementById(selectedEmoji).classList.add("selected");

            console.log("날짜 클릭:", clickedDate);
        },
        
        // 캘린더 위에 이모지 표시
        eventContent: function(arg) {
            return { html: '<div class="emoji-event">' + arg.event.title + '</div>' };
        },
        
        // 이벤트 클릭 시
        eventClick: function(info) {
            const diaryId = info.event.extendedProps.diary_id;
            const clickedDate = info.event.startStr; // 'YYYY-MM-DD' 포맷
            document.getElementById("diaryDate").innerText = clickedDate;
            loadDiaryById(diaryId);
        }   // 이벤트 클릭시 diary_id 넘김
    });

    calendar.render();
});

// 이모지 선택 처리
function selectEmoji(emoji) {
    selectedEmoji = emoji;
    document.querySelectorAll(".emoji-option").forEach(option => {
        option.classList.remove("selected");
    });
    document.getElementById(emoji).classList.add("selected");
}

// 특정 날짜의 일기 불러오기 (조회)
function loadDiaryById(diaryId) {
    fetch(`/diary/get?diaryId=${diaryId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("일기를 불러오지 못했습니다.");
            }
            return response.json();
        })
        .then(data => {
            if (data) {
                currentDiaryId = data.diary_id;

                // 날짜는 created_at 값을 불러와서 날짜만 보여주는 경우
                document.getElementById("diaryDate").innerText = data.created_at.substring(0, 10);
                document.getElementById("diaryContent").value = data.diary_content || "";

                selectedEmoji = data.emotion_emoji || "🙂";

                document.querySelectorAll(".emoji-option").forEach(option => {
                    option.classList.remove("selected");
                });
                document.getElementById(selectedEmoji).classList.add("selected");
            } else {
                // 일기 없음 → 새로 작성 모드
                currentDiaryId = null;
                document.getElementById("diaryDate").innerText = "";
                document.getElementById("diaryContent").value = "";
                selectedEmoji = "🙂";

                document.querySelectorAll(".emoji-option").forEach(option => {
                    option.classList.remove("selected");
                });
                document.getElementById(selectedEmoji).classList.add("selected");
            }
        })
        .catch(error => {
            alert(error.message);
        });
}

// 사용자의 user_id를 임시로 반환하는 함수
function getCurrentUserId() {
    return 'user2'; // DB에 넣은 유저 아이디와 같게!
}

// 일기 저장 처리
function saveDiary() {
    const userId = getCurrentUserId(); // 현재 로그인한 사용자 ID 가져오기
    const date = document.getElementById("diaryDate").innerText;
    const content = document.getElementById("diaryContent").value;

    const diaryData = {
        user_id: userId,
        diary_content: content,
        emotion_emoji: selectedEmoji
    };

    fetch('/diary', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(diaryData)
    })
        .then(response => {
            if (response.ok) {
                alert("일기가 저장되었습니다!");
                // 모달 열기 → 감정 점수 입력
                openEmotionModal();
            } else {
                alert("일기 저장 실패!");
            }
        });
}

// 감정 점수 저장 처리 (모달에서 호출)
function saveEmotionScore() {
    const score = document.getElementById("emotionScoreInput").value;

    if (!currentDiaryId) {
        alert("일기가 먼저 저장되어야 합니다.");
        return;
    }

    const scoreData = {
        diary_id: currentDiaryId,
        emotion_score: parseInt(score)
    };

    fetch('/diary/emotion/score', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(scoreData)
    })
        .then(response => {
            if (response.ok) {
                alert("감정 점수가 저장되었습니다!");
                closeEmotionModal();
                // 캘린더 이모지 갱신 (이벤트 리로딩 or 단일 추가)
                refreshCalendarEvents();
            } else {
                alert("감정 점수 저장 실패!");
            }
        });
}

// 감정 점수 입력 모달을 띄워주는 함수 (구현 필요)
function openEmotionModal() {
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "block";
}

function closeEmotionModal() {
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "none";
}

// 캘린더 이벤트 새로고침 함수
function refreshCalendarEvents() {
    const calendarEl = document.getElementById('calendar');
    const calendar = FullCalendar.getCalendar(calendarEl);
    calendar.refetchEvents();
}
