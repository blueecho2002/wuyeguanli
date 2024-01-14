package com.example.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OwnerRepairMapper {
    @Insert("insert into repair(description, house_id, device_name, name, status) values(#{description},#{houseId},#{deviceName},#{ownerName},0)")
    void addReapir(@Param("houseId")Integer houseId, @Param("deviceName")String deviceName,
                   @Param("description") String description,@Param("ownerName") String ownerName);
}
