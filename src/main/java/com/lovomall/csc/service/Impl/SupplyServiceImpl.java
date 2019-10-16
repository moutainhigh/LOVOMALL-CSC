package com.lovomall.csc.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.entity.SupplyAudit;
import com.lovomall.csc.entity.SupplyEntity;
import com.lovomall.csc.repository.SupplyAuditRepository;
import com.lovomall.csc.repository.SupplyRepository;
import com.lovomall.csc.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service(value = "supplyService")
@Transactional
public class SupplyServiceImpl implements ISupplyService {
    @Autowired
    private SupplyRepository supplyRepository;
    @Autowired
    private SupplyAuditRepository supplyAuditRepository;

    @Override
    public SupplyEntity svaeSupply(SupplyEntity supplyEntity) {
        return supplyRepository.save(supplyEntity);
    }

    @Override
    public List<SupplyEntity> findListSupply() {
        return (List<SupplyEntity>)supplyRepository.findAll();
    }

    @Override
    public void updateSupply(String payStatus, String deliveryOrderNum, SupplyAudit supplyAudit) {
        supplyRepository.updateSupply(payStatus,deliveryOrderNum);
        supplyAuditRepository.save(supplyAudit);
    }


    @Override
    public Page<SupplyEntity> findAllByPayStatusIs(int pageNO, int pageSize, String payStatus) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);
        return supplyRepository.findAllByPayStatusIs(pageable,payStatus);
    }

    @Override
    public SupplyEntity findByDeliveryOrderNumIs(String deliveryOrderNum) {
        return supplyRepository.findByDeliveryOrderNumIs(deliveryOrderNum);
    }



}
