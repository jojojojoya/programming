const DEFAULT_EMOJI = "🙂";
let calendar;
let currentDiaryId = null;
let selectedEmoji = DEFAULT_EMOJI;
let isViewMode = false;
let currentEmotionScore = 50;

// 유틸함수
/* 미래 날짜 여부 확인 */
function isFutureDate(dateStr) {
    const today = new Date();
    const targetDate = new Date(dateStr);   // (String) YYYY-MM-DD
    return targetDate > today; // futuredate = true
}

/* 이모지 선택 초기화 */
function resetEmojiSelection(sectionId, emoji = DEFAULT_EMOJI, isEditable = true) {
    selectedEmoji = emoji;

    document.querySelectorAll(`#${sectionId} .emoji-option`).forEach(option => {
        option.classList.remove("selected");
        option.style.cursor = isEditable ? "pointer" : "default";
        option.onclick = isEditable ? function () {
            selectEmoji(this.dataset.emoji);
        } : null;
    });

    const prefix = sectionId === "diaryWriteSection" ? "write" : "view";
    const emojiElement = document.getElementById(`${prefix}-${emoji}`);
    if (emojiElement) emojiElement.classList.add("selected");

}


// 초기화
/* 캘린더 날짜 하이라이트 강조 */
function highlightSelectedDate(dateStr) {
    document.querySelectorAll('.fc-daygrid-day').forEach(cell => {
        cell.classList.remove('fc-day-selected', 'fc-day-today');
        if (cell.getAttribute('data-date') === dateStr) {
            cell.classList.add('fc-day-selected');
        }
    });
}

/* 페이지 로드 후 초기 세팅 */
document.addEventListener('DOMContentLoaded', function () {
    const today = new Date().toISOString().slice(0, 10);
    if (!window.selectedDate || window.selectedDate === "null" || window.selectedDate === "undefined") {
        window.selectedDate = today;
    }
    document.getElementById("diaryDate").innerText = window.selectedDate;
    currentDiaryId = null;

    resetEmojiSelection("diaryWriteSection");
    initCalendar();
    bindWeeklySummaryClickEvent();
    loadDiaryByDate(window.selectedDate);
});

/* 캘린더 초기화 함수 */
function initCalendar() {
    const calendarEl = document.getElementById('calendar');
    const initialDate = window.selectedDate;

    calendar = new FullCalendar.Calendar(calendarEl, {
        initialView   : 'dayGridMonth',
        initialDate   : initialDate,
        locale        : 'ja',
        timeZone      : 'local',
        expandRows    : true,
        fixedWeekCount: false,
        aspectRatio   : 1.2,
        height        : 'auto',

        headerToolbar: {
            left  : 'prevCustom',
            center: 'title',
            right : 'nextCustom today'
        },
        customButtons: {
            prevCustom: {text: '◀', click: () => calendar.prev()},
            nextCustom: {text: '▶', click: () => calendar.next()}
        },

        dayCellContent: function(arg) {
            return {
                html: `
            <div class="day-content">
                <div class="day-number">${arg.date.getDate()}</div>
            </div>
        `
            };
        },

        events: (fetchInfo, successCallback, failureCallback) => {
            fetch('/diary/events')
                .then(res => res.json())
                .then(data => successCallback(data))
                .catch(error => {
                    console.error("イベント読み込み失敗", error);
                    failureCallback(error);
                });
        },

        // 날짜 클릭 → 작성 모드
        dateClick: (info) => {
            const dateStr = info.dateStr;
            if (isFutureDate(dateStr)) return alert("未来の日記は作成できません。");
            window.selectedDate = dateStr;
            document.getElementById("diaryDate").innerText = dateStr;
            highlightSelectedDate(dateStr);
            loadDiaryByDate(dateStr);
            loadWeeklySummary(dateStr);
        },

        // 이벤트 클릭 → 상세 조회
        eventClick: (info) => {
            const diaryId = info.event.id || info.event.extendedProps?.diary_id;
            if (!diaryId) return;
            window.selectedDate = info.event.startStr;
            loadDiaryById(diaryId);
            highlightSelectedDate(window.selectedDate);
            loadWeeklySummary(window.selectedDate);
        },


        // 이벤트 렌더링 → 이모지로 출력
        eventContent: (arg) => {
            return {html: `<div class="emoji-event">${arg.event.title}</div>`};
        },

        datesSet: (info) => {
            if (!window.selectedDate) {
                window.selectedDate = calendar.getDate().toISOString().slice(0, 10);
                highlightSelectedDate(window.selectedDate);
                loadDiaryByDate(window.selectedDate);
            }
        }
    });

    calendar.render();
}


