package com.lovomall.csc.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dao.ICustomerRefundDao;
import com.lovomall.csc.dao.IRefundInfoDao;
import com.lovomall.csc.dto.ReturnOrderDto_listenCheck;
import com.lovomall.csc.dto.ReturnOrderDto_sendCheck;
import com.lovomall.csc.entity.CustomerRefundEntity;
import com.lovomall.csc.entity.RefundInfoEntity;
import com.lovomall.csc.service.ICustomerRefundService;
import com.lovomall.csc.service.IRefundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 退款业务接口实体类
 */
@Service("customerRefundService")
@Transactional
public class CustomerRefundServiceImpl implements ICustomerRefundService {
    @Autowired
private ICustomerRefundDao customerRefundDao;
    @Autowired
    private IRefundInfoDao refundInfoDao;
    @Autowired
    private IRefundInfoService refundInfoService;



    public void save(CustomerRefundEntity customerFefund) {


        customerRefundDao.save(customerFefund);
    }

    @Override
    public int findList(String orderStatus) {
        return customerRefundDao.findList("待审核");
    }



    @Override
    public void update(String orderCode, String orderStatus) {
        customerRefundDao.update(orderCode,orderStatus);

    }






    @Override
    public List<CustomerRefundEntity> findPageList(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize);

        return  customerRefundDao.findPageList(pageable);
    }
    @JmsListener(destination = "addReturnOrdder")
    public void  saveCustomer(String userJson) throws IOException {
        ObjectMapper obj=new ObjectMapper();
        System.out.println("*********************"+userJson);
        ReturnOrderDto_sendCheck returnOrderDto=obj.readValue(userJson,ReturnOrderDto_sendCheck.class);
        CustomerRefundEntity refundEntity = new CustomerRefundEntity();
        refundEntity.setOrderCode(returnOrderDto.getOrderCode());
        refundEntity.setProductName(returnOrderDto.getProductName());
        refundEntity.setTotalPrice(returnOrderDto.getTotalPrice());
        refundEntity.setOrderStatus(returnOrderDto.getOrderStatus());
        customerRefundDao.save(refundEntity);
          RefundInfoEntity refundInfoEntity =new RefundInfoEntity();
                    refundInfoEntity.setdId(returnOrderDto.getReturnOrderInfoId());
                    refundInfoEntity.setApplyCause(returnOrderDto.getApplyCause());
                    refundInfoEntity.setOrderCode(returnOrderDto.getOrderCode());
                refundInfoService.addInfo(refundInfoEntity);


    }






}
