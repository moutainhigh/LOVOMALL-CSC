package com.lovomall.csc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "shoppingaudit")
public class ShoppingAudit {
    @Id
    @GenericGenerator(name = "spuuid",strategy = "uuid")
    @GeneratedValue(generator = "spuuid")
    @Column(length = 32,name = "su_id")
    private  String shoppingAuditId;
    //审核状态
    @Column(name = "shoppingResult_status",length = 48,nullable = false)
    private String shoppingResultStatus;
    //审核意见
    @Column(name = "shoppingResult_opinion",length = 48)
    private String shoppingOpinion;
    //审核时间
    @Column(name = "shoppingResult_time",length = 48,columnDefinition = "TIMESTAMP")
    private String shoppingTime;
    //审核人
    @Column(name = "shoppingResult_people",length = 48)
    private String shoppingPeople;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "u_id")
    private  ShoppingEntity shoppingEntity;
}


