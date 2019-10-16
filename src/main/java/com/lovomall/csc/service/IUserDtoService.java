package com.lovomall.csc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovomall.csc.dto.UserDto;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * 用户注册对象业务接口
 */
public interface IUserDtoService {
    /**
     * 分页显示用户注册信息
     * @param pageNO 开始位置
     * @param pageSize 行数
     * @return 用户注册信息集合
     */
    public Page<UserDto> findUserDtoPage(int pageNO, int pageSize);

    /**
     * 保存用户注册对象并发送成功的反馈信息
     * @param userDtoJson 用户注册对象Json字符串
     * @throws IOException
     */
    public void saveUserDto(String userDtoJson) throws IOException;

    /**
     * 根据用户注册对象Id查询对象
     * @param userId 对象id
     * @return UserDto对象
     */
    public UserDto findUserDtoById(String userId);

    /**
     * 根据用户id删除对象并发送不通过的反馈信息
     * @param userId 用户id
     * @param remark 不通过的信息
     */
    public void deleteByUserId(String userId, String remark) throws JsonProcessingException;
}
