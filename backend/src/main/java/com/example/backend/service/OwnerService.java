package com.example.backend.service;

import com.example.backend.common.PageBean;
import com.example.backend.pojo.Owner;

import java.util.List;

public interface OwnerService {

    List<Owner> selectAll();

    Owner getById(Integer id);

    Owner selectById(Integer ownerId);

}
