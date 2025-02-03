package com.KHH.signUp;

import com.google.gson.Gson;

public class OwnerDTO {
    private String owner_email;
    private String owner_pw;
    private String owner_name;
    private String owner_birth;
    private String owner_tel;
    private String owner_nickname;

    public OwnerDTO() {
    }

    public OwnerDTO(String owner_email, String owner_pw, String owner_name, String owner_birth, String owner_tel, String owner_nickname) {
        this.owner_email = owner_email;
        this.owner_pw = owner_pw;
        this.owner_name = owner_name;
        this.owner_birth = owner_birth;
        this.owner_tel = owner_tel;
        this.owner_nickname = owner_nickname;
    }

    public String getOwner_email() {
        return owner_email;
    }

    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }

    public String getOwner_pw() {
        return owner_pw;
    }

    public void setOwner_pw(String owner_pw) {
        this.owner_pw = owner_pw;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_birth() {
        return owner_birth;
    }

    public void setOwner_birth(String owner_birth) {
        this.owner_birth = owner_birth;
    }

    public String getOwner_nickname() {
        return owner_nickname;
    }

    public void setOwner_nickname(String owner_nickname) {
        this.owner_nickname = owner_nickname;
    }

    public String getOwner_tel() {
        return owner_tel;
    }

    public void setOwner_tel(String owner_tel) {
        this.owner_tel = owner_tel;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "owner_email='" + owner_email + '\'' +
                ", owner_pw='" + owner_pw + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", owner_birth='" + owner_birth + '\'' +
                ", owner_nickname='" + owner_nickname + '\'' +
                ", owner_tel='" + owner_tel + '\'' +
                '}';
    }

    public String toJSON() {

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
