package com.lovomall.csc.service;

import com.lovomall.csc.entity.ConsumeReviewRecord;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午10:37
 * Description: 消费审核记录业务层接口
 * Version: 1.0
 */
public interface ConsumeReviewRecordService {

    /**
     * 添加消费审核记录
     * @param consumeReviewRecord 消费审核记录实体
     * @return 添加成功返回消费审核记录对象， 失败返回null
     */
    ConsumeReviewRecord save(ConsumeReviewRecord consumeReviewRecord);
}
