package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.backend.mapper.DeviceMapper;
import com.example.backend.pojo.Device;
import com.example.backend.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public List<Device> selectByIds(List<Integer> deviceIds) {
        if (deviceIds == null || deviceIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 使用 MyBatis 的查询方法，这里假设 DeviceMapper 继承了 BaseMapper
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", deviceIds);
        return deviceMapper.selectList(queryWrapper);
    }

    @Override
    public String  selectById(Integer deviceId) {
        return lambdaQuery().eq(Device::getId, deviceId)
                .select(Device::getName)
                .one()
                .getName();
    }
}
