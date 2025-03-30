//
//
//
//
// // 오늘 날짜 반환
// function getTodayDateStr() {
//   const today = new Date();
//   const year = today.getFullYear();
//   const month = ("0" + (today.getMonth() + 1)).slice(-2);
//   const day = ("0" + today.getDate()).slice(-2);
//   return `${year}-${month}-${day}`;
// }
//
// // 전역 변수
// let selectedDate = null;
// let currentMonth = new Date().getMonth();
// let currentYear = new Date().getFullYear();
//
// // 날짜 하이라이트
// function highlightSelectedDate(dateStr) {
//   const calendarCells = document.querySelectorAll(".calendar-day");
//   calendarCells.forEach(cell => {
//     if (cell.dataset.date === dateStr) {
//       cell.classList.add("selected");
//     } else {
//       cell.classList.remove("selected");
//     }
//   });
// }
//
// // 체크박스 상태 불러오기
// function loadTrackingStatus(retry = 0) {
//   console.log("🧪 loadTrackingStatus 진입!", retry, "회차");
//   console.log("📆 selectedDate:", selectedDate);
//   if (!selectedDate) return;
//
//   const checkboxes = document.querySelectorAll('input[type="checkbox"]');
//   console.log("📦 체크박스 수:", checkboxes.length);
//
//   if (checkboxes.length === 0 && retry < 5) {
//     console.warn(`⏳ 체크박스 없음 → ${retry + 1}회 재시도`);
//     return setTimeout(() => loadTrackingStatus(retry + 1), 100);
//   }
//
//   fetch(`/habit/tracking/status?date=${selectedDate}`)
//       .then(res => res.json())
//       .then(result => {
//         const tracked = Array.isArray(result) ? result : result.data;
//         console.log("✅ 불러온 tracked 상태:", tracked);
//
//         checkboxes.forEach(cb => {
//           const habitId = parseInt(cb.id.split("-")[1]);
//           cb.checked = tracked.includes(habitId);
//         });
//
//         attachCheckboxEvents();
//       });
// }
//
// // 체크박스 이벤트 연결
// function attachCheckboxEvents() {
//   document.querySelectorAll('input[type="checkbox"]').forEach(oldCb => {
//     const newCb = oldCb.cloneNode(true);
//     oldCb.replaceWith(newCb);
//   });
//
//   document.querySelectorAll('input[type="checkbox"]').forEach(cb => {
//     cb.addEventListener("change", function () {
//       const habitId = parseInt(this.id.split("-")[1]);
//       const isChecked = this.checked ? 1 : 0;
//
//       if (!selectedDate) {
//         alert("먼저 날짜를 선택해주세요!");
//         this.checked = !this.checked;
//         return;
//       }
//
//       const payload = {
//         habit_id: habitId,
//         completed: isChecked,
//         tracking_date: selectedDate,
//         user_id: "user1"
//       };
//
//       // ✅ 이 fetch() 내부가 수정 대상
//       fetch("/habit/tracking", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify(payload)
//       }).then((response) => {
//         if (!response.ok) {
//           alert("상태 저장 실패");
//           this.checked = !isChecked;
//         } else {
//           // ✅ 여기 추가!
//           loadWeeklySummary(); // 체크 성공 시 주간 요약 재로딩
//         }
//       }).catch(err => {
//         console.error("저장 에러:", err);
//         this.checked = !isChecked;
//       });
//     });
//   });
// }
//
// // 습관 삭제 함수 (JSP에서 가져온 기능)
// function deleteHabit(habit_id) {
//   if (confirm("정말로 삭제하시겠습니까?")) {
//     fetch("/habit/delete/" + habit_id, {
//       method: "DELETE",
//       headers: { "Content-Type": "application/json" },
//     })
//         .then((response) => {
//           if (response.ok) {
//             document.getElementById("habit-box-" + habit_id).remove();
//           } else {
//             alert("삭제 실패");
//           }
//         })
//         .catch((error) => console.error("Error:", error));
//   }
// }
//
// // 습관 직접 입력 추가
// function addHabit() {
//   const habitName = document.getElementById("habitInput").value.trim();
//   if (!habitName) {
//     alert("습관을 입력해주세요!");
//     return;
//   }
//
//   const data = { habit_name: habitName, user_id: "user1" };
//
//   fetch("/habit/add", {
//     method: "POST",
//     headers: { "Content-Type": "application/json" },
//     body: JSON.stringify(data)
//   })
//       .then(res => res.json())
//       .then(data => {
//         if (data.habit_id || data.status === "success") {
//           alert("습관 추가 성공!");
//           location.reload();
//         } else {
//           alert("습관 추가 실패: " + data.message);
//         }
//       })
//       .catch(err => {
//         console.error("추가 실패:", err);
//         alert("서버 오류 발생");
//       });
// }
//
// // 추천 습관 추가
// function addHabitToDatabase(habitName) {
//   const data = { habit_name: habitName, user_id: "user1" };
//
//   fetch("/habit/add", {
//     method: "POST",
//     headers: { "Content-Type": "application/json" },
//     body: JSON.stringify(data)
//   })
//       .then(res => {
//         if (!res.ok) {
//           // ⚠️ 응답 본문 읽기 위해 json() 리턴
//           return res.json().then(errorData => {
//             if (errorData.message && errorData.message.includes("이미")) {
//               alert("이미 추가된 습관입니다!");
//             } else {
//               alert("습관 추가 실패 (서버 오류)");
//             }
//             throw new Error("서버 응답 오류");
//           });
//         }
//         return res.json(); // 정상 응답
//       })
//       .then(data => {
//         alert("추천 습관 추가 완료!");
//         location.reload();
//       })
//       .catch(err => {
//         console.error("추천 추가 실패:", err);
//         // 여기선 따로 alert 안 띄워도 됨 (위에서 이미 처리됨)
//       });
// }
//
//
//
// // 주간 통계
// function loadWeeklySummary() {
//   fetch(`/habit/week/status?date=${selectedDate}`)
//       .then(res => res.json())
//       .then(data => {
//         renderWeeklyMemo(data);
//         renderEncouragement(data);
//       });
// }
//
// function renderWeeklyMemo(data) {
//   const tbody = document.getElementById("weeklyHabitBody");
//   tbody.innerHTML = "";
//   data.forEach(habit => {
//     const tracking = habit.tracking;
//     let row = `<tr><td>${habit.habit_name}</td>`;
//     const dayOrder = [6, 0, 1, 2, 3, 4, 5];
//     dayOrder.forEach(i => {
//       row += `<td>${tracking[i] ? "O" : "X"}</td>`;
//     });
//     row += "</tr>";
//     tbody.innerHTML += row;
//   });
// }
//
// function renderEncouragement(data) {
//   const list = document.getElementById("encouragementList");
//   list.innerHTML = "";
//   data.forEach(habit => {
//     list.innerHTML += `<li><strong>${habit.habit_name}</strong>: ${habit.encouragement}</li>`;
//   });
// }
//
// // 캘린더 생성
// function generateCalendar(month, year) {
//   const calendarBody = document.getElementById("calendarBody");
//   calendarBody.innerHTML = "";
//
//   const monthYear = document.getElementById("monthYear");
//   monthYear.textContent = `${year}년 ${month + 1}월`;
//
//   const firstDay = new Date(year, month, 1).getDay();
//   const daysInMonth = new Date(year, month + 1, 0).getDate();
//
//   for (let i = 0; i < firstDay; i++) {
//     const empty = document.createElement("div");
//     empty.classList.add("calendar-day");
//     empty.innerHTML = "&nbsp;";
//     calendarBody.appendChild(empty);
//   }
//
//   for (let day = 1; day <= daysInMonth; day++) {
//     const cell = document.createElement("div");
//     cell.classList.add("calendar-day");
//     cell.textContent = day;
//
//     const m = String(month + 1).padStart(2, "0");
//     const d = String(day).padStart(2, "0");
//     const dateStr = `${year}-${m}-${d}`;
//     cell.dataset.date = dateStr;
//
//     cell.addEventListener("click", function () {
//       selectedDate = this.dataset.date;
//       highlightSelectedDate(selectedDate);
//       document.getElementById("selectedDateDisplay").textContent = `선택한 날짜: ${selectedDate}`;
//       loadTrackingStatus();
//       loadWeeklySummary();
//     });
//
//     calendarBody.appendChild(cell);
//   }
// }
//
// // 탭 전환
// function habitShowTab(tab) {
//   const allTabs = document.querySelectorAll(".habit-content");
//   const allTabButtons = document.querySelectorAll(".habit-tab");
//
//   allTabs.forEach(content => content.classList.add("habit-hidden"));
//   allTabButtons.forEach(button => button.classList.remove("habit-active"));
//
//   const targetTab = document.getElementById("habit-" + tab);
//   const targetBtn = document.getElementById("habit-tab-" + tab);
//
//   if (targetTab) targetTab.classList.remove("habit-hidden");
//   if (targetBtn) targetBtn.classList.add("habit-active");
// }
//
// // 초기 실행
// document.addEventListener("DOMContentLoaded", function () {
//   console.log("🌱 DOMContentLoaded 진입");
//
//   generateCalendar(currentMonth, currentYear);
//
//   selectedDate = getTodayDateStr();
//   console.log("📌 오늘 날짜:", selectedDate);
//
//   highlightSelectedDate(selectedDate);
//   document.getElementById("selectedDateDisplay").textContent = `선택한 날짜: ${selectedDate}`;
//
//   // 체크박스 상태 로딩 (딜레이 적용)
//   setTimeout(() => {
//     console.log("🚀 loadTrackingStatus 지연 실행");
//     loadTrackingStatus();
//   }, 100);
//
//   loadWeeklySummary();
//   habitShowTab("신체건강");
//
//   const recommendItems = document.querySelectorAll(".habit-recommend p");
//   recommendItems.forEach(item => {
//     item.addEventListener("click", function () {
//       const habitName = this.innerText;
//       addHabitToDatabase(habitName);
//     });
//   });
//
//   const addBtn = document.getElementById("addHabitBtn");
//   if (addBtn && !addBtn.dataset.listenerAttached) {
//     addBtn.setAttribute("type", "button");
//     addBtn.addEventListener("click", addHabit);
//     addBtn.dataset.listenerAttached = "true";
//   }
//
//   document.getElementById("prevMonth").addEventListener("click", function () {
//     currentMonth--;
//     if (currentMonth < 0) {
//       currentMonth = 11;
//       currentYear--;
//     }
//     generateCalendar(currentMonth, currentYear);
//   });
//
//   document.getElementById("nextMonth").addEventListener("click", function () {
//     currentMonth++;
//     if (currentMonth > 11) {
//       currentMonth = 0;
//       currentYear++;
//     }
//     generateCalendar(currentMonth, currentYear);
//   });
// });




