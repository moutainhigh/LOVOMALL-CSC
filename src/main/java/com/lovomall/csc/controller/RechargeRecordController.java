package com.lovomall.csc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lovomall.csc.dto.RechargeReviewResultDTO;
import com.lovomall.csc.entity.RechargeRecord;
import com.lovomall.csc.entity.RechargeReviewRecord;
import com.lovomall.csc.mqservice.PrecisionMQService;
import com.lovomall.csc.repository.DiscountLevelRepository;
import com.lovomall.csc.service.BalanceService;
import com.lovomall.csc.service.RechargeRecordService;
import com.lovomall.csc.service.RechargeReviewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:     cafebabe
 * Date:       2019/10/11 
 * Time:       下午4:12
 * Description: 预存款充值审核业务控制器
 * Version: 1.0
 */
@Controller
@RequestMapping(path = "/recharge")
public class RechargeRecordController {

    private final PrecisionMQService precisionMQService;
    private final RechargeRecordService rechargeRecordService;
    private final RechargeReviewRecordService rechargeReviewRecordService;
    private final BalanceService balanceService;
    private final DiscountLevelRepository discountLevelRepository;


    @Autowired
    public RechargeRecordController(PrecisionMQService precisionMQService,
                                    RechargeRecordService rechargeRecordService,
                                    RechargeReviewRecordService rechargeReviewRecordService,
                                    BalanceService balanceService,
                                    DiscountLevelRepository discountLevelRepository) {
        this.precisionMQService = precisionMQService;
        this.rechargeRecordService = rechargeRecordService;
        this.rechargeReviewRecordService = rechargeReviewRecordService;
        this.balanceService = balanceService;
        this.discountLevelRepository = discountLevelRepository;
    }

    @RequestMapping(path = "/page")
    @ResponseBody
    public Map<String, Object> loadTableData(int pageNO){
        Map<String, Object> map = new HashMap<>();
        Page<RechargeRecord> page =
                rechargeRecordService.findAllByReviewStatusIs(
                pageNO - 1, 5, "未审核");
        map.put("data", page.getContent());
        map.put("count", (int)page.getTotalElements());
        return map;
    }


    @RequestMapping(path = "/reviewPass")
    @ResponseBody
    public void reviewPass(String upId, String name){
        // 更新审核状态
        rechargeRecordService.updateReviewStatusById(upId, "已审核");

        // 添加审核记录
        RechargeRecord rechargeRecord = rechargeRecordService.findByUpIdIs(upId);
        RechargeReviewRecord reviewRecord = new RechargeReviewRecord();
        reviewRecord.setName(name);
        reviewRecord.setReviewResult("通过");
        reviewRecord.setRechargeRecord(rechargeRecord);
        rechargeReviewRecordService.save(reviewRecord);

        // 更新余额及累计充值金额
        balanceService.updateAccumulate(rechargeRecord.getBalance().getUserId(),
                rechargeRecord.getAmount());
        // 封装审核结果DTO
        RechargeReviewResultDTO resultDTO = new RechargeReviewResultDTO();
        resultDTO.setAccuMoney(rechargeRecord.getAmount());
        resultDTO.setCurrentBalance(rechargeRecord.getBalance().getCurrentBalance());
        resultDTO.setUserId(rechargeRecord.getBalance().getUserId());
        resultDTO.setUpId(upId);
        resultDTO.setReviewResult("通过");

        // 根据余额记录累计充值金查询对应折扣率
        double discountPer = discountLevelRepository.findDiscountPerByAccuMoney(
                rechargeRecord.getBalance().getAccuMoney());

        resultDTO.setDiscountLevel(discountPer);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = mapper.writeValueAsString(resultDTO);
            precisionMQService.rechargeReviewResultQueue(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/reviewFailed")
    @ResponseBody
    public void reviewFailed(String upId, String name, String reviewAdvice){
        // 添加审核记录
        RechargeRecord rechargeRecord = rechargeRecordService.findByUpIdIs(upId);
        RechargeReviewRecord reviewRecord = new RechargeReviewRecord();
        reviewRecord.setName(name);
        reviewRecord.setReviewResult("未通过");
        reviewRecord.setReviewAdvice(reviewAdvice);
        reviewRecord.setRechargeRecord(rechargeRecord);
        rechargeReviewRecordService.save(reviewRecord);
        LocalDate now = LocalDate.now();
        String jsonString =
                "{\"upId\":\"" + upId + "\",\"reviewAdvice\":\"" + reviewAdvice +
                "\",\"reviewResult\":\"" + "未通过" +"\",\"reviewDate\":\"" + now.toString()+ "\"}";

        precisionMQService.rechargeReviewResultQueue(jsonString);
    }

}
