function deleteMovie(noo) {

	let ok = confirm("삭제하시겠습니까?");

	if(ok) {
		location.href='MovieDeleteController?no=' + noo;	
	}
}

function updateMovie(noo,title,actor) {
	let title1 = prompt('title :',title);
	if(title1 == null || title1 == "") {
	title1 = title;
	}

	let actor1 = prompt('actor :',actor);
	if(actor1 == null || actor1 == "") {
	actor1 = actor;
	}
		location.href='MovieUpdateC?no=' + noo + '&title=' + title1 + '&actor=' + actor1;
	}
	
