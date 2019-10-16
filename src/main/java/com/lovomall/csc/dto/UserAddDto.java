package com.lovomall.csc.dto;

import lombok.Data;

/**
 * 用户注册信息返回对象
 */
public class UserAddDto {
    //用户id
    private String userId;
    //审核状态
    private String examineStatus;
    //审核信息
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(String examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public UserAddDto(String userId, String examineStatus, String remark) {
        this.userId = userId;
        this.examineStatus = examineStatus;
        this.remark = remark;
    }
}
