<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    제목 : <input type = "text" name="f_title">
    제목 : <input type = "file" name="f_file">
    <button>업로드</button>
</form>


<hr>
<%--다중파일 업로드 --%>

<form action="upload2" method="post" enctype="multipart/form-data">
    제목 : <input type = "file" name="files" multiple>
    <button> 다중 업로드 </button>
</form>
</body>
</html>