package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.Parking;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingMapper extends BaseMapper<Parking> {
}
