package com.lovomall.csc.service;


import com.lovomall.csc.entity.OrderdetailsEntity;

import java.util.List;

public interface IOrderdetalisService {
    public OrderdetailsEntity saveorderdetalis(OrderdetailsEntity orderdetailsEntity);
    public List<OrderdetailsEntity>findListOrderdetalisById(String id);
}
