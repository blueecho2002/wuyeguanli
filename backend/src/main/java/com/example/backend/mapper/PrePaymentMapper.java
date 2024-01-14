package com.example.backend.mapper;

import com.example.backend.pojo.ChargeDetail;
import com.example.backend.pojo.PrePayment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface PrePaymentMapper {

    List<PrePayment> getPrePayment(@Param("page")int page, @Param("pageSize")int pageSize, @Param("ownerName")String ownerName,
                                   @Param("ownerId")Integer ownerId, @Param("from_time") Timestamp from_time, @Param("to_time")Timestamp to_time);

    @Delete("delete from pre_payment where owner_id = #{id}")
    void deletePrePayment(Integer id);

    @Update("update pre_payment set balance = #{balance} where owner_id = #{ownerId}")
    void editBalance(@Param("ownerId")Integer ownerId, @Param("balance")Double balance);
}
