package com.example.backend.service.impl;


import com.example.backend.mapper.ComplainMapper;
import com.example.backend.mapper.OwnerComplainMapper;
import com.example.backend.pojo.Complain;
import com.example.backend.service.ComplainService;
import com.example.backend.common.PageBean;
import com.example.backend.service.OwnerComplainService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OwnerComplainServiceImpl implements OwnerComplainService {

    @Autowired
    private OwnerComplainMapper ownerComplainMapper;

    @Override
    public PageBean list(Integer page, Integer pageSize, String name)
    {
        PageHelper.startPage(page,pageSize);

        List<Complain> complainList = ownerComplainMapper.selectComplain(name);
        Page<Complain> p = (Page<Complain>) complainList;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    @Override
    public void addcomplain(Complain complain) {
        complain.setCreateTime(LocalDateTime.now());
        complain.setUpdateTime(LocalDateTime.now());
        ownerComplainMapper.insertcomplain(complain);
    }

    @Override
    public void updatecomplain(Complain complain) {
        complain.setUpdateTime(LocalDateTime.now());
        ownerComplainMapper.updatecomplain(complain);
    }

    @Override
    public void delete(Integer id) {
        ownerComplainMapper.delete(id);
    }
}
