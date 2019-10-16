package com.lovomall.csc.dto;

import lombok.Setter;

import java.time.LocalDate;

/**
 * Author:     cafebabe
 * Date:       2019/10/15 
 * Time:       上午10:41
 * Description: TODO
 * Version: 1.0
 */
@Setter
public class ConsumeReviewResultDTO {
    private String userId;
    private String orderId;
    private String reviewResult;
    private String reviewAdvice;
    private LocalDate reviewDate = LocalDate.now();
    private double payMoney;
}
