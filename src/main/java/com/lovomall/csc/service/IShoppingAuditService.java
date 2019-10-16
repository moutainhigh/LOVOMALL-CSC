package com.lovomall.csc.service;

import com.lovomall.csc.entity.ShoppingAudit;

public interface IShoppingAuditService {
    /**
     * 保存审核结果信息
     * @param shoppingAudit
     * @return
     */
    public ShoppingAudit savePriceReviewResult(ShoppingAudit shoppingAudit);

    /**
     * 修改报价信息审核状态
     * @param shoppingAuditId 审核状态
     * @param shoppingResultStatus 报价信息审核结果id
     */
    public void updatePriceReviewResultStatus(String shoppingResultStatus,String shoppingAuditId);
}
