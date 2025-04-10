// 슬라이더

const slider = document.getElementById('main-slider');
const btnLeft = document.getElementById('btn-left');
const btnRight = document.getElementById('btn-right');

let currentIndex = 0;
const slides = document.querySelectorAll('.slide');
const tImages = document.querySelectorAll('.t-image');
const totalSlides = slides.length;

tImages.forEach((ti, index)=>{
  ti.addEventListener("mouseenter",()=>{
    currentIndex = index;
    updateSlider();
  });
});


function updateSlider() {
    // 모든 슬라이드를 숨김 처리
    slides.forEach((slide, index) => {
        slide.classList.remove('active');
        if (index === currentIndex) {
            slide.classList.add('active');
        }
    });

    // 왼쪽 버튼 상태
    if (currentIndex === 0) {
        btnLeft.style.display = "none";
    } else {
        btnLeft.style.display = "flex";
    }

    // 오른쪽 버튼 상태
    if (currentIndex === totalSlides - 1) {
        btnRight.style.display = "none";
    } else {
        btnRight.style.display = "flex";
    }
}

btnLeft.addEventListener('click', () => {
    if (currentIndex > 0) {
        currentIndex--;
        updateSlider();
    }
});

btnRight.addEventListener('click', () => {
    if (currentIndex < totalSlides - 1) {
        currentIndex++;
        updateSlider();
    }
});

// 초기 상태 업데이트
updateSlider();


// 모달
const modalOpenButton = document.getElementById("modalOpenButton");
const modalCloseButton = document.getElementById("modalCloseButton");
const modal = document.getElementById("modalContainer");

modalOpenButton.addEventListener("click", () => {
  modal.classList.remove("hidden");
  document.body.style.overflowY = "hidden";
});

modalCloseButton.addEventListener("click", () => {
  modal.classList.add("hidden");
  document.body.style.overflowY = "unset";
});

window.addEventListener("click", function (event) {
  if (event.target === modal) {
    modal.classList.add("hidden");
    document.body.style.overflowY = "unset";
  }
});

// 장바구니

const minusButton = document.querySelector(".minus");
const plusButton = document.querySelector(".plus");
const quantityInput = document.querySelector(".quantity-input");
const cartButton = document.querySelector(".cart");
const cartPanel = document.getElementById("cartPanel");
const closePanel = document.getElementById("closePanel");
const cartQuantity = document.getElementById("cartQuantity");

// 수량 조정
function updateQuantity() {
  const quantity = parseInt(quantityInput.value, 10);
  minusButton.disabled = quantity <= 1;
  
  if (quantity === 1) {
    cartButton.textContent = "장바구니에 담기";
  } else {
    cartButton.textContent = `${quantity}개 구매하기`;
  }
}

minusButton.addEventListener("click", () => {
  quantityInput.value = Math.max(1, parseInt(quantityInput.value, 10) - 1);
  updateQuantity();
});

plusButton.addEventListener("click", () => {
  quantityInput.value = parseInt(quantityInput.value, 10) + 1;
  updateQuantity();
});

quantityInput.addEventListener("input", () => {
  if (quantityInput.value < 1) quantityInput.value = 1;
  updateQuantity();
});

// 초기 상태 업데이트
updateQuantity();

// 장바구니 열기/닫기
cartButton.addEventListener("click", () => {
  cartPanel.classList.add("open");
  cartQuantity.textContent = quantityInput.value;
});

closePanel.addEventListener("click", () => {
  cartPanel.classList.remove("open");
});
updateQuantity();


const textWrapper = document.querySelector(".product-story-text-wrapper");
const readMoreButton = document.querySelector(".readmore");
const readLessButton = document.querySelector(".readless");

const detailBtn = document.querySelector(".detail-btn");
console.log(detailBtn.dataset.val);
detailBtn.addEventListener("click", ()=>{
  const val = detailBtn.dataset.val;
  if (val == 0) {
    textWrapper.style.maxHeight = '1100px';
    textWrapper.style.height = '950px';
    detailBtn.dataset.val = 1;
    detailBtn.innerText = "간략히 보기";
  }else{
    textWrapper.style.maxHeight = '510px';
    detailBtn.dataset.val = 0;
    detailBtn.innerText = "자세히 보기";

  }
});