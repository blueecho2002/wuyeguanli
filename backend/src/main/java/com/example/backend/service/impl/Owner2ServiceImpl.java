package com.example.backend.service.impl;

import com.example.backend.mapper.Owner2Mapper;
import com.example.backend.pojo.Owner2;
import com.example.backend.common.PageBean;
import com.example.backend.service.Owner2Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class Owner2ServiceImpl implements Owner2Service {

    @Autowired
    private Owner2Mapper owner2Mapper;

    @Override
    public PageBean list(Integer page,Integer pageSize,String name)
    {
        PageHelper.startPage(page,pageSize);

        List<Owner2> owner2List = owner2Mapper.selectOwner(name);
        Page<Owner2> p = (Page<Owner2>) owner2List;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    @Override
    public PageBean group(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<Owner2> owner2List = owner2Mapper.groupOwner();
        Page<Owner2> p = (Page<Owner2>) owner2List;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    @Override
    public void addowner(Owner2 owner2) {
        owner2.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        owner2.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        owner2Mapper.insertowner(owner2);
    }

    @Override
    public void ownerhousemap(Owner2 owner2) {
        owner2Mapper.insertOwnerHouseMap(owner2);
    }

    @Override
    public void updateowner(Owner2 owner2) {
        owner2.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        owner2Mapper.updateowner(owner2);
    }

    @Override
    public void updateownerhousemap(Owner2 owner2) {
        owner2Mapper.updateownerhousemap(owner2);
    }

    @Override
    public void delete(Integer id) {
        owner2Mapper.delete(id);
    }

    @Override
    public void deleteohm(Integer id) {
        owner2Mapper.deleteohm(id);
    }
}
