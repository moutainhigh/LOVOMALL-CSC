package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 招标信息实体类
 */
@Entity
@Table(name = "sys_bidding_info")
public class BiddingInfoEntity {
    //招标号
    @Id
    @GenericGenerator(name = "uuuuid",strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32,name = "bidding_id")
    private String id;
    //发起招标时间
    @Column(length = 48)
    private String biddingTime;
    //供应商
    @ManyToOne
    @JoinColumn(name = "s_id")
    private SupplierSignEntity supplierEntity;
    //采购方案
    @JoinColumn(name = "pp_id")
    @ManyToOne
    private ProcurementPlanEntity procurementPlanEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBiddingTime() {
        return biddingTime;
    }

    public void setBiddingTime(String biddingTime) {
        this.biddingTime = biddingTime;
    }

    public SupplierSignEntity getSupplierEntity() {
        return supplierEntity;
    }

    public void setSupplierEntity(SupplierSignEntity supplierEntity) {
        this.supplierEntity = supplierEntity;
    }

    public ProcurementPlanEntity getProcurementPlanEntity() {
        return procurementPlanEntity;
    }

    public void setProcurementPlanEntity(ProcurementPlanEntity procurementPlanEntity) {
        this.procurementPlanEntity = procurementPlanEntity;
    }
}
