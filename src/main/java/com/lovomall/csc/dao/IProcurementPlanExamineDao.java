package com.lovomall.csc.dao;

import com.lovomall.csc.entity.ProcurementPlanInfoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购方案审核持久接口
 */
@Repository(value = "procurementPlanExamineDao")
public interface IProcurementPlanExamineDao extends CrudRepository<ProcurementPlanInfoEntity,String> {
    @Query("from ProcurementPlanInfoEntity where procurementPlanEntity.id=?1")
    public List<ProcurementPlanInfoEntity> findByPpId(String id);
}
