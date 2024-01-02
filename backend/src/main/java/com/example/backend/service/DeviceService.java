package com.example.backend.service;



import com.example.backend.pojo.Device;

import java.util.List;

public interface DeviceService {
    List<Device> selectByIds(List<Integer> deviceIds);

    String selectById(Integer deviceId);
}
