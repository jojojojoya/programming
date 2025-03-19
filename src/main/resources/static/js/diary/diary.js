// 전역 변수 선언
let currentDiaryId = null; // 현재 다이어리 ID
let selectedEmoji = "🙂"; // 기본 감정 이모지
let isViewMode = false; // true면 조회 뷰, false면 작성/수정 뷰
let selectedDate = null;
let calendar;

// 페이지 로드 후 초기 세팅
document.addEventListener('DOMContentLoaded', function() {
    const today = new Date().toISOString().slice(0, 10);

    document.getElementById("diaryDate").innerText = today;
    selectedEmoji = "🙂";
    currentDiaryId = null;

    document.querySelectorAll(".emoji-option").forEach(option => {
        option.classList.remove("selected");
        option.style.cursor = "pointer";
    });
    const emojiEl = document.getElementById(`write-${selectedEmoji}`);
    if (emojiEl) {
        emojiEl.classList.add("selected");
    }

    initCalendar();
    highlightSelectedDate(selectedDate);
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

                    successCallback(data);
                })
                .catch(error => {
                    console.error("이벤트 로딩 실패", error);
                    failureCallback(error);
                });
        },

        // 날짜 클릭 → 작성 모드
        dateClick: function(info) {
            const today = new Date().toISOString().slice(0, 10);
            selectedDate = info.dateStr;
            if (selectedDate > today) {
                alert("미래의 일기는 작성할 수 없습니다.");
                return;
            }
            openWriteMode(info.dateStr); // 신규 일기 작성 모드
            highlightSelectedDate(selectedDate);
        },

        // 이벤트 클릭 → 상세 조회
        eventClick: function(info) {
            const diaryId = info.event.extendedProps?.diary_id || info.event.extendedProps?.DIARY_ID || info.event.extendedProps?.diaryId;
            selectedDate = info.event.startStr || info.event.start;

            if (!diaryId) {
                alert("일기 ID가 없습니다!");
                return;
            }

            loadDiaryById(diaryId); // 상세 조회 함수 호출
            highlightSelectedDate(selectedDate);
        },

        // 이벤트 렌더링 → 이모지로 출력
        eventContent: function(arg) {
            return { html: `<div class="emoji-event">${arg.event.title}</div>` };
        },

        datesSet: function(info) {
            const today = new Date();
            const todayStr = today.toISOString().slice(0, 10);

            const currentViewDate = calendar.getDate().toISOString().slice(0, 10);


            if (currentViewDate === todayStr) {
                selectedDate = todayStr;

                highlightSelectedDate(todayStr);

                // 작성 폼을 바로 열고 싶으면 이거!
                openWriteMode(todayStr);

                // 만약 일기 존재 확인 후 조회 폼 or 작성 폼을 선택하고 싶으면 ↓
                // loadDiaryByDate(todayStr);
            }
        }
    });

    calendar.render();
}

/* 날짜 클릭 시: 작성 폼 초기화 */
function openWriteMode(dateStr) {
    const today = new Date().toISOString().slice(0, 10);

    if (dateStr > today) {
        alert("미래의 일기는 작성할 수 없습니다.");
        return;
    }

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
    const writeEmojiElement = document.getElementById(`write-${selectedEmoji}`);
    if (writeEmojiElement) writeEmojiElement.classList.add("selected");

    // 버튼 상태
    document.getElementById("saveBtn").style.display = "inline-block";
    document.getElementById("updateBtn").style.display = "none";
}

/* 날짜 클릭 시 캘린더 강조 */
function highlightSelectedDate(dateStr) {
    const dateCells = document.querySelectorAll('.fc-daygrid-day');

    dateCells.forEach(cell => {
        cell.classList.remove('fc-day-selected');
        cell.classList.remove('fc-day-today');

        const cellDate = cell.getAttribute('data-date');
        if (cellDate === dateStr) {
            console.log("📌 선택된 날짜:", cellDate);
            cell.classList.add('fc-day-selected');
        }
    });
}

// 캘린더에서 이모지 이벤트 로딩
function loadCalendarEmojis(fetchInfo, successCallback, failureCallback) {
    fetch('/diary/events')
        .then(response => response.json())
        .then(data => {
            const events = data.map(item => ({
                title: item.EMOTION_EMOJI,
                start: item.DIARY_DATE
            }));
            successCallback(events);
        })
        .catch(error => failureCallback(error));
}

