package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("owner")
public class Owner {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "id_card")
    private String idCard;

    @TableField(value = "telephone")
    private String telephone;

    @TableField(value = "sex")
    private String sex;

    @TableField(value = "type")
    private String type;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "birthday")
    private Date birthday;

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
