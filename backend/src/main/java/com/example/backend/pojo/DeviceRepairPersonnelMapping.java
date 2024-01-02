package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("device_repair_personnel_mapping")
public class DeviceRepairPersonnelMapping {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "device_id")
    private Integer deviceId;

    @TableField(value = "repair_id")
    private Integer repairId;

    @TableField(value = "personnel_id")
    private Integer personnelId;

}
