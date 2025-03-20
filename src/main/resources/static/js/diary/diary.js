// 전역 변수
let calendar;
let currentDiaryId = null;
let selectedEmoji = "🙂";
let isViewMode = false;
let selectedDate = null;


// 유틸 함수
/* 미래 날짜 여부 확인
 * @param {string} dateStr - YYYY-MM-DD 형식의 날짜 문자열
 * @returns {boolean} 미래 날짜이면 true 반환 */
function isFutureDate(dateStr) {
    const today = new Date();
    const targetDate = new Date(dateStr);

    return targetDate > today; // 미래 날짜면 true
}

/* 이모지 선택 초기화 함수
 * @param {string} sectionId - 대상 섹션 아이디 (diaryWriteSection / diaryViewSection)
 * @param {string} emoji - 선택할 이모지 값
 * @param {boolean} isEditable - 수정 가능 여부 (true: pointer / false: default) */
/* 이모지 선택 초기화 */
function resetEmojiSelection(sectionId, emoji, isEditable = true) {
    document.querySelectorAll(`#${sectionId} .emoji-option`).forEach(option => {
        option.classList.remove("selected");
        option.style.cursor = isEditable ? "pointer" : "default";
        if (!isEditable) option.onclick = null;
    });

    const prefix = sectionId === "diaryWriteSection" ? "write" : "view";
    const emojiElement = document.getElementById(`${prefix}-${emoji}`);

    if (emojiElement) {
        emojiElement.classList.add("selected");
    } else {
        console.warn(`❗ ${prefix}-${emoji} 요소를 찾을 수 없습니다!`);
    }
}


// 초기화
/* 페이지 로드 후 초기 세팅 */
document.addEventListener('DOMContentLoaded', function() {
    const today = new Date().toISOString().slice(0, 10);
    // 여기부터 서버 세션 사용하면 필요 x
    const storedDate = sessionStorage.getItem("selectedDate");
    console.log("✅ storedDate:", storedDate);  // 테스트용 로그

    selectedDate = storedDate || today;
    console.log("✅ 초기 selectedDate:", selectedDate);

    if (storedDate) {
        sessionStorage.removeItem("selectedDate");  // 한 번 쓰고 초기화
    }

    // // 서버에서 내려준 selectedDate가 존재하면 사용 (JSP에서 넘긴 값)
    // selectedDate = (typeof selectedDate !== "undefined" && selectedDate) ? selectedDate : today;
    //
    // console.log("✅ 초기 selectedDate:", selectedDate);

    document.getElementById("diaryDate").innerText = selectedDate;  // 기능은 똑같고 가독성만 높임
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

    console.log("✅ 초기 selectedDate:", selectedDate);

    initCalendar();
    highlightSelectedDate(selectedDate);
    loadDiaryByDate(selectedDate);
    });

/* 캘린더 초기화 함수 */
function initCalendar() {
    const calendarEl = document.getElementById('calendar');

    calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        initialDate: selectedDate,
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
            selectedDate = info.dateStr;

            if (isFutureDate(selectedDate)) {
                alert("미래의 일기는 작성할 수 없습니다.");
                return;
            }

            document.getElementById("diaryDate").innerText = selectedDate;
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
            if (!selectedDate) {
                    selectedDate = calendar.getDate().toISOString().slice(0, 10);
                    highlightSelectedDate(selectedDate);
                    loadDiaryByDate(selectedDate);
                }
            console.log("📅 datesSet selectedDate 최종:", selectedDate);
        }

    });

    calendar.render();
}