// 오늘 날짜 반환
function getTodayDateStr() {
  const today = new Date();
  const year = today.getFullYear();
  const month = ("0" + (today.getMonth() + 1)).slice(-2);
  const day = ("0" + today.getDate()).slice(-2);
  return `${year}-${month}-${day}`;
}

// 전역 변수
let selectedDate = null;
let currentMonth = new Date().getMonth();
let currentYear = new Date().getFullYear();

// 날짜 하이라이트
function highlightSelectedDate(dateStr) {
  const calendarCells = document.querySelectorAll(".calendar-day");
  calendarCells.forEach(cell => {
    if (cell.dataset.date === dateStr) {
      cell.classList.add("selected");
    } else {
      cell.classList.remove("selected");
    }
  });
}

// 체크박스 상태 불러오기
function loadTrackingStatus(retry = 0) {
  console.log("🧪 loadTrackingStatus 진입!", retry, "회차");
  console.log("📆 selectedDate:", selectedDate);
  if (!selectedDate) return;

  const checkboxes = document.querySelectorAll('input[type="checkbox"]');
  console.log("📦 체크박스 수:", checkboxes.length);

  if (checkboxes.length === 0 && retry < 5) {
    console.warn(`⏳ 체크박스 없음 → ${retry + 1}회 재시도`);
    return setTimeout(() => loadTrackingStatus(retry + 1), 100);
  }

  fetch(`/habit/tracking/status?date=${selectedDate}`)
      .then(res => res.json())
      .then(result => {
        const tracked = Array.isArray(result) ? result : result.data;
        console.log("✅ 불러온 tracked 상태:", tracked);

        checkboxes.forEach(cb => {
          const habitId = parseInt(cb.id.split("-")[1]);
          cb.checked = tracked.includes(habitId);
        });

        attachCheckboxEvents();
      });
}

