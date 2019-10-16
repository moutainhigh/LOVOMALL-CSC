package com.lovomall.csc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.entity.*;
import com.lovomall.csc.service.IBiddingInfoService;
import com.lovomall.csc.service.IProcurementPlanService;
import com.lovomall.csc.service.ISupplierService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
@RequestMapping(path = "/supplier")
public class SupplierPurchaseController {

    @Autowired
    private JmsMessagingTemplate messagingTemplate;
    @Autowired
    private IBiddingInfoService biddingInfoService;
    @Autowired
    private IProcurementPlanService procurementPlanService;
    @RequestMapping("/all")
    @ResponseBody
    public Map<String, Object> procurement(int pageNO) throws JsonProcessingException {
        List<ProcurementPlanEntity> list = procurementPlanService.getPageListProcurementPlanEntity(pageNO-1, 3);
        int i = procurementPlanService.countByStatus();
        System.err.println(i);
        Map<String, Object> map = new HashMap<>();
        map.put("count",i);
        map.put("data",list);
        return map;
    }

    @Autowired
    private ISupplierService supplierService;

    @RequestMapping("/allSupplier")
    @ResponseBody
    public Map<String, Object> supplierPage(int pageNO) throws JsonProcessingException {
        List<SupplierSignEntity> list = supplierService.getPageListSupplier(pageNO-1, 3);
        int i1 = supplierService.countByStatus();
        Map<String, Object> map = new HashMap<>();
        map.put("count",i1);
        map.put("data",list);
        return map;
    }

    @RequestMapping("/supplierExamineNo")
    @ResponseBody
    public void supplierExamineNo(String id, String grade, ExamineInfoEntity examineInfoEntity){
        examineInfoEntity.setStatus("审核未通过");
        supplierService.updateStatus(id,grade,examineInfoEntity,"审核未通过");
        Optional<SupplierSignEntity> byId = supplierService.findById(id);

        String supplierLogo = byId.get().getSupplierLogo();
        String json = "{\"supplierGrade\":\""+grade+"\",\"supplierLogo\":\""+supplierLogo+"\",\"supplierState\":\"审核未通过\"}\n";
//        System.err.println(json);
        ActiveMQQueue mqQueue=new ActiveMQQueue("supplierExamine");
        messagingTemplate.convertAndSend(mqQueue,json);
    }

    @RequestMapping("/supplierExamineYes")
    @ResponseBody
    public void supplierExamineYes(String id, String grade, ExamineInfoEntity examineInfoEntity){
        examineInfoEntity.setStatus("审核通过");
        supplierService.updateStatus(id,grade,examineInfoEntity,"审核通过");

        Optional<SupplierSignEntity> byId = supplierService.findById(id);
        String supplierLogo = byId.get().getSupplierLogo();
        String json = "{\"supplierGrade\":\""+grade+"\",\"supplierLogo\":\""+supplierLogo+"\",\"supplierState\":\"审核通过\"}\n";
//        System.err.println(json);
        ActiveMQQueue mqQueue=new ActiveMQQueue("supplierExamine");
        messagingTemplate.convertAndSend(mqQueue,json);
    }

    @RequestMapping("/procurementExamineNo")
    @ResponseBody
    public void procurementExamineNo(String id, String grade, ProcurementPlanInfoEntity procurementPlanInfoEntity){
        procurementPlanInfoEntity.setStatus("审核未通过");
        procurementPlanService.updateStatus(id,procurementPlanInfoEntity);
        String examineInfo = procurementPlanInfoEntity.getExamineInfo();
        String status = procurementPlanInfoEntity.getStatus();
        String code = procurementPlanService.findByIdCode(id);

//        System.err.println("productCode:"+code);
//        System.err.println("examineInfo:"+examineInfo);
//        System.err.println("status:"+status);
        String json = "{\"productCode\":\""+code+"\",\"examineInfo\":\""+examineInfo+"\",\"status\":\""+status+"\"}\n";
//        System.err.println(json);
        ActiveMQQueue mqQueue=new ActiveMQQueue("PURCHASE_REVIEW_RESULT_QUEUE");
        messagingTemplate.convertAndSend(mqQueue,json);
    }

    @RequestMapping("/procurementExamineYes")
    @ResponseBody
    public void procurementExamineYes(String id,ProcurementPlanInfoEntity procurementPlanInfoEntity){
        procurementPlanInfoEntity.setStatus("审核通过");
        procurementPlanService.updateStatus(id,procurementPlanInfoEntity);
        String examineInfo = procurementPlanInfoEntity.getExamineInfo();
        String status = procurementPlanInfoEntity.getStatus();
        String code = procurementPlanService.findByIdCode(id);

//        System.err.println("productCode:"+code);
//        System.err.println("examineInfo:"+examineInfo);
//        System.err.println("status:"+status);
        String json = "{\"productCode\":\""+code+"\",\"examineInfo\":\""+examineInfo+"\",\"status\":\""+status+"\"}\n";
//        System.err.println(json);
        ActiveMQQueue mqQueue=new ActiveMQQueue("PURCHASE_REVIEW_RESULT_QUEUE");
        messagingTemplate.convertAndSend(mqQueue,json);
    }

    @RequestMapping("/biddingInfo")
    @ResponseBody
    public Map<String, Object> supplierBiddingPage(int pageNO,String supplierScope) throws JsonProcessingException {
        List<SupplierSignEntity> list = supplierService.getPageListSupplierBidding(supplierScope,pageNO-1, 3);
        int i1 = supplierService.countBySupplierScope(supplierScope);
        Map<String, Object> map = new HashMap<>();
        map.put("count",i1);
        map.put("data",list);
        return map;
    }


    @RequestMapping("/biddingInfoAdd")
    @ResponseBody
    public void biddingInfoAdd(String supplierId,String procurementPlanId) throws JsonProcessingException {
        biddingInfoService.add(supplierId,procurementPlanId);
        Optional<ProcurementPlanEntity> procurementPlanEntity = procurementPlanService.findById(procurementPlanId);
        Optional<SupplierSignEntity> supplierSignEntity = supplierService.findById(supplierId);
        String supplierLogo = supplierSignEntity.get().getSupplierLogo();

        ActiveMQQueue mqQueue=new ActiveMQQueue("tenderInfo");
        Bidding bidding = new Bidding();
        bidding.setProductId(procurementPlanEntity.get().getProductCode());
        bidding.setProductName(procurementPlanEntity.get().getProductName());
        bidding.setProductSpec(procurementPlanEntity.get().getProductSpec());
        bidding.setProductType(procurementPlanEntity.get().getProductType());
        bidding.setProductNum(procurementPlanEntity.get().getPurchaseNum());
        bidding.setSupplierLogo(supplierLogo);


        //把user对象转换为json
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(bidding);
        System.err.println(json);
        messagingTemplate.convertAndSend(mqQueue,json);
    }
}
