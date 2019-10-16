package com.lovomall.csc.service.impl;

import com.lovomall.csc.dao.ISupplierInfoDao;
import com.lovomall.csc.entity.SupplierInfoEntity;
import com.lovomall.csc.service.ISupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("supplierInfoService")
@Transactional
public class SupplierInfoServiceImpl implements ISupplierInfoService {
    @Autowired
    private ISupplierInfoDao supplierInfoDao;
    @Override
    public void addSupplierInfo(SupplierInfoEntity supplierInfoEntity) {
        supplierInfoDao.save(supplierInfoEntity);



    }



}
