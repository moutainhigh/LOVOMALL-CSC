package com.lovomall.csc.repository;

import com.lovomall.csc.entity.RechargeReviewRecord;
import org.springframework.data.repository.Repository;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午8:41
 * Description: 充值审核记录持久层接口
 * Version: 1.0
 */
public interface RechargeReviewRecordRepository extends Repository<RechargeReviewRecord, String> {

    /**
     * 添加充值审核记录
     * @param var 充值审核记录实体
     * @return 添加成功返回充值记录对象， 失败返回null
     */
    <S extends RechargeReviewRecord> S save(S var);

}
