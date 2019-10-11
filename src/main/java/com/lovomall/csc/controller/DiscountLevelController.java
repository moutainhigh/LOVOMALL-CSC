package com.lovomall.csc.controller;

import com.lovomall.csc.service.DiscountLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:     cafebabe
 * Date:       2019/10/11 
 * Time:       下午2:34
 * Description: 折扣等级业务控制器
 * Version: 1.0
 */
@Controller
@RequestMapping(path = "/discount")
public class DiscountLevelController {

    private final DiscountLevelService discountLevelService;

    @Autowired
    public DiscountLevelController(DiscountLevelService discountLevelService) {
        this.discountLevelService = discountLevelService;
    }

    @RequestMapping(path = "/all")
    @ResponseBody
    public Map<String, Object> findAll(){
        Map<String, Object> map = new HashMap<>();
        map.put("list", discountLevelService.findAll());
        return map;
    }
}
