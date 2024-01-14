package com.example.backend.mapper;

import com.example.backend.pojo.Meter;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MeterMapper {

    List<Meter> selectMeter(@Param("meterType")String meterType);

    List<Meter> selectMyMeter(@Param("meterType")String meterType);

    void insertmeter(Meter meter);

    @Update("update meter set meter_type=#{meterType},meter_value=#{meterValue},personnel=#{personnel},update_time=#{updateTime} where meter_id=#{meterId}")
    void updatemeter(Meter meter);

    @Update("update meter set meter_type=#{meterType},meter_value=#{meterValue},update_time=#{updateTime} where meter_id=#{meterId}")
    void updateMyMeter(Meter meter);

    @Delete("delete from meter where meter_id=#{meterId}")
    void delete(Integer meterId);
}
