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
@Table(name = "supplyAudit")
public class SupplyAudit {
    @Id
    @GenericGenerator(name = "suuuid",strategy = "uuid")
    @GeneratedValue(generator = "suuuid")
    @Column(length = 32,name = "sh_id")
    private  String supplyAuditId;
    //审核状态
    @Column(name = "supplyResult_status",length = 48,nullable = false)
    private String supplyResultStatus;
    //审核意见
    @Column(name = "supplyResult_opinion",length = 48)
    private String supplyOpinion;
    //审核时间
    @Column(name = "supplyResult_time",length = 48,columnDefinition = "TIMESTAMP")
    private String supplyTime;
    //审核人
    @Column(name = "supplyResult_people",length = 48)
    private String supplyPeople = "罗涛";
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sl_id")
    private  SupplyEntity supplyEntity;
}
