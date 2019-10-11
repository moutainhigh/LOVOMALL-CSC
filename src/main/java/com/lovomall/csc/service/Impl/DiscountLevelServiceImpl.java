package com.lovomall.csc.service.Impl;

import com.lovomall.csc.entity.DiscountLevel;
import com.lovomall.csc.repository.DiscountLevelRepository;
import com.lovomall.csc.service.DiscountLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午9:22
 * Description: 折扣等级业务层接口
 * Version: 1.0
 */
@Service(value = "discountLevelService")
public class DiscountLevelServiceImpl implements DiscountLevelService {

    private final DiscountLevelRepository discountLevelRepository;

    @Autowired
    public DiscountLevelServiceImpl(DiscountLevelRepository discountLevelRepository) {
        this.discountLevelRepository = discountLevelRepository;
    }

    @Override
    public DiscountLevel save(DiscountLevel discountLevel) {
        return discountLevelRepository.save(discountLevel);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        discountLevelRepository.deleteById(id);
    }

    @Override
    public List<DiscountLevel> findAll() {
        return discountLevelRepository.findAll();
    }

    @Override
    public void updateMoneyRange(String id, double minMoney, double maxMoney) {
        discountLevelRepository.updateMoneyRange(id, minMoney, maxMoney);
    }
}
