package com.lovomall.csc.repository;
import com.lovomall.csc.entity.PriceReviewResult;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import java.util.Optional;

/**
 * @author GYM
 * @version 1.0
 */
public interface PriceReviewResultRepository extends Repository<PriceReviewResult,String> {
    /**
     * 添加报价审核结果信息
     * @param var1 报价审核结果信息实体
     * @return 添加成功返回实体
     */
    <S extends PriceReviewResult> S save(S var1);

    /**
     * 按id查询报价审核结果信息
     * @param var1  报价审核结果id
     * @return 成功返回实体对象
     */
    Optional<PriceReviewResult> findById(String var1);

    /**
     * 查询全部报价审核结果信息
     * @return
     */
    Iterable<PriceReviewResult> findAll();

    /**
     * 按报价审核信息结果id修改审核状态
     * @param priceReviewResultStatus 报价审核状态
     * @param priceReviewResultId 报价审核结果id
     */
    @Modifying
    @Query("update PriceReviewResult set priceReviewResultStatus=?1 where priceReviewResultId=?2")
    public void updatePriceReviewResultStatus(String priceReviewResultStatus,String priceReviewResultId);

}
