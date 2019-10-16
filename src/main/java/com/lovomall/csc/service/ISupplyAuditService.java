package com.lovomall.csc.service;

import com.lovomall.csc.entity.ShoppingAudit;
import com.lovomall.csc.entity.SupplyAudit;

public interface ISupplyAuditService {
    /**
     * 保存审核结果信息
     * @param supplyAudit
     * @return
     */
    public SupplyAudit savePriceReviewResult(SupplyAudit supplyAudit);

    /**
     * 修改报价信息审核状态
     * @param supplyResultStatus 审核状态
     * @param supplyAuditId 报价信息审核结果id
     */
    public void updatePriceReviewResultStatus(String supplyResultStatus,String supplyAuditId);
}
