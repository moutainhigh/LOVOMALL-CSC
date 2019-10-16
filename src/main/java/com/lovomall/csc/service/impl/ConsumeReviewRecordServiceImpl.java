package com.lovomall.csc.service.impl;

import com.lovomall.csc.entity.ConsumeReviewRecord;
import com.lovomall.csc.repository.ConsumeReviewRecordRepository;
import com.lovomall.csc.service.ConsumeReviewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午10:39
 * Description: 消费审核记录业务层接口实现类
 * Version: 1.0
 */
@Service(value = "consumeReviewRecordService")
public class ConsumeReviewRecordServiceImpl implements ConsumeReviewRecordService {

    private final ConsumeReviewRecordRepository consumeReviewRecordRepository;

    @Autowired
    public ConsumeReviewRecordServiceImpl(ConsumeReviewRecordRepository consumeReviewRecordRepository) {
        this.consumeReviewRecordRepository = consumeReviewRecordRepository;
    }

    @Override
    public ConsumeReviewRecord save(ConsumeReviewRecord consumeReviewRecord) {
        return consumeReviewRecordRepository.save(consumeReviewRecord);
    }
}
