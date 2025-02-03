package com.KHH.signUp;

import com.google.gson.Gson;

public class UserDTO {
    private String user_email;
    private String user_pw;
    private String user_name;
    private String user_birth;
    private String user_addr;
    private String user_tel;
    private String user_gen;
    private String user_nickname;
    private String user_grade;
    private String user_picture;

    public UserDTO() {
    }

    public UserDTO(String user_email, String user_pw, String user_name, String user_birth, String user_addr, String user_tel, String user_gen, String user_nickname, String user_grade, String user_picture) {
        super();
        this.user_email = user_email;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_birth = user_birth;
        this.user_addr = user_addr;
        this.user_tel = user_tel;
        this.user_gen = user_gen;
        this.user_nickname = user_nickname;
        this.user_grade = user_grade;
        this.user_picture = user_picture;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_addr() {
        return user_addr;
    }

    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_gen() {
        return user_gen;
    }

    public void setUser_gen(String user_gen) {
        this.user_gen = user_gen;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_grade() {
        return user_grade;
    }

    public void setUser_grade(String user_grade) {
        this.user_grade = user_grade;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_email='" + user_email + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_birth='" + user_birth + '\'' +
                ", user_addr='" + user_addr + '\'' +
                ", user_tel='" + user_tel + '\'' +
                ", user_gen='" + user_gen + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_grade='" + user_grade + '\'' +
                ", user_picture='" + user_picture + '\'' +
                '}';
    }

    public String toJSON() {

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
