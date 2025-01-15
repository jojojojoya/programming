
// 문제가 있으면 true, 문제가 없으면 false

// <input>을 넣으면...
// 거기게 글자가 없으면 true, 있으면 false
function isEmpty(input){
    return !input.value;

    /*if(input.value == ""){
        return true;
    } else {
        return false;
    }
    */
}

// <input>과 글자 수를 넣으면 
// 그 글자 수 보다 적으면 true, 아니면 false
function lessThan(input, len) {
    return input.value.length < len;

}

// Test
// <input> x 2 넣으면... (비번확인용)
// 내용이 다르면 true, 아니면 false
function notEquals(input1, input2) {
    return input1.value != input2.value;
}


// <input>을 넣어서 
// 값이 숫자가 아니면 true, 숫자면 false
function isNotNumber(input) {
    return isNaN(input.value);
}
// <input>을 넣으면.. 
// 한글 / 특수문자 들어있으면 true , 아니면 false
// id, pw, email @._

function containKR(input){
	let ok ="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_@.";
	
	// 진현 
	// mz세상
	// jh1004

	
	for(let i = 0; i<input.value.length; i++) {
		if(ok.indexOf(input.value[i]) == -1 ) {
			return true;
		}
	}
	
}

// <input>, 문자열 세트 넣으면 .. 
// 그 문자열 세트가 포함이 안되어 있으면 true 
// 들어있으면 false
// 비밀번호 조합 대,소,숫

function notContains(input, set) {
	//input : ASD
	//input : 1qASD
	// set : 1234567890 // 반드시 숫자를 포함
	// set : QWERTYUIOP // 반드시 대문자를 포함
	
	for(let i = 0; i < set.length; i++) {
		if(input.value.indexOf(set[i]) != -1) {
			return false;
		}
	}
	return true;
}

// <input>, 확장자를 넣으면
// 그 파일이 아니면 true, 맞으면 false
//jpg, jpeg, png, gif

// asd.jpg 
// aaa.mp4

//"jpg" 

function isNotType(input, type){
	type = "." + type;	
	return input.value.indexOf(type) == -1;
	
}

