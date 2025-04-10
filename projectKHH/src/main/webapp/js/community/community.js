function goToSearchCommunity(){
    let search_value = document.getElementById("search_value").value;
    let search_item = document.getElementById("search_item").value;
    if(search_value == "" || search_value == null || search_item == "all"){
        location.href="CommunityListController";
    }else{
        location.href="CommunitySearchController?item="+search_item+"&value="+search_value;
    }
}
function goToCommunityList(){
    let getOutOfUploadPage = confirm('작성중인 글이 있습니다. 목록으로 돌아갈까요?');
    if(getOutOfUploadPage){
        location.href="CommunityListController";
    }else{
        document.preventDefault();
    }
}
/*
function showFileList() {
    //event.preventDefault(); // 기본 폼 제출 동작 방지
    const input = document.getElementById("file");
    const fileList = input.files;
    
    const formData = new FormData(document.getElementById("communityUploadForm"));

    fetch("CommunityUploadController", {
        method: "POST",
        body: formData,
    })
        .then(response => response.text())
        .then(data => {
            console.log("서버 응답:", data);
            alert("파일 및 데이터 전송 성공!");
            if (data.includes("<title>Redirecting")) {
                window.location.href = "CommunityListController";
            }
        })
        .catch(error => {
            console.error("파일 전송 실패:", error);
        });
}*/
