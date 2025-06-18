package com.jojonezip.demo.vo;

import java.sql.Timestamp;

public class OrderVO {
    private int orderId;
    private String userId;
    private String orderName;
    private String orderTel;
    private String orderAddress;
    private Timestamp orderDate;
    private String orderStatus;

    public OrderVO() {}

    public OrderVO(int orderId, String userId, String orderName, String orderTel, String orderAddress, Timestamp orderDate, String orderStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderName = orderName;
        this.orderTel = orderTel;
        this.orderAddress = orderAddress;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    // Getters & Setters
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }
    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    public String getOrderAddress() {
        return orderAddress;
    }
    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
