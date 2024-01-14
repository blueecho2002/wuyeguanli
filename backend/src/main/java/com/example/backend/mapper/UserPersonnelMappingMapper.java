package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.UserOwnerMapping;
import com.example.backend.pojo.UserPersonnelMapping;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPersonnelMappingMapper extends BaseMapper<UserPersonnelMapping> {
}