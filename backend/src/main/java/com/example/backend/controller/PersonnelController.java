package com.example.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.backend.common.R;
import com.example.backend.pojo.Parking;
import com.example.backend.pojo.Personnel;
import com.example.backend.pojo.Repair;
import com.example.backend.service.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "无业人员接口")
@RestController
@Slf4j
@RequestMapping("/personnel")
public class PersonnelController {

    @Resource
    RepairService repairService;

    @Resource
    PersonnelService personnelService;

    @Resource
    UserPersonnelMappingService userPersonnelMappingService;

    @Resource
    DeviceRepairPersonnelMappingService deviceRepairPersonnelMappingService;

    @Resource
    ComplainService complainService;

    @Resource
    ComplainPersonnelMappingService complainPersonnelMappingService;

    @PutMapping("updateStatus/{id}/{status}")
    public R<?> updateStatus(@PathVariable Integer id, @PathVariable String status) {
        // 使用服务或存储库更新数据库中的状态
        return R.success(repairService.updateStatus(id, status));
    }

    @GetMapping("/selectPersonnel/{currentPage}/{pageSize}")
    public R<?> selectPersonnel(@PathVariable Integer currentPage, @PathVariable Integer pageSize,
                                @RequestParam String houseId,@RequestParam String serviceName,
                                @RequestParam String name, @RequestParam String personnelName,@RequestParam String status,
                                @RequestParam(required = false) String date1, @RequestParam(required = false) String date2){
        String username = personnelName;
        Integer personnelId = userPersonnelMappingService.getIdByUsername(username);
        List<Integer> repairIds = deviceRepairPersonnelMappingService.getListByPersonnelId(personnelId);
        System.out.println(repairIds);
        IPage<Repair> page =personnelService.getPage(currentPage, pageSize, houseId, serviceName, name, repairIds, status, date1, date2);

        //如果当前页码值大于了总页码值，就重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = personnelService.getPage(currentPage, pageSize,houseId, serviceName, name, repairIds, status, date1, date2);
        }
        return R.success(page);
    }

    @PutMapping("/updateComplainStatus/{id}/{status}")
    public R<?> updateComplainStatus(@PathVariable Integer id, @PathVariable String status) {
        // 使用服务或存储库更新数据库中的状态
        return R.success(complainService.updateStatus(id, status));
    }

    @GetMapping("/selectComplains/{currentPage}/{pageSize}")
    public R<?> selectComplains(@PathVariable Integer currentPage, @PathVariable Integer pageSize,
                                @RequestParam String username, @RequestParam String name, @RequestParam String description,
                                @RequestParam(required = false) String date1, @RequestParam(required = false) String date2){
        Integer personnelId = userPersonnelMappingService.getIdByUsername(username);

        List<Integer> complainIds = complainPersonnelMappingService.getIdsByPersonnelId(personnelId);
        System.out.println(complainIds);
        IPage<com.example.backend.pojo.Complain> page = personnelService.getPage2(currentPage, pageSize, complainIds, name, description, date1, date2);
        //如果当前页码值大于了总页码值，就重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = personnelService.getPage2((int)page.getPages(), pageSize, complainIds,name, description, date1, date2);
        }
        return R.success(page);
    }
}