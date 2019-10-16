package com.lovomall.csc.mqservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.entity.Balance;
import com.lovomall.csc.entity.RechargeRecord;
import com.lovomall.csc.service.BalanceService;
import com.lovomall.csc.service.RechargeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.io.IOException;
import java.sql.Date;

/**
 * Author:     cafebabe
 * Date:       2019/10/14 
 * Time:       下午2:02
 * Description: 预存款审核业务消息队列
 * Version: 1.0
 */
@Service
public class PrecisionMQService {

    private final JmsMessagingTemplate jmsMessagingTemplate;

    private final Queue rechargeReviewResultQueue;

    private final Queue consumeReviewResultQueue;

    private final RechargeRecordService rechargeRecordService;

    private final BalanceService balanceService;

    @Autowired
    public PrecisionMQService(RechargeRecordService rechargeRecordService,
                              BalanceService balanceService,
                              JmsMessagingTemplate jmsMessagingTemplate,
                              Queue rechargeReviewResultQueue,
                              Queue consumeReviewResultQueue) {

        this.rechargeRecordService = rechargeRecordService;
        this.balanceService = balanceService;

        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.rechargeReviewResultQueue = rechargeReviewResultQueue;
        this.consumeReviewResultQueue = consumeReviewResultQueue;
    }

    /**
     * 发送预存款充值审核结果消息
     * @param string 审核结果
     */
    public void rechargeReviewResultQueue(String string){
        jmsMessagingTemplate.convertAndSend(rechargeReviewResultQueue, string);
    }

    /**
     * 发送预存款结算审核结果消息
     * @param string 审核结果
     */
    public void consumeReviewResultQueue(String string){
        jmsMessagingTemplate.convertAndSend(consumeReviewResultQueue, string);
    }

    /**
     * 监听预存款充值审核消息队列
     * @param rechargeInfo 预存款审核信息json
     */
    @JmsListener(destination = "${queuename.precision.receive.recharge}")
    public void rechargeReviewQueue(String rechargeInfo){
        System.err.println(rechargeInfo);

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(rechargeInfo);
            String upId = node.get("upId").asText();
            String reviewStatus = node.get("reviewStatus").asText();
            double amount = node.get("amount").asDouble();
            Date upDate = Date.valueOf(node.get("upDate").asText());

            Balance balance =
                    balanceService.findBalanceByUserId(node.get("userId").asText());
            RechargeRecord rechargeRecord = new RechargeRecord();
            rechargeRecord.setAmount(amount);
            rechargeRecord.setReviewStatus(reviewStatus);
            rechargeRecord.setUpDate(upDate);
            rechargeRecord.setBalance(balance);
            rechargeRecord.setUpId(upId);
            rechargeRecordService.save(rechargeRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听预存款结算审核消息队列
     * @param string 审核结果
     */
    @JmsListener(destination = "${queuename.precision.receive.consume}")
    public void consumeReviewQueue(String string){

    }
}
