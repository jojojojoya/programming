const spans = document.querySelectorAll(".sort");
console.log(spans);

spans.forEach((span) => {
    span.addEventListener("click", (e)=>{
        const clickedSpan = e.target;

        spans.forEach((span) => span.classList.remove("active"));


        clickedSpan.classList.add("active");
    })
});


document.querySelector("#more-btn").addEventListener("click", ()=>{
    const itemWrap = document.querySelector(".item-wrap");
    const item1 = itemWrap.lastElementChild.cloneNode(true);
    const item2 = itemWrap.lastElementChild.previousElementSibling.cloneNode(true);
    const item3 = itemWrap.lastElementChild.previousElementSibling.previousElementSibling.cloneNode(true);

    itemWrap.append(item2);
    itemWrap.append(item1);
    itemWrap.append(item3);
});