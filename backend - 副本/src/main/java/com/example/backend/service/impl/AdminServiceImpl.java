package com.example.backend.service.impl;

import com.example.backend.mapper.AdminMapper;
import com.example.backend.pojo.Admin;
import com.example.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin AdminLogin(String username,String password){
        return adminMapper.selectAdmin(username, password);
    }
}
