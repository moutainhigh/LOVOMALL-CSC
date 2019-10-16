package com.lovomall.csc.repository;

import com.lovomall.csc.entity.ShoppingAudit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ShoppingAuditRepository extends Repository<ShoppingAudit,String> {
    /**
     * 添加报价审核结果信息
     * @param var1 报价审核结果信息实体
     * @return 添加成功返回实体
     */
    <S extends ShoppingAudit> S save(S var1);

    /**
     * 查询全部报价审核结果信息
     * @return
     */
    Iterable<ShoppingAudit> findAll();

    /**
     * 按报价审核信息结果id修改审核状态
     * @param shoppingResultStatus 报价审核状态
     * @param shoppingAuditId 报价审核结果id
     */
    @Modifying
    @Query("update ShoppingAudit set shoppingResultStatus=?1 where shoppingAuditId=?2")
    public void updatePriceReviewResultStatus(String shoppingResultStatus,String shoppingAuditId);

}



