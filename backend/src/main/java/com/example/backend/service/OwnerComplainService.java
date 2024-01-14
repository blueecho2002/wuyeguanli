package com.example.backend.service;

import com.example.backend.pojo.Complain;
import com.example.backend.common.PageBean;

public interface OwnerComplainService {

    PageBean list(Integer page, Integer pageSize, String name);

    void addcomplain(Complain complain);

    void updatecomplain(Complain complain);

    void delete(Integer id);
}
