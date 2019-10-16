package com.lovomall.csc.dao;

import com.lovomall.csc.entity.ExamineInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 供应商注册审核持久接口
 */
@Repository(value = "examineInfoDao")
public interface IExamineInfoDao extends CrudRepository<ExamineInfoEntity,String> {

}

