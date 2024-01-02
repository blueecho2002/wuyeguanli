package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("house_device_mapping")
public class HouseDeviceMapping {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "house_id")
    private Integer houseId;

    @TableField(value = "device_id")
    private Integer deviceId;
}
