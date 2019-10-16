package com.lovomall.csc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dto.StatusDto;
import com.lovomall.csc.dto.UserStatusDto;
import com.lovomall.csc.entity.UserEntity;
import com.lovomall.csc.repository.IStatusDtoRepository;
import com.lovomall.csc.service.IStatusDtoService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;




/**
 * 用户状态修改对象业务接口实现类
 */
@Service(value = "userStatusDtoService")
@Transactional
public class StatusDtoServiceImpl implements IStatusDtoService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private IStatusDtoRepository statusDtoRepository;

    @Override
    public Page<StatusDto> findUserStatusDtoPage(int pageNO, int pageSize) {
        Pageable p = PageRequest.of(pageNO,pageSize);
        return statusDtoRepository.findStatusDtoPage(p);
    }

    @Override
    @JmsListener(destination = "UserDownMQ")
    public void saveStatusDto(String statusJson) throws IOException {
        //获得MQ里面的用户信息
        ObjectMapper obj = new ObjectMapper();
        StatusDto statusDto = obj.readValue(statusJson, StatusDto.class);
        //把MQ里的用户保存到服务器
        statusDtoRepository.save(statusDto);
    }

    @Override
    public void deleteByUserId(String userId, String remark) throws JsonProcessingException {
        StatusDto statusDto = statusDtoRepository.findByUserId(userId);
        UserStatusDto userStatusDto = new UserStatusDto(userId,statusDto.getUserStatus(),remark,"不通过");
        System.out.println(userStatusDto.getDate());
        ActiveMQQueue mqQueue = new ActiveMQQueue("AuditMessageMQ");
        //把user对象转换为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userStatusDto);
        jmsMessagingTemplate.convertAndSend(mqQueue,json);
        statusDtoRepository.deleteByUserId(userId);
    }

    @Override
    public StatusDto findByUserId(String userId) {
        return statusDtoRepository.findByUserId(userId);
    }
}
