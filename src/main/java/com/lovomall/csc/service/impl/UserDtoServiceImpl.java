package com.lovomall.csc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dto.UserAddDto;
import com.lovomall.csc.dto.UserDto;
import com.lovomall.csc.repository.IUserDtoRepository;
import com.lovomall.csc.service.IUserDtoService;
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
 * 用户注册对象业务接口实现类
 */
@Service(value = "userDtoService")
@Transactional
public class UserDtoServiceImpl implements IUserDtoService {
    @Autowired
    private IUserDtoRepository userDtoRepository;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public Page<UserDto> findUserDtoPage(int pageNO, int pageSize) {
        Pageable p = PageRequest.of(pageNO, pageSize);
        return userDtoRepository.findUserDtoPage(p);
    }

    @Override
    @JmsListener(destination = "sendUserMessage")
    public void saveUserDto(String userDtoJson) throws IOException {
        //获得MQ里面的用户信息
        ObjectMapper obj = new ObjectMapper();
        UserDto userDto = obj.readValue(userDtoJson, UserDto.class);
        //把MQ里的用户保存到服务器
        userDtoRepository.save(userDto);
    }

    @Override
    public void deleteByUserId(String userId,String remark) throws JsonProcessingException {
        UserAddDto userAddDto = new UserAddDto(userId,"不通过",remark);
        ActiveMQQueue mqQueue = new ActiveMQQueue("getUserMessage");
        //把user对象转换为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userAddDto);
        jmsMessagingTemplate.convertAndSend(mqQueue,json);
        userDtoRepository.deleteByUserId(userId);
    }

    @Override
    public UserDto findUserDtoById(String userId) {
        return userDtoRepository.findUserDtoById(userId);
    }
}
