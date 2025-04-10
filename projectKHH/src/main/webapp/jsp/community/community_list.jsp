<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/community/community_list.css">
    <script type="text/javascript" src="../../js/community/community.js"></script>
</head>
<body>
<h1>커뮤니티</h1>
<div class="title"></div>
<div class="search_div">

    <div class="count_item"> <%--10%--%>
        전체 ${count }개의 글
    </div>

    <div class="content_upload"> <%--50%--%>
        <button onclick="location.href='CommunityUploadController?nickname=고로고로상'">글 쓰기</button>
    </div>

    <div class="search_item"> <%--40%--%>
        <div class="search_select">
            <div class="select_title"><label>조건</label></div>
            <div class="select_select">
                <select id="search_item">
                    <option value="all">전체</option>
                    <option value="title">제목</option>
                    <option value="writer">작성자</option>
                </select>
            </div>
        </div>
        <div><input id="search_value" type="text"></div>
        <div><button onclick="goToSearchCommunity()">검색</button></div>
    </div>

</div>
<div class="board_div">
    <div class="board_title">
        <div class="title_title">제목</div>
        <div class="title_nickname">작성자</div>
        <div class="title_date">작성일</div>
        <div class="title_readcnt">조회</div>
    </div>
    <c:forEach var="coms" items="${coms }">
        <div class="board_data">
            <div class="data_title"><a href="/CommunityDetailController?no=${coms.no}">${coms.title}</a></div>
            <div class="data_nickname">${coms.nickname }</div>
            <div class="data_date">${coms.date }</div>
            <div class="data_readcnt">${coms.readcnt }</div>
        </div>
    </c:forEach>
</div>
<%--<div class="page">
    <div class="page_div">
        <a>Prev</a>
        <c:forEach begin="1" end="${pageCount }" var="i">

        </c:forEach>
        <a>Next</a>
    </div>
</div>--%>
</body>
</html>
