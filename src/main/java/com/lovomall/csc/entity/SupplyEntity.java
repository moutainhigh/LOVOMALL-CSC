package com.lovomall.csc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_supply")
public class SupplyEntity {
    @Id
    @GenericGenerator(name = "sluuid",strategy = "uuid")
    @GeneratedValue(generator = "sluuid")
    @Column(length = 32,name = "sl_id")
    private String deliveryOrderNum;
    //送货地址
    @Column(length = 48)
    private String deliveryAddress;
    //送货状态
    @Column(length = 48)
    private String orderStatus;
    //联系方式
    @Column(length = 48)
    private String phone;
    //结算状态
    @Column(length = 48)
    private String payStatus;
    //总价格
    @Column(length = 48)
    private double totalPrice;


}