// 체크박스 이벤트 연결
function attachCheckboxEvents() {
  document.querySelectorAll('input[type="checkbox"]').forEach(oldCb => {
    const newCb = oldCb.cloneNode(true);
    oldCb.replaceWith(newCb);
  });

  document.querySelectorAll('input[type="checkbox"]').forEach(cb => {
    cb.addEventListener("change", function () {
      const habitId = parseInt(this.id.split("-")[1]);
      const isChecked = this.checked ? 1 : 0;

      if (!selectedDate) {
        alert("先に日付を選択してください！");
        this.checked = !this.checked;
        return;
      }

      const payload = {
        habit_id: habitId,
        completed: isChecked,
        tracking_date: selectedDate,
        user_id: "user1"
      };

      fetch("/habit/tracking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      }).then((response) => {
        if (!response.ok) {
          alert("状態の保存に失敗しました");
          this.checked = !isChecked;
        } else {
          loadWeeklySummary();
        }
      }).catch(err => {
        console.error("保存エラー:", err);
        this.checked = !isChecked;
      });
    });
  });
}

// 습관 삭제
function deleteHabit(habit_id) {
  if (confirm("정말로 삭제하시겠습니까?")) {
    fetch("/habit/delete/" + habit_id, {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
    })
        .then((response) => {
          if (response.ok) {
            // ✅ UI에서 삭제
            document.getElementById("habit-box-" + habit_id).remove();

            // ✅ 주간 이력 요약도 즉시 갱신!
            loadWeeklySummary();
          } else {
            alert("삭제 실패");
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          alert("서버 오류로 삭제 실패");
        });
  }
}


// 습관 직접 추가
function addHabit() {
  const habitName = document.getElementById("habitInput").value.trim();
  if (!habitName) {
    alert("習慣名を入力してください！");
    return;
  }

  const data = { habit_name: habitName, user_id: "user1" };

  fetch("/habit/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
      .then(res => res.json())
      .then(data => {
        if (data.habit_id || data.status === "success") {
          alert("習慣を追加しました！");
          location.reload();
        } else {
          alert("習慣の追加に失敗しました: " + data.message);
        }
      })
      .catch(err => {
        console.error("追加エラー:", err);
        alert("サーバーエラーが発生しました");
      });
}

// 추천 습관 추가
// // 추천 습관 추가
// function addHabitToDatabase(habitName) {
//   const data = { habit_name: habitName, user_id: "user1" };
//
//   fetch("/habit/add", {
//     method: "POST",
//     headers: { "Content-Type": "application/json" },
//     body: JSON.stringify(data)
//   })
//       .then(res => {
//         if (!res.ok) {
//           return res.json().then(errorData => {
//             console.log("🧾 서버 응답 메시지:", errorData);  // ✅ 여기 추가!
//             console.log("💬 서버 메시지:", errorData.message);  // ✅ 여기만 추가!
//             // ✅ 중복 메시지: 한국어("이미"), 일본어("すでに"), 혹은 status로 대응
//             const isDuplicate =
//                 (errorData.message && (
//                     errorData.message.includes("이미") ||
//                     errorData.message.includes("すでに")
//                 )) ||
//                 errorData.status === "duplicate";
//
//             if (isDuplicate) {
//               alert("すでに追加された習慣です！");
//             } else {
//               alert("習慣の追加に失敗しました（サーバーエラー）");
//             }
//
//             throw new Error("サーバー応答エラー");
//           });
//         }
//         return res.json(); // 정상 응답 시 JSON 파싱
//       })
//       .then(data => {
//         alert("おすすめ習慣を追加しました！");
//         location.reload();
//       })
//       .catch(err => {
//         console.error("おすすめ追加エラー:", err);
//       });
// }

function addHabitToDatabase(habitName) {
  const data = { habit_name: habitName, user_id: "user1" };

  fetch("/habit/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
      .then(res => {
        if (!res.ok) {
          return res.text().then(errorText => {
            if (!errorText) {
              // ✅ 응답 본문이 아예 없을 경우 (진짜 빈 응답)
              alert("サーバーからの応答がありません（空のレスポンス）");
              throw new Error("빈 응답");
            }

            let errorData = {};
            try {
              errorData = JSON.parse(errorText);
            } catch (e) {
              console.warn("⚠️ JSON 파싱 실패. 원문:", errorText);
            }

            const msg = errorData.message || "";

            const isDuplicate =
                msg.includes("이미") ||
                msg.includes("すでに") ||
                msg.includes("存在") ||
                msg.includes("등록") ||
                errorData.status === "duplicate";

            if (isDuplicate) {
              alert("すでに追加された習慣です！");
            } else {
              alert("習慣の追加に失敗しました（サーバーエラー）");
            }

            throw new Error("サーバー응답 에러");
          });
        }

        return res.json();
      })
      .then(data => {
        alert("おすすめ習慣を追加しました！");
        location.reload();
      })
      .catch(err => {
        console.error("おすすめ追加エラー:", err);
      });
}





// 주간 통계
function loadWeeklySummary() {
  fetch(`/habit/week/status?date=${selectedDate}`)
      .then(res => res.json())
      .then(data => {
        renderWeeklyMemo(data);
        renderEncouragement(data);
      });
}

function renderWeeklyMemo(data) {
  const tbody = document.getElementById("weeklyHabitBody");
  tbody.innerHTML = "";
  data.forEach(habit => {
    const tracking = habit.tracking;
    let row = `<tr><td>${habit.habit_name}</td>`;
    // const dayOrder = [6, 0, 1, 2, 3, 4, 5];
      const dayOrder = [0, 1, 2, 3, 4, 5, 6]; // 순서대로 일~토
    dayOrder.forEach(i => {
      row += `<td>${tracking[i] ? "O" : "X"}</td>`;
    });
    row += "</tr>";
    tbody.innerHTML += row;
  });
}

function renderEncouragement(data) {
  const list = document.getElementById("encouragementList");
  list.innerHTML = "";
  data.forEach(habit => {
    list.innerHTML += `<li><strong>${habit.habit_name}</strong>: ${habit.encouragement}</li>`;
  });
}

// 캘린더 생성
function generateCalendar(month, year) {
  const calendarBody = document.getElementById("calendarBody");
  calendarBody.innerHTML = "";

  const monthYear = document.getElementById("monthYear");
  monthYear.textContent = `${year}年 ${month + 1}月`;

  const firstDay = new Date(year, month, 1).getDay();
  const daysInMonth = new Date(year, month + 1, 0).getDate();

  for (let i = 0; i < firstDay; i++) {
    const empty = document.createElement("div");
    empty.classList.add("calendar-day");
    empty.innerHTML = "&nbsp;";
    calendarBody.appendChild(empty);
  }

  for (let day = 1; day <= daysInMonth; day++) {
    const cell = document.createElement("div");
    cell.classList.add("calendar-day");
    cell.textContent = day;

    const m = String(month + 1).padStart(2, "0");
    const d = String(day).padStart(2, "0");
    const dateStr = `${year}-${m}-${d}`;
    cell.dataset.date = dateStr;

    cell.addEventListener("click", function () {
      selectedDate = this.dataset.date;
      highlightSelectedDate(selectedDate);
      document.getElementById("selectedDateDisplay").textContent = `選択した日付：${selectedDate}`;
      loadTrackingStatus();
      loadWeeklySummary();
    });

    calendarBody.appendChild(cell);
  }
}

// 탭 전환
function habitShowTab(tab) {
  const allTabs = document.querySelectorAll(".habit-content");
  const allTabButtons = document.querySelectorAll(".habit-tab");

  allTabs.forEach(content => content.classList.add("habit-hidden"));
  allTabButtons.forEach(button => button.classList.remove("habit-active"));

  const targetTab = document.getElementById("habit-" + tab);
  const targetBtn = document.getElementById("habit-tab-" + tab);

  if (targetTab) targetTab.classList.remove("habit-hidden");
  if (targetBtn) targetBtn.classList.add("habit-active");
}

// 초기 실행
document.addEventListener("DOMContentLoaded", function () {
  console.log("🌱 DOMContentLoaded 진입");

  generateCalendar(currentMonth, currentYear);

  selectedDate = getTodayDateStr();
  console.log("📌 오늘 날짜:", selectedDate);

  highlightSelectedDate(selectedDate);
  document.getElementById("selectedDateDisplay").textContent = `選択した日付：${selectedDate}`;

  setTimeout(() => {
    console.log("🚀 loadTrackingStatus 지연 실행");
    loadTrackingStatus();
  }, 100);

  loadWeeklySummary();
  habitShowTab("신체건강");

  const recommendItems = document.querySelectorAll(".habit-recommend p");
  recommendItems.forEach(item => {
    item.addEventListener("click", function () {
      const habitName = this.innerText;
      addHabitToDatabase(habitName);
    });
  });

  const addBtn = document.getElementById("addHabitBtn");
  if (addBtn && !addBtn.dataset.listenerAttached) {
    addBtn.setAttribute("type", "button");
    addBtn.addEventListener("click", addHabit);
    addBtn.dataset.listenerAttached = "true";
  }

  document.getElementById("prevMonth").addEventListener("click", function () {
    currentMonth--;
    if (currentMonth < 0) {
      currentMonth = 11;
      currentYear--;
    }
    generateCalendar(currentMonth, currentYear);
  });

  document.getElementById("nextMonth").addEventListener("click", function () {
    currentMonth++;
    if (currentMonth > 11) {
      currentMonth = 0;
      currentYear++;
    }
    generateCalendar(currentMonth, currentYear);
  });
});
