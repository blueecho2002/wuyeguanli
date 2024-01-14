package com.example.backend.mapper;

import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.ChargeItem;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ChargeDetailMapper {
    List<ChargeDetail> getChargeDetail(@Param("page")int page, @Param("pageSize")int pageSize, @Param("ownerName")String ownerName,
                                       @Param("chargeItem")String chargeItem, @Param("from_time")Timestamp from_time,
                                       @Param("to_time")Timestamp to_time, @Param("ownerId")Integer ownerId);

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


}
