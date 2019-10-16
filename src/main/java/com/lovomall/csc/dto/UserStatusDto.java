package com.lovomall.csc.dto;


import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

/**
 * 用户修改状态信息返回对象
 */
public class UserStatusDto {
    //用户id
    private String userId;
    //用户状态
    private String userStatus;
    //审核信息
    private String remark;
    //审核时间
    private String date = LocalDate.now().toString();
    //审核状态
    private String reviewResult;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
    }

    public UserStatusDto(String userId, String userStatus, String remark, String reviewResult) {
        this.userId = userId;
        this.userStatus = userStatus;
        this.remark = remark;
        this.reviewResult = reviewResult;
    }
}
