package com.lovomall.csc.service.Impl;

import com.lovomall.csc.dao.IBiddingInfoDao;
import com.lovomall.csc.entity.BiddingInfoEntity;
import com.lovomall.csc.entity.ProcurementPlanEntity;
import com.lovomall.csc.entity.SupplierSignEntity;
import com.lovomall.csc.service.IBiddingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service(value = "biddingInfoService")
public class BiddingInfoServiceImpl implements IBiddingInfoService {
    @Autowired
    private IBiddingInfoDao biddingInfoDao;
    @Override
    public void add(String supplierId,String procurementPlanId) {
        BiddingInfoEntity biddingInfoEntity = new BiddingInfoEntity();
        biddingInfoEntity.setBiddingTime(new Date(System.currentTimeMillis())+"");
        ProcurementPlanEntity procurementPlanEntity = new ProcurementPlanEntity();
        procurementPlanEntity.setId(procurementPlanId);
        biddingInfoEntity.setProcurementPlanEntity(procurementPlanEntity);
        SupplierSignEntity supplierEntity = new SupplierSignEntity();
        supplierEntity.setSupplierId(supplierId);

        biddingInfoEntity.setSupplierEntity(supplierEntity);
        biddingInfoDao.save(biddingInfoEntity);
    }
}
