package com.lovomall.csc.service;

import com.lovomall.csc.entity.ConsumeRecord;
import org.springframework.data.domain.Page;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午9:38
 * Description: 消费记录业务层接口
 * Version: 1.0
 */
public interface ConsumeRecordService {
    /**
     * 添加消费记录
     * @param consumeRecord 消费记录实体
     * @return 添加成功返回消费记录实体，失败返回null
     */
    ConsumeRecord save(ConsumeRecord consumeRecord);

    /**
     * 分页查询待审核状态充值记录
     * @param pageNO 页码
     * @param pageSize 页大小
     * @param orderStatus 审核状态
     * @return 符合条件的消费记录对象集合
     */
    Page<ConsumeRecord> findAllByOrderStatusIs(int pageNO, int pageSize, String orderStatus);

    /**
     * 按充值记录Id 更新审核状态
     * @param consuId 消费记录id
     * @param orderStatus 审核状态
     */
    void updateOrderStatusById(String consuId, String orderStatus);
}
