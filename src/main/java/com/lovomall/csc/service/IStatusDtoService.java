package com.lovomall.csc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovomall.csc.dto.StatusDto;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * 用户状态修改对象特务接口
 */
public interface IStatusDtoService {
    /**
     * 分页显示用户状态修改信息
     * @param pageNO 开始位置
     * @param pageSize 行数
     * @return 用户状态修改信息集合
     */
    public Page<StatusDto> findUserStatusDtoPage(int pageNO, int pageSize);

    /**
     * 保存用户状态修改对象,并传输反馈信息
     * @param statusJson 状态修改对象Json字符串
     * @throws IOException
     */
    public void saveStatusDto(String statusJson) throws IOException;

    /**
     * 根据用户id删除请求,并传输反馈信息
     * @param userId 用户id
     * @param remark 不通过的信息
     */
    public void deleteByUserId(String userId, String remark) throws JsonProcessingException;


    public StatusDto findByUserId(String userId);
}
