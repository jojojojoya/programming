package com.KHH.owner_myPage;

public class Owner_DTO {
    private String email;
    private String nickname;
    private String message;
    private String picture;

    Owner_DTO() {
        super();
    }
    Owner_DTO(String email, String nickname, String message, String picture) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

}
