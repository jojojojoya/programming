function call() {

	const i1El = document.querySelector("input[name='i1']");
	const i2El = document.querySelector("input[name='i2']");
	const i3El = document.querySelector("input[name='i3']");
	const i4El = document.querySelector("input[name='i4']");
	const i5El = document.querySelector("input[name='i5']");
	const i6El = document.querySelector("input[name='i6']");


	// id는 #으로 찾긔
	if (isEmpty(i1El) || lessThan(i1El, 3) || containKR(i1El)) {
		alert('필수 입력창 에러!')
		i1El.focus();
		return;
	}
	/*	if(lessThan(i1El,5)){
			alert('5글자 이상 입력!')
			i1El.focus();
			return;
		}
	*/

	if (lessThan(i2El, 3)) {
		alert('글자 수 에러!')
		i2El.focus();
		return;
	}


	if(lessThan(i3El,5) || notContains(i3El,"1234567890")
	|| notContains(i3El,"QWERTTYUIOPASDFGHJKLZXCVBNM"))	
	{	alert('비번 오류!');
	i3El.focus();
	i3El.value = "";
	return;
}

	if(notEquals(i3El,i4El)) {
	alert('비밀번호가 같지 않습니다');
	return;
	}



	if (isNaN(i5El.value) || isEmpty(i5El)) {
		alert('숫자만 입력 가능!')
				i5El.focus();
		return;
	}



//	if(notContains(i3El,"1234567890") || notContains(i3El,""))
	
	
	
	//jpg, png
	
	if(isNotType(i6El,"jpg") && isNotType(i6El,"png") ){
		alert('파일 오류!')
		return;
	}

	document.querySelector("#myForm").submit();



}
