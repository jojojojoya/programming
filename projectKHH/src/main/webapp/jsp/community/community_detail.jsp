<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../../css/community/community_detail.css">
</head>
<body>
<h1>커뮤니티</h1>
<div class="community_title_div">
  <div class="community_title"><h2>${com.title }</h2></div>
  <div class="community_PNBtn">
    <button onclick="location.href='CommunityPNDetailController?no=${com.no}&page=prev'">이전 글</button>&nbsp;&nbsp;
    <button onclick="location.href='CommunityPNDetailController?no=${com.no}&page=next'">다음 글</button>
  </div>
</div>

<div class="community_profile_div">
  <div class="community_profile_image"><img alt="" src="image/user.png"></div>
  <div class="community_writer_div">
    <div class="community_writer">${com.nickname }</div>
    <div style="border:none;" class="community_dateNcnt">
      <div class="community_date">${com.date }</div>
      <div class="community_readcnt">조회수 ${com.readcnt }</div>
    </div>
  </div>
</div>

<hr>

<div class="community_content_div">
  <c:forEach var="images" items="${images }">
    <div class="community_content_image"><img alt="" src="../../image/community/${images.image}"></div>
  </c:forEach>
  <div class="community_content">${com.content }</div>
</div>

<hr>

<div class="community_comment_div">

  <form action="CommentUploadController" method="get">
    <input type="hidden" name="no" value="${com.no}">
    <input type="hidden" name="nickname" value="${com.nickname}">
    <div class="comment_upload_div">
      <div class="comment_upload_title">댓글쓰기</div>
      <div class="comment_txtBox"><input type="text" name="comment-content"></div>
      <div class="comment_uploadBtn"><button type="submit">등 록</button></div>
    </div>
  </form>

  <div class="comment_list_div">
    <c:forEach var="comment" items="${comments}">
    <div class="comment_parent_div">
      <div class="comment_content_div">
        <div class="comment_nickname">${comment.content}</div>
        <div class="comment_date">${comment.date}</div>
        <div class="comment_content">${comment.nickname}</div>
      </div>
      <div class="comment_btn_div_other">
        <button>답글달기</button>
      </div>
      <div class="comment_btn_div_writer">
        <button>수정하기</button>
        <button>삭제하기</button>
      </div>
    </div>
      </c:forEach>

    <%--<div class="comment_parent_div">
      <div class="comment_content_div">
        <div class="comment_nickname">지워니</div>
        <div class="comment_date">2025-01-23</div>
        <div class="comment_content">넘넘 맛나보여용~~</div>
      </div>
      <div class="comment_btn_div_other">
        <button>답글달기</button>
      </div>
      <div class="comment_btn_div_writer">
        <button>수정하기</button>
        <button>삭제하기</button>
      </div>
    </div>--%>
    <%--<div class="recomment_upload_div">
      <div class="comment_txtBox"><textarea></textarea></div>
      <div class="comment_uploadBtn"><button>등록</button></div>
    </div>
    <div class="comment_child_div">
      <div class="comment_content_div">
        <div class="comment_nickname">수지니</div>
        <div class="comment_date">2025-01-24</div>
        <div class="comment_content">저도 다음에 꼭 가봐야겟네요~~^^</div>
      </div>
      <div class="comment_btn_div_other">
        <button>답글달기</button>
      </div>
      <div class="comment_btn_div_writer">
        <button>수정하기</button>
        <button>삭제하기</button>
      </div>
    </div>--%>
  </div>
  <div class="community_mainBtn_div">
    <button onclick="location.href='CommunityListController'">목록으로</button>
  </div>
</div>
</body>
</html>
