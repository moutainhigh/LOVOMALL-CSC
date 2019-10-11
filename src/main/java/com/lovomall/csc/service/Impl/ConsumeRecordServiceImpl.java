package com.lovomall.csc.service.Impl;

import com.lovomall.csc.entity.ConsumeRecord;
import com.lovomall.csc.repository.ConsumeRecordRepository;
import com.lovomall.csc.service.ConsumeRecordService;
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
 * Time:       下午10:27
 * Description: 消费记录业务层接口实现类
 * Version: 1.0
 */
@Service(value = "consumeRecordService")
public class ConsumeRecordServiceImpl implements ConsumeRecordService {
    private final ConsumeRecordRepository consumeRecordRepository;

    @Autowired
    public ConsumeRecordServiceImpl(ConsumeRecordRepository consumeRecordRepository) {
        this.consumeRecordRepository = consumeRecordRepository;
    }

    @Override
    public ConsumeRecord save(ConsumeRecord consumeRecord) {
        return consumeRecordRepository.save(consumeRecord);
    }

    @Override
    public Page<ConsumeRecord> findAllByOrderStatusIs(int pageNO, int pageSize, String orderStatus) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);

        return consumeRecordRepository.findAllByOrderStatusIs(pageable, orderStatus);
    }

    @Transactional
    @Override
    public void updateOrderStatusById(String consuId, String orderStatus) {
        consumeRecordRepository.updateOrderStatusById(consuId, orderStatus);
    }
}
