package com.lovomall.csc.service.Impl;

import com.lovomall.csc.dao.IScopeDao;
import com.lovomall.csc.entity.SupplierScopeEntity;
import com.lovomall.csc.service.ISupplierScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "supplierScopeServiceImpl")
@Transactional
public class SupplierScopeServiceImpl implements ISupplierScopeService {

    @Autowired
    private IScopeDao scopeDao;
    @Override
    public void add(SupplierScopeEntity supplierScopeEntity) {
        scopeDao.save(supplierScopeEntity);
    }
}
