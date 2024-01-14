package com.example.backend.mapper;

import com.example.backend.pojo.Info;
import com.example.backend.pojo.Owner;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InfoMapper {
    @Select("select * from info")
    List<Info> selectInfo();

    @Update("update info set name=#{name},location=#{location},scale=#{scale}")
    void updateInfo(Info info);
}
