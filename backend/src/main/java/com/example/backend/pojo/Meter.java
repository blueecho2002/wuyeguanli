package com.example.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meter {
    private Integer meterId;
    private String meterType;
    private Double meterValue;
    private String personnel;
    private Timestamp createTime;
    private Timestamp updateTime;
}
