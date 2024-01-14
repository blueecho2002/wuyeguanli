package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.ComplainMapper;
import com.example.backend.mapper.PersonnelMapper;
import com.example.backend.mapper.RepairMapper;
import com.example.backend.pojo.Complain;
import com.example.backend.pojo.Personnel;
import com.example.backend.pojo.Repair;
import com.example.backend.service.PersonnelService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, Personnel> implements PersonnelService {

    @Resource
    RepairMapper repairMapper;

    @Resource
    private ComplainMapper complainMapper;

    @Override
    public List<Personnel> select() {
        return lambdaQuery().list();
    }

    @Override
    public Personnel selectById(Integer personnelId) {
        return lambdaQuery().eq(Personnel::getId, personnelId).one();
    }

    @Override
    public IPage<Repair> getPage(Integer currentPage, Integer pageSize, String houseId, String serviceName, String name, List<Integer> deviceIds, String status, String date1, String date2) {
        LambdaQueryWrapper<Repair> lqw = new LambdaQueryWrapper<>();
        lqw.like(StringUtils.isNotEmpty(name), Repair::getName, name);
        lqw.like(StringUtils.isNotEmpty(serviceName), Repair::getDeviceName, serviceName);

        lqw.eq(StringUtils.isNotEmpty(status), Repair::getStatus, status);
        lqw.eq(StringUtils.isNotEmpty(houseId), Repair::getHouseId, houseId);
        // 添加日期范围的条件
        if (StringUtils.isNotEmpty(date1) && StringUtils.isNotEmpty(date2) && !date1.equals("undefined") && !date2.equals("undefined")) {
            LocalDateTime dateTime1 = LocalDateTime.parse(date1 + "T00:00:00"); // 请根据日期字符串的格式进行适当的修改
            LocalDateTime dateTime2 = LocalDateTime.parse(date2 + "T23:59:59");
            lqw.between(Repair::getCreateTime, dateTime1, dateTime2);
        }

        // 添加设备ID的条件
        lqw.in(CollectionUtils.isNotEmpty(deviceIds), Repair::getId, deviceIds);

        IPage<Repair> page = new Page<>(currentPage, pageSize);
        IPage<Repair> iPage = repairMapper.selectPage(page, lqw);  // 使用 baseMapper 代替 complainMapper
        return iPage;
    }

    @Override
    public IPage<Complain> getPage2(Integer currentPage, Integer pageSize, List<Integer> complainIds, String name, String description, String date1, String date2) {
        LambdaQueryWrapper<com.example.backend.pojo.Complain> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(name), com.example.backend.pojo.Complain::getName, name);
        lqw.like(Strings.isNotEmpty(description), com.example.backend.pojo.Complain::getDescription, description);

        // 添加日期范围的条件
        if (Strings.isNotEmpty(date1) && Strings.isNotEmpty(date2) && !date1.equals("undefined") && !date2.equals("undefined")) {
            LocalDateTime dateTime1 = LocalDateTime.parse(date1+"T00:00:00"); // 请根据日期字符串的格式进行适当的修改
            LocalDateTime dateTime2 = LocalDateTime.parse(date2+"T23:59:59");
            lqw.between(com.example.backend.pojo.Complain::getCreateTime, dateTime1, dateTime2);
        }

        lqw.in(CollectionUtils.isNotEmpty(complainIds), com.example.backend.pojo.Complain::getId, complainIds);

        IPage<com.example.backend.pojo.Complain> page = new Page<>(currentPage, pageSize);
        IPage<com.example.backend.pojo.Complain> iPage = complainMapper.selectPage(page, lqw);
        return iPage;
    }
}