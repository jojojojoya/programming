package com.KHH.userreservationpage;

public class ReservationDTO {
    private int reservation_no;
    private String reservation_email;
    private String reservation_name;

    private int reservation_shop;
    private String reservation_date;
    private int reservation_people;
    private String reservation_address;


    public ReservationDTO() {

    }

    public String getReservation_email() {
        return reservation_email;
    }
    public void setReservation_email(String reservation_email) {
        this.reservation_email = reservation_email;
    }
    public int getReservation_no() {
        return reservation_no;
    }
    public void setReservation_no(int reservation_no) {
        this.reservation_no = reservation_no;
    }

    public int getReservation_shop() {
        return reservation_shop;
    }

    public void setReservation_shop(int reservation_shop) {
        this.reservation_shop = reservation_shop;
    }

    public String getReservation_name() {
        return reservation_name;
    }

    public void setReservation_name(String reservation_name) {
        this.reservation_name = reservation_name;
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


}
