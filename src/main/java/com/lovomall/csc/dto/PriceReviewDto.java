package com.lovomall.csc.dto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GYM
 * @version 1.0
 * @date 2019/10/15 9:55
 */
@Setter
@Getter
public class PriceReviewDto {
    //招标号
    private String bidNumber;
    //供应商名称
    private String supplierName;
    //商品名称
    private String productName;
    //商品报价
    private int allMoney;
    //商品数量
    private int productNum;
    //供应商唯一标识
    private String supplierLogo;
}
