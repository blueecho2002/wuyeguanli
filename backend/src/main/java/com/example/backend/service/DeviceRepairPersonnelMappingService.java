package com.example.backend.service;


import com.example.backend.pojo.DeviceRepairPersonnelMapping;

import java.util.List;

public interface DeviceRepairPersonnelMappingService {
    Integer insert(DeviceRepairPersonnelMapping deviceRepairPersonnelMapping);
    Integer update(Integer repairId, Integer personnelId);

    List<Integer> getListByPersonnelId(Integer personnelId);
}