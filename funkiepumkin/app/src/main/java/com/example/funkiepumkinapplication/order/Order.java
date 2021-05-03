package com.example.funkiepumkinapplication.order;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderId;
    private Integer memberId;
    private String status;
    private String orderAddr;
    private String orderPhone;
    private Integer totalOrderAmount;
    private String orderDate;

    public Order(String orderId, Integer memberId, String status, String orderAddr, String orderPhone, Integer totalOrderAmount, String orderDate) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.status = status;
        this.orderAddr = orderAddr;
        this.orderPhone = orderPhone;
        this.totalOrderAmount = totalOrderAmount;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderAddr() {
        return orderAddr;
    }

    public void setOrderAddr(String orderAddr) {
        this.orderAddr = orderAddr;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public Integer getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(Integer totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
