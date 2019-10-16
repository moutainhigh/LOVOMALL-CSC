package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 供应商范围实体类
 */
@Entity
@Table(name = "sys_scope")
public class SupplierScopeEntity {
    @Id
    @GenericGenerator(name = "uuuuid",strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32,name = "scope_id")
    private String supplierScopeId;
    @Column(length = 48)
    private String supplierScopeInfo;

    public String getSupplierScopeId() {
        return supplierScopeId;
    }

    public void setSupplierScopeId(String supplierScopeId) {
        this.supplierScopeId = supplierScopeId;
    }

    public String getSupplierScopeInfo() {
        return supplierScopeInfo;
    }

    public void setSupplierScopeInfo(String supplierScopeInfo) {
        this.supplierScopeInfo = supplierScopeInfo;
    }
}
