package com.lovomall.csc.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户状态记录表
 */
@Entity
@Table(name = "t_userStatusAudit")
public class StatusEntity {
    //用户状态记录Id
    @Id
    @GenericGenerator(name = "uuuuid", strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32)
    private String statusId;
    @Column(length = 10)
    //审核状态
    private String statusAuitd;
    @Column(columnDefinition = "TIMESTAMP")
    //记录时间
    private String date;
    @Column(columnDefinition = "text")
    //反馈信息
    private String statusMassage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private  UserEntity userEntity;

    public StatusEntity() {
    }

    public StatusEntity(String statusAuitd, String statusMassage) {
        this.statusAuitd = statusAuitd;
        this.statusMassage = statusMassage;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusAuitd() {
        return statusAuitd;
    }

    public void setStatusAuitd(String statusAuitd) {
        this.statusAuitd = statusAuitd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatusMassage() {
        return statusMassage;
    }

    public void setStatusMassage(String statusMassage) {
        this.statusMassage = statusMassage;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
