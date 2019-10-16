package com.lovomall.csc.repository;

import com.lovomall.csc.entity.OrderdetailsEntity;
import com.lovomall.csc.entity.SupplierdetailsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SupplierdetalisRepository extends Repository<SupplierdetailsEntity,String> {

    <S extends SupplierdetailsEntity> S save(S var1);


    @Query(value = "from SupplierdetailsEntity where supplyEntity.id=?1")
    SupplierdetailsEntity findAllById(String id);
}
