package com.lovomall.csc.service;

import com.lovomall.csc.entity.ProcurementPlanInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购方案审核业务接口
 */
public interface IProcurementPlanInfoService {
    public void add(ProcurementPlanInfoEntity procurementPlanInfoEntity);
    public List<ProcurementPlanInfoEntity> findByPpId(String id);
}
