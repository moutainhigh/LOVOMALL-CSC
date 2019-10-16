package com.lovomall.csc.dao;

import com.lovomall.csc.entity.GradeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 供应商等级持久接口
 */
@Repository(value = "gradeDao")
public interface IGradeDao extends CrudRepository<GradeEntity,String> {

    @Query("from GradeEntity")
    public List<GradeEntity> findAll();
}
