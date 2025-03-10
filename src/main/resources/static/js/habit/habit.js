function habitShowTab(tabId) {
    // 모든 탭 콘텐츠 숨기기
    document.getElementById("habit-신체건강").classList.add("habit-hidden");
    document.getElementById("habit-정신건강").classList.add("habit-hidden");

    // 선택한 탭 콘텐츠 보이기
    document.getElementById(`habit-${tabId}`).classList.remove("habit-hidden");

    // 모든 탭 버튼 비활성화
    document.getElementById("habit-tab-신체건강").classList.remove("habit-active");
    document.getElementById("habit-tab-정신건강").classList.remove("habit-active");

    // 선택한 탭 버튼 활성화
    document.getElementById(`habit-tab-${tabId}`).classList.add("habit-active");
}

// 기본적으로 '신체건강' 탭을 활성화
document.addEventListener('DOMContentLoaded', function () {
    habitShowTab('신체건강');
});
