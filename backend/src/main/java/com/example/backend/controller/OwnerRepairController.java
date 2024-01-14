package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.service.OwnerRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerRepairController {

    @Autowired
    OwnerRepairService ownerRepairService;

    @GetMapping("/addRepair")
    public Result addRepair(Integer houseId, String deviceName, String description, String ownerName){
        ownerRepairService.addRepair(houseId,deviceName,description,ownerName);
        return Result.success();
    }
}
