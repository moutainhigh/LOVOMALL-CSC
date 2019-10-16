package com.lovomall.csc.controller;

import com.lovomall.csc.entity.SupplierdetailsEntity;
import com.lovomall.csc.entity.SupplyAudit;
import com.lovomall.csc.entity.SupplyEntity;
import com.lovomall.csc.service.ISupplierdetalisService;
import com.lovomall.csc.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping(path = "/supplyAudit")
public class SupplyAuditController {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

    private final ISupplyService supplyService;
    private final Queue supplierOrderQueue;
    private final Queue productReviewResultQueue;
    private final ISupplierdetalisService supplierdetalisService;
    private final JmsMessagingTemplate jmsMessagingTemplate;


    @Autowired
    public SupplyAuditController(ISupplyService supplyService,
                                 Queue supplierOrderQueue,
                                 Queue productReviewResultQueue,
                                 JmsMessagingTemplate jmsMessagingTemplate,
                                 ISupplierdetalisService supplierdetalisService) {
        this.supplyService = supplyService;
        this.supplierOrderQueue = supplierOrderQueue;
        this.productReviewResultQueue = productReviewResultQueue;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.supplierdetalisService = supplierdetalisService;
    }


    @RequestMapping(path = "/updateT")
    @ResponseBody
    public String priceReviewUpdateT(String deliveryOrderNum, SupplyAudit supplyAudit) {

        supplyAudit.setSupplyResultStatus("通过");
        supplyAudit.setSupplyTime(dateFormat.format(date));
        SupplyEntity supplyEntity = supplyService.findByDeliveryOrderNumIs(deliveryOrderNum);
        supplyEntity.setDeliveryOrderNum(deliveryOrderNum);
        supplyAudit.setSupplyEntity(supplyEntity);
        supplyService.updateSupply("已审核", deliveryOrderNum, supplyAudit);

        SupplierdetailsEntity supplierdetailsEntity = supplierdetalisService.findOrderdetalisById(deliveryOrderNum);
        LocalDate now = LocalDate.now();
        String jsonString = "{\"deliveryOrderNum\":\"" + deliveryOrderNum +"\",\"payStatus\": \"已付款\",\"payDate\":\"" + now.toString() +"\"}";
        jmsMessagingTemplate.convertAndSend(supplierOrderQueue,jsonString);
        System.out.println(supplierdetailsEntity.getProductNumber());
        int productNum = supplierdetailsEntity.getProductNumber();
        String supplier=supplierdetailsEntity.getSupplier();
        double purchasePrice = supplierdetailsEntity.getPurchasePrice();
        SupplyEntity supplyEntity1 = supplyService.findByDeliveryOrderNumIs(deliveryOrderNum);
        String json="{\"deliveryOrderNum\":\""+deliveryOrderNum+"\",\"productNum\":\""+productNum+"\",\"supplier\":\""+supplier+"\",\"purchasePrice\":\""+purchasePrice+"\",\"inDate\":\""+now.toString()+"\"}";
        System.out.println(json);

        jmsMessagingTemplate.convertAndSend(productReviewResultQueue,json);
        return "ok";
    }

    @RequestMapping(path = "/updateF")
    @ResponseBody
    public String priceReviewUpdateF(String deliveryOrderNum, SupplyEntity supplyEntity, SupplyAudit supplyAudit,SupplierdetailsEntity supplierdetailsEntity) {

        supplyAudit.setSupplyResultStatus("不通过");
        supplyAudit.setSupplyTime(dateFormat.format(date));
        supplyEntity.setDeliveryOrderNum(deliveryOrderNum);
        supplyAudit.setSupplyEntity(supplyEntity);
        supplyService.updateSupply("未审核", deliveryOrderNum, supplyAudit);
        LocalDate now = LocalDate.now();
        String jsonString = "{\"deliveryOrderNum\":\"" + deliveryOrderNum +"\",\"payStatus\": \"未付款\",\"payDate\":\"" + now.toString() +"\"}";
        jmsMessagingTemplate.convertAndSend(supplierOrderQueue,jsonString);
        int productNum = supplierdetailsEntity.getProductNumber();
        String supplier=supplierdetailsEntity.getSupplier();
        double purchasePrice = supplierdetailsEntity.getPurchasePrice();

        String json="{\"deliveryOrderNum\":\""+deliveryOrderNum+"\",\"productNum\":\""+productNum+"\",\"supplier\":\""+supplier+"\",\"purchasePrice\":\""+purchasePrice+"\",\"inDate\":\""+now.toString()+"\"}";
        System.out.println(json);
        jmsMessagingTemplate.convertAndSend(productReviewResultQueue,json);
        return "ok";
    }
}
