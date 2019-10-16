package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 供应商注册审核实体类
 */
@Entity
@Table(name = "sys_supplier_info")
public class ExamineInfoEntity {
    @Id
    @GenericGenerator(name = "uuuuid",strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32,name = "info_id")
    private String id;
    //对应的供应商
    @ManyToOne
    @JoinColumn(name = "s_id")
    private SupplierSignEntity supplierEntity;
    //审核信息
    private String examineInfo;
    //审核人
    private String examineMan;
    //审核状态
    private String status;
    //审核时间
    private String examineTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SupplierSignEntity getSupplierEntity() {
        return supplierEntity;
    }

    public void setSupplierEntity(SupplierSignEntity supplierEntity) {
        this.supplierEntity = supplierEntity;
    }

    public String getExamineInfo() {
        return examineInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExamineInfo(String examineInfo) {
        this.examineInfo = examineInfo;
    }

    public String getExamineMan() {
        return examineMan;
    }

    public void setExamineMan(String examineMan) {
        this.examineMan = examineMan;
    }

    public String getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(String examineTime) {
        this.examineTime = examineTime;
    }
}
