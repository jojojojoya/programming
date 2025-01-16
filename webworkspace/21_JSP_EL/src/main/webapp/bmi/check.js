




function check() {
	const nameEl = document.querySelector('input[name="name"]');
	const heightEl = document.querySelector('input[name="height"]');
	const weightEl = document.querySelector('input[name="weight"]');
	const fileEl = document.querySelector('input[name="imgFile"]');
	if (isEmpty(nameEl) || lessThan(nameEl, 2)) {
		alert('이름 에러!');
		nameEl.focus();
		return;

	}
	if (lessThan(heightEl, 3) || isNaN(heightEl.value)) {
		alert('키 에러!');
		heightEl.focus();
		heightEl.value = "";
		return;
	}


	if (isEmpty(weightEl) || isNaN(weightEl.value)) {
		alert('몸무게 에러!');
		weightEl.focus();
		weightEl.value = "";
		return;
	}

	if (isNotType(fileEl, "jpg") &&
		isNotType(fileEl, "png") &&
		isNotType(fileEl, "gif")
	) {
		alert('파일은 jpg, png, gif만 사용 가능합니다.');
		return;

	}




	document.querySelector("#myForm").submit();
}