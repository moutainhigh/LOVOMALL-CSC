package com.lovomall.csc.service.Impl;

import com.lovomall.csc.entity.SupplierdetailsEntity;
import com.lovomall.csc.repository.SupplierdetalisRepository;
import com.lovomall.csc.service.ISupplierdetalisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "supplierdetalis")

public class SupplierdetalisServiceImpl implements ISupplierdetalisService {
    @Autowired
    private SupplierdetalisRepository supplierdetalisRepository;


    @Override
    public SupplierdetailsEntity svaeSupplierdetalis(SupplierdetailsEntity supplierdetailsEntity) {
        return supplierdetalisRepository.save(supplierdetailsEntity);
    }

    @Override
    public SupplierdetailsEntity findOrderdetalisById(String id) {
        return supplierdetalisRepository.findAllById(id);
    }


}
