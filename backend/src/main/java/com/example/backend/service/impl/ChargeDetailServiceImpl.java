package com.example.backend.service.impl;

import com.example.backend.mapper.ChargeDetailMapper;
import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.ChargeItem;
import com.example.backend.common.PageBean;
import com.example.backend.service.ChargeDetailService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ChargeDetailServiceImpl implements ChargeDetailService {

    @Autowired
    private ChargeDetailMapper chargeMapper;
    @Override
    public PageBean getChargeDetail(int page, int pageSize, String owner, String chargeItem, Timestamp from_time, Timestamp to_time,Integer ownerId) {
        PageHelper.startPage(page,pageSize);
        List<ChargeDetail> list = chargeMapper.getChargeDetail(page,pageSize,owner,chargeItem, from_time,to_time,ownerId);
        //System.out.println(list.get(0).getPayTime());
        Page<ChargeDetail> res = (Page<ChargeDetail>) list;
        return new PageBean(res.getTotal(), res.getResult());
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
}
