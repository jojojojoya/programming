package com.KHH.ShopInfo;

import java.util.ArrayList;

public class ShopDTO {
    private int shop_no;
    private String shop_owner;
    private String shop_name;
    private String shop_addr;
    private String shop_tel;
    private String shop_content;
    private String shop_opentime;
    private String shop_addrtype;
    private String shop_image;
    private String main_image;
    private ArrayList<String> sub_image;




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

    public String getShop_addrtype() {
        return shop_addrtype;
    }

    public void setShop_addrtype(String shop_addrtype) {
        this.shop_addrtype = shop_addrtype;
    }

    public String getShop_image() {
        return shop_image;
    }

    public void setShop_image(String shop_image) {
        this.shop_image = shop_image;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public ArrayList<String> getSub_image() {
        return sub_image;
    }

    public void setSub_image(ArrayList<String> sub_image) {
        this.sub_image = sub_image;
    }
}




