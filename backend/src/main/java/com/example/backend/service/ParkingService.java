package com.example.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.backend.pojo.Parking;

import java.util.List;

public interface ParkingService {
    IPage<Parking> getPage(int currentPage, int pageSize, String name, String gender, String date1, String date2);

    boolean deleteById(String id);

    Integer batchDeleteComplains(List<String> parkIds);

    Integer insert(Parking parking);

    List<Parking> selectAll();

    boolean updateById(Parking parking);

}
