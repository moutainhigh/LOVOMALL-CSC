package com.lovomall.csc.mqservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.Queue;

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

    @Autowired
    public PrecisionMQService(JmsMessagingTemplate jmsMessagingTemplate,
                              Queue rechargeReviewResultQueue,
                              Queue consumeReviewResultQueue) {

        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.rechargeReviewResultQueue = rechargeReviewResultQueue;
        this.consumeReviewResultQueue = consumeReviewResultQueue;
    }

    /**
     * 发送预存款充值审核结果消息
     * @param object 审核结果
     */
    public void rechargeReviewResultQueue(Object object){
        jmsMessagingTemplate.convertAndSend(rechargeReviewResultQueue, object);
    }

    /**
     * 发送预存款结算审核结果消息
     * @param object 审核结果
     */
    public void consumeReviewResultQueue(Object object){
        jmsMessagingTemplate.convertAndSend(consumeReviewResultQueue, object);
    }

    /**
     * 监听预存款充值审核消息队列
     * @param message 审核结果
     */
    @JmsListener(destination = "${queuename.precision.receive.recharge}")
    public void rechargeReviewQueue(Message message){

    }

    /**
     * 监听预存款结算审核消息队列
     * @param message 审核结果
     */
    @JmsListener(destination = "${queuename.precision.receive.consume}")
    public void consumeReviewQueue(Message message){

    }
}
