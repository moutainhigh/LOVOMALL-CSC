package com.lovomall.csc.service;

import com.lovomall.csc.entity.DiscountLevel;

import java.util.List;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午9:16
 * Description: 折扣等级业务层接口
 * Version: 1.0
 */
public interface DiscountLevelService {

    /**
     * 添加折扣等级实体
     * {@link com.lovomall.csc.repository.DiscountLevelRepository#save(com.lovomall.csc.entity.DiscountLevel)}
     * @param discountLevel 折扣等级实体对象
     * @return 折扣等级实体对象
     */
    DiscountLevel save(DiscountLevel discountLevel);

    /**
     * 按id删除折扣等级记录
     * {@link com.lovomall.csc.repository.DiscountLevelRepository#deleteById(String)}
     * @param id 折扣等级id
     */
    void deleteById(String id);

    /**
     * 查找所有折扣等级对象
     * {@link com.lovomall.csc.repository.DiscountLevelRepository#findAll()}
     * @return 折扣等级对象集合
     */
    List<DiscountLevel> findAll();

    /**
     * 按折扣等级id, 修改该折扣等级对应的累计充值金额范围
     * {@link com.lovomall.csc.repository.DiscountLevelRepository#updateMoneyRange(String, double, double)}
     * @param id 折扣等级记录id
     * @param minMoney 最小金额
     * @param maxMoney 最大金额
     */
    void updateMoneyRange(String id, double minMoney, double maxMoney);
}
