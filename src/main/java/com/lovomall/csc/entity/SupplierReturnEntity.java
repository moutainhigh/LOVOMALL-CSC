package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 供应商退货实体类
 */
@Entity
@Table(name = "s_supplier_return")
public class SupplierReturnEntity {
    /**供应商退货单id*/
    @Id

    @Column(length = 32)

    /**供应商退货单编号*/
    private String returnOrderCode ;
    /**供应商名称*/
    @Column(length = 32)
    private String supplierId;
    /**退货单生成时间*/
    @Column(length = 32)
    private String orderDate;
    /**商品编号*/
    @Column(length = 32)
    private String productCode;
    /**商品名称*/
    @Column(length = 32)
    private String productName;
    /**商品类型*/
    @Column(length = 32)
    private String productType;
    /**商品规格*/
    @Column(length = 32)
    private String productSpec;
    /**退货数量*/
    private int productNumber;
    /**退货总价*/
    private double totalPrice;
    /**退货原因*/
    @Column(length = 128,columnDefinition = "text")
    private String returnCause;
    /**退货单状态*/
    @Column(length = 32)
    private String orderStatus="未处理";


    public String getReturnOrderCode() {
        return returnOrderCode;
    }

    public void setReturnOrderCode(String returnOrderCode) {
        this.returnOrderCode = returnOrderCode;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReturnCause() {
        return returnCause;
    }

    public void setReturnCause(String returnCause) {
        this.returnCause = returnCause;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
