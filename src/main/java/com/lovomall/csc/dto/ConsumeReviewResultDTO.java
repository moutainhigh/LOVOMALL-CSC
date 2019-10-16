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
 * Time:       上午10:41
 * Description: TODO
 * Version: 1.0
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConsumeReviewResultDTO implements Serializable {
    private String userId;
    private String orderId;
    private String reviewResult;
    private String reviewAdvice;
    private LocalDate reviewDate = LocalDate.now();
    private double payMoney;
}
