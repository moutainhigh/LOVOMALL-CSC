package com.lovomall.csc.dao;

import com.lovomall.csc.entity.ProcurementPlanEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 采购方案持久接口
 */
@Repository(value = "procurementPlanDao")
public interface IProcurementPlanDao extends CrudRepository<ProcurementPlanEntity,String> {

    @Modifying
    @Query("update ProcurementPlanEntity set status=?2 where id=?1")
    public void updateStatus(String id,String status);

    @Query("from ProcurementPlanEntity where status='未审核'")
    public List<ProcurementPlanEntity> getPageListProcurementPlan(Pageable pageable);

    @Query("select count(id) from ProcurementPlanEntity where status='未审核'")
    public int countByStatus();

    @Query("select productCode from ProcurementPlanEntity where id=?1")
    public String findByIdCode(String id);

    @Query("from ProcurementPlanEntity where id=?1")
    public Optional<ProcurementPlanEntity> findById(String id);
}
