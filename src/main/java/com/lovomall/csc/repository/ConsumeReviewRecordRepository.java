package com.lovomall.csc.repository;

import com.lovomall.csc.entity.ConsumeReviewRecord;
import org.springframework.data.repository.Repository;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午9:25
 * Description: 消费审核记录持久层接口
 * Version: 1.0
 */
public interface ConsumeReviewRecordRepository extends Repository<ConsumeReviewRecord, String> {

    /**
     * 添加消费审核记录
     * @param var 消费审核记录实体
     * @return 添加成功返回消费审核记录对象， 失败返回null
     */
    <S extends ConsumeReviewRecord> S save(S var);
}
