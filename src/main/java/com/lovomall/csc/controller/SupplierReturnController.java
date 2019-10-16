package com.lovomall.csc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dto.SupplierOrderDto;
import com.lovomall.csc.entity.SupplierInfoEntity;
import com.lovomall.csc.entity.SupplierReturnEntity;
import com.lovomall.csc.service.ISupplierReturnService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/supplier")
public class SupplierReturnController {
    @Autowired
    private ISupplierReturnService supplierReturnService;
    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    @RequestMapping(path = "/page")
    @ResponseBody
    public Map<String, Object> loadTableData(int pageNO) {

        List<SupplierReturnEntity> list = supplierReturnService.findSupplierList(
                pageNO, 5);
        int count = supplierReturnService.findList("未处理");
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("count", count);
        return map;
    }

    @RequestMapping(path = "/supplierYes")
    @ResponseBody
    public String updateSupplier(String returnOrderCode, String orderStatus, SupplierInfoEntity supplierInfoEntity) {

        System.out.println(returnOrderCode);
        supplierInfoEntity.setExamine("通过");
        supplierReturnService.updateSupplier(returnOrderCode, "未退款", supplierInfoEntity);
        SupplierReturnEntity s = supplierReturnService.findById(returnOrderCode);

        ActiveMQQueue mqQueue = new ActiveMQQueue("returnInfoQueue");

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        messagingTemplate.convertAndSend(mqQueue, json);




        return "ok";
    }

    @RequestMapping(path = "/supplierNo")
    @ResponseBody
    public String updateSupplier1(String returnOrderCode, SupplierInfoEntity supplierInfoEntity) {
        supplierInfoEntity.setExamine("不通过");

     //   System.out.println(returnOrderCode);
//        System.out.println(productNumber);
        supplierReturnService.updateSupplier(returnOrderCode, "已审核", supplierInfoEntity);
        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();
        SupplierReturnEntity s = supplierReturnService.findById(returnOrderCode);

        supplierOrderDto.setExamine(supplierInfoEntity.getExamine());
        supplierOrderDto.setReturnOrderCode(returnOrderCode);
        supplierOrderDto.setProductNumber(s.getProductNumber());
        supplierOrderDto.setProductCode(s.getProductCode());
        supplierOrderDto.setRemark(supplierInfoEntity.getRemark());
        supplierOrderDto.setExamineDate(supplierInfoEntity.getExamineDate());
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



}
