package com.KHH.community;

import java.util.Date;

public class CommentDTO {
    private int parentNo;
    private int childNo;
    private int communityNo;
    private String content;
    private String nickname;
    private Date date;

    CommentDTO(){

    }

    CommentDTO(int parentNo, int childNo, int communityNo, String content, String nickname, Date date) {
        this.parentNo = parentNo;
        this.childNo = childNo;
        this.communityNo = communityNo;
        this.content = content;
        this.nickname = nickname;
        this.date = date;
    }

    public int getParentNo() {
        return parentNo;
    }
    public void setParentNo(int parentNo) {
        this.parentNo = parentNo;
    }
    public int getChildNo() {
        return childNo;
    }
    public void setChildNo(int childNo) {
        this.childNo = childNo;
    }
    public int getCommunityNo() {
        return communityNo;
    }
    public void setCommunityNo(int communityNo) {
        this.communityNo = communityNo;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
