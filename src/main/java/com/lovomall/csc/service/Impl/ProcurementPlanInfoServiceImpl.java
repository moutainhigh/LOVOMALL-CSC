package com.lovomall.csc.service.Impl;

import com.lovomall.csc.dao.IProcurementPlanExamineDao;
import com.lovomall.csc.entity.ProcurementPlanInfoEntity;
import com.lovomall.csc.service.IProcurementPlanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "procurementPlanInfoServiceImpl")
@Transactional
public class ProcurementPlanInfoServiceImpl implements IProcurementPlanInfoService {
    @Autowired
    private IProcurementPlanExamineDao procurementPlanExamineDao;
    @Override
    public void add(ProcurementPlanInfoEntity procurementPlanInfoEntity) {
        procurementPlanExamineDao.save(procurementPlanInfoEntity);
    }

    @Override
    public List<ProcurementPlanInfoEntity> findByPpId(String id) {
        return procurementPlanExamineDao.findByPpId(id);
    }
}
