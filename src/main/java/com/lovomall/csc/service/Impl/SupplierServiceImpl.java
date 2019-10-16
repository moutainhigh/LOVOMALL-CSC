package com.lovomall.csc.service.Impl;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dao.IExamineInfoDao;
import com.lovomall.csc.dao.ISupplierDao;
import com.lovomall.csc.entity.ExamineInfoEntity;
import com.lovomall.csc.entity.ProcurementPlanEntity;
import com.lovomall.csc.entity.SupplierScopeEntity;
import com.lovomall.csc.entity.SupplierSignEntity;
import com.lovomall.csc.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service(value = "supplierService")
@Transactional
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private ISupplierDao supplierDao;
    @Autowired
    private IExamineInfoDao examineInfoDao;


    @JmsListener(destination = "${queuename.supplier.receive.sign}")
    @Override
    public void add(String json) throws IOException {
//        System.err.println(json);
        ObjectMapper obj=new ObjectMapper();
        SupplierSignEntity supplierSignEntity=   obj.readValue(json,SupplierSignEntity.class);
//        SupplierScopeEntity supplierScope = supplierSignEntity.getSupplierScope();

        //把MQ里的采购方案保存到服务器
        System.err.println(json);
        String supplierTime = supplierSignEntity.getSupplierTime();
        System.err.println(supplierTime);
        supplierDao.save(supplierSignEntity);
    }

    @Override
    public Optional<SupplierSignEntity> findById(String id) {
        return supplierDao.findById(id);
    }

    @Override
    public List<SupplierSignEntity> findByStatus(String status) {
        return supplierDao.findByStatus(status);
    }

    @Override
    public void updateStatus(String id,String grade,ExamineInfoEntity examineInfoEntity,String status) {
//        if (examineInfoEntity.getStatus().equals("审核通过")){
            supplierDao.updateStatus(id,"已审核");
//        }
        supplierDao.updateGrade(id,grade);
        supplierDao.findByIdUpdateStatus(id,status);
        SupplierSignEntity supplierEntity = new SupplierSignEntity();
        supplierEntity.setSupplierId(id);
        examineInfoEntity.setSupplierEntity(supplierEntity);
        examineInfoDao.save(examineInfoEntity);
    }


    @Override
    public int countByStatus() {
        return supplierDao.countByStatus();
    }


    @Override
    public List<SupplierSignEntity> getPageListSupplier(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return supplierDao.getPageListSupplier(pageable);
    }

    @Override
    public List<SupplierSignEntity> getPageListSupplierBidding(String supplierScope, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return supplierDao.getPageListSupplierBidding(supplierScope,pageable);
    }

    @Override
    public int countBySupplierScope(String supplierScope) {
        return supplierDao.countBySupplierScope(supplierScope);
    }


}
