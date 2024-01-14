package com.example.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.backend.pojo.Complain;
import com.example.backend.pojo.Personnel;
import com.example.backend.pojo.Repair;

import java.util.List;

public interface PersonnelService {
    List<Personnel> select();

    Personnel selectById(Integer personnelId);

    IPage<Repair> getPage(Integer currentPage, Integer pageSize, String houseId, String serviceName, String name, List<Integer> deviceIds, String status, String date1, String date2);

    IPage<Complain> getPage2(Integer currentPage, Integer pageSize, List<Integer> personnelIds, String name, String description, String date1, String date2);
}