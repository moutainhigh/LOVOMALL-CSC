package com.lovomall.csc.dao;


import com.lovomall.csc.entity.SupplierSignEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 供应商持久接口
 */
@Repository(value = "supplierDao")
public interface ISupplierDao extends CrudRepository<SupplierSignEntity,String> {

    @Query("from SupplierSignEntity where supplierId=?1")
    public Optional<SupplierSignEntity> findById(String supplierId);

    @Query("from SupplierSignEntity where status=?1")
    public List<SupplierSignEntity> findByStatus(String status);

    @Query("select count(supplierId) from SupplierSignEntity where supplierState='未审核'")
    public int countByStatus();

    @Modifying
    @Query(value = "update SupplierSignEntity set supplierState=:supplierState where supplierId=:supplierId")
    public void updateStatus(@Param(value = "supplierId") String supplierId, @Param(value = "supplierState")String supplierState);

    @Modifying
    @Query(value = "update SupplierSignEntity set supplierGrade=:supplierGrade where supplierId=:supplierId")
    public void updateGrade(@Param(value = "supplierId") String supplierId, @Param(value = "supplierGrade")String supplierGrade);

    @Modifying
    void deleteById(String supplierId);

    @Query("from  SupplierSignEntity where supplierState='未审核'")
    public List<SupplierSignEntity> getPageListSupplier(Pageable pageable);

    @Query("from  SupplierSignEntity where supplierScope=?1 and supplierState='已审核' and supplierStatus='审核通过'")
    public List<SupplierSignEntity> getPageListSupplierBidding(String supplierScope, Pageable pageable);

    @Query("select count(supplierId) from SupplierSignEntity where supplierScope=?1 and supplierState='已审核' and supplierStatus='审核通过'")
    public int countBySupplierScope(String supplierScope);

    @Modifying
    @Query("update SupplierSignEntity set supplierStatus=?2 where supplierId=?1")
    public void findByIdUpdateStatus(String id,String status);
}
