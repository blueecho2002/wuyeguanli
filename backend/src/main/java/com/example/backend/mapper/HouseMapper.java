package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.House;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseMapper extends BaseMapper<House> {
}
