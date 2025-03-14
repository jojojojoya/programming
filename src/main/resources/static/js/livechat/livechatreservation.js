function showNext(nextStep) {
    let nextElement = document.getElementById(nextStep);
    // 라이브챗 예약 넥스트 버튼 구현

    if (nextElement) {
        //classList > 기존의 클래스 목록에 새로운 목록 show를 추가
        nextElement.classList.add("show");
    } else {
        // 다음 요소가 없으면 next step이 없다를 출력
        console.error("Error: Element with ID '" + nextStep + "' not found.");
    }
}


document.getElementById("livechat_reserve_btn").addEventListener("click", function () {
//reserve_btn을 클릭 시 데이트는 데이트 id를 가진 엘리먼트의 내용을 출력

    let livechatreservedate = document.getElementById("livechat_reserve_date").value;
//reserve_btn을 클릭 시 타임은 타임 id를 가진 엘리먼트의 내용을 출력

    let livechatreservetime = document.getElementById("livechat_reserve_time").value;
//reserve_btn을 클릭 시 카테고리는 해당 카테고리를 가진 엘리먼트의 내용을 출력

    let livechatcategory = document.getElementById("livechat_reserve_category").value;


    if (!livechatreservedate || !livechatreservetime || !livechatcategory) {
        // 라이브챗 데이트, 시간, 카테고리가 다 선택되지 않았으면
        alert("모든 항목을 선택해주세요!");
        return;
    }

    fetch("/livechatreservation", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ livechatreservedate, livechatreservetime, livechatcategory })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById("reserveSection").classList.remove("show");
                document.getElementById("conformation").classList.add("show");
            } else {
                alert("예약에 실패했습니다.");
            }
        })
        .catch(error => console.error("Error:", error));
});

document.getElementById("livechat_exit_btn").
addEventListener("click", function (){
    console.log("usermypage 이동 버튼 클릭됨");
    window.location.href = "/usermypage";

});
