package com.example.backend.service.impl;

import com.example.backend.mapper.OwnerRepairMapper;
import com.example.backend.service.OwnerRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerRepairServiceImpl implements OwnerRepairService {

    @Autowired
    private OwnerRepairMapper ownerRepairMapper;

    @Override
    public void addRepair(Integer houseId, String deviceName, String description, String ownerName) {
        ownerRepairMapper.addReapir(houseId,deviceName,description,ownerName);
    }
}
