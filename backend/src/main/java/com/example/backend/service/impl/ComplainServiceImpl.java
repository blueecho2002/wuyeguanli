package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.ComplainMapper;
import com.example.backend.pojo.Complain;
import com.example.backend.service.ComplainService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplainServiceImpl extends ServiceImpl<ComplainMapper, Complain> implements ComplainService {

    @Resource
    ComplainMapper complainMapper;

    @Override
    public IPage<Complain> getPage(int currentPage, int pageSize, String name, String description, String date1, String date2) {
        LambdaQueryWrapper<Complain> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(name), Complain::getName, name);
        lqw.like(Strings.isNotEmpty(description), Complain::getDescription, description);

        // 添加日期范围的条件
        if (Strings.isNotEmpty(date1) && Strings.isNotEmpty(date2) && !date1.equals("undefined") && !date2.equals("undefined")) {
            LocalDateTime dateTime1 = LocalDateTime.parse(date1+"T00:00:00"); // 请根据日期字符串的格式进行适当的修改
            LocalDateTime dateTime2 = LocalDateTime.parse(date2+"T23:59:59");
            lqw.between(Complain::getCreateTime, dateTime1, dateTime2);
        }

        IPage<Complain> page = new Page<>(currentPage, pageSize);
        IPage<Complain> iPage = complainMapper.selectPage(page, lqw);
        return iPage;
    }

    @Override
    public boolean deleteById(String id) {
        return complainMapper.deleteById(id) == 1;
    }

    @Override
    public Integer batchDeleteComplains(List<String> complainIds) {
        return complainMapper.deleteBatchIds(complainIds);
    }

    @Override
    public Integer update(Complain complain) {
        complain.setUpdateTime(LocalDateTime.now());
        return complainMapper.updateById(complain);
    }


}
