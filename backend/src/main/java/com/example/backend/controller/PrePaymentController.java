package com.example.backend.controller;

import com.example.backend.common.PageBean;
import com.example.backend.common.Result;
import com.example.backend.mapper.PrePaymentMapper;
import com.example.backend.service.PrePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;

@RestController
public class PrePaymentController {

    @Autowired
    private PrePaymentService prePaymentService;

    @Autowired
    private PrePaymentMapper prePaymentMapper;

    @GetMapping("/getPrePayment")
    public Result getPrePayment(int page, int pageSize, String owner, String ownerId,
                                  String from_time, String to_time){
        Timestamp fromTimeStamp = null, toTimeStamp = null;
        Integer ownerId_int = null;
        if(from_time != null)fromTimeStamp = Timestamp.valueOf(from_time);
        if(from_time != null)toTimeStamp = Timestamp.valueOf(to_time);
        if(ownerId != null && ownerId != "")ownerId_int = Integer.parseInt(ownerId);
        PageBean pageBean = prePaymentService.getPrePayment(page,pageSize,owner,ownerId_int,fromTimeStamp,toTimeStamp);
        return Result.success(pageBean);
    }

    @DeleteMapping("/deletePrePayment/{id}")
    public Result deletePrePayment(@PathVariable("id")Integer id){
        prePaymentService.deletePrePayment(id);
        return Result.success();
    }
    @GetMapping("/editBalance")
    public Result editBalance(Integer ownerId, Double balance){
        System.out.println(ownerId+" "+balance);
        prePaymentMapper.editBalance(ownerId,balance);
        return Result.success();
    }

}
