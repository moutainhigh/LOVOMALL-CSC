package com.lovomall.csc.service;

import com.lovomall.csc.entity.ShoppingAudit;
import com.lovomall.csc.entity.ShoppingEntity;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IShoppingService {
    //保存
    public ShoppingEntity saveShopping(ShoppingEntity shoppingEntity);
    //显示
    public List<ShoppingEntity>findListShopping();

    public void updateShopping(String orderStatus, String ID, ShoppingAudit shoppingAudit);

    Page<ShoppingEntity> findAllByOrderStatusIsNot(int pageNO, int pageSize, String orderStatus);


    /**
     * 按充值记录Id 更新审核状态
     * @param ID 消费记录id
     * @param orderStatus 审核状态
     */
    void updateOrderStatusById(String ID, String orderStatus);


}
