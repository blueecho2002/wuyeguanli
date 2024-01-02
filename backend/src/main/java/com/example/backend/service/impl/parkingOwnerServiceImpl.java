package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.ParkingOwnerMappingMapper;
import com.example.backend.pojo.ParkingOwnerMapping;
import com.example.backend.service.ParkingOwnerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class parkingOwnerServiceImpl extends ServiceImpl<ParkingOwnerMappingMapper, ParkingOwnerMapping> implements ParkingOwnerService {

    @Resource
    ParkingOwnerMappingMapper parkingOwnerMappingMapper;

    @Override
    public boolean isExist(Integer parkingId) {
        QueryWrapper<ParkingOwnerMapping> wrapper = new QueryWrapper<>();
        wrapper
                .eq("parking_id", parkingId);

        return parkingOwnerMappingMapper.selectCount(wrapper) > 0;
    }

    @Override
    public void insert(ParkingOwnerMapping parkingOwnerMapping) {
        parkingOwnerMappingMapper.insert(parkingOwnerMapping);
    }

    @Override
    public boolean updateByParkingId(Integer parkingId, Integer newOwnerId) {
        // 构造更新条件
        UpdateWrapper<ParkingOwnerMapping> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("parking_id", parkingId);

        // 构造更新的字段
        ParkingOwnerMapping updateEntity = new ParkingOwnerMapping();
        updateEntity.setOwnerId(newOwnerId);

        // 执行更新操作
        int rows = parkingOwnerMappingMapper.update(updateEntity, updateWrapper);

        return rows > 0;
    }

    @Override
    public boolean isEmpty(Integer parkId) {
        ParkingOwnerMapping one = lambdaQuery().eq(ParkingOwnerMapping::getParkingId, parkId).one();

        return one == null;
    }

}
