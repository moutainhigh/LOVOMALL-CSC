package com.lovomall.csc.dao;


import com.lovomall.csc.entity.SupplierReturnEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 供应商退货持久接口
 */
public interface ISupplierReturnDao extends CrudRepository<SupplierReturnEntity,String> {
    @Query("from SupplierReturnEntity where orderStatus='未处理'")
 List<SupplierReturnEntity>findSupplierList(Pageable pageable);

    @Query("select count(returnOrderCode) from SupplierReturnEntity where orderStatus=?1 ")
    int findList(String orderStatus);

    @Modifying
    @Transactional
    @Query(value = "UPDATE s_supplier_return SET order_status=?2 where return_order_code=?1",nativeQuery = true)
      void  updateSupplier(String returnOrderCode ,String orderStatus);

    @Query("from SupplierReturnEntity where returnOrderCode=?1")
     SupplierReturnEntity findByOrderCode(String returnOrderCode);
    @Query("from SupplierReturnEntity where orderStatus='未退款'")
    List<SupplierReturnEntity>findStatusList(Pageable pageable);



}
