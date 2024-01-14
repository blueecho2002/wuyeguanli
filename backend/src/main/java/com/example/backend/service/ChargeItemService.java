package com.example.backend.service;

import com.example.backend.common.PageBean;
import com.example.backend.pojo.ChargeItem;

import java.sql.Timestamp;
import java.util.List;

public interface ChargeItemService {
    List<ChargeItem> getAllChargeItem();
    PageBean getChargeItem(int page, int pageSize, String chargeItemName, String chargeItemId, Timestamp from_time, Timestamp to_time);
    void addChargeItem(ChargeItem chargeItem);
    void deleteChargeItem(Integer id);
}
