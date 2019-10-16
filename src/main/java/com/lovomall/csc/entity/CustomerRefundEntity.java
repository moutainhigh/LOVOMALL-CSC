package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 客户退款实体类
 */
@Entity
@Table(name = "c_fefund")
public class CustomerRefundEntity {

    /**退款id*/
    @Id

    @Column(length = 48)
private  String orderCode;
    /**订单状态*/
    @Column(length = 48)
private  String  orderStatus ="未处理";


    /**商品名称*/
    @Column(length = 48)
private  String productName;
    /**退货订单总价*/
    @Column(length = 48)
private  double totalPrice;
/**明细集合*/

/**审核人*/
@Column(length = 48)
private  String auditor="007";

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


    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }
}
