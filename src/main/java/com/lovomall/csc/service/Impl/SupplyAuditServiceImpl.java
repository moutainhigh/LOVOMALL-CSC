package com.lovomall.csc.service.Impl;

import com.lovomall.csc.entity.SupplyAudit;
import com.lovomall.csc.repository.SupplyAuditRepository;
import com.lovomall.csc.service.ISupplyAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "supplyAuditService")
@Transactional
public class SupplyAuditServiceImpl implements ISupplyAuditService {
    @Autowired
    private SupplyAuditRepository supplyAuditRepository;
    @Override
    public SupplyAudit savePriceReviewResult(SupplyAudit supplyAudit) {
        return supplyAuditRepository.save(supplyAudit);
    }

    @Override
    public void updatePriceReviewResultStatus(String supplyResultStatus, String supplyAuditId) {
        supplyAuditRepository.updatePriceReviewResultStatus(supplyResultStatus,supplyAuditId);

    }
}
