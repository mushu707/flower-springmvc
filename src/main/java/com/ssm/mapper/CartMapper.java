package com.ssm.mapper;
import com.ssm.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CartMapper {

    List<Cart> getCartList(String uname);

    Cart getCartInfoByName(@Param("name")String name, @Param("uname")String uname);

    int addToCart(Cart data);

    int deleteToCart(@Param("name") String name, @Param("uname")String uname);

    int updateCart(@Param("name")String name,
                   @Param("buy_count")int buy_count,
                   @Param("uname")String uname,
                   @Param("update_time") Timestamp update_time,
                   @Param("isChecked") int isChecked);
}