package com.lovomall.csc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * 供应商实体类
 */
@Entity
@Table(name = "sys_supplier")
public class SupplierSignEntity {
    @Id
    @GenericGenerator(name = "uuuuid",strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32,name = "s_id")
    private String supplierId;
    //资质证书
    @Column(columnDefinition = "LONGBLOB")
    private String supplierLicansePath;
    @Column(length = 48)
    private String supplierName;

    @Column(length = 48)
    private String supplierScope;
    @Column(length = 48)
    private String supplierPhone;
    //供应商地址
    @Column(length = 48)
    private String supplierSite;
    //供应商等级
    @Column(length = 48)
    private String supplierGrade;
    //注册时间
    @Column(length = 48)
    private String supplierTime;
    @Column(length = 48)
    private String supplierLogo;
    @Column(length = 48)
    private String supplierStatus;
    //审核状态
    @Column(length = 48)
    private String supplierState="未审核";

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierLogo() {
        return supplierLogo;
    }

    public void setSupplierLogo(String supplierLogo) {
        this.supplierLogo = supplierLogo;
    }

    public String getSupplierLicansePath() {
        return supplierLicansePath;
    }

    public void setSupplierLicansePath(String supplierLicansePath) {
        this.supplierLicansePath = supplierLicansePath;
    }

    public String getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(String supplierState) {
        this.supplierState = supplierState;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierScope() {
        return supplierScope;
    }

    public void setSupplierScope(String supplierScope) {
        this.supplierScope = supplierScope;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierSite() {
        return supplierSite;
    }

    public void setSupplierSite(String supplierSite) {
        this.supplierSite = supplierSite;
    }

    public String getSupplierGrade() {
        return supplierGrade;
    }

    public void setSupplierGrade(String supplierGrade) {
        this.supplierGrade = supplierGrade;
    }

    public String getSupplierTime() {
        return supplierTime;
    }

    public void setSupplierTime(String supplierTime) {
        this.supplierTime = supplierTime;
    }
}
