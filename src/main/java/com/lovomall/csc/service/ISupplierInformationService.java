package com.lovomall.csc.service;

import com.lovomall.csc.entity.SupplierInformationEntity;

import java.util.List;

/**
 * 供应商反馈消息业务接口
 */
public interface ISupplierInformationService {

    public  void  addInformation(SupplierInformationEntity supplierInformationEntity);

    List<SupplierInformationEntity>findList(String supplierOrderId);
}
