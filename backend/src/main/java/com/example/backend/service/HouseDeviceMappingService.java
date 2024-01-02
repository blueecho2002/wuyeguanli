package com.example.backend.service;

import java.util.List;

public interface HouseDeviceMappingService {
    List<Integer> getDeviceId(String houseId);
}
