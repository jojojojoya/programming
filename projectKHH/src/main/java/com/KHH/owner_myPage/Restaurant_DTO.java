package com.KHH.owner_myPage;

public class Restaurant_DTO {
    private int id;
    private String owner;
    private String name;
    private String address;
    private String opentime;
    private String phone;
    private String explain;
    private String image;

    public Restaurant_DTO() {
        super();
    }

    Restaurant_DTO(int id, String owner, String name, String address, String opentime, String phone, String explain, String image) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.opentime = opentime;
        this.phone = phone;
        this.explain = explain;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
