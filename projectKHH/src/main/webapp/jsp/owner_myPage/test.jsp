<%--
  Created by IntelliJ IDEA.
  User: dutch
  Date: 1/21/2025
  Time: 12:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="communityUploadForm" enctype="multipart/form-data">
    <div class="community_writer_div">
        <div class="title_writer">작성자</div>
        <div class="title_writer_nickname"><span>${param.nickname}</span></div>
        <input type="hidden" value="${param.nickname}" name="writer">
    </div>

    <div class="community_title_div">
        <div class="title_title">제목</div>
        <div class="title_title_input"><input type="text" name="title" placeholder="제목을 입력하세요"></div>
    </div>

    <div class="community_content_div">
        <div class="content_title">내용</div>
        <div class="content_textarea"><textarea name="content" placeholder="내용을 입력하세요"></textarea></div>
    </div>

    <div class="community_image_div">
        <div class="image_title">이미지</div>
        <div class="image_input">
            <label for="file">
                <div class="btn-upload">이미지 업로드하기</div>
            </label>
            <input type="file" name="file" id="file" multiple accept=".jpg,.jpeg,.png">
        </div>
    </div>

    <div class="community_uploadBtn_div">
        <button onclick="showFileList()">등록하기</button>
        <button type="button" onclick="goToCommunityList()">목록으로</button>
    </div>
    </div>
</form>
</body>
</html>
