package com.lovomall.csc.controller;
import com.lovomall.csc.entity.PriceReview;
import com.lovomall.csc.service.IPriceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GYM
 * @version 1.0
 * @date 2019/10/11 17:23
 */
@Controller
@RequestMapping(path = "/pagePriceReview")
public class PriceReviewController {
    @Autowired
    private final IPriceReviewService priceReviewService;


    public PriceReviewController(IPriceReviewService priceReviewService) {
        this.priceReviewService = priceReviewService;
    }

    @RequestMapping(path = "/pageP")
    @ResponseBody
    public Map<String, Object> loadTableData(int pageNO){
        Map<String, Object> map = new HashMap<>();
            Page<PriceReview> page = priceReviewService.findAllByPriceReviewStatusIs(
                    pageNO - 1, 5, "未审核");
            map.put("data", page.getContent());
            map.put("count", (int) page.getTotalElements());
        return map;
    }
}
