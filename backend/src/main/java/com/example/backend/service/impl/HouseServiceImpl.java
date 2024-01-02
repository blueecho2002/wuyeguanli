package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.HouseMapper;
import com.example.backend.pojo.House;
import com.example.backend.service.HouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Resource
    HouseMapper houseMapper;

    @Override
    public List<Integer> selectAllIds() {
        // 使用 lambdaQuery() 方法创建 LambdaQueryWrapper
        LambdaQueryWrapper<House> queryWrapper = Wrappers.lambdaQuery();

        // 仅选择 'id' 列
        queryWrapper.select(House::getId);

        // 执行查询并返回结果
        return houseMapper.selectList(queryWrapper)
                .stream()
                .map(House::getId)
                .collect(Collectors.toList());
    }
}
