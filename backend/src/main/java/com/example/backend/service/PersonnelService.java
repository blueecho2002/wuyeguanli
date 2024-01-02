package com.example.backend.service;

import com.example.backend.pojo.Personnel;

import java.util.List;

public interface PersonnelService {
    List<Personnel> select();

    Personnel selectById(Integer personnelId);
}
