package com.example.backend.mapper;

import com.example.backend.pojo.Pay;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AliPayMapper {

    @Select("select * from pay where id = #{id}")
    List<Pay> selectPayById(String id);

    @Select("select owner_id from user_owner_mapping where username = #{username}")
    Integer getIdByUsername(String username);

    @Update("update pre_payment set balance = balance + #{money} where owner_id = #{userId}")
    void payUpdate(@Param("userId")Integer userId, @Param("money")double money);

    @Select("select balance from pre_payment where owner_id = #{userId}")
    Double getBalance(@Param("userId")Integer userId);

    @Insert("insert into pay(id,money) values(#{id},#{money})")
    void pay(@Param("id")String id, @Param("money")double money);
}
