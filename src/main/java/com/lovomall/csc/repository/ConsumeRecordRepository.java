package com.lovomall.csc.repository;

import com.lovomall.csc.entity.ConsumeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午8:45
 * Description: 消费记录审核持久层接口
 * Version: 1.0
 */
public interface ConsumeRecordRepository extends Repository<ConsumeRecord, String> {

    /**
     * 添加消费记录
     * @param var 消费记录实体
     * @return 添加成功返回消费记录实体，失败返回null
     */
    <S extends ConsumeRecord> S save(S var);

    /**
     * 按审核状态分页查询消费记录
     * @param var 分页实体
     * @param orderStatus 审核状态
     * @return 符合给定审核状态的消费记录分页实体对象
     */
    Page<ConsumeRecord> findAllByOrderStatusIs(Pageable var, String orderStatus);

    /**
     * 按消费记录Id 更新审核状态
     * @param consuId 消费记录id
     * @param orderStatus 审核状态
     */
    @Modifying
    @Query (value = "update ConsumeRecord set orderStatus=:orderStatus " +
            "where consuId=:consuId")
    void updateOrderStatusById(String consuId, String orderStatus);
}
