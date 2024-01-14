package com.example.backend.service.impl;

import com.example.backend.mapper.InfoMapper;
import com.example.backend.pojo.Info;
import com.example.backend.pojo.Owner;
import com.example.backend.common.PageBean;
import com.example.backend.service.InfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoMapper infoMapper;

    @Override
    public PageBean list(Integer page, Integer pageSize)
    {
        PageHelper.startPage(page,pageSize);

        List<Info> ownerList = infoMapper.selectInfo();
        Page<Info> p = (Page<Info>) ownerList;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    @Override
    public void updateInfo(Info info) {
        infoMapper.updateInfo(info);
    }
}
