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
 * Description: TODO
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

    public void rechargeReviewResultQueue(Object object){
        jmsMessagingTemplate.convertAndSend(rechargeReviewResultQueue, object);
    }

    public void consumeReviewResultQueue(Object object){
        jmsMessagingTemplate.convertAndSend(consumeReviewResultQueue, object);
    }

    @JmsListener(destination = "${queuename.precision.result.recharge}")
    public void rechargeReviewQueue(Message message){

    }

    @JmsListener(destination = "${queuename.precision.result.consume}")
    public void consumeReviewQueue(Message message){

    }
}
