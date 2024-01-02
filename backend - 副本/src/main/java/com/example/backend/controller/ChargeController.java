package com.example.backend.controller;

import com.example.backend.mapper.ChargeMapper;
import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.ChargeItem;
import com.example.backend.pojo.PageBean;
import com.example.backend.pojo.Result;
import com.example.backend.service.ChargeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @GetMapping("/getChargeDetail")
    public Result getChargeDetail(int page, int pageSize, String owner, String chargeItem,
                                  String from_time, String to_time, String ownerId){
        Timestamp fromTimeStamp = null, toTimeStamp = null;
        Integer ownerId_int = null;
        if(from_time != null)fromTimeStamp = Timestamp.valueOf(from_time);
        if(from_time != null)toTimeStamp = Timestamp.valueOf(to_time);
        if(ownerId != null && ownerId != "")ownerId_int = Integer.parseInt(ownerId);
        PageBean pageBean = chargeService.getChargeDetail(page,pageSize,owner,chargeItem,fromTimeStamp,toTimeStamp,ownerId_int);
        return Result.success(pageBean);
    }

    @GetMapping("/getAllChargeItem")
    public Result getAllChargeItem(){
        List<ChargeItem> res = chargeService.getAllChargeItem();
        return Result.success(res);
    }

    @GetMapping("/getChargeItem")
    public Result getChargeItem(int page, int pageSize, String chargeItemName, String chargeItemId, String from_time, String to_time){
        Timestamp fromTimeStamp = null, toTimeStamp = null;
        if(from_time != null)fromTimeStamp = Timestamp.valueOf(from_time);
        if(from_time != null)toTimeStamp = Timestamp.valueOf(to_time);
        PageBean pageBean = chargeService.getChargeItem(page, pageSize, chargeItemName, chargeItemId, fromTimeStamp, toTimeStamp);
        return Result.success(pageBean);
    }

    @PostMapping("/editChargeDetail")
    public Result editChargeDetail(@RequestBody ChargeDetail chargeDetail){
        System.out.println(chargeDetail);
        if(chargeService.editDetail(chargeDetail)) return Result.success();
        else return Result.error("付款人ID不存在");
    }

    @DeleteMapping("deleteChargeDetail/{id}")
    public Result deleteChargeDetail(@PathVariable Integer id){
        chargeService.deleteChargeDetail(id);
        return Result.success();
    }

    @PostMapping("/addChargeDetail")
    public Result addChargeDetail(@RequestBody ChargeDetail chargeDetail){
        System.out.println(chargeDetail);
        if(chargeService.addChargeDetail(chargeDetail))return Result.success();
        else return Result.error("付款人ID不存在");
    }

    @PostMapping("/addChargeItem")
    public Result addChargeItem(@RequestBody ChargeItem chargeItem){
        chargeService.addChargeItem(chargeItem);
        return Result.success();
    }

}
