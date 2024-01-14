package com.example.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.backend.common.PageBean;
import com.example.backend.pojo.Complain;

import java.util.List;

public interface ComplainService {
    IPage<Complain> getPage(int currentPage, int pageSize, String name, String description, String date1,String date2);

    boolean deleteById(String id);

    Integer batchDeleteComplains(List<String> complainIds);

    Integer update(Complain complain);

    Complain selectById(Integer id);

    Integer updateStatus(Integer id, String status);


}