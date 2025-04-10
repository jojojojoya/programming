package com.KHH.ShopInfo;

public class MenuDTO {
    private int menu_no;
    private String menu_shop;
    private String menu_type;
    private String menu_name;
    private String menu_price;

    public MenuDTO() {

    }
    public MenuDTO(int menu_no, String menu_shop, String menu_type, String menu_name,String menu_price) {
        this.menu_no = menu_no;
        this.menu_shop = menu_shop;
        this.menu_type = menu_type;
        this.menu_name = menu_name;
        this.menu_price = menu_price;

    }
    public int getMenu_no() {
        return menu_no;
    }
    public void setMenu_no(int menu_no) {
        this.menu_no = menu_no;
    }
    public String getMenu_shop() {
        return menu_shop;
    }
    public void setMenu_shop(String menu_shop) {
        this.menu_shop = menu_shop;
    }
    public String getMenu_type() {
        return menu_type;
    }
    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }
    public String getMenu_name() {
        return menu_name;
    }
    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
    public String getMenu_price() {
        return menu_price;
    }
    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }


}
