package com.lovomall.csc.repository;

import com.lovomall.csc.entity.RechargeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午4:26
 * Description: 充值记录持久层接口
 * Version: 1.0
 */
public interface RechargeRecordRepository extends Repository<RechargeRecord, String> {

    /**
     * 添加充值记录
     * @param var 充值记录实体
     * @return 添加成功返回充值记录实体，失败返回null
     */
    <S extends RechargeRecord> S save(S var);

    /**
     * 按审核状态分页查询充值记录
     * @param var 分页实体
     * @param reviewStatus 审核状态
     * @return 符合给定审核状态的充值记录分页实体对象
     */
    Page<RechargeRecord> findAllByReviewStatusIs(Pageable var, String reviewStatus);

    /**
     * 按充值记录Id 更新审核状态
     * @param upId 充值记录id
     * @param reviewStatus 审核状态
     */
    @Modifying
    @Query(value = "update RechargeRecord set reviewStatus=:reviewStatus " +
            "where upId=:upId")
    void updateReviewStatusById(String upId, String reviewStatus);

    /**
     * 按充值记录id查找充值记录对象
     * @param upId 充值记录id
     * @return 充值记录对象
     */
    RechargeRecord findByUpIdIs(String upId);
}
