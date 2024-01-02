package com.example.backend.service;

import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.ChargeItem;
import com.example.backend.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface ChargeService {
    PageBean getChargeDetail(int page, int pageSize, String owner, String chargeItem, Timestamp from_time, Timestamp to_time, Integer ownerId);
    List<ChargeItem> getAllChargeItem();
    PageBean getChargeItem(int page, int pageSize, String chargeItemName, String chargeItemId, Timestamp from_time, Timestamp to_time);
    boolean editDetail(ChargeDetail chargeDetail);
    void deleteChargeDetail(Integer id);
    boolean addChargeDetail(ChargeDetail chargeDetail);
    void addChargeItem(ChargeItem chargeItem);
}
