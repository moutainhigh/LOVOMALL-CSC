package com.lovomall.csc.service.Impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.entity.*;
import com.lovomall.csc.repository.ShoppingAuditRepository;
import com.lovomall.csc.repository.ShoppingRepository;
import com.lovomall.csc.repository.SupplierdetalisRepository;
import com.lovomall.csc.repository.SupplyRepository;
import com.lovomall.csc.service.BalanceService;
import com.lovomall.csc.service.ConsumeRecordService;
import com.lovomall.csc.service.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service(value = "shoppingService")
@Transactional
public class ShoppingServiceImpl implements IShoppingService {
    @Autowired
    private ShoppingRepository shoppingRepository;
    @Autowired
    private ShoppingAuditRepository shoppingAuditRepository;
    @Autowired
    private SupplyRepository supplyRepository;
    @Autowired
    private SupplierdetalisRepository supplierdetalisRepository;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private ConsumeRecordService consumeRecordService;

    @Override
    public ShoppingEntity saveShopping(ShoppingEntity shoppingEntity) {
        return shoppingRepository.save(shoppingEntity);
    }

    @Override
    public List<ShoppingEntity> findListShopping() {
        return (List<ShoppingEntity>) shoppingRepository.findAll();
    }

    @Override
    public void updateShopping(String orderStatus, String ID, ShoppingAudit shoppingAudit) {
        shoppingRepository.updateShopping(orderStatus,ID);
        shoppingAuditRepository.save(shoppingAudit);

    }


    @Override
    public Page<ShoppingEntity> findAllByOrderStatusIsNot(int pageNO, int pageSize, String orderStatus) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);

        return shoppingRepository.findAllByOrderStatusIsNot(pageable, orderStatus);
    }

    @Override
    public void updateOrderStatusById(String ID, String orderStatus) {
        shoppingRepository.updateOrderStatusById(ID,orderStatus);
    }

    @JmsListener(destination = "SHOPPING_SETTLEMENT_QUEUE")
    public void shoppingSettlemQueue(String string) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(string);
        String orderId = jsonNode.get("orderId").asText();
        String userId = jsonNode.get("userId").asText();
        String orderCode = jsonNode.get("orderCode").asText();
        String totalPrice = jsonNode.get("totalPrice").asText();
        String orderStatus = jsonNode.get("orderStatus").asText();
        String payStyle = jsonNode.get("payStyle").asText();

        ShoppingEntity shoppingEntity = new ShoppingEntity();
        shoppingEntity.setID(orderId);
        shoppingEntity.setCode(orderCode);
        shoppingEntity.setCusID(userId);
        shoppingEntity.setPayStatus(payStyle);
        shoppingEntity.setOrderStatus(orderStatus);
        shoppingEntity.setOrderDate(Date.valueOf(LocalDate.now()));
        shoppingEntity.setTotalPrice(Float.valueOf(totalPrice));

        shoppingRepository.save(shoppingEntity);

        if ("预存款".equals(shoppingEntity.getPayStatus())){

            ConsumeRecord consumeRecord = new ConsumeRecord();
            consumeRecord.setTotalPrice(jsonNode.get("totalPrice").asDouble());
            consumeRecord.setConsuId(userId);
            consumeRecord.setOrderDate(Date.valueOf(LocalDate.now()));
            consumeRecord.setOrderStatus("未审核");
            Balance balance = balanceService.findBalanceByUserId(userId);
            consumeRecord.setBalance(balance);
            consumeRecord.setOrderId(orderId);
            consumeRecordService.save(consumeRecord);
        }

        System.out.println(string);
    }

    @JmsListener(destination = "SUPPLIER_SHOP_QUEUE")
    public void supplierShopQueue(String string) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(string);
        String  deliveryOrderNum=jsonNode.get("deliveryOrderNum").asText();
        String deliveryAddress=jsonNode.get("deliveryAddress").asText();
        String orderStatus=jsonNode.get("orderStatus").asText();
        String phone=jsonNode.get("phone").asText();
        String payStatus=jsonNode.get("payStatus").asText();
        String totalPrice=jsonNode.get("totalPrice").asText();
        String productCode=jsonNode.get("productCode").asText();
        String productName=jsonNode.get("productName").asText();
        String productSpec=jsonNode.get("productSpec").asText();
        String purchasePrice=jsonNode.get("purchasePrice").asText();
        String supplier=jsonNode.get("supplier").asText();
        String businessName=jsonNode.get("businessName").asText();
        String productNumber=jsonNode.get("productNumber").asText();

        SupplyEntity supplyEntity=new SupplyEntity();
        supplyEntity.setDeliveryOrderNum(deliveryOrderNum);
        supplyEntity.setDeliveryAddress(deliveryAddress);
        supplyEntity.setOrderStatus(orderStatus);
        supplyEntity.setPhone(phone);
        supplyEntity.setPayStatus(payStatus);
        supplyEntity.setTotalPrice(Double.valueOf(totalPrice));
        SupplyEntity save = supplyRepository.save(supplyEntity);
        SupplierdetailsEntity supplierdetailsEntity=new SupplierdetailsEntity();
        supplierdetailsEntity.setProductCode(productCode);
        supplierdetailsEntity.setSupplyEntity(save);
        supplierdetailsEntity.setSupplier(supplier);
        supplierdetailsEntity.setBusinessName(businessName);
        supplierdetailsEntity.setProductName(productName);
        supplierdetailsEntity.setProductSpec(productSpec);
        supplierdetailsEntity.setProductNumber(Integer.valueOf(productNumber));
        supplierdetailsEntity.setPurchasePrice(Double.valueOf(purchasePrice));
        supplierdetalisRepository.save(supplierdetailsEntity);

        System.out.println(supplierdetailsEntity.getProductSpec());



        System.out.println(string);
    }

}
