package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.ComplainPersonnelMappingMapper;
import com.example.backend.pojo.ComplainPersonnelMapping;
import com.example.backend.service.ComplainPersonnelMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplainPersonnelMappingServiceImpl extends ServiceImpl<ComplainPersonnelMappingMapper, ComplainPersonnelMapping> implements ComplainPersonnelMappingService {
    @Resource
    private ComplainPersonnelMappingMapper complainPersonnelMappingMapper;

    @Override
    public void update(Integer complainId, Integer personnelId) {

        ComplainPersonnelMapping existingMapping = lambdaQuery().eq(ComplainPersonnelMapping::getComplainId, complainId).one();

        if (existingMapping != null) {
            // 如果存在记录，执行更新操作
            existingMapping.setPersonnelId(personnelId);
            complainPersonnelMappingMapper.updateById(existingMapping);
        } else {
            // 如果不存在记录，执行插入操作
            ComplainPersonnelMapping newMapping = new ComplainPersonnelMapping();
            newMapping.setComplainId(complainId);
            newMapping.setPersonnelId(personnelId);
            complainPersonnelMappingMapper.insert(newMapping);
        }
    }

    @Override
    public List<Integer> getIdsByPersonnelId(Integer personnelId) {
        return lambdaQuery().eq(ComplainPersonnelMapping::getPersonnelId, personnelId)
                .list()
                .stream()
                .map(ComplainPersonnelMapping::getComplainId)
                .collect(Collectors.toList());
    }
}