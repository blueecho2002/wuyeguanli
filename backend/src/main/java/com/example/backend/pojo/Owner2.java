package com.example.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner2 {
    private Integer id;
    private String name;
    private String idCard;
    private String telephone;
    private String profession;
    private String sex;
    private String type;
    private String remark;
    private Timestamp birthday;
    private String room;
    private Timestamp createTime;
    private Timestamp updateTime;
}
