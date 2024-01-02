package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.OwnerMapper;
import com.example.backend.pojo.Owner;
import com.example.backend.service.OwnerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements OwnerService {

    @Resource
    OwnerMapper ownerMapping;

    @Override
    public List<Owner> selectAll() {
        return lambdaQuery().list();
    }

    @Override
    public Owner getById(Integer id) {
        return ownerMapping.selectById(id);
    }

    @Override
    public Owner selectById(Integer ownerId) {
        return lambdaQuery().eq(Owner::getId, ownerId).one();
    }
}
