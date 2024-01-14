package com.example.backend.controller;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.backend.common.R;
import com.example.backend.pojo.Complain;
import com.example.backend.pojo.Personnel;
import com.example.backend.service.ComplainPersonnelMappingService;
import com.example.backend.service.ComplainService;
import com.example.backend.service.PersonnelService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.example.backend.common.Code.REQUEST_FAILURE;


@Api(tags = "投诉接口")
@RestController
@Slf4j
@RequestMapping("/complain")
public class ComplainController {

    @Resource
    ComplainService complainService;

    @Resource
    PersonnelService personnelService;

    @Resource
    ComplainPersonnelMappingService complainPersonnelMappingService;

    @GetMapping("/selectComplains/{currentPage}/{pageSize}")
    public R<?> selectComplains(@PathVariable Integer currentPage, @PathVariable Integer pageSize,
                                @RequestParam String name, @RequestParam String description,
                                @RequestParam(required = false) String date1, @RequestParam(required = false) String date2){

        IPage<Complain> page = complainService.getPage(currentPage, pageSize,name, description, date1, date2);
        //如果当前页码值大于了总页码值，就重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = complainService.getPage((int)page.getPages(), pageSize,name, description, date1, date2);
        }
        return R.success(page);
    }

    @DeleteMapping("/deleteComplainById/{id}")
    public R<?> deleteComplainById(@PathVariable("id") String id) {
        return R.success(complainService.deleteById(id));
    }

    @PostMapping("/batchDeleteComplaints")
    public R<?> batchDeleteComplaints(@RequestBody List<String> complainIds) {
        try {
            // 在这里调用您的服务层或数据访问层执行批量删除
            complainService.batchDeleteComplains(complainIds);
            return R.success("批量删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(REQUEST_FAILURE,"批量删除失败");
        }
    }

    @PostMapping("/updateComplaint/{id}")
    public R<?> updateComplaint(@PathVariable Integer id, @RequestBody Complain complain) {
        complain.setId(id);
        return R.success(complainService.update(complain));
    }


    @PostMapping("/updatePersonnel/")
    public R<?> updatePersonnel(@RequestBody JSONObject requestData) {
        Integer id = requestData.getInt("id");
        Integer personnelId = requestData.getInt("personnelId");
        Personnel personnel = personnelService.selectById(personnelId);
        Complain complain = complainService.selectById(id);
        complain.setPersonnelName(personnel.getName());
        complain.setPersonnelPhoneNumber(personnel.getTelephone());
        complainPersonnelMappingService.update(id, personnelId);
        return R.success(complainService.update(complain));
    }
}