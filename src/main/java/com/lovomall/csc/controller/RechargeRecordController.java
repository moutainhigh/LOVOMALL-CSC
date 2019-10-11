package com.lovomall.csc.controller;

import com.lovomall.csc.entity.RechargeRecord;
import com.lovomall.csc.service.BalanceService;
import com.lovomall.csc.service.RechargeRecordService;
import com.lovomall.csc.service.RechargeReviewRecordService;
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
 * Time:       下午4:12
 * Description: 预存款充值审核业务控制器
 * Version: 1.0
 */
@Controller
@RequestMapping(path = "/recharge")
public class RechargeRecordController {

    private final RechargeRecordService rechargeRecordService;
    private final RechargeReviewRecordService rechargeReviewRecordService;
    private final BalanceService balanceService;

    @Autowired
    public RechargeRecordController(RechargeRecordService rechargeRecordService,
                                    RechargeReviewRecordService rechargeReviewRecordService,
                                    BalanceService balanceService) {
        this.rechargeRecordService = rechargeRecordService;
        this.rechargeReviewRecordService = rechargeReviewRecordService;
        this.balanceService = balanceService;
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
}
