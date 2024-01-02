package com.example.backend.controller;

import com.example.backend.common.R;
import com.example.backend.service.RepairService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "无业人员接口")
@RestController
@Slf4j
@RequestMapping("/personnel")
public class PersonnelController {

    @Resource
    RepairService repairService;

    @PutMapping("updateStatus/{id}/{status}")
    public R<?> updateStatus(@PathVariable Integer id, @PathVariable String status) {
        // 使用服务或存储库更新数据库中的状态
        return R.success(repairService.updateStatus(id, status));
    }
}
