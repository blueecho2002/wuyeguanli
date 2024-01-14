package com.example.backend.mapper;

import com.example.backend.pojo.Owner2;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface Owner2Mapper {

    List<Owner2> selectOwner(@Param("name")String name);

    List<Owner2> groupOwner();

    void insertowner(Owner2 owner2);

    @Insert("insert into owner_house_mapping(owner_id, house_id) values (#{id},(select id from house where name = #{room}))")
    void insertOwnerHouseMap(Owner2 owner2);

    @Update("update owner set name=#{name},id_card=#{idCard}," +
            "telephone=#{telephone},profession=#{profession}," +
            "sex=#{sex},type=#{type},remark=#{remark}," +
            "birthday=#{birthday},update_time=#{updateTime} where id=#{id}")
    void updateowner(Owner2 owner2);

    @Update("update owner_house_mapping set house_id=(select id from house where name = #{room}) where owner_id=#{id}")
    void updateownerhousemap(Owner2 owner2);

    @Delete("delete from owner where id=#{id}")
    void delete(Integer id);

    @Delete("delete from owner_house_mapping where owner_id=#{id}")
    void deleteohm(Integer id);
}
