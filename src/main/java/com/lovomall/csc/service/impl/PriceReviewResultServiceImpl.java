package com.lovomall.csc.service.Impl;
import com.lovomall.csc.entity.PriceReviewResult;
import com.lovomall.csc.repository.PriceReviewResultRepository;
import com.lovomall.csc.service.IPriceReviewResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * @author GYM
 * @version 1.0
 */
@Service(value = "priceReviewResultService")
@Transactional
public class PriceReviewResultServiceImpl implements IPriceReviewResultService {
    @Autowired
    private PriceReviewResultRepository priceReviewResultRepository;
    @Override
    public PriceReviewResult savePriceReviewResult(PriceReviewResult priceReviewResult) {
        return priceReviewResultRepository.save(priceReviewResult);
    }

    @Override
    public void updatePriceReviewResultStatus(String priceReviewResultStatus, String priceReviewResultId) {
        priceReviewResultRepository.updatePriceReviewResultStatus(priceReviewResultStatus,priceReviewResultId);
    }
}
