package com.example.backend.mapper;

import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.ChargeItem;
import org.apache.ibatis.annotations.*;
import org.springframework.data.redis.core.index.Indexed;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ChargeMapper {
    List<ChargeDetail> getChargeDetail(@Param("page")int page, @Param("pageSize")int pageSize, @Param("ownerName")String ownerName,
                                       @Param("chargeItem")String chargeItem, @Param("from_time")Timestamp from_time,
                                       @Param("to_time")Timestamp to_time, @Param("ownerId")Integer ownerId);

    @Select("select * from charge_item")
    List<ChargeItem> getAllChargeItem();

    List<ChargeItem> getChargeItem(@Param("page")int page, @Param("pageSize")int pageSize,@Param("chargeItemName")String chargeItemName,
                                   @Param("chargeItemId")String chargeItemId,  @Param("from_time")Timestamp from_time,
                                   @Param("to_time")Timestamp to_time);

    @Update("update charge_detail set charge_item_id = #{chargeItemId}, owner_id = #{ownerId}," +
            "pay_money = #{payMoney}, actual_money = #{actualMoney}, pay_money = #{payMoney} where id = #{id}")
    void editDetail(ChargeDetail chargeDetail);

    @Delete("delete from charge_detail where id = #{id}")
    void deleteChargeDetail(Integer id);

    @Select("select id from owner where id = #{id}")
    Integer selectOwnerById(Integer id);

    @Insert("insert into charge_detail(charge_item_id, owner_id, pay_money, actual_money, pay_time) " +
            "values(#{chargeItemId},#{ownerId},#{payMoney},#{actualMoney},#{payTime})")
    void addChargeDetail(ChargeDetail chargeDetail);

    @Insert("insert into charge_item(name, money, period, unit) values(#{name},#{money},#{period},#{unit})")
    void addChargeItem(ChargeItem chargeItem);
}
