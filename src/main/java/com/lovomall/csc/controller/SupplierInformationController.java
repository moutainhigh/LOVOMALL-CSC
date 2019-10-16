package com.lovomall.csc.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dto.SupplierOrderDto;
import com.lovomall.csc.entity.SupplierInformationEntity;
import com.lovomall.csc.entity.SupplierReturnEntity;
import com.lovomall.csc.service.ISupplierInformationService;
import com.lovomall.csc.service.ISupplierReturnService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/information")
public class SupplierInformationController {

    @Autowired
    private ISupplierReturnService supplierReturnService;
    @Autowired
    private  ISupplierInformationService supplierInformationService;
    @Autowired
    private JmsMessagingTemplate messagingTemplate;
    @RequestMapping(path = "/page")
    @ResponseBody
    public Map<String, Object> loadTableData(int pageNO){

        List<SupplierReturnEntity> list = supplierReturnService.findStatusList(
                pageNO , 5);
        int count=supplierReturnService.findList("未退款");
        Map<String, Object> map = new HashMap<>();
        map.put("data",list );
        map.put("count",count );
        return map;
    }
    @RequestMapping(path = "/supplierYes")
    @ResponseBody
    public  String updateSupplier(String returnOrderCode,  SupplierInformationEntity supplierInformationEntity){

        //System.out.println(supplierOrderId);
        supplierInformationEntity.setMessageInfo("通过");
        supplierInformationEntity.setCollectionTime(new Date(System.currentTimeMillis()));
        supplierReturnService.updateStatus(returnOrderCode,"已退款",supplierInformationEntity);
        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();
        SupplierReturnEntity s = supplierReturnService.findById(returnOrderCode);

        supplierOrderDto.setExamine(supplierInformationEntity.getMessageInfo());
        supplierOrderDto.setReturnOrderCode(returnOrderCode);
        supplierOrderDto.setProductNumber(s.getProductNumber());
        supplierOrderDto.setProductCode(s.getProductCode());
        supplierOrderDto.setRemark("");
        supplierOrderDto.setExamineDate(supplierInformationEntity.getMessageDate());
        ActiveMQQueue mqQueue = new ActiveMQQueue("getSupplierOrder");

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(supplierOrderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        messagingTemplate.convertAndSend(mqQueue, json);


        return  "ok";
    }
    @RequestMapping(path = "/supplierNo")
    @ResponseBody
    public  String updateSupplier1(String supplierOrderId,String orderStatus,SupplierInformationEntity supplierInformationEntity){

        supplierReturnService.updateStatus(supplierOrderId,"未退款",supplierInformationEntity);
        return  "ok";
    }
    @RequestMapping(path = "/findList")
    @ResponseBody
    public   Map<String, Object>findList(String supplierOrderId){
        List<SupplierInformationEntity> list = supplierInformationService.findList(supplierOrderId);
        Map<String, Object> map = new HashMap<>();
        map.put("data",list );


        return map;

    }


}
