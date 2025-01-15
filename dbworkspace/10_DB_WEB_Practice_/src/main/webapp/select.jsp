<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  /* General styles for the container */
    .container {
        margin: 20px;
        font-family: Arial, sans-serif;
    }

    /* Flexbox layout for rows */
    .row {
        display: flex;
        justify-content: space-between;
        padding: 10px;
        border-bottom: 1px solid #ddd; /* Adds a light border between rows */
    }

    /* Styling for header row */
    .row:nth-child(2) {
        font-weight: bold;
        background-color: #f4f4f4; /* Light background for header */
    }

    /* Styling for each column */
    .row div {
        flex: 1; /* Makes each column equally spaced */
        text-align: left; /* Aligns text to the left */
        padding: 5px;
    }
    .row:hover {
    	background: tomato;
    }

    /* Add some spacing around the container */
    .container {
        max-width: 800px;
        margin: 0 auto;
    }
</style>
<script type="text/javascript">
function deleteRes(nn) {
	let ok = confirm('really?')
	if (ok) {
		location.href='DelResC?num='+nn;
	}
}

function updateRes(col, val, pk) {
	let value = "";
	if (col == 'r_name') {
	value = prompt('수정할 가게명?', val);
		
	}
	else {
		value = prompt('수정할 메뉴명?', val);
		}
	
	if (value != null && value !="") {
		
	location.href='UpdateResC?col='+col+'&val=' + value + '&no=' + pk;
	}
}


</script>
</head>
<body>

	<div class="container">
		<div style="text-align: right;"> <a href="InsertResC"> <button>Add</button> </a></div>
		<div class="row">
			<div>No</div>
			<div>Name</div>
			<div>Menu</div>
			<div>Place</div>
			<div></div>
		</div>
		<c:forEach var="r" items="${restaurants }">
			<div class="row">
				<div>${r.no }</div>
				<div> <span onclick="updateRes('r_name','${r.name }','${r.no}')">${r.name }</span></div>
				<div onclick="updateRes('r_menu','${r.menu }','${r.no}')">${r.menu }</div>
				<div>${r.place }</div>
				<div> <button onclick="deleteRes('${r.no}')">delete</button>
				 	<button onclick="updateRes('${r.name }','${r.no}')">update</button>
				</div>
			</div>
		</c:forEach>
	</div>





</body>
</html>