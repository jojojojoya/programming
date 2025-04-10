package com.KHH.shopSimple;

import com.google.gson.Gson;

public class ShopSimpleDTO {
    private int shop_no;
    private String shop_name;
    private String shop_addrtype;
    private String shop_tel;
    private String shop_opentime;

    public ShopSimpleDTO() {
    }

    public ShopSimpleDTO(int shop_no, String shop_name, String shop_addrtype, String shop_tel, String shop_opentime) {
        this.shop_no = shop_no;
        this.shop_name = shop_name;
        this.shop_addrtype = shop_addrtype;
        this.shop_tel = shop_tel;
        this.shop_opentime = shop_opentime;
    }


    public int getShop_no() {
        return shop_no;
    }

    public void setShop_no(int shop_no) {
        this.shop_no = shop_no;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
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

    public String getShop_opentime() {
        return shop_opentime;
    }

    public void setShop_opentime(String shop_opentime) {
        this.shop_opentime = shop_opentime;
    }

    @Override
    public String toString() {
        return "ShopSimpleDTO{" +
                "shop_no=" + shop_no +
                ", shop_name='" + shop_name + '\'' +
                ", shop_addrtype='" + shop_addrtype + '\'' +
                ", shop_tel='" + shop_tel + '\'' +
                ", shop_opentime='" + shop_opentime + '\'' +
                '}';
    }

    public String toJSON() {

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
