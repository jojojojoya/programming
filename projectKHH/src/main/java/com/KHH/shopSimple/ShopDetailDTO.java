package com.KHH.shopSimple;

import com.google.gson.Gson;

public class ShopDetailDTO {
    private int shop_no;
    private String shop_owner;
    private String shop_name;
    private String shop_addr;
    private String shop_addrtype;
    private String shop_tel;
    private String shop_content;
    private String shop_opentime;
    private String shop_img;

    public ShopDetailDTO() {
    }

    public ShopDetailDTO(int shop_no, String shop_owner, String shop_name, String shop_addr, String shop_addrtype, String shop_tel, String shop_content, String shop_opentime, String shop_img) {
        super();
        this.shop_no = shop_no;
        this.shop_owner = shop_owner;
        this.shop_name = shop_name;
        this.shop_addr = shop_addr;
        this.shop_addrtype = shop_addrtype;
        this.shop_tel = shop_tel;
        this.shop_content = shop_content;
        this.shop_opentime = shop_opentime;
        this.shop_img = shop_img;
    }

    public int getShop_no() {
        return shop_no;
    }

    public void setShop_no(int shop_no) {
        this.shop_no = shop_no;
    }

    public String getShop_owner() {
        return shop_owner;
    }

    public void setShop_owner(String shop_owner) {
        this.shop_owner = shop_owner;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_addr() {
        return shop_addr;
    }

    public void setShop_addr(String shop_addr) {
        this.shop_addr = shop_addr;
    }

    public String getShop_addrtype() {
        return shop_addrtype;
    }

    public void setShop_addrtype(String shop_addrtype) {
        this.shop_addrtype = shop_addrtype;
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

    public String getShop_opentime() {
        return shop_opentime;
    }

    public void setShop_opentime(String shop_opentime) {
        this.shop_opentime = shop_opentime;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    @Override
    public String toString() {
        return "ShopDTO{" +
                "shop_owner='" + shop_owner + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_addr='" + shop_addr + '\'' +
                ", shop_addrtype='" + shop_addrtype + '\'' +
                ", shop_tel='" + shop_tel + '\'' +
                ", shop_content='" + shop_content + '\'' +
                ", shop_opentime='" + shop_opentime + '\'' +
                '}';
    }

    public String toJSON() {

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
