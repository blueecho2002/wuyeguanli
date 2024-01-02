package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.RepairMapper;
import com.example.backend.pojo.Personnel;
import com.example.backend.pojo.Repair;
import com.example.backend.service.RepairService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

    @Resource
    RepairMapper repairMapper;

    @Override
    public IPage<Repair> getPage(Integer currentPage, Integer pageSize, String houseId, String serviceName, String name, String personnelName, String status, String date1, String date2) {
        LambdaQueryWrapper<Repair> lqw = new LambdaQueryWrapper<>();
        lqw.like(StringUtils.isNotEmpty(name), Repair::getName, name);
        lqw.like(StringUtils.isNotEmpty(serviceName), Repair::getDeviceName, serviceName);
        lqw.like(StringUtils.isNotEmpty(personnelName), Repair::getPersonnelName, personnelName);
        lqw.eq(StringUtils.isNotEmpty(status), Repair::getStatus, status);
        lqw.eq(StringUtils.isNotEmpty(houseId), Repair::getHouseId, houseId);
        // 添加日期范围的条件
        if (StringUtils.isNotEmpty(date1) && StringUtils.isNotEmpty(date2) && !date1.equals("undefined") && !date2.equals("undefined")) {
            LocalDateTime dateTime1 = LocalDateTime.parse(date1 + "T00:00:00"); // 请根据日期字符串的格式进行适当的修改
            LocalDateTime dateTime2 = LocalDateTime.parse(date2 + "T23:59:59");
            lqw.between(Repair::getCreateTime, dateTime1, dateTime2);
        }

        IPage<Repair> page = new Page<>(currentPage, pageSize);
        IPage<Repair> iPage = baseMapper.selectPage(page, lqw);  // 使用 baseMapper 代替 complainMapper
        return iPage;
    }

    @Override
    public boolean deleteById(String id) {
        return repairMapper.deleteById(id) == 1;
    }

    @Override
    public Integer batchDeleteRepairs(List<String> repairIds) {
        return repairMapper.deleteBatchIds(repairIds);
    }

    @Override
    public Integer insertAndGetId(Repair repair) {
        int result = repairMapper.insert(repair);

        if (result > 0) {
            return repair.getId();
        } else {
            return null;
        }
    }

    @Override
    public Integer update(Integer id, Personnel personnel) {
        Repair repair = new Repair();
        repair.setId(id);
        repair.setPersonnelName(personnel.getName());
        repair.setPersonnelPhoneNumber(personnel.getTelephone());
        return repairMapper.updateById(repair);
    }

    @Override
    public Integer updateStatus(Integer id, String status) {
        Repair repair = new Repair();
        repair.setId(id);
        repair.setStatus(status);
        return repairMapper.updateById(repair);
    }
}
