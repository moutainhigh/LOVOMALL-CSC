package com.lovomall.csc.service;

import com.lovomall.csc.entity.ShoppingEntity;
import com.lovomall.csc.entity.SupplyAudit;
import com.lovomall.csc.entity.SupplyEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISupplyService {
    public SupplyEntity svaeSupply(SupplyEntity supplyEntity);
    public List<SupplyEntity>findListSupply();

    public void updateSupply(String payStatus, String deliveryOrderNum, SupplyAudit supplyAudit);

    Page<SupplyEntity> findAllByPayStatusIs(int pageNO, int pageSize, String payStatus);

    SupplyEntity findByDeliveryOrderNumIs(String deliveryOrderNum);
}
