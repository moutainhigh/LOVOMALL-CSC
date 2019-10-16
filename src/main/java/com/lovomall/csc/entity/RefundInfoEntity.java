package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 退款审核意见明细表
 */
@Entity
@Table(name = "r_refundInfo")
public class RefundInfoEntity {
    /**明细表id*/
    @Id
    @Column(length = 48)
//    @GenericGenerator(strategy = "uuid",name = "duuid")
//    @GeneratedValue(generator = "duuid")
    private  String dId ;

    /**审核时间*/
    @Column(columnDefinition = "datetime")
    private String checkDate ;

    /**审核结果*/
    @Column(length = 48)
    private  String checkResult;
    /**不通过原因*/
    @Column(length = 48)
    private  String rejectCause;
    /**退款时间*/
    @Column(length = 48)
    private  Date retumDate;

    /**申请退货原因*/
    @Column(length = 48)
    private  String  applyCause;
    /**退款单号*/
    @Column(length = 48)
    private  String orderCode;


    public String getApplyCause() {
        return applyCause;
    }

    public void setApplyCause(String applyCause) {
        this.applyCause = applyCause;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(String rejectCause) {
        this.rejectCause = rejectCause;
    }

    public Date getRetumDate() {
        return retumDate;
    }

    public void setRetumDate(Date retumDate) {
        this.retumDate = retumDate;
    }


}
