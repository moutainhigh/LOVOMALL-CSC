package com.lovomall.csc.dao;


import com.lovomall.csc.entity.RefundInfoEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 退款审核明细持久接口
 */
public interface IRefundInfoDao extends CrudRepository<RefundInfoEntity,String> {
    /**
     * 根据订单单号查询审核意见
     * @param orderCode
     * @return
     */
    @Modifying
    @Query("from RefundInfoEntity where orderCode=?1")
    List<RefundInfoEntity> fandByOrderCode(String orderCode );
    @Transactional
    @Modifying
    @Query("UPDATE  RefundInfoEntity  SET checkDate=?2,checkResult=?3,rejectCause=?4, retumDate=?5 where dId=?1")
    void  updateinfo(String id, String checkDate, String checkResult, String rejectCause, Date retumDate);


}
