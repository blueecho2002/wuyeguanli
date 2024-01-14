package com.example.backend.service;

import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.ChargeItem;
import com.example.backend.common.PageBean;

import java.sql.Timestamp;
import java.util.List;

public interface ChargeDetailService {
    PageBean getChargeDetail(int page, int pageSize, String owner, String chargeItem, Timestamp from_time, Timestamp to_time, Integer ownerId);
    boolean editDetail(ChargeDetail chargeDetail);
    void deleteChargeDetail(Integer id);
    boolean addChargeDetail(ChargeDetail chargeDetail);
}
