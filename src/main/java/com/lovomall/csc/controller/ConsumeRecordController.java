package com.lovomall.csc.controller;

import com.lovomall.csc.entity.ConsumeRecord;
import com.lovomall.csc.service.BalanceService;
import com.lovomall.csc.service.ConsumeRecordService;
import com.lovomall.csc.service.ConsumeReviewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
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

    private final ConsumeRecordService consumeRecordService;
    private final ConsumeReviewRecordService consumeReviewRecordService;
    private final BalanceService balanceService;

    @Autowired
    public ConsumeRecordController(ConsumeRecordService consumeRecordService,
                                   ConsumeReviewRecordService consumeReviewRecordService,
                                   BalanceService balanceService) {

        this.consumeRecordService = consumeRecordService;
        this.consumeReviewRecordService = consumeReviewRecordService;
        this.balanceService = balanceService;
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
}
