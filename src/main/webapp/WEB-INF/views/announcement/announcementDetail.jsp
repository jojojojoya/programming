<%@ page import="com.koyoi.main.vo.AdminMypageVO" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="/static/css/announcement/announcementDetail.css">

<div class="announcement-view">
    <div class="title">${announcement.title}</div>
    <div class="meta">
        작성자: ${announcement.admin_id} | 작성일: ${announcement.formattedCreatedAt}
    </div>
    <div class="content">${announcement.content}</div>

    <a href="/announcement/list" class="btn-back">← 목록으로</a>
</div>


