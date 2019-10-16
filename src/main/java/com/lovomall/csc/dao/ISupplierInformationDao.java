package com.lovomall.csc.dao;


import com.lovomall.csc.entity.SupplierInformationEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 供应商消息反馈持久接口
 */
public interface ISupplierInformationDao extends CrudRepository<SupplierInformationEntity,String> {

    @Query(value = "SELECT * FROM s_supplier_return s LEFT JOIN s_informatiom i ON s.return_order_code=i.return_order_code WHERE s.return_order_code=?1",nativeQuery = true)
    List<SupplierInformationEntity>findList(String supplierOrderId);

//    @Query("from SupplierInformationEntity where messageSender='1'")
//    List<SupplierInformationEntity>findList();


}
