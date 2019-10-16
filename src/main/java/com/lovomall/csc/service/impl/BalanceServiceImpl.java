package com.lovomall.csc.service.impl;

import com.lovomall.csc.entity.Balance;
import com.lovomall.csc.repository.BalanceRepository;
import com.lovomall.csc.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午9:13
 * Description: 用户余额持久层接口实现类
 * Version: 1.0
 */
@Service(value = "balanceService")
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Balance findBalanceByUserId(String userId) {
        return balanceRepository.findBalanceByUserId(userId);
    }

    @Transactional
    @Override
    public void updateBalance(String userId, double totalPrice) {
        balanceRepository.updateBalance(userId, totalPrice);
    }

    @Transactional
    @Override
    public void updateAccumulate(String userId, double amount) {
        balanceRepository.updateAccumulate(userId, amount);
    }
}
