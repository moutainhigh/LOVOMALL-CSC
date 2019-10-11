package com.lovomall.csc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午3:49
 * Description: 账户余额实体对象
 * Version: 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_balance")
public class Balance {

    @Id
    @GenericGenerator(name = "baUUID", strategy = "uuid")
    @GeneratedValue(generator = "baUUID")
    @Column(name = "ba_id", length = 32)
    private String baId;

    @Column(name = "user_id", length = 32)
    private String userId;

    @Column(name = "curr_balance", length = 10, nullable = false)
    private double currentBalance;

    @Column(name = "accu_money", length = 10, nullable = false)
    private double accuMoney;
}
