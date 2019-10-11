package com.lovomall.csc.service.Impl;

import com.lovomall.csc.entity.RechargeReviewRecord;
import com.lovomall.csc.repository.RechargeReviewRecordRepository;
import com.lovomall.csc.service.RechargeReviewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午10:35
 * Description: 充值审核记录业务层接口实现类
 * Version: 1.0
 */
@Service(value = "rechargeReviewRecordService")
public class RechargeReviewRecordServiceImpl implements RechargeReviewRecordService {

    private final RechargeReviewRecordRepository rechargeReviewRecordRepository;

    @Autowired
    public RechargeReviewRecordServiceImpl(RechargeReviewRecordRepository rechargeReviewRecordRepository) {
        this.rechargeReviewRecordRepository = rechargeReviewRecordRepository;
    }

    @Override
    public RechargeReviewRecord save(RechargeReviewRecord rechargeReviewRecord) {
        return rechargeReviewRecordRepository.save(rechargeReviewRecord);
    }
}