// UI 전환
/* 이모지 선택 */
function selectEmoji(emoji) {
    if (isViewMode) {
        return;
    }

    selectedEmoji = emoji;
    resetEmojiSelection("diaryWriteSection", selectedEmoji, true);

    document.querySelectorAll("#diaryWriteSection .emoji-option").forEach(option => {
        option.classList.remove("selected");
    });

    const writeEmojiElement = document.getElementById(`write-${selectedEmoji}`);
    if (writeEmojiElement) writeEmojiElement.classList.add("selected");
}

/* 조회모드 렌더링 함수 */
function renderDiaryView(data) {
    document.getElementById("diaryWriteSection").style.display = "none";
    document.getElementById("diaryViewSection").style.display = "block";
    document.getElementById("viewDiaryDate").innerText = data.formattedCreatedAt || data.created_at?.split('T')[0] || "日付なし";
    document.getElementById("viewDiaryTitle").innerText = data.title || "タイトルなし";
    document.getElementById("viewDiaryContent").innerText = data.diary_content || "内容なし";
    resetEmojiSelection("diaryViewSection", data.emotion_emoji || DEFAULT_EMOJI, false);
    document.getElementById("editBtn").style.display = "inline-block";
    document.getElementById("deleteBtn").style.display = "inline-block";
}

/* 뷰모드 -> 작성 모드 전환 */
function openWriteMode(dateStr) {
    if (isFutureDate(dateStr)) {
        alert("未来の日記は作成できません。");
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
    resetEmojiSelection("diaryWriteSection");
    // 버튼 상태
    document.getElementById("saveBtn").style.display = "inline-block";
    document.getElementById("updateBtn").style.display = "none";
}

/* 수정하기 버튼 클릭 → 수정 모드 전환 */
function switchToEditMode() {
    isViewMode = false;
    document.getElementById("diaryViewSection").style.display = "none";
    document.getElementById("diaryWriteSection").style.display = "block";

    document.getElementById("diaryDate").innerText = document.getElementById("viewDiaryDate").innerText;
    document.getElementById("diaryTitle").value = document.getElementById("viewDiaryTitle").innerText;

    const contentHtml = document.getElementById("viewDiaryContent").innerHTML;
    const contentText = contentHtml.replace(/<br\s*\/?>/gi, "\n");
    document.getElementById("diaryContent").value = contentText;

    resetEmojiSelection("diaryWriteSection", selectedEmoji, true);

    document.getElementById("saveBtn").style.display = "none";
    document.getElementById("updateBtn").style.display = "inline-block";
}


// CRUD, 조회, 감정점수 함수
/* 일기 등록 */
function saveDiary() {
    const diaryTitle = document.getElementById("diaryTitle")?.value || "タイトルなし";
    const diaryContent = document.getElementById("diaryContent").value;
    const diaryDateTime = `${window.selectedDate}T00:00:00`;

    if (isFutureDate(window.selectedDate)) return alert("未来の日記は作成できません。");
    if (!diaryTitle.trim()) return alert("こよいのタイトルは忘れずに書いてください。");
    if (!diaryContent.trim()) return alert("こよいのことばを忘れずに書いてください。");

    const data = {
        user_id      : "userId",
        title        : diaryTitle,
        diary_content: diaryContent,
        created_at   : diaryDateTime,
        emotion_emoji: selectedEmoji
    };

    fetch('/diary/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then(resData => {
            if (resData && resData.diaryId) {
                currentDiaryId = resData.diaryId;
                openEmotionModal();
            } else {
                alert("こよいを登録に失敗しました。もう一度お試しください");
            }
        });
}

/* 일기 수정 */
function updateDiary() {
    const diaryTitle = document.getElementById("diaryTitle").value;
    const diaryContent = document.getElementById("diaryContent").value;

    if (!currentDiaryId) return alert("選択された日記が見つかりません。");
    if (!diaryTitle.trim()) return alert("こよいのタイトルは忘れずに書いてください。");
    if (!diaryContent.trim()) return alert("こよいのことばを忘れずに書いてください。");

    const data = {
        diary_id: currentDiaryId,
        title: diaryTitle,
        diary_content: diaryContent,
        created_at: `${window.selectedDate}T00:00:00`,
        emotion_emoji: selectedEmoji
    };

    fetch('/diary/update', {
        method : 'PUT',
        headers: {'Content-Type': 'application/json'},
        body   : JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                openEmotionModal();
            } else {
                alert("こよいの編集に失敗しました。");
            }
        })
        .catch(error => {
            console.error("編集中にエラーが発生しました:", error);
        });
}

