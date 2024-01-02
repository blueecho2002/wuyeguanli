package com.example.backend.service;


import com.example.backend.pojo.DeviceRepairPersonnelMapping;

public interface DeviceRepairPersonnelMappingService {
    Integer insert(DeviceRepairPersonnelMapping deviceRepairPersonnelMapping);
    Integer update(Integer repairId, Integer personnelId);
}