// CRUD 함수
/* 일기 등록 */
function saveDiary() {
    console.log("✏️ updateDiary() 호출됨");
    const diaryTitle = document.getElementById("diaryTitle")?.value || "제목 없음";
    const diaryContent = document.getElementById("diaryContent").value;
    const diaryDateTime = `${selectedDate}T00:00:00`;

    if (isFutureDate(selectedDate)) {
        alert("미래의 일기는 저장할 수 없습니다.");
        return;
    }

    if (!diaryContent.trim()) {
        alert("일기 내용을 입력해주세요!");
        return;
    }

    const data = {
        user_id: "user1",   // 나중에 세션에서 받을
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

/* 일기 수정 */
function updateDiary() {
    console.log("✏️ updateDiary() 호출됨");
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
        created_at: `${selectedDate}T00:00:00`, // 기존 날짜 유지 (필요시 수정 가능)
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

                openEmotionModal();

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
    console.log("🗑️ deleteDiary() 호출됨");
    console.log("삭제 시도! currentDiaryId:", currentDiaryId);
    if (!currentDiaryId) {
        alert("삭제할 일기가 없습니다.");
        return;
    }

    if (!confirm("정말 삭제하시겠습니까?")) {
        return;
    }
    fetch(`/diary/emotion/delete/${currentDiaryId}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) {
                throw new Error("감정 점수 삭제 실패");
            }
            console.log("감정 점수 삭제 완료");

            return fetch(`/diary/delete/${currentDiaryId}`, {method: 'DELETE'});
        })
        .then(response => {
            if (response.ok) {
                alert("일기 삭제 완료!");
                currentDiaryId = null; // 삭제했으니 초기화
                loadDiaryByDate(selectedDate);  // 삭제 후 작성 모드로 전환 (없는 경우)

                refreshCalendarEvents();
                openWriteMode(selectedDate);
            } else {
                alert("일기 삭제 실패");
            }
        })
        .catch(error => {
            console.error("❌ 삭제 실패:", error);
            alert("삭제 중 오류가 발생했습니다!");
        });
}


// 조회 함수
/* diaryId 기준 상세 조회 */
function loadDiaryById(diaryId) {
    isViewMode = true;

    console.log("🔎 loadDiaryById 호출됨, diaryId:", diaryId); // ← 여기에 추가!

    fetch(`/diary/${diaryId}`)
        .then(async (response) => {
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

            resetEmojiSelection("diaryWriteSection", selectedEmoji, true);

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
    console.log("📅 loadDiaryByDate() 호출됨 | dateStr:", dateStr);
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
    console.log("📝 switchToEditMode() 호출됨");
    isViewMode = false;

    document.getElementById("diaryViewSection").style.display = "none";
    document.getElementById("diaryWriteSection").style.display = "block";

    document.getElementById("diaryDate").innerText = document.getElementById("viewDiaryDate").innerText;
    document.getElementById("diaryTitle").value = document.getElementById("viewDiaryTitle").innerText;
    document.getElementById("diaryContent").value = document.getElementById("viewDiaryContent").innerText;

    resetEmojiSelection("diaryWriteSection", selectedEmoji, true);

    const emojiElement = document.querySelector("#diaryViewSection .emoji-option.selected");
    if (emojiElement) {
        const emojiId = emojiElement.id; // ex) view-🙂
        const emoji = emojiId.split("view-")[1]; // 🙂 만 추출
        selectedEmoji = emoji;
    } else {
        selectedEmoji = "🙂";
        console.warn("선택된 이모지가 없습니다. 기본값으로 초기화!");
    }

    document.getElementById("saveBtn").style.display = "none";
    document.getElementById("updateBtn").style.display = "inline-block";
}


// 오늘의 점수 함수
/* 오늘의 감정 점수 모달 열기 */
function openEmotionModal(existingScore = 50) {
    console.log("😊 openEmotionModal() 호출됨");
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "flex";

    loadEmotionScoreByDiaryId(currentDiaryId);
}

/* 오늘의 감정 점수 모달 닫기 */
function closeEmotionModal() {
    console.log("❌ closeEmotionModal() 호출됨");
    const modal = document.getElementById("emotionScoreModal");
    modal.style.display = "none";
}

/* 오늘의 점수 가져오는 함수 */
function loadEmotionScoreByDiaryId(diaryId) {
    console.log("🔍 loadEmotionScoreByDiaryId() 호출됨 | diaryId:", diaryId);
    if (!diaryId) {
        console.warn("❗ diaryId가 없습니다. 감정 점수 조회 불가");
        return;
    }

    fetch(`/diary/emotion/score/${diaryId}`)
        .then(response => response.json())
        .then(data => {
            if (data && data.emotion_score !== undefined) {
                const score = data.emotion_score;

                // input에 값 반영
                document.getElementById("emotionScoreInput").value = score;
                document.getElementById("scoreDisplay").innerText = score;

                // 전역 변수도 세팅 (선택 사항)
                currentEmotionScore = score;

                console.log("✅ 감정 점수 조회 성공:", score);
            } else {
                console.warn("❗ 감정 점수 없음, 기본값 사용");
            }
        })
        .catch(error => {
            console.error("❌ 감정 점수 조회 실패", error);
        });
}

/* 감정 점수 저장 */
function saveEmotionScore() {
    console.log("💾 saveEmotionScore() 호출됨");
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

                // 캘린더 이벤트 리프레시 상세조회 호출
                refreshCalendarEvents();
                loadDiaryById(currentDiaryId);

                // currentDiaryId 초기화
                currentDiaryId = null;
            } else {
                alert("감정 점수 저장 실패");
            }
        });
}

/* 오늘의 점수 입력값 변경*/
function updateScoreValue(value) {
    console.log("📝 updateScoreValue() 호출됨 | value:", value);
    document.getElementById("scoreDisplay").innerText = value;
}


// UI 보조 함수
/* 뷰모드 -> 작성 모드 전환 */
function openWriteMode(dateStr) {
    console.log("✍️ openWriteMode() 호출됨 | dateStr:", dateStr);
    if (isFutureDate(dateStr)) {
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

    // 이모지 선택 초기화 함수 연결
    resetEmojiSelection("diaryWriteSection", selectedEmoji, true);

    // 버튼 상태
    document.getElementById("saveBtn").style.display = "inline-block";
    document.getElementById("updateBtn").style.display = "none";
}

/* 캘린더 날짜 하이라이트 강조 */
function highlightSelectedDate(dateStr) {
    console.log("🎯 highlightSelectedDate() 호출됨 | dateStr:", dateStr);
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

/* 이모지 선택 */
function selectEmoji(emoji) {
    console.log("😀 selectEmoji() 호출됨 | emoji:", emoji);
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

/* 캘린더 이벤트 리프레시 */
function refreshCalendarEvents() {
    console.log("🔄 refreshCalendarEvents() 호출됨");
        if (calendar) {
            console.log("✅ 캘린더 이벤트 다시 불러오는 중!");
            calendar.refetchEvents();
        } else {
            console.warn("❗ 캘린더 인스턴스를 찾을 수 없음");
        }
}