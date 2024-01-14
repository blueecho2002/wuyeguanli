package com.example.backend.service.impl;

import com.example.backend.common.PageBean;
import com.example.backend.mapper.ChargeItemMapper;
import com.example.backend.pojo.ChargeItem;
import com.example.backend.service.ChargeItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ChargeItemServiceImpl implements ChargeItemService {

    @Autowired
    private ChargeItemMapper chargeItemMapper;

    @Override
    public List<ChargeItem> getAllChargeItem(){
        List<ChargeItem> res = chargeItemMapper.getAllChargeItem();
        return res;
    }

    @Override
    public PageBean getChargeItem(int page, int pageSize, String chargeItemName, String chargeItemId, Timestamp from_time, Timestamp to_time){
        PageHelper.startPage(page,pageSize);
        List<ChargeItem> list = chargeItemMapper.getChargeItem(page, pageSize, chargeItemName, chargeItemId, from_time, to_time);
        Page<ChargeItem> res = (Page<ChargeItem>) list;
        return new PageBean(res.getTotal(), res.getResult());
    }

    @Override
    public void addChargeItem(ChargeItem chargeItem) {
        chargeItemMapper.addChargeItem(chargeItem);
    }

    @Override
    public void deleteChargeItem(Integer id) {
        chargeItemMapper.deleteChargeItem(id);
    }
}
