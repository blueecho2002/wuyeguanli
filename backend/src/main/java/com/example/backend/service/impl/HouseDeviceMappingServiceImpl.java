package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.HouseDeviceMappingMapper;
import com.example.backend.pojo.HouseDeviceMapping;
import com.example.backend.service.HouseDeviceMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseDeviceMappingServiceImpl extends ServiceImpl<HouseDeviceMappingMapper, HouseDeviceMapping> implements HouseDeviceMappingService {

    @Resource
    private HouseDeviceMappingMapper houseDeviceMappingMapper;

    @Override
    public List<Integer> getDeviceId(String houseId) {
        // 使用 MyBatis-Plus 的查询方法，这里假设 HouseDeviceMappingMapper 继承了 BaseMapper
        QueryWrapper<HouseDeviceMapping> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("house_id", houseId);
        List<HouseDeviceMapping> houseDeviceMappings = houseDeviceMappingMapper.selectList(queryWrapper);

        // 提取 deviceId 列表
        List<Integer> deviceIds = houseDeviceMappings.stream()
                .map(HouseDeviceMapping::getDeviceId)
                .collect(Collectors.toList());

        return deviceIds;
    }
}
