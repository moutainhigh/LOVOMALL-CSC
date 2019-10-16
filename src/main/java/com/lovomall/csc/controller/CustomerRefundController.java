package com.lovomall.csc.controller;
import com.lovomall.csc.entity.CustomerRefundEntity;
import com.lovomall.csc.entity.RefundInfoEntity;
import com.lovomall.csc.service.ICustomerRefundService;
import com.lovomall.csc.service.IRefundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/refund")
public class CustomerRefundController {

    @Autowired
    private ICustomerRefundService customerRefundService;
    @Autowired
    private IRefundInfoService refundInfoService;

    @RequestMapping(path = "/findAll")
    @ResponseBody
    public Map<String, Object> loadTableData(int pageNO){

        List<CustomerRefundEntity> list = customerRefundService.findPageList(
                pageNO , 5);
        Map<String, Object> map = new HashMap<>();
        int count=customerRefundService.findList("待审核");
        map.put("data",list );

        map.put("count",count );
        return map;
    }

    @RequestMapping(path = "/update")
    @ResponseBody
    public  String update(String orderCode, String dId,String checDate){

        refundInfoService.updateinfo(dId,checDate,"通过",
        "",new Date(System.currentTimeMillis()));
        customerRefundService.update(orderCode,"已审核");


        return "ok";
    }
    @RequestMapping(path = "/updateInfo")
    @ResponseBody
public  String  updateInfo(String orderCode,String dId,String rejectCause,String checDate){
        System.out.println(orderCode+ "+++++"+dId);
    refundInfoService.updateinfo(dId,checDate,"不通过"
  ,rejectCause ,null);

    customerRefundService.update(orderCode,"已审核");

        return "ok";
}
    @RequestMapping(path = "/findList")
    @ResponseBody
    public   Map<String, Object>findList(String orderCode){
        List<RefundInfoEntity> list = refundInfoService.findInfoList(orderCode);
        Map<String, Object> map = new HashMap<>();
        map.put("data",list );
       // System.out.println(list.size()+"****************************");

        return map;

    }





}
