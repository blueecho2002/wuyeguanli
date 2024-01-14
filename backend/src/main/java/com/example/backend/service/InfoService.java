package com.example.backend.service;

import com.example.backend.pojo.Info;
import com.example.backend.common.PageBean;

import java.util.List;

public interface InfoService {
    PageBean list(Integer page, Integer pageSize);

    void updateInfo(Info info);
}
