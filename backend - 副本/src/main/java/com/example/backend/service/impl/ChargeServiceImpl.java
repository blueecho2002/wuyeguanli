package com.example.backend.service.impl;

import com.example.backend.mapper.ChargeMapper;
import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.ChargeItem;
import com.example.backend.pojo.PageBean;
import com.example.backend.service.ChargeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeMapper chargeMapper;
    @Override
    public PageBean getChargeDetail(int page, int pageSize, String owner, String chargeItem, Timestamp from_time, Timestamp to_time,Integer ownerId) {
        PageHelper.startPage(page,pageSize);
        List<ChargeDetail> list = chargeMapper.getChargeDetail(page,pageSize,owner,chargeItem, from_time,to_time,ownerId);
        //System.out.println(list.get(0).getPayTime());
        Page<ChargeDetail> res = (Page<ChargeDetail>) list;
        return new PageBean(res.getTotal(), res.getResult());
    }

    @Override
    public List<ChargeItem> getAllChargeItem(){
        List<ChargeItem> res = chargeMapper.getAllChargeItem();
        return res;
    }

    @Override
    public boolean editDetail(ChargeDetail chargeDetail){
        if(chargeMapper.selectOwnerById(chargeDetail.getOwnerId()) == null)return false;
        chargeMapper.editDetail(chargeDetail);
        return true;
    }

    @Override
    public void deleteChargeDetail(Integer id){
        chargeMapper.deleteChargeDetail(id);
    }

    @Override
    public boolean addChargeDetail(ChargeDetail chargeDetail){
        if(chargeMapper.selectOwnerById(chargeDetail.getOwnerId()) == null)return false;
        chargeMapper.addChargeDetail(chargeDetail);
        return true;
    }

    @Override
    public PageBean getChargeItem(int page, int pageSize, String chargeItemName, String chargeItemId, Timestamp from_time, Timestamp to_time){
        PageHelper.startPage(page,pageSize);
        List<ChargeItem> list = chargeMapper.getChargeItem(page, pageSize, chargeItemName, chargeItemId, from_time, to_time);
        Page<ChargeItem> res = (Page<ChargeItem>) list;
        return new PageBean(res.getTotal(), res.getResult());
    }

    @Override
    public void addChargeItem(ChargeItem chargeItem) {
        chargeMapper.addChargeItem(chargeItem);
    }
}
