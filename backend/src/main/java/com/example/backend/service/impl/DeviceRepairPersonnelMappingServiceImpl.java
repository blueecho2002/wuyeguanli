package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.DeviceRepairPersonnelMappingMapper;
import com.example.backend.pojo.DeviceRepairPersonnelMapping;
import com.example.backend.pojo.HouseDeviceMapping;
import com.example.backend.service.DeviceRepairPersonnelMappingService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceRepairPersonnelMappingServiceImpl extends ServiceImpl<DeviceRepairPersonnelMappingMapper, DeviceRepairPersonnelMapping> implements DeviceRepairPersonnelMappingService {

    @Resource
    DeviceRepairPersonnelMappingMapper deviceRepairPersonnelMappingMapper;

    @Override
    public Integer insert(DeviceRepairPersonnelMapping deviceRepairPersonnelMapping) {
        return deviceRepairPersonnelMappingMapper.insert(deviceRepairPersonnelMapping);
    }

    @Override
    public Integer update(Integer repairId, Integer personnelId) {
        DeviceRepairPersonnelMapping updatedMapping = new DeviceRepairPersonnelMapping();
        updatedMapping.setId(repairId);
        updatedMapping.setPersonnelId(personnelId);
        return deviceRepairPersonnelMappingMapper.updateById(updatedMapping);
    }

    @Override
    public List<Integer> getListByPersonnelId(Integer personnelId) {
        return lambdaQuery().eq(DeviceRepairPersonnelMapping::getPersonnelId, personnelId)
                .list()
                .stream()
                .map(DeviceRepairPersonnelMapping::getRepairId)
                .collect(Collectors.toList());
    }
}