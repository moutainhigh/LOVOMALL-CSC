package com.lovomall.csc.repository;

import com.lovomall.csc.entity.DiscountLevel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午3:17
 * Description: 折扣等级持久层接口
 * 测试通过 10/10 15：42
 * 实体 ： {@link com.lovomall.csc.entity.DiscountLevel}
 * Version: 1.0
 */
public interface DiscountLevelRepository extends Repository<DiscountLevel, String> {

    /**
     * 添加折扣等级实体
     * {@link org.springframework.data.repository.CrudRepository#save(Object)}
     * @param var 折扣等级实体对象
     * @return 折扣等级实体对象
     */
    <S extends DiscountLevel> S save(S var);

    /**
     * 按id删除折扣等级记录
     * {@link org.springframework.data.repository.CrudRepository#deleteById(Object)}
     * @param id 折扣等级id
     */
    @Modifying
    void deleteById(String id);

    /**
     * 查找所有折扣等级对象
     * @return 折扣等级对象集合
     */
    List<DiscountLevel> findAll();

    /**
     * 按折扣等级id, 修改该折扣等级对应的累计充值金额范围
     * @param id 折扣等级记录id
     * @param minMoney 最小金额
     * @param maxMoney 最大金额
     */
    @Transactional
    @Modifying
    @Query("update DiscountLevel set minMoney=:minMoney, maxMoney=:maxMoney where " +
            "id=:id")
    void updateMoneyRange(String id, double minMoney, double maxMoney);

}
