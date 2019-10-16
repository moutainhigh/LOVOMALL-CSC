package com.lovomall.csc.repository;
import com.lovomall.csc.entity.PriceReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author GYM
 * @version 1.0
 */
public interface PriceReviewRepository extends Repository<PriceReview,String> {

    /**
     * 添加报价审核信息
     * @param var1 报价审核信息实体
     * @return 添加成功返回实体对象
     */
    <S extends PriceReview> S save(S var1);


    /**
     * 查询全部报价审核信息
     * @return
     */
    Iterable<PriceReview> findAll();

    /**
     * 按id修改商品数量
     * @param productNum 商品数量
     * @param priceReviewId 商品id
     */
    @Modifying
    @Query(value = "update t_price_review set price_review_product_num=?1 where price_review_id=?2 ",nativeQuery = true)
    public void updateProductNum(int productNum,String priceReviewId);

    @Query("from PriceReview where priceReviewId=?1")
    public PriceReview findById(String priceReviewId);
    @Transactional
    @Modifying
    @Query("update PriceReview set priceReviewStatus=?1 where priceReviewId=?2")
    public void updatePriceReviewStatus(String priceReviewStatus,String priceReviewId);

    /**
     * 按审核状态分页查询消费记录
     * @param var 分页实体
     * @param PriceReviewStatus 审核状态
     * @return 符合给定审核状态的消费记录分页实体对象
     */
    Page<PriceReview> findAllByPriceReviewStatusIs(Pageable var, String PriceReviewStatus);


}
