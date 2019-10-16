package com.lovomall.csc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovomall.csc.dto.StatusDto;
import com.lovomall.csc.dto.UserAddDto;
import com.lovomall.csc.dto.UserStatusDto;
import com.lovomall.csc.entity.*;
import com.lovomall.csc.repository.*;
import com.lovomall.csc.service.IUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户业务接口实现类
 */
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private IAddRepository addRepository;
    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private IUserDtoRepository userDtoRepository;

    @Autowired
    private IStatusDtoRepository statusDtoRepository;

    @Override
    public void saveUserEntity(UserEntity userEntity) throws JsonProcessingException {
        Balance b = new Balance();
        b.setUserId(userEntity.getUserId());
        balanceRepository.save(b);
        userEntity.setBalance(b);
        userRepository.save(userEntity);
        userDtoRepository.deleteById(userEntity.getUserId());
        AddEntity addEntity = new AddEntity();
        addEntity.setUserId(userEntity.getUserId());
        addRepository.save(addEntity);
        UserAddDto userAddDto = new UserAddDto(userEntity.getUserId(), "通过", "");
        ActiveMQQueue mqQueue = new ActiveMQQueue("getUserMessage");
        //把user对象转换为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userAddDto);
        //把json字符串放入到MQ
        jmsMessagingTemplate.convertAndSend(mqQueue, json);
        
    }


    @Override
    public String updateUserStatus(String userId, String useruserStatus) throws JsonProcessingException {
        UserEntity userUpdate = userRepository.findUserById(userId);
        String s1 = "";
        if ("已冻结".equals(useruserStatus)){
            userUpdate.setUserStatus("已冻结");
            s1 = "用户冻结";
        }
        else {
            userUpdate.setUserStatus("正常");
            s1 = "用户解冻";
        }
        userRepository.save(userUpdate);
        statusDtoRepository.deleteByUserId(userId);
        StatusEntity statusEntity = new StatusEntity("通过",s1);
        statusEntity.setUserEntity(userUpdate);
        statusRepository.save(statusEntity);
        UserStatusDto userStatusDto = new UserStatusDto(userId,userUpdate.getUserStatus(),
                "","通过");
        ActiveMQQueue mqQueue = new ActiveMQQueue("AuditMessageMQ");
        //把user对象转换为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userStatusDto);
        //把json字符串放入到MQ
        jmsMessagingTemplate.convertAndSend(mqQueue, json);
        return "ok";
    }

    @Override
    public UserEntity findUserById(String userId) {
        return userRepository.findUserById(userId);
    }
}
