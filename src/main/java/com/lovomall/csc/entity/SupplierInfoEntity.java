package com.lovomall.csc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 供应商审核明细实体类
 */
@Entity
@Table(name = "s_supplierInfo")
public class SupplierInfoEntity {
    /**供应商审核id*/
    @Id
    @GenericGenerator(name = "euuid",strategy = "uuid")
    @GeneratedValue(generator = "euuid")
    @Column(length = 32,name = "e_id")
    private String id;
    /**外键供应商退货单id*/
    @Column(length = 32)
    private String returnOrderCode;
    /**审核结果*/
    @Column(length = 32)
    private String examine;
    /**备注*/
    @Column(length = 128,columnDefinition = "text")
    private String remark;
    /**审核日期*/
    @Column(columnDefinition = "datetime")

    private String examineDate=
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    format(new java.sql.Date(System.currentTimeMillis()));

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReturnOrderCode() {
        return returnOrderCode;
    }

    public void setReturnOrderCode(String returnOrderCode) {
        this.returnOrderCode = returnOrderCode;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExamineDate() {
        return examineDate;
    }

    public void setExamineDate(String examineDate) {
        this.examineDate = examineDate;
    }
}
