package com.lovomall.csc.controller;

import com.lovomall.csc.entity.ShoppingEntity;
import com.lovomall.csc.service.IOrderdetalisService;
import com.lovomall.csc.service.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping(path = "/shopping")
class ShoppingController {

    private final IShoppingService shoppingService;


    @Autowired
    public ShoppingController(IShoppingService shoppingService) {
        this.shoppingService = shoppingService;

    }


    @RequestMapping(path = "/page")
    @ResponseBody
    public Map<String, Object> loadTableData(int pageNO){
        Map<String, Object> map = new HashMap<>();
        Page<ShoppingEntity> page = shoppingService.findAllByOrderStatusIsNot(
                pageNO-1 , 5, "已审核");

        map.put("data", page.getContent());
        map.put("count", (int)page.getTotalElements());
        System.out.println(map);
        return map;

    }
}
