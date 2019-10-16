package com.lovomall.csc.service.Impl;

import com.lovomall.csc.entity.OrderdetailsEntity;
import com.lovomall.csc.repository.OrderdetalisRepository;
import com.lovomall.csc.service.IOrderdetalisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "orderdetalisService")
public class OrderdetalistServiceImpl implements IOrderdetalisService {
    @Autowired
    private OrderdetalisRepository orderdetalisRepository;
    //保存
    @Override
    public OrderdetailsEntity saveorderdetalis(OrderdetailsEntity orderdetailsEntity) {
        return orderdetalisRepository.save(orderdetailsEntity);
    }

    @Override
    public List<OrderdetailsEntity> findListOrderdetalisById(String id) {
        return orderdetalisRepository.findAllById(id);
    }


}