/* 이모지 선택 */
function selectEmoji(emoji) {
    if (isViewMode) {
        return;
    }

    selectedEmoji = emoji;

    document.querySelectorAll("#diaryWriteSection .emoji-option").forEach(option => {
        option.classList.remove("selected");
    });

    const writeEmojiElement = document.getElementById(`write-${selectedEmoji}`);
    if (writeEmojiElement) writeEmojiElement.classList.add("selected");
}

/* 일기 등록 */
function saveDiary() {
    const diaryTitle = document.getElementById("diaryTitle")?.value || "제목 없음";
    const diaryContent = document.getElementById("diaryContent").value;
    const diaryDate = document.getElementById("diaryDate").innerText;
    const diaryDateTime = `${diaryDate}T00:00:00`;
    const today = new Date().toISOString().slice(0, 10);

    console.log("✅ diaryDateTime:", diaryDateTime);

    if (diaryDate > today) {
        alert("미래의 일기는 저장할 수 없습니다.");
        return;
    }

    if (!diaryContent.trim()) {
        alert("일기 내용을 입력해주세요!");
        return;
    }

    const data = {
        user_id: "user1",
        title: diaryTitle,
        diary_content: diaryContent,
        created_at: diaryDateTime,
        emotion_emoji: selectedEmoji
    };

    fetch('/diary/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(resData => {
            if (resData && resData.diaryId) {
                alert("일기가 등록되었습니다!");

                currentDiaryId = resData.diaryId;
                console.log("✅ currentDiaryId 업데이트됨:", currentDiaryId);

                openEmotionModal();
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
    const diaryTitle = document.getElementById("diaryTitle").value;
    const diaryContent = document.getElementById("diaryContent").value;

    if (!diaryContent.trim()) {
        alert("일기 내용을 입력해주세요!");
        return;
    }

    const data = {
        diary_id: currentDiaryId,
        title: diaryTitle,
        diary_content: diaryContent,
        emotion_emoji: selectedEmoji
    };

    console.log("📝 수정 요청 데이터:", data); // 디버깅 로그 추가!

    fetch('/diary/update', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                alert("일기 수정 완료!");
                refreshCalendarEvents(); // 캘린더 이벤트 새로고침
                loadDiaryById(currentDiaryId);
            } else {
                alert("일기 수정 실패");
            }
        })
        .catch(error => {
            console.error("❌ 수정 중 에러 발생:", error);
        });
}

