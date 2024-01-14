package com.example.backend.service.impl;

import com.example.backend.common.Result;
import com.example.backend.controller.AliPayController;
import com.example.backend.mapper.AliPayMapper;
import com.example.backend.pojo.Pay;
import com.example.backend.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    AliPayMapper aliPayMapper;

    @Override
    public Result getPayFeedback(String id, String username) {
        List<Pay> list = aliPayMapper.selectPayById(id);
        if(list.size() == 0){
            return Result.error("充值失败!");
        }else{
            Integer ownerId = aliPayMapper.getIdByUsername(username);
            aliPayMapper.payUpdate(ownerId, list.get(0).getMoney());
            return Result.success("充值成功！");
        }
    }

    @Override
    public double getBalance(String username) {
        Integer ownerId = aliPayMapper.getIdByUsername(username);
        return aliPayMapper.getBalance(ownerId);
    }

    @Override
    public void pay(String id, double money) {
        aliPayMapper.pay(id,money);
    }
}
