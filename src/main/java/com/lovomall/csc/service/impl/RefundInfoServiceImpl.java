package com.lovomall.csc.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dao.IRefundInfoDao;
import com.lovomall.csc.dto.ReturnOrderDto_listenCheck;
import com.lovomall.csc.entity.RefundInfoEntity;
import com.lovomall.csc.service.IRefundInfoService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 退款审核意见明细业务接口实现类
 */
@Service("refundInfoService")
@Transactional
public class RefundInfoServiceImpl implements IRefundInfoService {
    @Autowired
    private IRefundInfoDao refundInfoDao;
    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    public void addInfo(RefundInfoEntity refundInfoEntity) {
        refundInfoDao.save(refundInfoEntity);
    }

    public List<RefundInfoEntity> findInfoList(String orderCode) {
        return refundInfoDao.fandByOrderCode(orderCode);
    }

    @Override

    public void updateinfo(String id, String checkDate,
                           String checkResult, String rejectCause,
                           Date retumDate) {
     checkDate=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(new java.sql.Date(System.currentTimeMillis()));
        refundInfoDao.updateinfo(id,checkDate,checkResult,rejectCause,retumDate);
        ReturnOrderDto_listenCheck listenCheck=new ReturnOrderDto_listenCheck();
        listenCheck.setReturnOrderInfoId(id);

       listenCheck.setCheckDate(checkDate);
        listenCheck.setCheckResult(checkResult);
        listenCheck.setRejectCause(rejectCause);


        ActiveMQQueue mqQueue=new ActiveMQQueue("returnOrdderBack");

        ObjectMapper mapper=new ObjectMapper();
        String json =null;
        try {
            json = mapper.writeValueAsString(listenCheck);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        messagingTemplate.convertAndSend(mqQueue,json);





    }


}
