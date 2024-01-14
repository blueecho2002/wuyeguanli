package com.example.backend.mapper;

import com.example.backend.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    User selectAdmin(@Param("username")String username , @Param("password")String password);

    @Select("select id from owner where id_card = #{idCard}")
    Integer getIdByIdCard(String idCard);

    @Select("select count(*) from user_owner_mapping where owner_id = #{ownerId}")
    Integer isAccountExist(Integer ownerId);

    @Insert("insert into user_owner_mapping(username, owner_id) values (#{username},#{ownerId})")
    void register(@Param("ownerId")Integer ownerId, @Param("username")String username);

    @Insert("insert into user(username, password, type) values (#{username},#{password},1)")
    void addUser(@Param("username")String username, @Param("password")String password);

    @Insert("insert into pre_payment(owner_id, balance, telephone) values(#{ownerId},0,#{telephone})")
    void addPrePayment(@Param("ownerId")Integer ownerId,@Param("telephone")String telephone);

    @Select("select telephone from owner where id = #{ownerId}")
    String getTelephoneByOwnerId(@Param("ownerId")Integer ownerId);

    @Select("select count(*) from user where username = #{username}")
    Integer isUsernameUsed(String username);

    @Select("select name from user_owner_mapping join owner o on o.id = user_owner_mapping.owner_id where username = #{username}")
    String getNameByUsername(String username);
}
