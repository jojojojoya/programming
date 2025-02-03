package com.KHH.userreservationpage;

public class MyPageReservationDTO {
    private String reservation_email;
    private String reservation_date;
    private int reservation_people;
    private String reservation_name;
    private String reservation_tel;
    private String shop_name;
    private String shop_tel;
    private String shop_addr;
    private String shop_image;

    //  영업시간, 예약자이름, 예약자 전화번호

    // 이걸로 dao에서 이미지 불러냄
    public MyPageReservationDTO() {

    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    public int getReservation_people() {
        return reservation_people;
    }

    public void setReservation_people(int reservation_people) {
        this.reservation_people = reservation_people;
    }

    public String getReservation_name() {
        return reservation_name;
    }

    public void setReservation_name(String reservation_name) {
        this.reservation_name = reservation_name;
    }

    public String getReservation_tel() {
        return reservation_tel;
    }

    public void setReservation_tel(String reservation_tel) {
        this.reservation_tel = reservation_tel;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
    public String getShop_tel() {
        return shop_tel;
}
public void setShop_tel(String shop_tel) {
        this.shop_tel = shop_tel;
}
public String getShop_addr() {
        return shop_addr;
}

public void setShop_addr(String shop_addr) {
        this.shop_addr = shop_addr;
}
public String getShop_image() {
        return shop_image;
}
public void setShop_image(String shop_image) {
        this.shop_image = shop_image;
}
 public String getReservation_email() {
        return reservation_email;
 }
 public void setReservation_email(String reservation_email) {
        this.reservation_email = reservation_email;
 }

    @Override
    public String toString() {
        return "MyPageReservationDTO{" +
                "reservation_email='" + reservation_email + '\'' +
                ", reservation_date='" + reservation_date + '\'' +
                ", reservation_people=" + reservation_people +
                ", reservation_name='" + reservation_name + '\'' +
                ", reservation_tel='" + reservation_tel + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_tel='" + shop_tel + '\'' +
                ", shop_addr='" + shop_addr + '\'' +
                ", shop_image='" + shop_image + '\'' +
                '}';
    }
}
