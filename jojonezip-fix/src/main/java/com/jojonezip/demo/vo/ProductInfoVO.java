package com.jojonezip.demo.vo;

import java.sql.Timestamp;

public class ProductInfoVO {
    private int info_id;
    private int product_id;
    private String info_content;
    private Timestamp info_date;

    // Getters and Setters
    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getInfo_content() {
        return info_content;
    }

    public void setInfo_content(String info_content) {
        this.info_content = info_content;
    }

    public Timestamp getInfo_date() {
        return info_date;
    }

    public void setInfo_date(Timestamp info_date) {
        this.info_date = info_date;
    }
}
