package com.lovomall.csc.service.Impl;

import com.lovomall.csc.entity.RechargeRecord;
import com.lovomall.csc.repository.RechargeRecordRepository;
import com.lovomall.csc.service.RechargeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午9:33
 * Description: 预存款充值业务层接口实现类
 * Version: 1.0
 */
@Service(value = "rechargeRecordService")
public class RechargeRecordServiceImpl implements RechargeRecordService {

    private final RechargeRecordRepository rechargeRecordRepository;

    @Autowired
    public RechargeRecordServiceImpl(RechargeRecordRepository rechargeRecordRepository) {
        this.rechargeRecordRepository = rechargeRecordRepository;
    }

    @Override
    public RechargeRecord save(RechargeRecord rechargeRecord) {
        return rechargeRecordRepository.save(rechargeRecord);
    }

    @Override
    public Page<RechargeRecord> findAllByReviewStatusIs(int pageNO, int pageSize, String reviewStatus) {

        Pageable pageable = PageRequest.of(pageNO, pageSize);

        return rechargeRecordRepository.findAllByReviewStatusIs(pageable, reviewStatus);
    }

    @Transactional
    @Override
    public void updateReviewStatusById(String upId, String reviewStatus) {
        rechargeRecordRepository.updateReviewStatusById(upId, reviewStatus);
    }

    @Override
    public RechargeRecord findByUpIdIs(String upId) {
        return rechargeRecordRepository.findByUpIdIs(upId);
    }
}
