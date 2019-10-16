package com.lovomall.csc.service;

import com.lovomall.csc.entity.BiddingInfoEntity;

/**
 * 招标信息业务接口
 */
public interface IBiddingInfoService {
    public void add(String supplierId,String procurementPlanId);
}
