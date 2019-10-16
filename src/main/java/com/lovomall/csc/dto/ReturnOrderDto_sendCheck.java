package com.lovomall.csc.dto;

import lombok.Data;

@Data
public class ReturnOrderDto_sendCheck {
    private String orderCode;

    private String orderStatus;

    private String applyCause;

    private String productName;

    private double totalPrice;

    private String returnOrderInfoId;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getApplyCause() {
        return applyCause;
    }

    public void setApplyCause(String applyCause) {
        this.applyCause = applyCause;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReturnOrderInfoId() {
        return returnOrderInfoId;
    }

    public void setReturnOrderInfoId(String returnOrderInfoId) {
        this.returnOrderInfoId = returnOrderInfoId;
    }
}
