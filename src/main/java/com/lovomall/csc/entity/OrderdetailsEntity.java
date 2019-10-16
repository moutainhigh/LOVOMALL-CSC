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
@Table(name = "t_orderDetails")
public class OrderdetailsEntity {
    @Id
    @GenericGenerator(name = "oruuid",strategy = "uuid")
    @GeneratedValue(generator = "oruuid")
    @Column(length = 32,name = "o_id")
    private String orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="u_id")
    private ShoppingEntity shoppingEntity;
    //商品名称
    @Column(length = 48,nullable = false)
    private String productName;
    //商品数量
    @Column(length = 48,nullable = false)
    private int productNumber;
    //商品单价
    @Column(length = 48,nullable = false)
    private double productPrice;
    //规格
    @Column(length = 48,nullable = false)
    private String productSpec;




}
