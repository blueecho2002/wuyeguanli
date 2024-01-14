package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.pojo.User;

public interface UserService {
    User AdminLogin(String username , String password);
    Result register(String idCard, String username, String password);

    String getNameByUsername(String username);
}
