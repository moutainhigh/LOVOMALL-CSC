package com.lovomall.csc.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户注册记录表
 */
@Entity
@Table(name = "t_userAddAuitd")
public class AddEntity {
    //用户注册记录Id
    @Id
    @GenericGenerator(name = "uuuuid", strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32)
    private String addId;
    @Column(length = 10)
    //审核状态
    private String status;
    @Column(columnDefinition = "TIMESTAMP")
    //记录时间
    private String date;
    @Column(columnDefinition = "text")
    //反馈信息
    private String statusMassage;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
    @Column(length = 32)
    private String userId;

    public AddEntity() {
    }

    public String getAddId() {
        return addId;
    }

    public void setAddId(String addId) {
        this.addId = addId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
