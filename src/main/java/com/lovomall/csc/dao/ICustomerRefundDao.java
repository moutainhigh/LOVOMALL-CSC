package com.lovomall.csc.dao;


import com.lovomall.csc.entity.Balance;
import com.lovomall.csc.entity.CustomerRefundEntity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * 退款的持久接口
 */

public interface ICustomerRefundDao extends CrudRepository<CustomerRefundEntity,String> {
    /**
     * 根据退货单号修改订单状态
     * @param orderCode 单号
     * @param orderStatus 订单状态
     */
    @Modifying
    @Query("update CustomerRefundEntity set orderStatus=?2 WHERE orderCode=?1")
    public  void  update(String orderCode, String orderStatus);
    /**
     * 查询未处理的的请求
     * @return
     */

    @Query("select count(order_code) from CustomerRefundEntity where orderStatus=?1 ")
     int findList(String orderStatus);

    @Query("from CustomerRefundEntity where orderStatus='待审核' ")
    List<CustomerRefundEntity> findPageList(Pageable pageable);


}
