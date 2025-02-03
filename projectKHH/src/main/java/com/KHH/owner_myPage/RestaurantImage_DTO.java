package com.KHH.owner_myPage;

public class RestaurantImage_DTO {
    private int no;
    private String image;
    private String type;

    RestaurantImage_DTO(){

    }
    RestaurantImage_DTO(int no, String image, String type){
        this.no = no;
        this.image = image;
        this.type = type;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
