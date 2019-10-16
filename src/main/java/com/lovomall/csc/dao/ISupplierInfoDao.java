package com.lovomall.csc.dao;


import com.lovomall.csc.entity.SupplierInfoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISupplierInfoDao extends CrudRepository<SupplierInfoEntity,String> {
    @Query("from SupplierInfoEntity where supplierReturn=?1")
    public List<SupplierInfoEntity>findInfoLis(String id);

}
