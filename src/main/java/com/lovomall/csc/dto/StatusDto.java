package com.lovomall.csc.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户状态修改请求对象
 */
@Entity
@Table(name = "uptowebStatus")
public class StatusDto {
    //状态id
    @Id
    @GenericGenerator(name = "uuuuid", strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32)
    private String statusId;


    //用户id
    @Column(length = 32)
    private String userId;

    //用户状态
    @Column(length = 10)
    private String userStatus;

    public StatusDto() {
    }

    public StatusDto(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
