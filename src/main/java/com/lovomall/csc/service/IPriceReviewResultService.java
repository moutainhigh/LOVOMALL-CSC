package com.lovomall.csc.service;
import com.lovomall.csc.entity.PriceReviewResult;

/**
 * @author GYM
 * @version 1.0
 */
public interface IPriceReviewResultService {
    /**
     * 保存审核结果信息
     * @param priceReviewResult
     * @return
     */
    public PriceReviewResult savePriceReviewResult(PriceReviewResult priceReviewResult);

    /**
     * 修改报价信息审核状态
     * @param priceReviewResultStatus 审核状态
     * @param priceReviewResultId 报价信息审核结果id
     */
    public void updatePriceReviewResultStatus(String priceReviewResultStatus,String priceReviewResultId);
}
