package com.lovomall.csc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovomall.csc.dto.StatusDto;
import com.lovomall.csc.dto.UserAddDto;
import com.lovomall.csc.dto.UserDto;
import com.lovomall.csc.entity.UserEntity;
import com.lovomall.csc.service.IStatusDtoService;
import com.lovomall.csc.service.IUserDtoService;
import com.lovomall.csc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {
    private final IUserService userService;
    private final IUserDtoService userDtoService;
    private final IStatusDtoService statusDtoService;


    @Autowired
    public UserController(IUserService userService,IUserDtoService userDtoService,IStatusDtoService statusDtoService){
        this.userService = userService;
        this.userDtoService = userDtoService;
        this.statusDtoService = statusDtoService;
    }


    //分页显示用户注册请求
    @RequestMapping(path = "/page")
    @ResponseBody
    public Map<String,Object> UserList(int pageNO){
        Map<String, Object> map = new HashMap<>();
        Page<UserDto> p = userDtoService.findUserDtoPage(pageNO - 1,5);

        map.put("data",p.getContent());
        map.put("count",(int)p.getTotalElements());

        return map;
    }

    //分页显示用户修改状态请求
    @RequestMapping(path = "/statuspage")
    @ResponseBody
    public Map<String,Object> StatusList(int pageNO){
        Map<String, Object> map = new HashMap<>();
        Page<StatusDto> p = statusDtoService.findUserStatusDtoPage(pageNO - 1,5);

        map.put("data",p.getContent());
        map.put("count",(int)p.getTotalElements());

        return map;
    }

    //添加用户并传输信息
    @RequestMapping(path = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public String saveUserEntity(String userId){
        UserDto userDto = userDtoService.findUserDtoById(userId);
        try {
            userService.saveUserEntity(new UserEntity(userDto.getUserId(),userDto.getLoginUser(),userDto.getPwd(),
                    userDto.getUserName(), userDto.getSex(),userDto.getPhoneNum(),userDto.getAptitudeImg(),
                    userDto.getIdentityImg(),userDto.getCompanyName()));
        } catch (Exception e) {
            return "down";
        }
        return "pass";
    }

    //修改用户状态并传输信息
    @RequestMapping(path = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public String updateUserStatusEntity(String userId){
        StatusDto statusDto = statusDtoService.findByUserId(userId);
        String statusIS = statusDto.getUserStatus();
        System.out.println(statusIS);
        String s="";
        try {
            s = userService.updateUserStatus(userId, statusIS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if("ok".equals(s)){
            return "pass";
        }
        return "down";
    }

    @RequestMapping(path = "/addDown",method = RequestMethod.POST)
    @ResponseBody
    public String addDownMassage(String userId,String remark) throws JsonProcessingException {
        userDtoService.deleteByUserId(userId,remark);
        return "pass";
    }

    @RequestMapping(path = "/updateDown",method = RequestMethod.POST)
    @ResponseBody
    public String updateDownMassage(String userId,String remark) throws JsonProcessingException {
        statusDtoService.deleteByUserId(userId,remark);
        return "pass";
    }

}
