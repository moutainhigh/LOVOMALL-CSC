package com.lovomall.csc.service.impl;

import com.lovomall.csc.entity.PriceReview;
import com.lovomall.csc.entity.PriceReviewResult;
import com.lovomall.csc.repository.PriceReviewRepository;
import com.lovomall.csc.service.IPriceReviewResultService;
import com.lovomall.csc.service.IPriceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author GYM
 * @version 1.0
 */
@Service(value = "priceReviewService")
@Transactional
public class PriceReviewServiceImpl implements IPriceReviewService {


    @Autowired
    private PriceReviewRepository priceReviewRepository;
    @Autowired
    private IPriceReviewResultService priceReviewResultService;

    @Override
    public PriceReview savePriceReview(PriceReview priceReview) {
        return priceReviewRepository.save(priceReview);
    }

    @Override
    public List<PriceReview> findListPriceReview() {
        return (List<PriceReview>)priceReviewRepository.findAll();
    }

    @Override
    public void updateProductNum(int productNum,String priceReviewId) {
        priceReviewRepository.updateProductNum(productNum,priceReviewId);
    }


    @Override
    public Page<PriceReview> findAllByPriceReviewStatusIs(int pageNO, int pageSize, String priceReviewStatus) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);
        return priceReviewRepository.findAllByPriceReviewStatusIs(pageable,priceReviewStatus);
    }

    @Override
    public void updatePriceReviewStatus(String priceReviewStatus, String priceReviewId, PriceReviewResult priceReviewResult) {
        priceReviewRepository.updatePriceReviewStatus(priceReviewStatus,priceReviewId);
        priceReviewResultService.savePriceReviewResult(priceReviewResult);
    }

    @Override
    public PriceReview findById(String priceReviewId) {
        return priceReviewRepository.findById(priceReviewId);
    }
}
