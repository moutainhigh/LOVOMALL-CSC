package com.lovomall.csc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dao.ISupplierReturnDao;
import com.lovomall.csc.entity.SupplierInfoEntity;
import com.lovomall.csc.entity.SupplierInformationEntity;
import com.lovomall.csc.entity.SupplierReturnEntity;
import com.lovomall.csc.service.ISupplierInfoService;
import com.lovomall.csc.service.ISupplierInformationService;
import com.lovomall.csc.service.ISupplierReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service("supplierReturnService")
@Transactional
public class SupplierReturnServiceImpl implements ISupplierReturnService {
    @Autowired
    private ISupplierReturnDao supplierReturnDao;
    @Autowired
    private ISupplierInfoService supplierInfoService;
    @Autowired
    private ISupplierInformationService supplierInformationService;

    public void addSupplier(SupplierReturnEntity supplierReturnEntity) {
        supplierReturnDao.save(supplierReturnEntity);



    }


    public List<SupplierReturnEntity> findSupplierList(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return supplierReturnDao.findSupplierList(pageable);
    }


    public void updateSupplier(String returnOrderCode, String orderStatus, SupplierInfoEntity supplierInfoEntity) {
        supplierReturnDao.updateSupplier(returnOrderCode, orderStatus);
        supplierInfoService.addSupplierInfo(supplierInfoEntity);
    }

    @Override
    public SupplierReturnEntity findById(String id) {
        return supplierReturnDao.findByOrderCode(id);
    }


    public int findList(String orderStatus) {
        return supplierReturnDao.findList(orderStatus);
    }

    @Override
    public void updateStatus(String returnOrderCode, String orderStatus, SupplierInformationEntity supplierInformationEntity) {
        supplierReturnDao.updateSupplier(returnOrderCode, orderStatus);
        supplierInformationService.addInformation(supplierInformationEntity);

    }

    @Override
    public List<SupplierReturnEntity> findStatusList(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return supplierReturnDao.findStatusList(pageable);
    }

    @JmsListener(destination = "addSupplierOrder")
    public void saveSupplier(String supplierJson) throws IOException {
        ObjectMapper obj = new ObjectMapper();

        SupplierReturnEntity supplierReturnEntity = obj.readValue(supplierJson, SupplierReturnEntity.class);
        supplierReturnDao.save(supplierReturnEntity);


    }


}