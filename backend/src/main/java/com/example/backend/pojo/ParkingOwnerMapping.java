package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("parking_owner_mapping")
public class ParkingOwnerMapping {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "parking_id")
    private Integer parkingId;

    @TableField(value = "owner_id")
    private Integer ownerId;
}
