package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * 采购方案实体类
 */
@Entity
@Table(name = "t_product_plan_Info")
public class ProcurementPlanEntity {
    //采购方案ID
    @Id
    @GenericGenerator(name = "uuuuid",strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32,name = "pp_id")
    private String id;
    //采购时间
    @Column(length = 48)
    private Date purchaseTime=new Date(System.currentTimeMillis());
    //招标号
    @Column(length = 48)
    private String bidNumber=System.currentTimeMillis()+"";
    //采购方案审核状态
    @Column(length = 48)
    private String status="未审核";
    //商品编号
    @Column(length = 48)
    private String productCode;
    //商品名
    @Column(length = 48)
    private String productName;
    //采购数量
    private int purchaseNum;
    //商品类型
    @Column(length = 48)
    private String productType;
    //商品规格
    @Column(length = 48)
    private String productSpec;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
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
