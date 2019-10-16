package com.lovomall.csc.service;

import com.lovomall.csc.entity.OrderdetailsEntity;
import com.lovomall.csc.entity.SupplierdetailsEntity;

import java.util.List;

public interface ISupplierdetalisService {
    public SupplierdetailsEntity svaeSupplierdetalis(SupplierdetailsEntity supplierdetailsEntity);
    public SupplierdetailsEntity findOrderdetalisById(String id);
}
