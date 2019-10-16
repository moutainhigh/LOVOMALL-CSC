package com.lovomall.csc.dto;

/**
 * 接收审核系统发送的dto
 */
public class SupplierOrderDto {
    /**退货单编号*/
   private String returnOrderCode;
    /**商品编号*/
    private  String productCode;
    /**退货数量*/
    private  int productNumber;
    /**审核结果*/
    private String examine;
    /**审核日期*/
    private String examineDate;
    /**备注*/
    private String remark;

    public String getReturnOrderCode() {
        return returnOrderCode;
    }

    public void setReturnOrderCode(String returnOrderCode) {
        this.returnOrderCode = returnOrderCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getExamineDate() {
        return examineDate;
    }

    public void setExamineDate(String examineDate) {
        this.examineDate = examineDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
