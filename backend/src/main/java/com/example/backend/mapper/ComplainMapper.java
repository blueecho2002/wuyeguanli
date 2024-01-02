package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.Complain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComplainMapper extends BaseMapper<Complain> {
}
