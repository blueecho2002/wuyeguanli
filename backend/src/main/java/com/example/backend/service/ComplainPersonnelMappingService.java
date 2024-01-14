package com.example.backend.service;


import java.util.List;

public interface ComplainPersonnelMappingService {
    void update(Integer complainId, Integer personnelId);

    List<Integer> getIdsByPersonnelId(Integer personnelId);
}