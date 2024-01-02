package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("repair")
public class Repair {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "description")
    private String description;

    @TableField(value = "house_id")
    private Integer houseId;

    @TableField(value = "device_name")
    private String deviceName;

    @TableField(value = "name")
    private String name;

    @TableField(value = "phone_number")
    private String phoneNumber;

    @TableField(value = "personnel_name")
    private String personnelName;

    @TableField(value = "personnel_phone_number")
    private String personnelPhoneNumber;

    @TableField(value = "status")
    private String status;

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
