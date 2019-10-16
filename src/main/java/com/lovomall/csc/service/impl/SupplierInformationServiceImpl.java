package com.lovomall.csc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dao.ISupplierInformationDao;
import com.lovomall.csc.entity.SupplierInformationEntity;
import com.lovomall.csc.service.ISupplierInformationService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service("supplierInformationService")
@Transactional
public class SupplierInformationServiceImpl implements ISupplierInformationService {
    @Autowired
    private ISupplierInformationDao supplierInformationDao;
    @Autowired
    private JmsMessagingTemplate messagingTemplate;
    public void addInformation(SupplierInformationEntity supplierInformationEntity) {
        supplierInformationDao.save(supplierInformationEntity);
        ActiveMQQueue mqQueue = new ActiveMQQueue("returnMessageQueue");

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(supplierInformationEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        messagingTemplate.convertAndSend(mqQueue, json);


    }

    @Override
    public List<SupplierInformationEntity> findList(String supplierOrderId) {
        return supplierInformationDao.findList(supplierOrderId);
    }

    @JmsListener(destination = "messageQueue")
    public  void  saveInformatio(String informatioJson) throws IOException {
        ObjectMapper obj=new ObjectMapper();

        SupplierInformationEntity supplierInformationEntity=obj.readValue(informatioJson,
                SupplierInformationEntity.class);
        supplierInformationDao.save(supplierInformationEntity);

    }


}
