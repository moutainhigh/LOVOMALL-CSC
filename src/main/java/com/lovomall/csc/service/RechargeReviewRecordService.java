package com.lovomall.csc.service;

import com.lovomall.csc.entity.RechargeReviewRecord;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午10:34
 * Description: 充值审核记录业务层接口
 * Version: 1.0
 */
public interface RechargeReviewRecordService {

    /**
     * 添加充值审核记录
     * @param rechargeReviewRecord 充值审核记录实体
     * @return 添加成功返回充值记录对象， 失败返回null
     */
    RechargeReviewRecord save(RechargeReviewRecord rechargeReviewRecord);
}
