package com.example.backend.service;

import com.example.backend.common.Result;

public interface AliPayService {
    Result getPayFeedback(String id, String username);
    double getBalance(String username);
    void pay(String id, double money);
}
