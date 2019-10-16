package com.lovomall.csc.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户注册请求传输对象
 */
@Entity
@Table(name = "uptowebUser")
public class UserDto {
    //用户id
    @Id
    @Column(length = 32)
    private String userId;

    //用户名
    @Column(length = 40)
    private String loginUser;

    //密码
    @Column(length = 40)
    private String pwd;

    //真实姓名
    @Column(length = 40)
    private String userName;

    //性别
    @Column(length = 10)
    private String sex;

    //电话号码
    @Column(length = 20)
    private String phoneNum;

    //资质图片
    @Column(columnDefinition = "text")
    private String aptitudeImg;

    //用身份证图片
    @Column(columnDefinition = "text")
    private String identityImg;

    //公司名字
    @Column(length = 40)
    private String companyName;

    public UserDto() {
    }

    public UserDto(String loginUser, String pwd, String userName, String sex, String phoneNum, String aptitudeImg, String identityImg, String companyName) {
        this.loginUser = loginUser;
        this.pwd = pwd;
        this.userName = userName;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.aptitudeImg = aptitudeImg;
        this.identityImg = identityImg;
        this.companyName = companyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAptitudeImg() {
        return aptitudeImg;
    }

    public void setAptitudeImg(String aptitudeImg) {
        this.aptitudeImg = aptitudeImg;
    }

    public String getIdentityImg() {
        return identityImg;
    }

    public void setIdentityImg(String identityImg) {
        this.identityImg = identityImg;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
