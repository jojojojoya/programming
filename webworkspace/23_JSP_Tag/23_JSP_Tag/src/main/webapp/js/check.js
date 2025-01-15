


function check(){
	
const nameEl = document.querySelector('input[name="name"]');
const heightEl = document.querySelector('input[name="height"]');
const weightEl = document.querySelector('input[name="weight"]');
const fileEl = document.querySelector('input[name="imgFile"]');
	
	if(isEmpty(nameEl) || lessThan(nameEl, 2)){
		alert('이름 에러!');
		nameEl.focus();
		return;
	}
	
	if(isEmpty(heightEl) || lessThan(heightEl, 3) || isNaN(heightEl.value)){
		alert('키 에러!');
		heightEl.focus();
		heightEl.value="";
		return;
		
	}
	
	
	if(isEmpty(weightEl) || isNaN(weightEl.value)){
		alert('체중 에러!');
		weightEl.focus();
		weightEl.value="";
		return;
	}
	
	if(isNotType(fileEl,"jpg") && isNotType(fileEl,"png")){
		alert('jpg, png 만 첨부 가능합니다');
		fileEl.value="";
		return;
	}
	
	
	document.querySelector("#myform").submit();
	
}



function selectTag(){
	
}
