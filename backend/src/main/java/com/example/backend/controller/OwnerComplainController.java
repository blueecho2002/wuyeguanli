package com.example.backend.controller;


import com.example.backend.pojo.Complain;
import com.example.backend.common.PageBean;
import com.example.backend.common.Result;
import com.example.backend.service.OwnerComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OwnerComplainController {

    @Autowired
    private OwnerComplainService ownerComplainService;

    @GetMapping("/getComplain")
    public Result getComplain(@RequestParam(defaultValue = "1")Integer page,
                           @RequestParam(defaultValue = "10")Integer pageSize,
                           String name)
    {
        PageBean complain = ownerComplainService.list(page, pageSize,name);
        return Result.success(complain);
    }

    @PostMapping("AddComplain")
    public Result AddComplain(@RequestBody Complain complain)
    {

        ownerComplainService.addcomplain(complain);
        System.out.println(complain);
        return Result.success();
    }

    @PostMapping("EditComplain")
    public Result EditComplain(@RequestBody Complain complain)
    {

        ownerComplainService.updatecomplain(complain);
        System.out.println(complain);
        return Result.success();
    }

    @DeleteMapping("DeleteComplain/{id}")
    public Result DeleteComplain(@PathVariable Integer id)
    {
        ownerComplainService.delete(id);
        System.out.println(id);
        return Result.success();
    }
}
