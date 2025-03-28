// ✅ 전역 상태 변수
let selectedDate = null;
let currentMonth = new Date().getMonth();
let currentYear = new Date().getFullYear();

// ✅ 주간 시작 일요일 계산
function getSundayOfWeek(dateStr) {
  const date = new Date(dateStr);
  const day = date.getDay();
  date.setDate(date.getDate() - day);
  return date.toISOString().split("T")[0];
}

// ✅ 회고 메모 불러오기
function loadWeeklyMemo() {
  console.log("📝 loadWeeklyMemo() 실행됨. selectedDate =", selectedDate);
  if (!selectedDate || selectedDate.trim() === "") {
    console.warn("⛔ [loadWeeklyMemo] selectedDate가 유효하지 않음:", selectedDate);
    return;
  }

  const sunday = getSundayOfWeek(selectedDate);
  console.log("📅 회고 메모 요청할 sunday =", sunday);

  fetch(`/habit/memo?date=${sunday}&user_id=user1`)
      .then(res => res.text())
      .then(text => {
        const memoField = document.getElementById("weeklyMemoText");
        memoField.value = text || "";

        const today = new Date().toISOString().split("T")[0];
        const isEditable = selectedDate === today && new Date(selectedDate).getDay() === 0;
        memoField.disabled = !isEditable;
        document.getElementById("saveMemoBtn").disabled = !isEditable;

        console.log("✅ 회고 메모 불러오기 완료");
      })
      .catch(err => {
        console.error("❌ 회고 메모 불러오기 실패:", err);
      });
}

// ✅ 회고 메모 저장
function setupMemoSaveButton() {
  document.getElementById("saveMemoBtn").addEventListener("click", () => {
    if (!selectedDate) {
      alert("날짜를 먼저 선택해주세요!");
      return;
    }

    if (new Date(selectedDate).getDay() !== 0) {
      alert("일요일에만 회고 메모를 작성할 수 있어요!");
      return;
    }

    const sunday = getSundayOfWeek(selectedDate);
    const content = document.getElementById("weeklyMemoText").value;
    console.log("💾 회고 메모 저장 요청:", { sunday, content });

    fetch("/habit/memo", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        tracking_date: sunday,
        feedback: content,
        user_id: "user1"
      }),
    })
        .then(res => {
          if (res.ok) alert("회고 메모 저장 완료!");
          else alert("저장 실패...");
        })
        .catch(err => {
          console.error("❌ 회고 메모 저장 실패:", err);
        });
  });
}

// ✅ 날짜 클릭 시 호출
function onCalendarDateClick(dateStr) {
  selectedDate = dateStr;
  console.log("📌 날짜 선택됨: selectedDate =", selectedDate);
  document.getElementById("selectedDateDisplay").textContent = `선택한 날짜: ${selectedDate}`;
  loadTrackingStatus();
  loadWeeklySummary();
  loadWeeklyMemo();
}

// ✅ 달력 생성
function generateCalendar(month, year) {
  const calendarBody = document.getElementById("calendarBody");
  calendarBody.innerHTML = "";

  const monthYear = document.getElementById("monthYear");
  monthYear.textContent = `${year}년 ${month + 1}월`;

  const firstDay = new Date(year, month, 1).getDay();
  const daysInMonth = new Date(year, month + 1, 0).getDate();

  for (let i = 0; i < firstDay; i++) {
    const emptyCell = document.createElement("div");
    emptyCell.classList.add("calendar-day");
    emptyCell.innerHTML = "&nbsp;";
    calendarBody.appendChild(emptyCell);
  }

  for (let day = 1; day <= daysInMonth; day++) {
    const dateCell = document.createElement("div");
    dateCell.classList.add("calendar-day");
    dateCell.textContent = day;

    const formattedMonth = (month + 1).toString().padStart(2, "0");
    const formattedDay = day.toString().padStart(2, "0");
    dateCell.dataset.date = `${year}-${formattedMonth}-${formattedDay}`;

    dateCell.addEventListener("click", function () {
      document.querySelectorAll(".calendar-day").forEach(cell => cell.classList.remove("selected"));
      this.classList.add("selected");

      onCalendarDateClick(this.dataset.date);
    });

    calendarBody.appendChild(dateCell);
  }
}

// ✅ 체크 상태 불러오기
function loadTrackingStatus() {
  if (!selectedDate || selectedDate.trim() === "") {
    console.warn("⛔ [loadTrackingStatus] selectedDate가 유효하지 않음:", selectedDate);
    return;
  }

  console.log("🚀 loadTrackingStatus 호출됨, selectedDate =", selectedDate);

  fetch(`/habit/tracking/status?date=${selectedDate}`)
      .then(response => response.json())
      .then(result => {
        const trackedHabitIds = Array.isArray(result) ? result : result.data;
        console.log("✅ 서버 응답 trackedHabitIds =", trackedHabitIds);

        document.querySelectorAll('input[type="checkbox"]').forEach((checkbox) => {
          const habitId = parseInt(checkbox.id.split("-")[1]);
          checkbox.checked = trackedHabitIds.includes(habitId);
          console.log(`🟡 habitId = ${habitId}, checked = ${checkbox.checked}`);
        });

        attachCheckboxEvents();
      })
      .catch((error) => {
        console.error("❌ 습관 상태 로드 실패:", error);
      });
}

