package com.lovomall.csc.dao;

import com.lovomall.csc.entity.SupplierScopeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 供应商经营范围持久接口
 */
@Repository(value = "scopeDao")
public interface IScopeDao extends CrudRepository<SupplierScopeEntity,String> {


}
