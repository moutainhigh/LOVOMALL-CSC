package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//采购方案审核信息实体类
@Entity
@Table(name = "procurement_plan_examine_Info")
public class ProcurementPlanInfoEntity {
    @Id
    @GenericGenerator(name = "uuuuid",strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32,name = "pr_ex_id")
    private String id;
    //对应的供应商
    @ManyToOne
    @JoinColumn(name = "pp_id")
    private ProcurementPlanEntity procurementPlanEntity ;
    //审核信息
    @Column(length = 48)
    private String examineInfo;
    //审核人
    @Column(length = 48)
    private String examineMan;
    //审核状态
    @Column(length = 48)
    private String status;
    //审核时间
    @Column(length = 48)
    private String examineTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProcurementPlanEntity getProcurementPlanEntity() {
        return procurementPlanEntity;
    }

    public void setProcurementPlanEntity(ProcurementPlanEntity procurementPlanEntity) {
        this.procurementPlanEntity = procurementPlanEntity;
    }

    public String getExamineInfo() {
        return examineInfo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(String examineTime) {
        this.examineTime = examineTime;
    }
}
