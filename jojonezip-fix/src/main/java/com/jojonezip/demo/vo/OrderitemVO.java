package com.jojonezip.demo.vo;

public class OrderitemVO {
    private int orderItemId;
    private int orderId;       // FK
    private int productId;
    private int productAmount;
    private int productPrice;

    public OrderitemVO() {}

    public OrderitemVO(int orderItemId, int orderId, int productId, int productAmount, int productPrice) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
    }

    // Getters & Setters
    public int getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductAmount() {
        return productAmount;
    }
    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public int getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
