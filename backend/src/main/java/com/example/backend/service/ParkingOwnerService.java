package com.example.backend.service;


import com.example.backend.pojo.ParkingOwnerMapping;

public interface ParkingOwnerService {
    boolean isExist(Integer parkingId);

    void insert(ParkingOwnerMapping parkingOwnerMapping);

    boolean updateByParkingId(Integer parkingId, Integer newOwnerId);

    boolean isEmpty(Integer parkId);
}
