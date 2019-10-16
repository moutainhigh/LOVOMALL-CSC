package com.lovomall.csc.service;





import com.lovomall.csc.entity.CustomerRefundEntity;
import com.lovomall.csc.entity.RefundInfoEntity;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * 退款业务接口
 */
public interface ICustomerRefundService {

    /*
    添加退款订单对象
     */
    public void save(CustomerRefundEntity customerFefund);

    /**
     * 根据订单状态查询总条数
     * @param orderStatus
     * @return
     */
    public int findList(String orderStatus);

    /**
     * 根据订单单号 修改订单状态 并且添加审核意见
     * @param orderCode 订单单号
     * @param orderStatus 订单状态

     */
    public  void  update(String orderCode, String orderStatus );

    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @return
     */
      List<CustomerRefundEntity> findPageList(int pageNumber, int pageSize);

}
