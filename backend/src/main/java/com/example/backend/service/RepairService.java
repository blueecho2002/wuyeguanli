package com.example.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.backend.pojo.Personnel;
import com.example.backend.pojo.Repair;

import java.util.List;

public interface RepairService {
    IPage<Repair> getPage(Integer currentPage, Integer pageSize, String houseId, String serviceName, String name, String personnelName, String status, String date1, String date2);

    boolean deleteById(String id);

    Integer batchDeleteRepairs(List<String> repairIds);

    Integer insertAndGetId(Repair repair);

    Integer update(Integer id, Personnel personnel);

    Integer updateStatus(Integer id, String status);
}
