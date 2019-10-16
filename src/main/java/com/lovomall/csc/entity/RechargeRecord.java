package com.lovomall.csc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午4:15
 * Description: 账户充值记录实体
 * Version: 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "t_recharge")
public class RechargeRecord {

    /** 充值记录id*/
    @Id
    @GenericGenerator (name = "reUUID", strategy = "uuid")
    @GeneratedValue (generator = "reUUID")
    @Column (name = "up_id", length = 32)
    private String upId;

    /** 用户余额对象*/
    @ManyToOne
    @JoinColumn (name = "baId", nullable = false)
    private Balance balance;

    /** 充值记录审核状态*/
    @Column (name = "re_status", length = 10, nullable = false)
    private String reviewStatus = "未审核";

    /** 充值金额*/
    @Column (name = "re_amount", length = 10, nullable = false)
    private double amount;

    /** 充值时间*/
    @Column (name = "up_date", length = 20, nullable = false)
    private Date upDate;

}
