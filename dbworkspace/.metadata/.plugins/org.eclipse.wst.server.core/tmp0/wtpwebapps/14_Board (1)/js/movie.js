function deleteMovie(no){
	let ok = confirm('정말로 삭제하시겠습니까?');
	if(ok){
		location.href='MovieDeleteController?no='+no;
	}
}

function updateMovie(no,title,actor){
	let u_title = prompt('title : ',title);
	if(!u_title == null || !u_title == ""){
		let u_actor = prompt('actor : ',actor);
		if(!u_actor == null || !u_actor == ""){
			location.href = 'MovieUpdateController?no='+no+'&title='+u_title+'&actor='+u_actor;
		}		
	}
}