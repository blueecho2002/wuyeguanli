package com.example.backend.service;

import com.example.backend.common.PageBean;
import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.PrePayment;

import java.sql.Timestamp;

public interface PrePaymentService {
    PageBean getPrePayment(int page, int pageSize, String owner, Integer ownerId, Timestamp from_time, Timestamp to_time);
    boolean editPrePayment(PrePayment prePayment);
    void deletePrePayment(Integer id);
    boolean addPrePayment(PrePayment prePayment);
}
