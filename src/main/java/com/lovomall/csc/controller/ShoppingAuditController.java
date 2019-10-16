package com.lovomall.csc.controller;

import com.lovomall.csc.entity.ShoppingAudit;
import com.lovomall.csc.entity.ShoppingEntity;
import com.lovomall.csc.service.IShoppingAuditService;
import com.lovomall.csc.service.IShoppingService;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.jms.Queue;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping(path = "/shoppingAudit")
public class ShoppingAuditController {
    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

    private final IShoppingService shoppingService;
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Queue shoppingReviewResultQueue;

    public ShoppingAuditController(IShoppingService shoppingService,
                                   JmsMessagingTemplate jmsMessagingTemplate,
                                   Queue shoppingReviewResultQueue) {
        this.shoppingService = shoppingService;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.shoppingReviewResultQueue = shoppingReviewResultQueue;
    }

    @RequestMapping(path = "/updateT")
    @ResponseBody
    public String priceReviewUpdateT(String id, ShoppingEntity shoppingEntity, ShoppingAudit shoppingAudit){

       shoppingAudit.setShoppingResultStatus("通过");
        shoppingAudit.setShoppingTime(dateFormat.format(date));
        shoppingEntity.setID(id);
        shoppingAudit.setShoppingEntity(shoppingEntity);
        shoppingService.updateShopping("已审核",id,shoppingAudit);
        LocalDate now = LocalDate.now();
        String jsonString = "{\"ID\":\"" + id +"\",\"orderStatus\": \"通过\",\"payDate\":\"" + now.toString() +"\"}";
        jmsMessagingTemplate.convertAndSend(shoppingReviewResultQueue,jsonString);
        System.out.println(jsonString);
        return "ok";
    }

    @RequestMapping(path = "/updateF")
    @ResponseBody
    public String priceReviewUpdateF(String id, ShoppingEntity shoppingEntity, ShoppingAudit shoppingAudit){

        String opinion = shoppingAudit.getShoppingOpinion();
        shoppingAudit.setShoppingResultStatus("不通过");
        shoppingAudit.setShoppingTime(dateFormat.format(date));
        shoppingEntity.setID(id);
        shoppingAudit.setShoppingEntity(shoppingEntity);
        shoppingService.updateShopping("未审核",id,shoppingAudit);
        LocalDate now = LocalDate.now();
        String jsonString = "{\"ID\":\"" + id +"\",\"orderStatus\": \"未通过\",\"payDate\":\"" + now.toString() +"\",\"reviewAdvice\":\"" + opinion +"\"}";
        jmsMessagingTemplate.convertAndSend(shoppingReviewResultQueue,jsonString);
        System.out.println(jsonString);
        return "ok";
    }
}
