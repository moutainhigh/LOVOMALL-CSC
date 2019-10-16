package com.lovomall.csc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Author:     cafebabe
 * Date:       2019/10/15 
 * Time:       上午10:14
 * Description: 充值业务审核结果传输DTO
 * Version: 1.0
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RechargeReviewResultDTO implements Serializable {
    private String upId;
    private String userId;
    private double discountLevel;
    private double accuMoney;
    private double currentBalance;
    private String reviewResult;
    private String reviewAdvice;
    private String reviewDate = LocalDate.now().toString();
}
