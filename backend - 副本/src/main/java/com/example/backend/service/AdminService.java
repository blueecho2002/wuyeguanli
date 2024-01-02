package com.example.backend.service;

import com.example.backend.pojo.Admin;
import org.springframework.stereotype.Component;


public interface AdminService {
    Admin AdminLogin(String username ,String password);
}
