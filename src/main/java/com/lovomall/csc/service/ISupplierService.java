package com.lovomall.csc.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovomall.csc.entity.ExamineInfoEntity;
import com.lovomall.csc.entity.SupplierSignEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 供应商业务接口
 */
public interface ISupplierService {

    public void add(String  json) throws IOException;
    public Optional<SupplierSignEntity> findById(String id);
    public List<SupplierSignEntity> findByStatus(String status);

    /**
     * 审核业务，按照点击的供应商，审核，完成之后，修改供应商注册审核状态，给供应商修改一个等级，然后
     * 添加一条供应商注册审核记录，需要添加审核人、审核时间
     * @param id
     * @param grade
     * @param examineInfoEntity
     */
    public void updateStatus(String id,String grade, ExamineInfoEntity examineInfoEntity,String status);

    /**
     * 统计总条数
     * @return  返回的总条数
     */
    public int countByStatus();
    /**
     * 供应商注册信息分页
     * @param pageNumber 当前页
     * @param pageSize 每页显示的行数
     * @return
     */
    public List<SupplierSignEntity> getPageListSupplier(int pageNumber, int pageSize);

    /**
     * 供应商注册信息分页
     * @param pageNumber 当前页
     * @param pageSize 每页显示的行数
     * @return
     */
    public List<SupplierSignEntity> getPageListSupplierBidding(String supplierScope, int pageNumber, int pageSize);

    /**
     * 按照供应商经营范围统计供应商条数
     * @param supplierScope 供应商经营范围
     * @return  符合条件的条数
     */
    public int countBySupplierScope(String supplierScope);
}
