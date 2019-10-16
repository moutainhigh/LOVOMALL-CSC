package com.lovomall.csc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "t_shopping")
public class ShoppingEntity {
    @Id
    @GenericGenerator(name = "uuuuid",strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32,name = "u_id")
    private String ID;
    //订单号
    @Column(length = 48,nullable = false)
    private String Code;
    //顾客id
    @Column(length = 48,nullable = false)
    private String CusID;
    //总价
    @Column(length = 48,nullable = false)
    private float TotalPrice;
    //订单日期
    @Column(length = 48,nullable = false)
    private Date orderDate;
    //订单状态
    @Column(length = 48,nullable = false)
    private String orderStatus;
    //结算方式
    @Column(length = 48,nullable = false)
    private String payStatus;

    
}
