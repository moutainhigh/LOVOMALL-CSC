package com.lovomall.csc.service;

import com.lovomall.csc.entity.RechargeRecord;

import java.util.List;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午9:27
 * Description: 预存款充值业务层接口
 * Version: 1.0
 */
public interface RechargeRecordService {
    /**
     * 添加充值记录
     * @param rechargeRecord 充值记录实体
     * @return 添加成功返回充值记录实体，失败返回null
     */
    RechargeRecord save(RechargeRecord rechargeRecord);

    /**
     * 分页查询待审核状态充值记录
     * @param pageNO 页码
     * @param pageSize 页大小
     * @param reviewStatus 审核状态
     * @return 符合条件的审核记录对象集合
     */
    List<RechargeRecord> findAllByReviewStatusIs(int pageNO, int pageSize, String reviewStatus);

    /**
     * 按充值记录Id 更新审核状态
     * @param upId 充值记录id
     * @param reviewStatus 审核状态
     */
    void updateReviewStatusById(String upId, String reviewStatus);
}
