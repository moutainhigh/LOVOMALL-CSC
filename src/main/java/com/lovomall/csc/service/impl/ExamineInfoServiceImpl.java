package com.lovomall.csc.service.impl;

import com.lovomall.csc.dao.IExamineInfoDao;
import com.lovomall.csc.entity.ExamineInfoEntity;
import com.lovomall.csc.service.IExamineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "examineInfoService")
@Transactional
public class ExamineInfoServiceImpl implements IExamineInfoService {
    @Autowired
    private IExamineInfoDao examineInfoDao;
    @Override
    public void add(ExamineInfoEntity examineInfoEntity) {
        examineInfoDao.save(examineInfoEntity);
    }
}
