package com.lovomall.csc.repository;

import com.lovomall.csc.entity.OrderdetailsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface OrderdetalisRepository extends Repository<OrderdetailsEntity,String> {
    <S extends OrderdetailsEntity> S save(S var1);
    @Query(value = "from OrderdetailsEntity where shoppingEntity.id=?1")
    List<OrderdetailsEntity> findAllById(String id);
}
