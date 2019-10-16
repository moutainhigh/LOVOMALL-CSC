package com.lovomall.csc.repository;

import com.lovomall.csc.entity.ShoppingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;


public interface ShoppingRepository extends Repository<ShoppingEntity,String> {

    <S extends ShoppingEntity> S save(S var1);


    Iterable<ShoppingEntity> findAll();

    @Modifying
    @Query(value = "update ShoppingEntity set orderStatus=?1 where ID=?2")
    void updateShopping(String orderStatus, String ID);

    /**
     * 按审核状态分页查询消费记录
     * @param var 分页实体
     * @param orderStatus 审核状态
     * @return 符合给定审核状态的消费记录分页实体对象
     */
    Page<ShoppingEntity> findAllByOrderStatusIsNot(Pageable var, String orderStatus);
    /**
     * 按消费记录Id 更新审核状态
     * @param ID 消费记录id
     * @param orderStatus 审核状态
     */
    @Modifying
    @Query (value = "update ShoppingEntity set orderStatus=:orderStatus " +
            "where ID=:ID")
    void updateOrderStatusById(String ID, String orderStatus);













}
