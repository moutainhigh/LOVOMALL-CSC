package com.lovomall.csc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovomall.csc.entity.ConsumeRecord;
import com.lovomall.csc.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户业务接口
 */
public interface IUserService {
    /**
     * 注册用户,同时添加注册记录，设置默认折扣等级和余额
     * @param userEntity 用户对象
     */
    public void saveUserEntity(UserEntity userEntity) throws JsonProcessingException;

    /**
     * 根据用户id修改用户状态，同时添加状态审核记录
     * @param userId 用户id
     * @param useruserStatus 用户状态
     * @return 修改后提示
     */
    public String updateUserStatus(String userId, String useruserStatus) throws JsonProcessingException;

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户对象
     */
    public UserEntity findUserById(String userId);


}
