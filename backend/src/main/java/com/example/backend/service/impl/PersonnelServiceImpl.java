package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.PersonnelMapper;
import com.example.backend.pojo.Personnel;
import com.example.backend.service.PersonnelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, Personnel> implements PersonnelService {
    @Override
    public List<Personnel> select() {
        return lambdaQuery().list();
    }

    @Override
    public Personnel selectById(Integer personnelId) {
        return lambdaQuery().eq(Personnel::getId, personnelId).one();
    }
}
