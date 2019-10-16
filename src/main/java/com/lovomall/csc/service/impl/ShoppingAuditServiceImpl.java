package com.lovomall.csc.service.impl;

import com.lovomall.csc.entity.ShoppingAudit;
import com.lovomall.csc.repository.ShoppingAuditRepository;
import com.lovomall.csc.service.IShoppingAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "shoppingAuditService")
@Transactional
public class ShoppingAuditServiceImpl implements IShoppingAuditService {
    @Autowired
    private ShoppingAuditRepository shoppingAuditRepository;

    @Override
    public ShoppingAudit savePriceReviewResult(ShoppingAudit shoppingAudit) {
        return shoppingAuditRepository.save(shoppingAudit);
    }

    @Override
    public void updatePriceReviewResultStatus(String shoppingResultStatus, String shoppingAuditId) {
        shoppingAuditRepository.updatePriceReviewResultStatus(shoppingResultStatus,shoppingAuditId);
    }


}
