package com.lovomall.csc.service;

import com.lovomall.csc.entity.Balance;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午9:10
 * Description: 用户余额业务层接口
 * Version: 1.0
 */
public interface BalanceService {

    /**
     * 添加账户余额实体记录
     * @param balance 余额实体对象
     * @return 添加成功返回余额实体对象， 失败返回null
     */
    Balance save(Balance balance);

    /**
     * 按用户🆔id查询余额记录对象
     * @param userId 用户id
     * @return 余额记录对象
     */
    Balance findBalanceByUserId(String userId);

    /**
     * 按用户id，减少用户余额
     * @param userId 用户id
     * @param totalPrice 减少金额
     */
    void updateBalance(String userId, double totalPrice);

    /**
     * 按账户id,增加账户余额，更新累计充值金额
     * @param userId 用户id
     * @param amount 充值金额
     */
    void updateAccumulate(String userId, double amount);
}
