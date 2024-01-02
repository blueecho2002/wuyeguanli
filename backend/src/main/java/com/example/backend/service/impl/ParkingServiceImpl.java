package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.ParkingMapper;
import com.example.backend.pojo.Parking;
import com.example.backend.service.ParkingService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingServiceImpl extends ServiceImpl<ParkingMapper, Parking> implements ParkingService {

    @Resource
    ParkingMapper parkingMapper;
    @Override
    public IPage<Parking> getPage(int currentPage, int pageSize, String name, String gender, String date1, String date2) {
        LambdaQueryWrapper<Parking> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(name), Parking::getName, name);
        lqw.like(Strings.isNotEmpty(gender), Parking::getGender, gender);

        // 添加日期范围的条件
        if (Strings.isNotEmpty(date1) && Strings.isNotEmpty(date2) && !date1.equals("undefined") && !date2.equals("undefined")) {
            LocalDateTime dateTime1 = LocalDateTime.parse(date1 + "T00:00:00");
            LocalDateTime dateTime2 = LocalDateTime.parse(date2 + "T23:59:59");
            lqw.between(Parking::getCreateTime, dateTime1, dateTime2);
        }

        IPage<Parking> page = new Page<>(currentPage, pageSize);
        IPage<Parking> iPage = parkingMapper.selectPage(page, lqw);
        return iPage;
    }

    @Override
    public boolean deleteById(String id) {
        return parkingMapper.deleteById(id) == 1;
    }

    @Override
    public Integer batchDeleteComplains(List<String> parkIds) {
        return parkingMapper.deleteBatchIds(parkIds);
    }

    @Override
    public Integer insert(Parking parking) {
        return parkingMapper.insert(parking);
    }

    @Override
    public List<Parking> selectAll() {
        return lambdaQuery().list();
    }

    @Override
    public boolean updateById(Parking parking) {
        int rows = parkingMapper.updateById(parking);
        return rows > 0;
    }

}
