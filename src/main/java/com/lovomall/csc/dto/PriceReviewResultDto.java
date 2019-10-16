package com.lovomall.csc.dto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GYM
 * @version 1.0
 * @date 2019/10/15 10:33
 */

@Setter
@Getter
public class PriceReviewResultDto {
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
    //审核状态
    private String priceReviewResultStatus;
    //审核意见
    private String priceReviewOpinion;
    //审核时间
    private String priceReviewTime;
    //供应商唯一标识
    private String supplierLogo;
}