// ✅ 체크박스 변경 이벤트
function attachCheckboxEvents() {
  document.querySelectorAll('input[type="checkbox"]').forEach((oldCheckbox) => {
    const newCheckbox = oldCheckbox.cloneNode(true);
    oldCheckbox.replaceWith(newCheckbox);
  });

  document.querySelectorAll('input[type="checkbox"]').forEach((checkbox) => {
    checkbox.addEventListener("change", function () {
      const habitId = parseInt(this.id.split("-")[1]);
      const isChecked = this.checked ? 1 : 0;

      if (!selectedDate) {
        alert("먼저 날짜를 선택해주세요!");
        this.checked = !this.checked;
        return;
      }

      const payload = {
        habit_id: habitId,
        completed: isChecked,
        tracking_date: selectedDate,
        user_id: "user1",
      };

      console.log("📤 체크 상태 저장 요청:", payload);

      fetch("/habit/tracking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      })
          .then((res) => {
            if (!res.ok) {
              alert("습관 상태 저장 실패");
              this.checked = !isChecked;
            }
          })
          .catch((err) => {
            console.error("❌ 체크 저장 실패:", err);
            this.checked = !isChecked;
          });
    });
  });
}

// ✅ 주간 이력 + 격려 호출
function loadWeeklySummary() {
  if (!selectedDate || selectedDate.trim() === "") {
    console.warn("⛔ [loadWeeklySummary] selectedDate가 유효하지 않음:", selectedDate);
    return;
  }

  console.log("📤 [loadWeeklySummary] 요청, selectedDate =", selectedDate);

  fetch(`/habit/week/status?date=${selectedDate}`)
      .then(res => res.json())
      .then(data => {
        renderWeeklyMemo(data);
        renderEncouragement(data);
      })
      .catch((err) => {
        console.error("❌ 주간 이력 로딩 실패:", err);
      });
}

// ✅ 주간 이력 표시
function renderWeeklyMemo(data) {
  const tbody = document.getElementById("weeklyHabitBody");
  tbody.innerHTML = "";

  data.forEach((habit) => {
    const tracking = habit.tracking;
    let row = `<tr><td>${habit.habit_name}</td>`;
    const dayOrder = [6, 0, 1, 2, 3, 4, 5];

    dayOrder.forEach((i) => {
      row += `<td>${tracking[i] ? "O" : "X"}</td>`;
    });

    row += "</tr>";
    tbody.innerHTML += row;
  });
}

// ✅ 격려 메시지 표시
function renderEncouragement(data) {
  const list = document.getElementById("encouragementList");
  list.innerHTML = "";

  data.forEach((habit) => {
    list.innerHTML += `<li><strong>${habit.habit_name}</strong>: ${habit.encouragement}</li>`;
  });
}

// ✅ 습관 추가
function addHabit() {
  const habitName = document.getElementById("habitInput").value.trim();
  if (!habitName) {
    alert("習慣名の入力をお願いします");
    return;
  }

  const data = { habit_name: habitName, user_id: "user1" };
  console.log("📩 습관 추가 요청:", data);

  fetch("/habit/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
      .then(res => res.json())
      .then(data => {
        if (data.habit_id || data.status === "success") {
          alert("習慣追加成功!");
          location.reload();
        } else {
          alert("習慣追加失敗: " + data.message);
        }
      })
      .catch((error) => {
        console.error("❌ 습관 추가 실패:", error);
      });
}

// ✅ 추천 습관 클릭 시
function addHabitToDatabase(habitName) {
  const requestData = { habit_name: habitName, user_id: "user1" };
  console.log("💡 추천 습관 추가:", habitName);

  fetch("/habit/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(requestData),
  })
      .then(res => res.json())
      .then(data => {
        if (data.habit_id || data.status === "success") {
          location.reload();
        } else {
          alert("習慣追加失敗");
        }
      })
      .catch((error) => console.error("❌ 추천 습관 추가 실패:", error));
}

// ✅ 이전/다음 달 이동
document.getElementById("prevMonth").addEventListener("click", () => {
  currentMonth--;
  if (currentMonth < 0) {
    currentMonth = 11;
    currentYear--;
  }
  generateCalendar(currentMonth, currentYear);
});

document.getElementById("nextMonth").addEventListener("click", () => {
  currentMonth++;
  if (currentMonth > 11) {
    currentMonth = 0;
    currentYear++;
  }
  generateCalendar(currentMonth, currentYear);
});

// ✅ 탭 전환
function habitShowTab(tab) {
  document.querySelectorAll(".habit-content").forEach((tabContent) => {
    tabContent.classList.add("habit-hidden");
  });
  document.querySelectorAll(".habit-tab").forEach((btn) => {
    btn.classList.remove("habit-active");
  });

  document.getElementById("habit-" + tab)?.classList.remove("habit-hidden");
  document.getElementById("habit-tab-" + tab)?.classList.add("habit-active");
}

// ✅ 초기 실행
document.addEventListener("DOMContentLoaded", () => {
  console.log("🌱 페이지 로딩 완료 - 초기화 시작");

  setupMemoSaveButton();
  generateCalendar(currentMonth, currentYear);

  document.getElementById("addHabitBtn").addEventListener("click", addHabit);

  document.querySelectorAll(".habit-recommend p").forEach((item) => {
    item.addEventListener("click", () => {
      const habitName = item.innerText;
      addHabitToDatabase(habitName);
    });
  });

  habitShowTab("신체건강");

  console.log("✅ 초기화 완료");
});
