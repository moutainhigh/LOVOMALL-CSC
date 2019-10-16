package com.lovomall.csc.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dao.IProcurementPlanDao;
import com.lovomall.csc.dao.IProcurementPlanExamineDao;
import com.lovomall.csc.entity.ProcurementPlanEntity;
import com.lovomall.csc.entity.ProcurementPlanInfoEntity;
import com.lovomall.csc.service.IProcurementPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service(value = "procurementPlanService")
@Transactional
public class ProcurementPlanServiceImpl implements IProcurementPlanService {

    @Autowired
    private IProcurementPlanDao procurementPlanDao;
    @Autowired
    private IProcurementPlanExamineDao procurementPlanExamineDao;

    @JmsListener(destination = "${queuename.pruchase.receive.purchasePlan}")
    public void add(String json) throws IOException {
        //获得MQ里面的采购方案信息
        System.err.println(json);
        ObjectMapper obj=new ObjectMapper();
        ProcurementPlanEntity procurementPlanEntity=   obj.readValue(json,ProcurementPlanEntity.class);
        //把MQ里的采购方案保存到服务器
        procurementPlanDao.save(procurementPlanEntity);
    }

    @Override
    public void updateStatus(String id,ProcurementPlanInfoEntity procurementPlanInfoEntity) {
            procurementPlanDao.updateStatus(id,"已审核");
        ProcurementPlanEntity procurementPlanEntity = new ProcurementPlanEntity();
        procurementPlanEntity.setId(id);
        procurementPlanInfoEntity.setProcurementPlanEntity(procurementPlanEntity);
        procurementPlanExamineDao.save(procurementPlanInfoEntity);
    }

    @Override
    public List<ProcurementPlanEntity> getPageListProcurementPlanEntity(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return procurementPlanDao.getPageListProcurementPlan(pageable);
    }

    @Override
    public int countByStatus() {
        return procurementPlanDao.countByStatus();
    }

    @Override
    public String findByIdCode(String id) {
        return procurementPlanDao.findByIdCode(id);
    }

    @Override
    public Optional<ProcurementPlanEntity> findById(String id) {
        return procurementPlanDao.findById(id);
    }

}
