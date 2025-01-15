// 공통으로 사용할 요소들
const modalOverlay = document.querySelector('.modal_overlay');

// 우편번호 모달 관련
const postalCodeBtn = document.getElementById('postal_code_btn');
const modalWindow = document.querySelector('.modal_window_pc');

// 사이드바 관련
const hamBtn = document.querySelector('.modal_btn_ham');
const leftSidebar = document.querySelector('.left_sidebar');

// 우편번호 모달 열기
postalCodeBtn.addEventListener('click', () => {
    modalWindow.classList.add('active');
    modalOverlay.classList.add('active');
    document.body.style.overflow = 'hidden';
});

// 햄버거 메뉴 열기
hamBtn.addEventListener('click', () => {
    leftSidebar.classList.add('active');
    modalOverlay.classList.add('active');
    document.body.style.overflow = 'hidden';
});

// 공통 닫기 함수
const closeModal = () => {
    modalWindow.classList.remove('active');
    leftSidebar.classList.remove('active');
    modalOverlay.classList.remove('active');
    document.body.style.overflow = '';
};

// 오버레이 클릭시 닫기
modalOverlay.addEventListener('click', closeModal);

// ESC 키로 닫기
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
        closeModal();
    }
});

// 스크롤 변수
let lastScrollTop = 0;
const navBar = document.querySelector('.nav_bar');

// 스크롤 이벤트
window.addEventListener('scroll', () => {
    const scroll = window.scrollY || document.documentElement.scrollTop;
    // pageYOffset 대신 scrollY 사용

    // 스크롤 방향 확인
    if (scroll > lastScrollTop) {
        // 스크롤 방향이 아래로 내려갈 때 navBar 숨김
        navBar.classList.remove('visible');
    } else {
        // 스크롤 방향이 위로 올라갈 때 navBar 표시
        navBar.classList.add('visible');
    }
    // 페이지 상단, 헤더 밑에 항상 표시
    if (scroll === 0) {
        navBar.classList.add('visible');
    }
    lastScrollTop = scroll;
});

// 스와이퍼 초기화
const mySwiper = new Swiper('.ikea-deco-swiper', {
    slidesPerView: 3.5,
    spaceBetween: 30,
    preventInteractionOnTransition: true,
    touchAngle: 0,
    direction: 'horizontal',
    freeMode: true,
    mousewheel: {
        forceToAxis: true,
    },
    scrollbar: {
        el: '.swiper-scrollbar',
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    }
});

// 우편번호 api 열기
function openPostalCode() 
    {
    new daum.Postcode({
        oncomplete: function(data) {
        }
    }).open();
}

// 홈퍼니싱 탭 클릭 이벤트
const tabMenuList = document.querySelectorAll('.fur_tab');
const tabContentList = document.querySelectorAll('.tab_content');
let activeTab = "#all"; // 초기 활성화 탭

// 탭 변경
function changeTab(tab) {
    // 모든 탭 컨텐츠 숨기기
    tabContentList.forEach(content => {
        content.style.display = 'none';
    });
    // 클릭한 탭 컨텐츠 표시
    const selectedTab = document.getElementById(tab);
    if (selectedTab) {
        selectedTab.style.display = 'block';
    }

    // 모든 탭 버튼에서 active 클래스 제거
    tabMenuList.forEach(button => {
        button.classList.remove('active');
    });

    // 선택한 탭 버튼에 active 클래스 추가
    const activeButton = Array.from(tabMenuList).find(button => button.dataset.tab === tab);
    if (activeButton) {
        activeButton.classList.add('active');
    }
}
// 초기 탭 설정
changeTab('all'); // 페이지 로드 시 기본 탭 설정

