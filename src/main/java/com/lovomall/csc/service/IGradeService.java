package com.lovomall.csc.service;

import com.lovomall.csc.entity.GradeEntity;

import java.util.List;

/**
 * 供应商等级业务接口
 */
public interface IGradeService {
    public void add(GradeEntity gradeEntity);
    public List<GradeEntity> findAll();
}