/* 일기 삭제 */
function deleteDiary() {
    if (!currentDiaryId) return alert("削除できる日記が見つかりません。");
    if (!confirm("本当に削除してもよろしいですか？")) return;

    fetch(`/diary/delete/${currentDiaryId}`, {method: 'DELETE'})
        .then(response => {
            if (!response.ok) throw new Error("こよいの削除に失敗しました。");
            alert("こよいを削除しました。");
            currentDiaryId = null;
            loadDiaryByDate(window.selectedDate);
            refreshCalendarEvents();
            openWriteMode(window.selectedDate);
        })
        .catch(() => alert("削除中にエラーが発生しました。もう一度お試しください。"));
}

/* diaryId 기준 상세 조회 */
function loadDiaryById(diaryId) {
    isViewMode = true;
    fetch(`/diary/${diaryId}`)
        .then(res => res.ok ? res.json() : Promise.reject(res))
        .then(data => {
            if (data && data.diary_id) {
                currentDiaryId = data.diary_id;
                renderDiaryView(data);
            } else {
                alert("こよいを読み込めませんでした。");
            }
        })
        .catch(() => alert("こよいの読み込み中にエラーが発生しました。"));
}

/* 날짜 기준 조회 */
function loadDiaryByDate(dateStr) {
    fetch(`/diary/date/${dateStr}`)
        .then(res => res.ok ? res.json() : null)
        .then(data => {
            if (data) {
                currentDiaryId = data.diary_id;
                renderDiaryView(data);
            } else {
                openWriteMode(dateStr);
            }
        })
        .catch(() => {
            openWriteMode(dateStr);
        });
}

/* 주간 요약 리스트 조회 함수 */
function bindWeeklySummaryClickEvent() {
    document.querySelectorAll(".weekly-item").forEach(item => {
        item.addEventListener("click", function () {
            const diaryId = this.getAttribute("data-diary-id");
            if (!diaryId) {
                alert("日記のIDが見つかりません。");
                return;
            }
            loadDiaryById(diaryId);
        });
    });
}

/*  Ajax로 주간 리스트 불러오는 함수 */
function loadWeeklySummary(dateStr) {
    fetch(`/diary/weekly?date=${dateStr}`)
        .then(res => res.json())
        .then(data => {
            const summaryBox = document.querySelector(".weekly-summary ul");
            summaryBox.innerHTML = "";
            if (!data || data.length === 0) {
                summaryBox.innerHTML = "<li class='weekly-item'>今週はまだ、こよいが残されていません。</li>";
                return;
            }
            data.forEach(diary => {
                const li = document.createElement("li");
                li.className = "weekly-item";
                li.setAttribute("data-diary-id", diary.diary_id);

                const emoji = document.createElement("span");
                emoji.className = "weekly-item-emoji";
                emoji.innerText = diary.emotion_emoji;

                const dateText = diary.created_at?.split("T")[0];
                const titleText = diary.title;

                li.appendChild(emoji);
                li.append(` ${dateText} ${titleText}`);
                summaryBox.appendChild(li);
            });
            bindWeeklySummaryClickEvent();
        });
}

/* 오늘의 감정 점수 모달 열기 */
function openEmotionModal() {
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "flex";

    if (currentDiaryId) {
        loadEmotionScoreByDiaryId(currentDiaryId);
    } else {
        document.getElementById("emotionScoreInput").value = 50;
        document.getElementById("scoreDisplay").innerText = 50;
        currentEmotionScore = 50;
    }
}

/* 오늘의 감정 점수 모달 닫기 */
function closeEmotionModal() {
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "none";
}

/* 오늘의 점수 입력값 변경*/
function updateScoreValue(value) {
    document.getElementById("scoreDisplay").innerText = value;
}

/* 오늘의 점수 가져오는 함수 */
function loadEmotionScoreByDiaryId(diaryId) {
    if (!diaryId) return;

    fetch(`/diary/emotion/score/${diaryId}`)
        .then(response => {
            if (!response.ok) throw new Error();
            return response.json();
        })
        .then(data => {
            if (data && data.emotion_score !== undefined) {
                const score = data.emotion_score;
                document.getElementById("emotionScoreInput").value = score;
                document.getElementById("scoreDisplay").innerText = score;
                currentEmotionScore = score;
            }
        })
        .catch(() => {
            console.error("感情スコアを取得できませんでした。");
        });
}

/* 감정 점수 저장 */
function saveEmotionScore() {
    const score = document.getElementById("emotionScoreInput").value;
    if (!currentDiaryId) return alert("こよいを登録したあとにスコアを入力できます。");

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
                closeEmotionModal();
                refreshCalendarEvents();
                loadDiaryById(currentDiaryId);
            } else {
                alert("スコアの登録に失敗しました。");
            }
        });
}

/* 캘린더 이벤트 리프레시 */
function refreshCalendarEvents() {
    if (calendar) {
        calendar.refetchEvents();
    }
}