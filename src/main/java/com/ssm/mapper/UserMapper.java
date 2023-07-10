package com.ssm.mapper;
import com.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

public interface UserMapper {

    User getUserInfoByName(String name);
    User getUserInfoByToken(String token);
    Map<String, Integer> getCount(String name);

    int addUser(User user);
    int updateUserInfo(@Param("token") String token, @Param("user") User user);
    int updateUserPassword(@Param("token") String token, @Param("password") String password);
    int updateTokenByName(@Param("token") String token, @Param("name") String name);

}
