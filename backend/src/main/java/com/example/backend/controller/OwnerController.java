package com.example.backend.controller;

import com.example.backend.pojo.Owner2;
import com.example.backend.common.PageBean;
import com.example.backend.common.Result;
import com.example.backend.service.Owner2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OwnerController {

    @Autowired
    private Owner2Service owner2Service;

    @GetMapping("/getOwner")
    public Result getOwner(@RequestParam(defaultValue = "1")Integer page,
                           @RequestParam(defaultValue = "10")Integer pageSize,
                           String name)
    {
        PageBean owner = owner2Service.list(page, pageSize,name);
        return Result.success(owner);
    }

    @GetMapping("/group")
    public Result group(@RequestParam(defaultValue = "1")Integer page,
                        @RequestParam(defaultValue = "10")Integer pageSize)
    {
        PageBean owner = owner2Service.group(page, pageSize);
        return Result.success(owner);
    }

    @PostMapping("AddOwner")
    public Result AddOwner(@RequestBody Owner2 owner2)
    {

        owner2Service.addowner(owner2);
        System.out.println(owner2);
        owner2Service.ownerhousemap(owner2);
        return Result.success();
    }

    @PostMapping("EditOwner")
    public Result EditOwner(@RequestBody Owner2 owner2)
    {

        owner2Service.updateowner(owner2);
        System.out.println(owner2);
        owner2Service.updateownerhousemap(owner2);
        return Result.success();
    }

    @DeleteMapping("DeleteOwner/{id}")
    public Result DeleteOwner(@PathVariable Integer id)
    {
        owner2Service.delete(id);
        System.out.println(id);
        owner2Service.deleteohm(id);
        return Result.success();
    }
}