/* 일기 삭제 */
function deleteDiary() {
    console.log("삭제 시도! currentDiaryId:", currentDiaryId);
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

    console.log("🔎 loadDiaryById 호출됨, diaryId:", diaryId); // ← 여기에 추가!

    fetch(`/diary/${diaryId}`)
        .then(async (response) => {
            console.log("👉 응답 상태:", response.status);

            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(`서버 오류: ${response.status}, 내용: ${errorMessage}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("가져온 일기 데이터:", data);

            if (!data || !data.diary_id) {
                alert("일기를 불러오지 못했습니다.");
                return;
            }

            currentDiaryId = data.diary_id;

            const writeSection = document.getElementById("diaryWriteSection");
            const viewSection = document.getElementById("diaryViewSection");

            if (!writeSection || !viewSection) {
                console.warn("diaryWriteSection 또는 diaryViewSection이 없습니다!");
                return;
            }

            writeSection.style.display = "none";
            viewSection.style.display = "block";

            document.getElementById("viewDiaryDate").innerText = data.formattedCreatedAt || data.created_at.split('T')[0];
            document.getElementById("viewDiaryTitle").innerText = data.title || "제목 없음";
            document.getElementById("viewDiaryContent").innerText = data.diary_content || "내용 없음";

            // 뷰 이모지 초기화
            document.querySelectorAll("#diaryViewSection .emoji-option").forEach(option => {
                option.classList.remove("selected");
                option.style.cursor = "default";
            });

            if (data.emotion_emoji) {
                const viewEmojiElement = document.getElementById(`view-${data.emotion_emoji}`);
                if (viewEmojiElement) {
                    viewEmojiElement.classList.add("selected");
                } else {
                    console.warn(`😮 이모지 view-${data.emotion_emoji}에 해당하는 요소가 없습니다!`);
                }
            }

            document.getElementById("editBtn").style.display = "inline-block";
            document.getElementById("deleteBtn").style.display = "inline-block";
        })
        .catch(error => {
            console.error("❌ 일기 조회 실패:", error);
            console.error(error.stack);
            alert("일기 조회 중 오류가 발생했습니다!");
        });
}

/* 날짜 기준 조회 */
function loadDiaryByDate(dateStr) {
    fetch(`/diary/date/${dateStr}`)
        .then(async (response) => {
            if (!response.ok) {
                console.warn("✅ 일기 없음 → 작성모드로 전환");
                openWriteMode(dateStr);
                return null;
            }

            return response.json();
        })
        .then(data => {
            if (!data) return;

            currentDiaryId = data.diary_id;

            const writeSection = document.getElementById("diaryWriteSection");
            const viewSection = document.getElementById("diaryViewSection");

            if (!writeSection || !viewSection) {
                console.warn("❗ diaryWriteSection 또는 diaryViewSection이 없습니다!");
                return;
            }

            writeSection.style.display = "none";
            viewSection.style.display = "block";

            document.getElementById("viewDiaryDate").innerText = data.formattedCreatedAt || "날짜 없음";
            document.getElementById("viewDiaryTitle").innerText = data.title || "제목 없음";
            document.getElementById("viewDiaryContent").innerText = data.diary_content || "내용 없음";

            document.querySelectorAll("#diaryViewSection .emoji-option").forEach(option => {
                option.classList.remove("selected");
                option.style.cursor = "default";
                option.onclick = null;
            });

            if (data.emotion_emoji) {
                const viewEmojiElement = document.getElementById(`view-${data.emotion_emoji}`);
                if (viewEmojiElement) {
                    viewEmojiElement.classList.add("selected");
                } else {
                    console.warn(`😮 이모지 view-${data.emotion_emoji}에 해당하는 요소가 없습니다!`);
                }
            }

            document.getElementById("editBtn").style.display = "inline-block";
            document.getElementById("deleteBtn").style.display = "inline-block";
        })
        .catch(error => {
            console.error("❌ 일기 조회 실패:", error);
            openWriteMode(dateStr);
        });
}

/* 수정하기 버튼 클릭 → 수정 모드 전환 */
function switchToEditMode() {
    isViewMode = false;

    document.getElementById("diaryViewSection").style.display = "none";
    document.getElementById("diaryWriteSection").style.display = "block";

    document.getElementById("diaryDate").innerText = document.getElementById("viewDiaryDate").innerText;
    document.getElementById("diaryTitle").value = document.getElementById("viewDiaryTitle").innerText;
    document.getElementById("diaryContent").value = document.getElementById("viewDiaryContent").innerText;

    document.querySelectorAll("#diaryWriteSection .emoji-option").forEach(option => {
        option.classList.remove("selected");
        option.style.cursor = "pointer";
    });

    const emojiElement = document.querySelector("#diaryViewSection .emoji-option.selected");
    if (emojiElement) {
        const emojiId = emojiElement.id; // ex) view-🙂
        const emoji = emojiId.split("view-")[1]; // 🙂 만 추출
        selectedEmoji = emoji;
    } else {
        selectedEmoji = "🙂";
        console.warn("선택된 이모지가 없습니다. 기본값으로 초기화!");
    }

    const writeEmojiElement = document.getElementById(`write-${selectedEmoji}`);
    if (writeEmojiElement) {
        writeEmojiElement.classList.add("selected");
    } else {
        console.warn(`write-${selectedEmoji} 요소를 찾을 수 없습니다!`);
    }

    document.getElementById("saveBtn").style.display = "none";
    document.getElementById("updateBtn").style.display = "inline-block";
}

/* 오늘의 감정 점수 모달 열기 */
function openEmotionModal() {
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "flex";
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

                // ✅ 캘린더 이벤트 리프레시
                refreshCalendarEvents();

                // ✅ 작성 폼 초기화 (오늘 날짜로 이동)
                const today = new Date().toISOString().slice(0, 10);
                openWriteMode(today);
                highlightSelectedDate(today);

                // ✅ currentDiaryId 초기화
                currentDiaryId = null;
            } else {
                alert("감정 점수 저장 실패");
            }
        });
}

function updateScoreValue(value) {
    document.getElementById("scoreDisplay").innerText = value;
}

/* 캘린더 이벤트 리프레시 */
function refreshCalendarEvents() {
        if (calendar) {
            console.log("✅ 캘린더 이벤트 다시 불러오는 중!");
            calendar.refetchEvents();
        } else {
            console.warn("❗ 캘린더 인스턴스를 찾을 수 없음");
        }
}