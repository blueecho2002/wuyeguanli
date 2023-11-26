package com.example.backend.controller;

import com.example.backend.pojo.Admin;
import com.example.backend.pojo.Result;
import com.example.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin-login")
    public Result selectAdminByUsername(String username, String password){
        Admin admin = adminService.AdminLogin(username, password);
        System.out.println("账号："+username);
        System.out.println("密码："+password);
        System.out.println();
        if(admin == null) return Result.success();
        else return Result.success(admin);
    }
}
