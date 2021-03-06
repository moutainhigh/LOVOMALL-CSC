package com.lovomall.csc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dto.ConsumeReviewResultDTO;
import com.lovomall.csc.entity.Balance;
import com.lovomall.csc.entity.ConsumeRecord;
import com.lovomall.csc.entity.ConsumeReviewRecord;
import com.lovomall.csc.mqservice.PrecisionMQService;
import com.lovomall.csc.repository.DiscountLevelRepository;
import com.lovomall.csc.service.BalanceService;
import com.lovomall.csc.service.ConsumeRecordService;
import com.lovomall.csc.service.ConsumeReviewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:     cafebabe
 * Date:       2019/10/11 
 * Time:       下午4:09
 * Description: 预存款支付审核业务控制器
 * Version: 1.0
 */
@Controller
@RequestMapping(path = "/consume")
public class ConsumeRecordController {

    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Queue shoppingReviewResultQueue;
    private final PrecisionMQService precisionMQService;
    private final ConsumeRecordService consumeRecordService;
    private final ConsumeReviewRecordService consumeReviewRecordService;
    private final BalanceService balanceService;
    private final DiscountLevelRepository discountLevelRepository;

    @Autowired
    public ConsumeRecordController(JmsMessagingTemplate jmsMessagingTemplate, Queue shoppingReviewResultQueue, PrecisionMQService precisionMQService,
                                   ConsumeRecordService consumeRecordService,
                                   ConsumeReviewRecordService consumeReviewRecordService,
                                   BalanceService balanceService,
                                   DiscountLevelRepository discountLevelRepository) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.shoppingReviewResultQueue = shoppingReviewResultQueue;
        this.precisionMQService = precisionMQService;
        this.consumeRecordService = consumeRecordService;
        this.consumeReviewRecordService = consumeReviewRecordService;
        this.balanceService = balanceService;
        this.discountLevelRepository = discountLevelRepository;
    }

    @RequestMapping(path = "/page")
    @ResponseBody
    public Map<String, Object> loadTableData(int pageNO){
        Map<String, Object> map = new HashMap<>();
        Page<ConsumeRecord> page = consumeRecordService.findAllByOrderStatusIs(
                pageNO - 1, 5, "未审核");

        map.put("data", page.getContent());
        map.put("count", (int)page.getTotalElements());
        return map;
    }

    @RequestMapping(path = "/discountPer")
    @ResponseBody
    public Map<String, Object> discountPer(String consuId){

        ConsumeRecord consumeRecord = consumeRecordService.findByConsuIdIs(consuId);
        Balance balance = consumeRecord.getBalance();
        Map<String, Object> map = new HashMap<>();

        // 根据余额记录累计充值金查询对应折扣率
        double discountPer = discountLevelRepository.findDiscountPerByAccuMoney(
                balance.getAccuMoney());
        map.put("payMoney", consumeRecord.getTotalPrice() * discountPer);
        map.put("discountPer", discountPer);
        return map;
    }

    @RequestMapping(path = "/reviewPass")
    @ResponseBody
    public void reviewPass(String consuId, String name,double payMoney){
        ConsumeRecord consumeRecord = consumeRecordService.findByConsuIdIs(consuId);
        consumeRecord.setPayMoney(payMoney);
        consumeRecord.setOrderStatus("已审核");
        consumeRecordService.save(consumeRecord);
        // 添加消费审核记录
        ConsumeReviewRecord reviewRecord = new ConsumeReviewRecord();
        reviewRecord.setName(name);
        reviewRecord.setReviewResult("通过");
        reviewRecord.setConsumeRecord(consumeRecord);

        // 更新余额
        balanceService.updateBalance(consumeRecord.getBalance().getUserId(),
                payMoney);

        // 发送审核结果消息至前端
        ConsumeReviewResultDTO resultDTO = new ConsumeReviewResultDTO();
        resultDTO.setPayMoney(payMoney);
        resultDTO.setUserId(consuId);
        resultDTO.setOrderId(consumeRecord.getOrderId());
        resultDTO.setReviewResult("通过");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(resultDTO);
            jmsMessagingTemplate.convertAndSend(shoppingReviewResultQueue,jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/reviewFailed")
    @ResponseBody
    public void reviewFailed(String consuId, String name, String reviewAdvice){

        // 按消费记录查找消费记录对象
        ConsumeRecord consumeRecord = consumeRecordService.findByConsuIdIs(consuId);
        // 添加消费审核记录
        ConsumeReviewRecord reviewRecord = new ConsumeReviewRecord();
        reviewRecord.setName(name);
        reviewRecord.setReviewAdvice(reviewAdvice);
        reviewRecord.setReviewResult("未通过");
        reviewRecord.setConsumeRecord(consumeRecord);

        consumeReviewRecordService.save(reviewRecord);

        // 发送审核结果消息至前端
        ConsumeReviewResultDTO resultDTO = new ConsumeReviewResultDTO();
        resultDTO.setPayMoney(0);
        resultDTO.setUserId(consuId);
        resultDTO.setOrderId(consumeRecord.getOrderId());
        resultDTO.setReviewResult("未通过");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(resultDTO);
            jmsMessagingTemplate.convertAndSend(shoppingReviewResultQueue,jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
