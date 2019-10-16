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
@Table(name = "t_supplierdetalis")
public class SupplierdetailsEntity {
    @Id
    @GenericGenerator(name = "liuuid",strategy = "uuid")
    @GeneratedValue(generator = "liuuid")
    @Column(length = 32,name = "pp_id")
    private String productCode;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sl_id")
    private  SupplyEntity supplyEntity;

    //供应商
    @Column(length = 48)
    private String supplier;
    //商家名
    @Column(length = 48)
    private String businessName;
    //商品名
    @Column(length = 48)
    private String productName;
    //规格
    @Column(length = 48)
    private String productSpec;
    //采购数量
    @Column(length = 48)
    private int productNumber;
    //采购价
   @Column(length =48 )
    private double purchasePrice;




}
