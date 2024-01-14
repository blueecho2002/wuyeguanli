package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("complain_personnel_mapping")
public class ComplainPersonnelMapping {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "complain_id")
    private Integer complainId;

    @TableField(value = "personnel_id")
    private Integer personnelId;
}