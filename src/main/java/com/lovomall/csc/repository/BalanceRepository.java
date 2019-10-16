package com.lovomall.csc.repository;

import com.lovomall.csc.entity.Balance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午3:57
 * Description: 用户余额持久层接口
 * Version: 1.0
 */
public interface BalanceRepository extends Repository<Balance, String> {

    /**
     * 添加账户余额实体记录
     * @param var 余额实体对象
     * @return 添加成功返回余额实体对象， 失败返回null
     */
    <S extends Balance> S save(S var);

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
    @Modifying
    @Query(value = "update Balance set currentBalance = currentBalance - " +
            ":totalPrice where userId=:userId")
    void updateBalance(String userId, double totalPrice);

    /**
     * 按账户id,增加账户余额，更新累计充值金额
     * @param userId 用户id
     * @param amount 充值金额
     */
    @Modifying
    @Query(value = "update Balance set currentBalance = currentBalance + " +
            ":amount, accuMoney = accuMoney + :amount where userId=:userId")
    void updateAccumulate(String userId, double amount);
}
