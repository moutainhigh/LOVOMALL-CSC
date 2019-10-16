package com.lovomall.csc.controller;

import com.lovomall.csc.entity.OrderdetailsEntity;
import com.lovomall.csc.service.IOrderdetalisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/orderdeta")
public class OrderdetalisController {

    private final IOrderdetalisService orderdetalisService;

    public OrderdetalisController(IOrderdetalisService orderdetalisService) {
        this.orderdetalisService = orderdetalisService;
    }
    @RequestMapping(path = "/findById")
    @ResponseBody
    public Map<String, Object> loadTableData(String id){
        Map<String, Object> map = new HashMap<>();
        List<OrderdetailsEntity> orderdetailsEntity =  orderdetalisService.findListOrderdetalisById(id);
        map.put("data",orderdetailsEntity);
        return map;
    }
}
