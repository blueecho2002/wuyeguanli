package com.example.backend.service.impl;

import com.example.backend.common.Result;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User AdminLogin(String username, String password){
        return userMapper.selectAdmin(username, password);
    }

    @Override
    public Result register(String idCard, String username, String password) {
        Integer ownerId = userMapper.getIdByIdCard(idCard);
        if(ownerId == null)return Result.error("身份证号不存在!");
        if(userMapper.isAccountExist(ownerId) != 0)return Result.error("该身份证号已经注册！");
        if(userMapper.isUsernameUsed(username) != 0)return Result.error("用户名已存在！");

        String telephone = userMapper.getTelephoneByOwnerId(ownerId);

        userMapper.addUser(username, password);
        userMapper.register(ownerId, username);
        userMapper.addPrePayment(ownerId,telephone);
        return Result.success();
    }

    @Override
    public String getNameByUsername(String username) {
        return userMapper.getNameByUsername(username);
    }
}
