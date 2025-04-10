function deleteReview(no){
	let ok = confirm('삭제하시겠습니까?');
	if(ok){
		location.href='ReviewDeleteController?no='+no;
	}
}

$(function(){
	
	searchReview();
	
	
});

function searchReview() {
	$("#search-btn").click(function(){
	const reviewTitle = $("#search-input").val();
	console.log(reviewTitle);
	$.ajax({
		url : 'ReviewSearchC',		// 어디로 요청?
		data : { reviewTitle}		//파라미터 
		
	}).done(function(resData) {
		$("#result").empty();
		console.log(resData)		
		console.log(resData.length);		
		console.log(JSON.stringify(resData))	
//		console.log(resData[0].r_title)	
	
		if(resData.length != 0){
			showResult(resData);
		}

		
	}).fail(function(xhr){
		console.log(xhr)
	});
	
})
}


function showResult(resData) {
	$.each(resData, function(i, r){
		console.log(i);	
		console.log(r);	
		
		let content = 
`	<div class="review-row">	
	<div>
	<span onclick="location.href='ReviewDetailController?no=${r.no}'">${r.title }</span>
	</div>
	<div>
		${r.date }
	</div>
	</div>`;
	
	$("#result").append(content);
	

	})
	
}