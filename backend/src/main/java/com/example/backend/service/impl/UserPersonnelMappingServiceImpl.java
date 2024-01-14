package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.RepairMapper;
import com.example.backend.mapper.UserPersonnelMappingMapper;
import com.example.backend.pojo.Repair;
import com.example.backend.pojo.UserPersonnelMapping;
import com.example.backend.service.RepairService;
import com.example.backend.service.UserPersonnelMappingService;
import org.springframework.stereotype.Service;

@Service
public class UserPersonnelMappingServiceImpl extends ServiceImpl<UserPersonnelMappingMapper, UserPersonnelMapping> implements UserPersonnelMappingService {

    @Override
    public Integer getIdByUsername(String username) {
        Integer personnelId = lambdaQuery().eq(UserPersonnelMapping::getUsername, username).one().getPersonnelId();
        return personnelId;
    }
}