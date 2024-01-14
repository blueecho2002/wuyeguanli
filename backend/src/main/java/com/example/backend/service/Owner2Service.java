package com.example.backend.service;

import com.example.backend.pojo.Owner2;
import com.example.backend.common.PageBean;

public interface Owner2Service {
    PageBean list(Integer page, Integer pageSize,String name);

    PageBean group(Integer page, Integer pageSize);

    void addowner(Owner2 owner2);

    void ownerhousemap(Owner2 owner2);

    void updateowner(Owner2 owner2);

    void updateownerhousemap(Owner2 owner2);

    void delete(Integer id);

    void deleteohm(Integer id);
}
