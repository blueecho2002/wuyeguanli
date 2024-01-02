package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.example.backend.common.R;
import com.example.backend.pojo.Owner;
import com.example.backend.pojo.Parking;
import com.example.backend.pojo.ParkingOwnerMapping;
import com.example.backend.service.OwnerService;
import com.example.backend.service.ParkingOwnerService;
import com.example.backend.service.ParkingService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.backend.common.Code.REQUEST_FAILURE;


@Api(tags = "投诉接口")
@RestController
@Slf4j
@RequestMapping("/park")
public class ParkingController {

    @Resource
    ParkingService parkingService;

    @Resource
    OwnerService ownerService;

    @Resource
    ParkingOwnerService parkingOwnerService;

    @GetMapping("/selectPark/{currentPage}/{pageSize}")
    public R<?> selectPark(@PathVariable Integer currentPage, @PathVariable Integer pageSize,
                           @RequestParam String name, @RequestParam String gender,
                           @RequestParam String date1, @RequestParam String date2){

        IPage<Parking> page = parkingService.getPage(currentPage, pageSize,name, gender, date1, date2);
        //如果当前页码值大于了总页码值，就重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = parkingService.getPage((int)page.getPages(), pageSize,name, gender, date1, date2);
        }
        return R.success(page);
    }

    @DeleteMapping("/deleteParkById/{id}")
    public R<?> deleteParkById(@PathVariable("id") String id) {
        return R.success(parkingService.deleteById(id));
    }

    @PostMapping("/batchDeleteParks")
    public R<?> batchDeleteParks(@RequestBody List<String> parkIds) {
        try {
            // 在这里调用您的服务层或数据访问层执行批量删除
            parkingService.batchDeleteComplains(parkIds);
            return R.success("批量删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(REQUEST_FAILURE,"批量删除失败");
        }
    }

    @GetMapping("/selectOwner")
    public R<?> selectOwner() {
        return R.success(ownerService.selectAll());
    }

    @GetMapping("/selectPark")
    public R<?> selectPark() {
        return R.success(parkingService.selectAll());
    }

    @PostMapping("/insertPark")
    public R<?> insertPark(@RequestBody JSONObject requestData) {

        // 从requestData中获取数据
        Integer parkId = requestData.getInt("id");
        if(!parkingOwnerService.isEmpty(parkId)){
            System.out.println(parkingOwnerService.isEmpty(parkId));
            return R.error(REQUEST_FAILURE, "车位已有所有人");
        }
        Integer ownerId = requestData.getInt("ownerId");
        ParkingOwnerMapping parkingOwnerMapping = new ParkingOwnerMapping();
        Owner owner = ownerService.getById(ownerId);
        Parking parking = new Parking();
        parking.setId(parkId);
        parking.setGender(owner.getGender());
        parking.setName(owner.getName());
        parking.setPhoneNumber(owner.getTelephone());
        parking.setUpdateTime(LocalDateTime.now());
        parkingOwnerMapping.setParkingId(parkId);
        parkingOwnerMapping.setOwnerId(ownerId);
        if(parkingOwnerService.isExist(parkId)){
            parkingOwnerService.updateByParkingId(parkId, ownerId);
            parkingService.updateById(parking);
        }
        else {
            parkingOwnerService.insert(parkingOwnerMapping);
            parkingService.updateById(parking);
        }
        return R.success("车位添加成功");
    }

    @PostMapping("/updatePark")
    public R<?> updatePark(@RequestBody JSONObject requestData) {
// 从requestData中获取数据
        Integer parkId = requestData.getInt("id");
        Integer ownerId = requestData.getInt("ownerId");
        System.out.println(parkId);
        System.out.println(ownerId);
        ParkingOwnerMapping parkingOwnerMapping = new ParkingOwnerMapping();
        Owner owner = ownerService.getById(ownerId);
        Parking parking = new Parking();
        parking.setId(parkId);
        parking.setGender(owner.getGender());
        parking.setName(owner.getName());
        parking.setPhoneNumber(owner.getTelephone());
        parking.setUpdateTime(LocalDateTime.now());
        parkingOwnerMapping.setParkingId(parkId);
        parkingOwnerMapping.setOwnerId(ownerId);
        boolean flag = parkingOwnerService.updateByParkingId(parkId, ownerId);
        return R.success(parkingService.updateById(parking));
    }
}
