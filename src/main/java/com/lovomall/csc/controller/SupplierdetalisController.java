package com.lovomall.csc.controller;

import com.lovomall.csc.entity.OrderdetailsEntity;
import com.lovomall.csc.entity.SupplierdetailsEntity;
import com.lovomall.csc.service.ISupplierdetalisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/supplierdetalis")
public class SupplierdetalisController {
    @Autowired
    private final ISupplierdetalisService supplierdetalisService;

    public SupplierdetalisController(ISupplierdetalisService supplierdetalisService) {
        this.supplierdetalisService = supplierdetalisService;
    }
    @RequestMapping(path = "/page")
    @ResponseBody
    public Map<String, Object> loadTableData(String deliveryOrderNum){
        Map<String, Object> map = new HashMap<>();
        SupplierdetailsEntity supplierdetailsEntity =  supplierdetalisService.findOrderdetalisById(deliveryOrderNum);
        map.put("data",supplierdetailsEntity);
        return map;
    }
}
