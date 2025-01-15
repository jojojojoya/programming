// JavaScript for 하트 버튼

// 모든 하트를 클릭 가능하게 설정
document.addEventListener("DOMContentLoaded", () => {
  const hearts = document.querySelectorAll(".heart");

  hearts.forEach((heart) => {
    heart.addEventListener("click", () => {
      heart.classList.toggle("selected");
    });
  });
});
const comparison = document.querySelector("#comparisonBar");
const comparisonList = document.querySelector(".comparison-list");
const chks = document.querySelectorAll("input[type='checkbox']");
console.log(chks);

chks.forEach((chk) => {
  chk.addEventListener("click", () => {
    const img = chk.parentElement.previousElementSibling.previousElementSibling;
    console.log(img);
    comparison.style.display = "flex";
    comparisonList.appendChild(img.cloneNode(true));
  });
});
