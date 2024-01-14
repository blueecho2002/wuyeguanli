package com.example.backend.controller;

import com.example.backend.pojo.Meter;
import com.example.backend.common.PageBean;
import com.example.backend.common.Result;
import com.example.backend.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeterController {

    @Autowired
    private MeterService meterService;

    @GetMapping("/getAllMeter")
    public Result getAllMeter(@RequestParam(defaultValue = "1")Integer page,
                           @RequestParam(defaultValue = "10")Integer pageSize,
                           String meterType)
    {
        PageBean meter = meterService.list(page, pageSize,meterType);
        return Result.success(meter);
    }

    @GetMapping("/getMyMeter")
    public Result getMyMeter(@RequestParam(defaultValue = "1")Integer page,
                              @RequestParam(defaultValue = "10")Integer pageSize,
                              String personnel)
    {
        PageBean meter = meterService.list1(page, pageSize,personnel);
        return Result.success(meter);
    }

    @PostMapping("/AddMeter")
    public Result AddMeter(@RequestBody Meter meter)
    {

        meterService.addmeter(meter);
        System.out.println(meter);
        return Result.success();
    }

    @PostMapping("/EditMeter")
    public Result EditMeter(@RequestBody Meter meter)
    {

        meterService.updatemeter(meter);
        System.out.println(meter);
        return Result.success();
    }

    @PostMapping("/EditMyMeter")
    public Result EditMyMeter(@RequestBody Meter meter)
    {

        meterService.updateMyMeter(meter);
        System.out.println(meter);
        return Result.success();
    }

    @DeleteMapping("/DeleteMeter/{meterId}")
    public Result DeleteMeter(@PathVariable Integer meterId)
    {
        meterService.delete(meterId);
        System.out.println(meterId);
        return Result.success();
    }
}
