package com.lovomall.csc.service;

import com.lovomall.csc.entity.RefundInfoEntity;

import java.sql.Date;
import java.util.List;

/**
 * 退款审核明细业务接口
 */
public interface IRefundInfoService {

  void  addInfo(RefundInfoEntity refundInfoEntity);

  List<RefundInfoEntity>findInfoList(String orderCode);

    void  updateinfo(String id, String checkDate, String checkResult, String rejectCause, Date retumDate);

}
