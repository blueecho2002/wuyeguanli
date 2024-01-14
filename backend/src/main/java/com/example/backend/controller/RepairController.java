package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.backend.common.R;
import com.example.backend.pojo.*;
import com.example.backend.service.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.example.backend.common.Code.REQUEST_FAILURE;

@Api(tags = "报修接口")
@RestController
@Slf4j
@RequestMapping("/repair")
public class RepairController {

    @Resource
    HouseService houseService;

    @Resource
    RepairService repairService;

    @Resource
    DeviceService deviceService;

    @Resource
    HouseDeviceMappingService houseDeviceMappingService;

    @Resource
    PersonnelService personnelService;

    @Resource
    DeviceRepairPersonnelMappingService deviceRepairPersonnelMappingService;

    @Resource
    OwnerService ownerService;



    @GetMapping("/selectRepairs/{currentPage}/{pageSize}")
    public R<?> selectRepairs(@PathVariable Integer currentPage, @PathVariable Integer pageSize,
                              @RequestParam String houseId,@RequestParam String serviceName,
                              @RequestParam String name, @RequestParam String personnelName,@RequestParam String status,
                              @RequestParam(required = false) String date1, @RequestParam(required = false) String date2){
        IPage<Repair> page =repairService.getPage(currentPage, pageSize,houseId, serviceName, name, personnelName, status, date1, date2);

        //如果当前页码值大于了总页码值，就重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = repairService.getPage(currentPage, pageSize,houseId, serviceName, name, personnelName, status, date1, date2);
        }
        return R.success(page);
    }

    @GetMapping("/selectHouseIds")
    public R<?> selectHouseIds() {
        return R.success(houseService.selectAllIds());
    }

    @DeleteMapping("/deleteRepairById/{id}")
    public R<?> deleteRepairById(@PathVariable("id") String id) {
        return R.success(repairService.deleteById(id));
    }

    @PostMapping("/batchDeleteRepair")
    public R<?> batchDeleteRepair(@RequestBody List<String> repairIds) {
        try {
            // 在这里调用您的服务层或数据访问层执行批量删除
            repairService.batchDeleteRepairs(repairIds);
            return R.success("批量删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(REQUEST_FAILURE,"批量删除失败");
        }
    }

    @GetMapping("/getDevicesByHouseId")
    public R<?> getDevicesByHouseId(@RequestParam String houseId) {
        List<Integer> deviceIds = houseDeviceMappingService.getDeviceId(houseId);
        return R.success(deviceService.selectByIds(deviceIds));
    }

    @GetMapping("/selectPersonnel")
    public R<?> selectPersonnel() {
        System.out.println(1);
        System.out.println(personnelService.select());
        return R.success(personnelService.select());
    }

    @PostMapping("/insertRepair")
    public R<?> insertRepair(@RequestBody JSONObject requestData) {

        // 从requestData中获取数据
        String description = requestData.getStr("description");
        Integer houseId = requestData.getInt("houseId");
        Integer deviceId = requestData.getInt("deviceId");
        Integer ownerId = requestData.getInt("ownerId");
        Integer personnelId = requestData.getInt("personnelId");
        Repair repair = new Repair();
        repair.setHouseId(houseId);
        repair.setDescription(description);
        System.out.println(deviceId);
        String deviceName = deviceService.selectById(deviceId);

        repair.setDeviceName(deviceName);
        Owner owner = ownerService.selectById(ownerId);
        repair.setName(owner.getName());
        repair.setPhoneNumber(owner.getTelephone());
        Personnel personnel = personnelService.selectById(personnelId);
        repair.setPersonnelName(personnel.getName());
        repair.setPersonnelPhoneNumber(personnel.getTelephone());
        repair.setStatus("0");
        System.out.println(repair);
        // 插入数据到repair表
        Integer repairId = repairService.insertAndGetId(repair);
        DeviceRepairPersonnelMapping deviceRepairPersonnelMapping = new DeviceRepairPersonnelMapping();
        deviceRepairPersonnelMapping.setDeviceId(deviceId);
        deviceRepairPersonnelMapping.setPersonnelId(personnelId);
        deviceRepairPersonnelMapping.setRepairId(repairId);
        return R.success(deviceRepairPersonnelMappingService.insert(deviceRepairPersonnelMapping));
    }

    @PostMapping("/updateRepair")
    public R<?> updateRepair(@RequestBody JSONObject requestData) {
        Integer personnelId = requestData.getInt("personnelId");
        Integer repairId = requestData.getInt("id");
        deviceRepairPersonnelMappingService.update(repairId, personnelId);
        Personnel personnel = personnelService.selectById(personnelId);
        repairService.update(repairId, personnel);
        return R.success(repairService.update(repairId, personnel));
    }
}