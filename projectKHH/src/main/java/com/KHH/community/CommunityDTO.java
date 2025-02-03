package com.KHH.community;

import java.util.Date;

public class CommunityDTO {
    private int no;
    private String title;
    private String content;
    private String nickname;
    private Date date;
    private int readcnt;

    public CommunityDTO() {
        // TODO Auto-generated constructor stub
    }

    public CommunityDTO(int no, String title, String content, String nickname, Date date, int readcnt) {
        super();
        this.no = no;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.date = date;
        this.readcnt = readcnt;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getReadcnt() {
        return readcnt;
    }

    public void setReadcnt(int readcnt) {
        this.readcnt = readcnt;
    }

}
