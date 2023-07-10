package com.ssm.service;
import com.ssm.entity.User;
import com.ssm.vo.R;
import java.util.Map;

public interface UserService{

    Map<String, Object> tokenData(User user, boolean isMobile);
    User toMobileUser(String name, String password, String identity);

    R checkUser(User user, String password, String identity, boolean mobile);

    User getUserInfoByName(String name);
    User getUserInfoByToken(String token);

    int clearToken(User user);

    int addUser(User user);
    int updateUserInfo(String token, User user);
    int updateUserPassword(String token, String oldPsw, String newPsw);
    boolean verifyInfo(String token);
}
