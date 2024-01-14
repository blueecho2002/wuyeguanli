package com.example.backend.mapper;

import com.example.backend.pojo.ChargeItem;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ChargeItemMapper {
    @Select("select * from charge_item")
    List<ChargeItem> getAllChargeItem();

    List<ChargeItem> getChargeItem(@Param("page")int page, @Param("pageSize")int pageSize, @Param("chargeItemName")String chargeItemName,
                                   @Param("chargeItemId")String chargeItemId, @Param("from_time") Timestamp from_time,
                                   @Param("to_time")Timestamp to_time);

    @Insert("insert into charge_item(name, money, period, unit) values(#{name},#{money},#{period},#{unit})")
    void addChargeItem(ChargeItem chargeItem);


    @Delete("delete from charge_item where id = #{id}")
    void deleteChargeItem(Integer id);
}
