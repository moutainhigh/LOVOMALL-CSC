package com.lovomall.csc.service.impl;

import com.lovomall.csc.dao.IGradeDao;
import com.lovomall.csc.entity.GradeEntity;
import com.lovomall.csc.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "gradeService")
@Transactional
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private IGradeDao gradeDao;
    @Override
    public void add(GradeEntity gradeEntity) {
        gradeDao.save(gradeEntity);
    }

    @Override
    public List<GradeEntity> findAll() {
        return gradeDao.findAll();
    }
}
