package com.KHH.community;

public class CommunityImageDTO {
    private int no;
    private String image;

    CommunityImageDTO(){

    }

    CommunityImageDTO(int no, String image) {
        this.no = no;
        this.image = image;
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
}
