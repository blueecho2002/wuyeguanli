package com.example.backend.mapper;


import com.example.backend.pojo.Complain;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OwnerComplainMapper {

    List<Complain> selectComplain(@Param("name")String name);

    void insertcomplain(Complain complain);

    @Update("update complain set description=#{description},update_time=#{updateTime} where id=#{id}")
    void updatecomplain(Complain complain);

    @Delete("delete from complain where id=#{id}")
    void delete(Integer id);
}
