package com.example.backend.controller;

import com.example.backend.common.PageBean;
import com.example.backend.common.Result;
import com.example.backend.pojo.ChargeItem;
import com.example.backend.service.ChargeDetailService;
import com.example.backend.service.ChargeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class ChargeItemController {
    @Autowired
    private ChargeItemService chargeItemService;

    @GetMapping("/getAllChargeItem")
    public Result getAllChargeItem(){
        List<ChargeItem> res = chargeItemService.getAllChargeItem();
        return Result.success(res);
    }

    @GetMapping("/getChargeItem")
    public Result getChargeItem(int page, int pageSize, String chargeItemName, String chargeItemId, String from_time, String to_time){
        Timestamp fromTimeStamp = null, toTimeStamp = null;
        if(from_time != null)fromTimeStamp = Timestamp.valueOf(from_time);
        if(from_time != null)toTimeStamp = Timestamp.valueOf(to_time);
        PageBean pageBean = chargeItemService.getChargeItem(page, pageSize, chargeItemName, chargeItemId, fromTimeStamp, toTimeStamp);
        return Result.success(pageBean);
    }


    @PostMapping("/addChargeItem")
    public Result addChargeItem(@RequestBody ChargeItem chargeItem){
        chargeItemService.addChargeItem(chargeItem);
        return Result.success();
    }

    @DeleteMapping("deleteChargeItem/{id}")
    public Result deleteChargeDetail(@PathVariable Integer id){
        chargeItemService.deleteChargeItem(id);
        return Result.success();
    }
}
