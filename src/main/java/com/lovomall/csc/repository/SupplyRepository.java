package com.lovomall.csc.repository;

import com.lovomall.csc.entity.ShoppingEntity;
import com.lovomall.csc.entity.SupplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SupplyRepository extends Repository<SupplyEntity,String> {
    <S extends SupplyEntity> S save(S var1);


    Iterable<SupplyEntity> findAll();

    @Modifying
    @Query(value = "update SupplyEntity set payStatus=?1 where deliveryOrderNum=?2")
    void updateSupply(String payStatus, String deliveryOrderNum);

    /**
     * 按审核状态分页查询消费记录
     * @param var 分页实体
     * @param payStatus 审核状态
     * @return 符合给定审核状态的消费记录分页实体对象
     */
    Page<SupplyEntity> findAllByPayStatusIs(Pageable var, String payStatus);

    SupplyEntity findByDeliveryOrderNumIs(String deliveryOrderNum);
}
