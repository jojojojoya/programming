package com.jojonezip.demo.vo;

import java.sql.Date;

public class QnaVO {
    private int qna_id;
    private String user_id;
    private String qna_title;
    private String qna_text;
    private String qna_answer; // 🆕 관리자 답변
    private Date qna_date;
    private int product_id;
    private String product_name;
    private String product_image;


    public String getQna_answer() {
        return qna_answer;
    }

    public void setQna_answer(String qna_answer) {
        this.qna_answer = qna_answer;
    }
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    // Getters and Setters
    public int getQna_id() {
        return qna_id;
    }

    public void setQna_id(int qna_id) {
        this.qna_id = qna_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQna_title() {
        return qna_title;
    }

    public void setQna_title(String qna_title) {
        this.qna_title = qna_title;
    }

    public String getQna_text() {
        return qna_text;
    }

    public void setQna_text(String qna_text) {
        this.qna_text = qna_text;
    }

    public Date getQna_date() {
        return qna_date;
    }

    public void setQna_date(Date qna_date) {
        this.qna_date = qna_date;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}