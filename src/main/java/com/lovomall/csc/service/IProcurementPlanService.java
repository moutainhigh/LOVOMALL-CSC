package com.lovomall.csc.service;

import com.lovomall.csc.entity.ProcurementPlanEntity;
import com.lovomall.csc.entity.ProcurementPlanInfoEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 采购方案业务接口
 */
public interface IProcurementPlanService {
    public void add(String json) throws IOException;
    public void updateStatus(String id,ProcurementPlanInfoEntity procurementPlanInfoEntity);

    /**
     * 分页
     * @param pageNumber 当前页
     * @param pageSize 每页显示条数
     * @return  符合条件的采购方案信息
     */
    public List<ProcurementPlanEntity> getPageListProcurementPlanEntity(int pageNumber, int pageSize);

    public int countByStatus();

    /**
     * 根据ID查找商品编号
     * @param id 采购方案ID
     * @return  商品编号
     */
    public String findByIdCode(String id);

    public Optional<ProcurementPlanEntity> findById(String id);
}
