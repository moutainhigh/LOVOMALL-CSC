package com.lovomall.csc.mqservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dto.PriceReviewDto;
import com.lovomall.csc.dto.PriceReviewResultDto;
import com.lovomall.csc.entity.PriceReview;
import com.lovomall.csc.entity.PriceReviewResult;
import com.lovomall.csc.repository.PriceReviewRepository;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author GYM
 * @version 1.0
 * @date 2019/10/14 15:58
 */
@Service(value = "priceReviewMQService")
@Transactional
public class PriceReviewMQService {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final PriceReviewRepository reviewRepository;

    @Autowired
    public PriceReviewMQService(JmsMessagingTemplate jmsMessagingTemplate, PriceReviewRepository reviewRepository) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.reviewRepository = reviewRepository;
    }


    public void priceReviewQueue(String priceReviewId, PriceReviewResult priceReviewResult) throws JsonProcessingException {
        PriceReview priceReview =reviewRepository.findById(priceReviewId);
        ActiveMQQueue mqQueue = new ActiveMQQueue("priceReviewInfo");
        PriceReviewResultDto priceReviewResultDto = new PriceReviewResultDto();
        priceReviewResultDto.setBidNumber(priceReview.getBidNumber());
        priceReviewResultDto.setSupplierName(priceReview.getSupplierName());
        priceReviewResultDto.setProductName(priceReview.getProductName());
        priceReviewResultDto.setAllMoney(priceReview.getAllMoney());
        priceReviewResultDto.setProductNum(priceReview.getProductNum());
        priceReviewResultDto.setSupplierLogo(priceReview.getSupplierLogo());
        priceReviewResultDto.setPriceReviewOpinion(priceReviewResult.getPriceReviewOpinion());
        priceReviewResultDto.setPriceReviewResultStatus(priceReviewResult.getPriceReviewResultStatus());
        priceReviewResultDto.setPriceReviewTime(priceReviewResult.getPriceReviewTime());
        String json = new ObjectMapper().writeValueAsString(priceReviewResultDto);

        jmsMessagingTemplate.convertAndSend(mqQueue, json);
    }
//    @JmsListener(destination = "priceReviewResultInfo")
//    public void aaa (String str){
//        System.out.println(str);
//    }

    @JmsListener(destination = "getSupplierInfo")
    public void priceReviewResultQueue(String priceReviewJson) throws IOException {
        PriceReviewDto priceReviewDto = new ObjectMapper().readValue(priceReviewJson,PriceReviewDto.class);
        PriceReview priceReview = new PriceReview() ;
        priceReview.setBidNumber(priceReviewDto.getBidNumber());
        priceReview.setAllMoney(priceReviewDto.getAllMoney());
        priceReview.setSupplierName(priceReviewDto.getSupplierName());
        priceReview.setProductName(priceReviewDto.getProductName());
        priceReview.setProductNum(priceReviewDto.getProductNum());
        priceReview.setSupplierLogo(priceReviewDto.getSupplierLogo());
        reviewRepository.save(priceReview);
    }

}
