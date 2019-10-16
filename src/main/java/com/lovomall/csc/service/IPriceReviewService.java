package com.lovomall.csc.service;
import com.lovomall.csc.entity.PriceReview;
import com.lovomall.csc.entity.PriceReviewResult;
import org.springframework.data.domain.Page;
import java.util.List;

/**
 * @author GYM
 * @version 1.0
 */
public interface IPriceReviewService {
    /**
     * 保存报价审核信息
     * @param priceReview
     * @return
     */
    public PriceReview savePriceReview(PriceReview priceReview);


    /**
     * 查询所有报价审核信息
     * @return
     */
    public List<PriceReview>findListPriceReview();

    /**
     * 修改商品数量
     * @param productNum 商品数量
      * @param priceReviewId 报价审核id
     */
    public void updateProductNum(int productNum,String priceReviewId);


//    /**
//     * 按审核状态分页查询消费记录
//     * @param var 分页实体
//     * @param PriceReviewStatus 审核状态
//     * @return 符合给定审核状态的消费记录分页实体对象
//     */

    /**
     * 按审核状态分页查询消费记录
     * @param pageNO 页码
     * @param pageSize 每页显示条数
     * @param priceReviewStatus 审核状态
     * @return
     */
    Page<PriceReview> findAllByPriceReviewStatusIs(int pageNO, int pageSize, String priceReviewStatus);

    /**
     * 按id修改审核状态
     * @param priceReviewStatus 审核状态
     * @param priceReviewId 报价审核id
     */
    public void updatePriceReviewStatus(String priceReviewStatus, String priceReviewId, PriceReviewResult priceReviewResult);

    /**
     * 按id查询
     * @param priceReviewId
     * @return
     */
    public PriceReview findById(String priceReviewId);
}
