// 전역 변수 선언
let currentDiaryId = null; // 현재 다이어리 ID
let selectedEmoji = "🙂"; // 기본 감정 이모지
let isViewMode = false; // true면 조회 뷰, false면 작성/수정 뷰

// 페이지 로드 후 초기 세팅
document.addEventListener('DOMContentLoaded', function() {
    const today = new Date().toISOString().slice(0, 10);
    const storedDate = sessionStorage.getItem("selectedDate");

    if (storedDate) {
        document.getElementById("diaryDate").innerText = storedDate; // sessionStorage에 저장된 날짜 적용
        sessionStorage.removeItem("selectedDate"); // 사용 후 sessionStorage에서 삭제
    } else {
        document.getElementById("diaryDate").innerText = today; // 기본값으로 오늘 날짜 사용
    }

   // document.getElementById("diaryDate").innerText = today;
    selectedEmoji = "🙂";
    currentDiaryId = null;

    document.querySelectorAll(".emoji-option").forEach(option => {
        option.classList.remove("selected");
        option.style.cursor = "pointer";
    });
    document.getElementById(selectedEmoji).classList.add("selected");

    initCalendar();
});

/* 캘린더 초기화 함수 */
function initCalendar() {
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

        // 이모지 이벤트 불러오고 넘기기!
        events: function(fetchInfo, successCallback, failureCallback) {
            fetch('/diary/events')
                .then(response => response.json())
                .then(data => {
                    console.log("서버 응답 데이터: ", data);

                    const events = data.map(item => {
                        const event = {
                            title: item.title || "제목 없음",
                            start: item.start || null,
                            extendedProps: {
                                diary_id: item.extendedProps ? item.extendedProps.diary_id : null
                            }
                        };
                        console.log("변환된 이벤트: ", event);
                        return event;
                    });

                    successCallback(events);
                })
                .catch(error => {
                    console.error("이벤트 로딩 실패", error);
                    failureCallback(error);
                });
        },

        // 날짜 클릭 → 작성 모드
        dateClick: function(info) {
            openWriteMode(info.dateStr); // 신규 일기 작성 모드
        },

        // 이벤트 클릭 → 상세 조회
        eventClick: function(info) {
            const diaryId = info.event.extendedProps.diary_id;
            loadDiaryById(diaryId); // 상세 조회 함수 호출

        },

        // 이벤트 렌더링 → 이모지로 출력
        eventContent: function(arg) {
            console.log("이벤트 콘텐츠 arg 확인: ", arg.event);
            return { html: `<div class="emoji-event">${arg.event.title}</div>` };
        }
    });

    calendar.render();
}

/* 날짜 클릭 시: 작성 폼 초기화 */
function openWriteMode(dateStr) {
    isViewMode = false;

    // 폼 전환
    document.getElementById("diaryWriteSection").style.display = "block";
    document.getElementById("diaryViewSection").style.display = "none";

    // 값 초기화
    document.getElementById("diaryDate").innerText = dateStr;
    document.getElementById("diaryContent").value = "";

    currentDiaryId = null;
    selectedEmoji = "🙂";

    document.querySelectorAll(".emoji-option").forEach(option => {
        option.classList.remove("selected");
        option.style.cursor = "pointer";
    });
    document.getElementById(selectedEmoji).classList.add("selected");

    // 버튼 상태
    document.getElementById("saveBtn").style.display = "inline-block";
    document.getElementById("updateBtn").style.display = "none";
}

/* 이모지 선택 */
function selectEmoji(emoji) {
    if (isViewMode) {
        return;
    }

    selectedEmoji = emoji;

    document.querySelectorAll(".emoji-option").forEach(option => {
        option.classList.remove("selected");
    });

    document.getElementById(emoji).classList.add("selected");
}

/* 일기 등록 */
function saveDiary() {
    const diaryContent = document.getElementById("diaryContent").value;
    const diaryDate = document.getElementById("diaryDate").innerText;

    if (!diaryContent.trim()) {
        alert("일기 내용을 입력해주세요!");
        return;
    }

    const data = {
        user_id: "user2", // 하드코딩된 유저 (추후 로그인 세션으로 변경 예정)
        diary_content: diaryContent,
        created_at: diaryDate,
        emotion_emoji: selectedEmoji
    };

    fetch('/diary/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                alert("일기가 등록되었습니다!");
                openEmotionModal(); // 감정 점수 입력 모달 띄움
            } else {
                alert("일기 등록 실패");
            }
        });
}

