package com.example.backend.service.impl;

import com.example.backend.mapper.MeterMapper;
import com.example.backend.pojo.Meter;
import com.example.backend.common.PageBean;
import com.example.backend.service.MeterService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeterServiceImpl implements MeterService {

    @Autowired
    private MeterMapper meterMapper;

    @Override
    public PageBean list(Integer page,Integer pageSize,String meterType)
    {
        PageHelper.startPage(page,pageSize);

        List<Meter> meterList = meterMapper.selectMeter(meterType);
        Page<Meter> p = (Page<Meter>) meterList;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    @Override
    public void addmeter(Meter meter) {
        meter.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        meter.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        meterMapper.insertmeter(meter);
    }

    @Override
    public void updatemeter(Meter meter) {
        meter.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        meterMapper.updatemeter(meter);
    }

    @Override
    public void updateMyMeter(Meter meter) {
        meter.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        meterMapper.updateMyMeter(meter);
    }

    @Override
    public void delete(Integer meterId) {
        meterMapper.delete(meterId);
    }

    @Override
    public PageBean list1(Integer page, Integer pageSize, String personnel) {
        PageHelper.startPage(page,pageSize);

        List<Meter> meterList = meterMapper.selectMyMeter(personnel);
        Page<Meter> p = (Page<Meter>) meterList;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

}
