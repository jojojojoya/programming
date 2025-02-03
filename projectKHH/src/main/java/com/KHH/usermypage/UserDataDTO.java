package com.KHH.usermypage;

public class UserDataDTO {

    private String user_email;
    private String user_nickname;
    private String user_grade;
    private String user_picture;

    public UserDataDTO() {
        //기본 생성자
    }

    public UserDataDTO(String user_email, String user_nickname, String user_grade, String user_picture) {
        this.user_email = user_email;
        this.user_nickname = user_nickname;
        this.user_grade = user_grade;
        this.user_picture = user_picture;
    }

    //get,set
    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
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

}
