package com.example.backend.service.impl;

import com.example.backend.common.PageBean;
import com.example.backend.controller.PrePaymentController;
import com.example.backend.mapper.PrePaymentMapper;
import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.PrePayment;
import com.example.backend.service.PrePaymentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PrePaymentServiceImpl implements PrePaymentService {

    @Autowired
    private PrePaymentMapper prePaymentMapper;
    @Override
    public PageBean getPrePayment(int page, int pageSize, String owner, Integer ownerId, Timestamp from_time, Timestamp to_time) {
        PageHelper.startPage(page,pageSize);
        List<PrePayment> list = prePaymentMapper.getPrePayment(page,pageSize,owner,ownerId, from_time,to_time);
        System.out.println(list);
        Page<PrePayment> res = (Page<PrePayment>) list;
        return new PageBean(res.getTotal(), res.getResult());
    }

    @Override
    public boolean editPrePayment(PrePayment prePayment) {
        return false;
    }

    @Override
    public void deletePrePayment(Integer id) {
        prePaymentMapper.deletePrePayment(id);
    }

    @Override
    public boolean addPrePayment(PrePayment prePayment) {
        return false;
    }
}
