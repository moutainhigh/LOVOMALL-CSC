package com.lovomall.csc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 用户对象表
 */
@Entity
@Table(name = "t_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    //用户id
    @Id
    @Column(length = 32)
    private String userId;

    //用户
    @Column(length = 40)
    private String loginName;

    //用户密码
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

    //用户状态
    @Column(length = 10)
    private String userStatus = "未冻结";

    @OneToOne
    @JoinColumn(name = "ba_id")
    //余额id
    private Balance balance;


    public UserEntity(String userId,String loginName, String pwd, String userName, String sex, String phoneNum, String aptitudeImg, String identityImg, String companyName) {
        this.userId = userId;
        this.loginName = loginName;
        this.pwd = pwd;
        this.userName = userName;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.aptitudeImg = aptitudeImg;
        this.identityImg = identityImg;
        this.companyName = companyName;
    }

}
