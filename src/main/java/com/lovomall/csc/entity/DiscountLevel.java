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
 * Time:       下午2:50
 * Description:
 * 折扣等级实体
 * Version: 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_discLevel")
@Entity
public class DiscountLevel {

    /** 折扣等级ID*/
    @Id
    @GeneratedValue(generator = "discUUID")
    @GenericGenerator(name = "discUUID", strategy = "uuid")
    @Column(name = "disc_id", length = 32)
    private String id;

    /** 折扣等级*/
    @Column(name = "disc_level", length = 15, nullable = false)
    private String discountLevel;

    /** 折扣等级对应最小累计冲值金额*/
    @Column(name = "min_money", length = 15, nullable = false)
    private double minMoney;

    /** 折扣等级对应最大累计充值金额*/
    @Column(name = "max_money", length = 15, nullable = false)
    private double maxMoney;

    /** 折扣等级对应折扣率*/
    @Column(name = "disc_per", length = 15, nullable = false)
    private double discountPer = 1.0;
}
