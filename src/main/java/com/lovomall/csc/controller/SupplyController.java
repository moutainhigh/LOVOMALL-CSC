package com.lovomall.csc.controller;

import com.lovomall.csc.entity.SupplyEntity;
import com.lovomall.csc.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping(path = "/supply")
public class SupplyController {


    private final ISupplyService supplyService;


    @Autowired
    public SupplyController(Queue supplierOrderQueue,
                            ISupplyService supplyService,
                            JmsMessagingTemplate jmsMessagingTemplate) {

        this.supplyService = supplyService;

    }


    @RequestMapping(path = "/page")
    @ResponseBody
    public Map<String, Object> loadTableData(int pageNO){
        Map<String, Object> map = new HashMap<>();
        Page<SupplyEntity> page = supplyService.findAllByPayStatusIs(pageNO-1,5,"未付款");

        map.put("data", page.getContent());
        map.put("count", (int)page.getTotalElements());
        System.out.println(map);
        return map;

    }
}
