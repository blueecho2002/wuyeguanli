package com.example.backend.service;

import com.example.backend.pojo.Meter;
import com.example.backend.common.PageBean;

import java.util.List;

public interface MeterService {

    PageBean list(Integer page, Integer pageSize,String meterType);

    void addmeter(Meter meter);

    void updatemeter(Meter meter);

    void delete(Integer meterId);

    PageBean list1(Integer page, Integer pageSize, String personnel);

    void updateMyMeter(Meter meter);
}
