package com.lovomall.csc.repository;

import com.lovomall.csc.entity.ShoppingAudit;
import com.lovomall.csc.entity.SupplyAudit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SupplyAuditRepository extends Repository<SupplyAudit,String> {
    /**
     * 添加报价审核结果信息
     * @param var1 报价审核结果信息实体
     * @return 添加成功返回实体
     */
    <S extends SupplyAudit> S save(S var1);

    /**
     * 查询全部报价审核结果信息
     * @return
     */
    Iterable<SupplyAudit> findAll();

    /**
     * 按报价审核信息结果id修改审核状态
     * @param supplyResultStatus 报价审核状态
     * @param supplyAuditId 报价审核结果id
     */
    @Modifying
    @Query("update SupplyAudit set supplyResultStatus=?1 where supplyAuditId=?2")
    public void updatePriceReviewResultStatus(String supplyResultStatus,String supplyAuditId);
}
