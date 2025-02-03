package com.KHH.userscrappage;

public class ScrapDTO {
    private int scrap_no;
    private String scrap_email;
    private int scrap_shop;
    private String scrap_name;
    private String shop_name;
    private int shop_no;
    private String scrap_date;
    private String shop_image;
    private String shop_addr;
    private String shop_tel;
    private String shop_content;

    public ScrapDTO() {

    }

    public int getScrap_no() {
        return scrap_no;
    }

    public void setScrap_no(int scrap_no) {
        this.scrap_no = scrap_no;
    }

    public String getScrap_email() {
        return scrap_email;
    }

    public void setScrap_email(String scrap_email) {
        this.scrap_email = scrap_email;
    }

    public int getScrap_shop() {
        return scrap_shop;
    }

    public void setScrap_shop(int scrap_shop) {
        this.scrap_shop = scrap_shop;
    }

    public String getScrap_name() {
        return scrap_name;
    }

    public void setScrap_name(String scrap_name) {
        this.scrap_name = scrap_name;
    }

    public String getScrap_date() {
        return scrap_date;
    }

    public void setScrap_date(String scrap_date) {
        this.scrap_date = scrap_date;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getShop_no() {
        return shop_no;
    }

    public void setShop_no(int shop_no) {
        this.shop_no = shop_no;
    }

    public String getShop_image() {
        return shop_image;
    }

    public void setShop_image(String shop_image) {
        this.shop_image = shop_image;
    }

    public String getShop_addr() {
        return shop_addr;
    }

    public void setShop_addr(String shop_addr) {
        this.shop_addr = shop_addr;
    }

    public String getShop_tel() {
        return shop_tel;
    }

    public void setShop_tel(String shop_tel) {
        this.shop_tel = shop_tel;
    }

    public String getShop_content() {
        return shop_content;
    }

    public void setShop_content(String shop_content) {
        this.shop_content = shop_content;
    }

    @Override
    public String toString() {
        return "ScrapDTO{" +
                "scrap_no=" + scrap_no +
                ", scrap_email='" + scrap_email + '\'' +
                ", scrap_shop=" + scrap_shop +
                ", scrap_name='" + scrap_name + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_no=" + shop_no +
                ", scrap_date='" + scrap_date + '\'' +
                ", shop_image='" + shop_image + '\'' +
                ", shop_addr='" + shop_addr + '\'' +
                ", shop_tel='" + shop_tel + '\'' +
                ", shop_content='" + shop_content + '\'' +
           '}';
    }
}


