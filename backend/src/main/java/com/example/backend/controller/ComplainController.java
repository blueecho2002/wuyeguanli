package com.example.backend.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.backend.common.R;
import com.example.backend.pojo.Complain;
import com.example.backend.service.ComplainService;
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

    @GetMapping("/selectComplains/{currentPage}/{pageSize}")
    public R<?> selectComplains(@PathVariable Integer currentPage, @PathVariable Integer pageSize,
                               @RequestParam String name, @RequestParam String description,
                               @RequestParam String date1, @RequestParam String date2){

        IPage<Complain> page = complainService.getPage(currentPage, pageSize,name, description, date1, date2);
        System.out.println(page.getRecords());
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
        System.out.println(complainIds);
        try {
            // 在这里调用您的服务层或数据访问层执行批量删除
            complainService.batchDeleteComplains(complainIds);
            return R.success("批量删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(REQUEST_FAILURE,"批量删除失败");
        }
    }

    @PutMapping("/updateComplaint/{id}")
    public R<?> updateComplaint(@PathVariable Integer id, @RequestBody Complain complain) {
        complain.setId(id);
        System.out.println(complain);
        return R.success(complainService.update(complain));
    }
}
