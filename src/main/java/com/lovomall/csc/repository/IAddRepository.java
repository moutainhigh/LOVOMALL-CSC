package com.lovomall.csc.repository;

import com.lovomall.csc.entity.AddEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户注册审核记录持久接口
 */
public interface IAddRepository extends CrudRepository<AddEntity,String> {
}
