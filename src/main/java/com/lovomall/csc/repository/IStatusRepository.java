package com.lovomall.csc.repository;

import com.lovomall.csc.entity.StatusEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户状态审核记录持久接口
 */
public interface IStatusRepository extends CrudRepository<StatusEntity,String> {
}
