package com.example.backend.service;

import com.example.backend.pojo.Admin;

public interface AdminService {
    Admin AdminLogin(String username ,String password);
}
