package com.example.backend.service;

import com.example.backend.pojo.Owner;

public interface OwnerRepairService {
    void addRepair(Integer houseId, String deviceName, String description, String ownerName);
}