/* 일기 수정 완료 */
function updateDiary() {
    if (!currentDiaryId) {
        alert("수정할 일기가 없습니다.");
        return;
    }

    const diaryContent = document.getElementById("diaryContent").value;

    if (!diaryContent.trim()) {
        alert("일기 내용을 입력해주세요!");
        return;
    }

    const data = {
        diary_id: currentDiaryId,
        diary_content: diaryContent,
        emotion_emoji: selectedEmoji
    };

    fetch('/diary/update', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                alert("일기 수정 완료!");
                refreshCalendarEvents(); // 캘린더 이벤트 새로고침
            } else {
                alert("일기 수정 실패");
            }
        });
}

/* 일기 삭제 */
function deleteDiary() {
    if (!currentDiaryId) {
        alert("삭제할 일기가 없습니다.");
        return;
    }

    if (!confirm("정말 삭제하시겠습니까?")) {
        return;
    }

    fetch(`/diary/delete/${currentDiaryId}`, { method: 'DELETE' })
        .then(response => {
            if (response.ok) {
                alert("일기 삭제 완료!");
                refreshCalendarEvents();
                openWriteMode(new Date().toISOString().slice(0, 10)); // 오늘 날짜로 초기화
            } else {
                alert("일기 삭제 실패");
            }
        });
}

/* 일기 상세 조회 */
function loadDiaryById(diaryId) {
    isViewMode = true;

    fetch(`/diary/${diaryId}`)
        .then(response => response.json())
        .then(data => {
            if (!data) {
                alert("일기를 불러오지 못했습니다.");
                return;
            }

            currentDiaryId = data.diary_id;

            // 뷰 전환
            document.getElementById("diaryWriteSection").style.display = "none";
            document.getElementById("diaryViewSection").style.display = "block";

            document.getElementById("viewDiaryDate").innerText = data.formattedCreatedAt;

            document.getElementById("viewDiaryTitle").innerText = data.title || "제목 없음";
            document.getElementById("viewDiaryContent").innerText = data.diary_content;

            // 이모지 선택 초기화 + 셀렉트
            document.querySelectorAll(".emoji-option").forEach(option => {
                option.classList.remove("selected");
                option.style.cursor = "default";
                option.onclick = null;
            });
            if (selectedElement) {
            document.getElementById(data.emotion_emoji).classList.add("selected");
            }

            // 버튼 활성화
            document.getElementById("editBtn").style.display = "inline-block";
            document.getElementById("deleteBtn").style.display = "inline-block";
        });
}

/* 수정하기 버튼 클릭 → 수정 모드 전환 */
function switchToEditMode() {
    isViewMode = false;

    document.getElementById("diaryViewSection").style.display = "none";
    document.getElementById("diaryWriteSection").style.display = "block";

    document.getElementById("diaryDate").innerText = document.getElementById("viewDiaryDate").innerText;
    document.getElementById("diaryContent").value = document.getElementById("viewDiaryContent").innerText;

    const emoji = document.getElementById("viewDiaryEmotion").innerText;

    document.querySelectorAll(".emoji-option").forEach(option => {
        option.classList.remove("selected");
        option.style.cursor = "pointer";
    });
    document.getElementById(emoji).classList.add("selected");

    selectedEmoji = emoji;

    document.getElementById("saveBtn").style.display = "none";
    document.getElementById("updateBtn").style.display = "inline-block";
}

/* 오늘의 감정 점수 모달 열기 */
function openEmotionModal() {
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "flex"; // display block보다 flex가 가운데 정렬 쉬움
}

/* 오늘의 감정 점수 모달 닫기 */
function closeEmotionModal() {
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "none";
}

/* 감정 점수 저장 */
function saveEmotionScore() {
    const score = document.getElementById("emotionScoreInput").value;

    if (!currentDiaryId) {
        alert("일기가 저장되어야 점수 등록 가능!");
        return;
    }

    const data = {
        diary_id: currentDiaryId,
        emotion_score: parseInt(score)
    };

    fetch('/diary/emotion/score', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                alert("감정 점수가 저장되었습니다!");
                closeEmotionModal();
                refreshCalendarEvents();
            } else {
                alert("감정 점수 저장 실패");
            }
        });
}

/* 캘린더 이벤트 리프레시 */
function refreshCalendarEvents() {
    const calendarEl = document.getElementById('calendar');
    const calendar = FullCalendar.getCalendar(calendarEl);
    calendar.refetchEvents();
}

