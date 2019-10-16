package com.lovomall.csc.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovomall.csc.entity.PriceReview;
import com.lovomall.csc.entity.PriceReviewResult;
import com.lovomall.csc.mqservice.PriceReviewMQService;
import com.lovomall.csc.service.IPriceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GYM
 * @version 1.0
 * @date 2019/10/12 14:54
 */
@Controller
@RequestMapping(path = "/priceReview")
public class PriceReviewInfoController {


    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

    private final PriceReviewMQService priceReviewMQService;
    private final IPriceReviewService priceReviewService;
    @Autowired
    public PriceReviewInfoController(PriceReviewMQService priceReviewMQService, IPriceReviewService priceReviewService) {
        this.priceReviewMQService = priceReviewMQService;
        this.priceReviewService = priceReviewService;
    }


    @RequestMapping(path = "/findById")
    @ResponseBody
    public Map<String, Object> loadTableData(String priceReviewId){
        Map<String, Object> map = new HashMap<>();
        PriceReview priceReview = priceReviewService.findById(priceReviewId);
        map.put("data",priceReview);
        return map;
    }

    @RequestMapping(path = "/updateNum")
    @ResponseBody
    public void updateNum(String priceReviewId,int productNum){
        priceReviewService.updateProductNum(productNum,priceReviewId);
    }

    @RequestMapping(path = "/updateT")
    @ResponseBody
    public String priceReviewUpdateT(String priceReviewId,PriceReview priceReview,PriceReviewResult priceReviewResult) throws JsonProcessingException {

        priceReviewResult.setPriceReviewResultStatus("通过");
        priceReviewResult.setPriceReviewTime(dateFormat.format(date));
        priceReview.setPriceReviewId(priceReviewId);
        priceReviewResult.setPriceReview(priceReview);
        priceReviewService.updatePriceReviewStatus("已审核",priceReviewId, priceReviewResult);

        priceReviewMQService.priceReviewQueue( priceReviewId, priceReviewResult);
        return "ok";
    }

    @RequestMapping(path = "/updateF")
    @ResponseBody
    public String priceReviewUpdateF(String priceReviewId,PriceReview priceReview,PriceReviewResult priceReviewResult) throws JsonProcessingException {
        priceReviewResult.setPriceReviewResultStatus("不通过");
        priceReviewResult.setPriceReviewTime(dateFormat.format(date));
        priceReview.setPriceReviewId(priceReviewId);
        priceReviewResult.setPriceReview(priceReview);
        priceReviewService.updatePriceReviewStatus("已审核",priceReviewId, priceReviewResult);

        priceReviewMQService.priceReviewQueue( priceReviewId,priceReviewResult);
        return "ok";
    }

}
