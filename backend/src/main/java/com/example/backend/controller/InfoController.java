package com.example.backend.controller;

import com.example.backend.pojo.Info;
import com.example.backend.common.PageBean;
import com.example.backend.common.Result;
import com.example.backend.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/getInfo")
    public Result getInfo(@RequestParam(defaultValue = "1")Integer page,
                          @RequestParam(defaultValue = "10")Integer pageSize){
        PageBean info = infoService.list(page, pageSize);
        return Result.success(info);
    }
    @PostMapping("editInfo")
    public Result editInfo(@RequestBody Info info)
    {

        infoService.updateInfo(info);
        System.out.println(info);
        return Result.success();
    }
}
