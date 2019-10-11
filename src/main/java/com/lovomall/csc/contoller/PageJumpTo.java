package com.lovomall.csc.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:     cafebabe
 * Date:       2019/10/11 
 * Time:       上午11:24
 * Description: templates 下html文件无法直接访问，需通过控制器跳转。
 * Version: 1.0
 */
@Controller
public class PageJumpTo {

    @RequestMapping(path = "/frame")
    public String frame(){ return "frame"; }

    @RequestMapping(path = "/updatePwd")
    public String updatePwd(){ return "updatePwd"; }

    @RequestMapping(path = "/priceReview")
    public String priceReview(){ return "QuotationAudit/priceReview"; }

    @RequestMapping(path = "/promotion")
    public String promotion(){ return "MarketingAudit/promotion"; }

    @RequestMapping(path = "/precisionUpTo")
    public String precisionUpTo(){ return "AdvanceDepositAudit/precisionUpTo"; }

    @RequestMapping(path = "/precisionPayReview")
    public String precisionPayReview(){ return "AdvanceDepositAudit/precisionPayReview"; }

    @RequestMapping(path = "/discountLevel")
    public String discountLevel(){ return "AdvanceDepositAudit/discountLevel"; }

    @RequestMapping(path = "/supplierReturn")
    public String supplierReturn(){ return "RefundAudit/supplierReturn"; }

    @RequestMapping(path = "/salesReturn")
    public String salesReturn(){ return "RefundAudit/salesReturn"; }

    @RequestMapping(path = "/refund")
    public String refund(){ return "RefundAudit/refund"; }

    @RequestMapping(path = "/supplerRegiste")
    public String supplerRegiste(){ return "APAuditor/supplerRegiste"; }

    @RequestMapping(path = "/purchaseInfo")
    public String purchaseInfo(){ return "APAuditor/purchaseInfo"; }

    @RequestMapping(path = "/userStatusAuitd")
    public String userStatusAuitd(){ return "userAudit/userStatusAuitd"; }

    @RequestMapping(path = "/userAddAuitd")
    public String userAddAuitd(){ return "userAudit/userAddAuitd"; }

    //SettlementAudit/agent_list
    @RequestMapping(path = "/agent_list")
    public String agent_list(){ return "SettlementAudit/agent_list"; }

    @RequestMapping(path = "/agent_add")
    public String agent_add(){ return "SettlementAudit/agent_add"; }
}
