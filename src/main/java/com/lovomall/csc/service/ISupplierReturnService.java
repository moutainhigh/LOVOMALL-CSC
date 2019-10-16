package com.lovomall.csc.service;

import com.lovomall.csc.entity.SupplierInfoEntity;
import com.lovomall.csc.entity.SupplierInformationEntity;
import com.lovomall.csc.entity.SupplierReturnEntity;

import java.util.List;
import java.util.Optional;

/**
 * 供应商退货业务接口
 */
public interface ISupplierReturnService {

    public  void addSupplier(SupplierReturnEntity supplierReturnEntity);

    public List<SupplierReturnEntity> findSupplierList(int pageNumber, int pageSize);

    void  updateSupplier(String returnOrderCode , String orderStatus, SupplierInfoEntity supplierInfoEntity);

    SupplierReturnEntity findById(String id );

    int findList(String orderStatus);
    void  updateStatus(String returnOrderCode , String orderStatus, SupplierInformationEntity supplierInformationEntity);

    List<SupplierReturnEntity> findStatusList(int pageNumber, int pageSize);
}
