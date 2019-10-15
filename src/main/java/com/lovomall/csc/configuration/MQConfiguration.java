package com.lovomall.csc.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * Author:     cafebabe
 * Date:       2019/10/14 
 * Time:       下午1:55
 * Description: 项目MQ消息队列配置类
 * Version: 1.0
 */
@Configuration
public class MQConfiguration {

    @Value(value = "${queuename.precision.result.recharge}")
    private String RECHARGE_REVIEW_RESULT_QUEUE;

    @Value(value = "${queuename.precision.result.consume}")
    private String CONSUME_REVIEW_RESULT_QUEUE;

    /** 预存款充值审核结果发送队列*/
    @Bean
    public Queue rechargeReviewResultQueue(){
        return new ActiveMQQueue(RECHARGE_REVIEW_RESULT_QUEUE);
    }

    /** 预存款结算审核结果发送队列*/
    @Bean
    public Queue consumeReviewResultQueue(){
        return new ActiveMQQueue(CONSUME_REVIEW_RESULT_QUEUE);
    }
}
