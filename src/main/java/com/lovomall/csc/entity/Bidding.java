package com.lovomall.csc.entity;

import javax.persistence.Column;

public class Bidding {
    @Column(length = 48)
    private String bidNumber=System.currentTimeMillis()+"";
    //商品编号
    @Column(length = 48)
    private String productId;
    //商品名
    @Column(length = 48)
    private String productName;
    //采购数量
    private int productNum;
    //商品类型
    @Column(length = 48)
    private String productType;
    //商品规格
    @Column(length = 48)
    private String productSpec;

    private String supplierLogo;

    public String getSupplierLogo() {
        return supplierLogo;
    }

    public void setSupplierLogo(String supplierLogo) {
        this.supplierLogo = supplierLogo;
    }

    public Bidding() {
    }

    public Bidding(String bidNumber, String productId, String productName, int productNum, String productType, String productSpec,String supplierLogo) {
        this.bidNumber = bidNumber;
        this.productId = productId;
        this.productName = productName;
        this.productNum = productNum;
        this.productType = productType;
        this.productSpec = productSpec;
        this.supplierLogo=supplierLogo;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }
}
