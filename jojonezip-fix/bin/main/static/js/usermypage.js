document.getElementById("nickname-change-btn").addEventListener("click",function(){
	const newnickname = prompt("새 닉네임을 입력하세요.");
	if(newnickname === null){
		return;
	}
	
	if (confirm(`"${newnickname}"으로 변경하시겠습니까?`)) {
		
		fetch("/updateNickname", {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"},body: `nickname=${encodeURIComponent(newnickname)}`
				
		})
		.then(res => res.text())
		.then(msg => {
			alert(msg);
			location.reload();
		})
		.catch(err => {
			alert("닉네임 변경 중 오류가 발생했습니다.")
		})
	} 
	
	})

	document.addEventListener("DOMContentLoaded", function () {
	  const statuses = document.querySelectorAll(".order-status");

	  statuses.forEach(function (status) {
	    const text = status.textContent.trim();
	    if (text === "결제완료") {
	      status.style.color = "green";
	    } else if (text === "주문 취소요청") {
	      status.style.color = "red";
	    } else if (text === "주문 취소완료") {
	      status.style.color = "black";
	    } else if (text === "배송 처리완료") {
	      status.style.color = "green";
	    }
	  });
	});

