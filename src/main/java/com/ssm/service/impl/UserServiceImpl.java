package com.ssm.service.impl;
import com.ssm.mapper.CollectionMapper;
import com.ssm.util.time.Time;
import com.ssm.util.token.TokenProcessor;
import com.ssm.vo.R;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ssm.entity.User;
import com.ssm.mapper.UserMapper;
import com.ssm.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;
    @Resource
    private CollectionMapper collectionMapper;

    /**
     * 判断用户是否存在token，如果存在则表示登录中，如果不存在则表示离线，需要添加token
     * 并添加身份信息
     * @param user 用户
     * @return
     */
    public Map<String, Object> tokenData(User user, boolean isMobile){

        Map<String, Object> map = new HashMap<>();
        if (user.getToken() != null){
            map.put("token", user.getToken());
        }else {
            String token = TokenProcessor.getInstance().makeToken();
            map.put("token", token);
            this.setToken(user, token);
        }
        if (isMobile) {
            map.put("name", user.getName());
            map.put("imageUrl", user.getImageUrl());
            map.put("count", userMapper.getCount(user.getName()));
            map.put("collection", collectionMapper.getCollectionIdByUname(user.getName()));
        }
        map.put("identity", user.getIdentity());
        map.put("loginTime", Time.getTimeByString());
        return map;
    }

    /**
     * 返回移动端用户信息
     * @param name 用户名
     * @param password 用户密码
     * @param identity 身份
     */
    public User toMobileUser(String name, String password, String identity){
        User m = new User();
        m.setName(name);
        m.setPassword(password);
        m.setIdentity(identity);
        m.setImageUrl(null);
        return m;
    }

    /**
     * 检测用户登录的渠道并返回相应的数据
     * @param user 用户信息
     * @param password 密码
     * @param identity 身份
     * @param mobile 登录渠道 移动端/PC端
     */
    public R checkUser(User user, String password, String identity, boolean mobile){
        Map<String, Object> map;
        if (password.equals(user.getPassword()) && identity.equals(user.getIdentity())){
            System.out.println((mobile ? "移动" : "PC") + "端用户 '" + user.getName() + "' 登录成功! 身份: " + identity + " ,时间: " + Time.getTimeByString());
            map = this.tokenData(user, mobile);
            return R.ok().setMessage("login success").setData(map);
        }else return R.error();
    }

    /**
     * 根据用户名获取用户信息
     * @param name 用户名
     */
    public User getUserInfoByName(String name){
        return userMapper.getUserInfoByName(name);
    }

    /**
     * 根据token获取用户信息
     * @param token token
     * @return
     */
    public User getUserInfoByToken(String token){ return userMapper.getUserInfoByToken(token); }

    /**
     * 验证token信息是否为管理员
     * @param token token
     */
    public boolean verifyInfo(String token){
        return getUserInfoByToken(token).getIdentity().equals("admin");
    }

    /**
     * 设置token
     * @param user 用户
     * @param token token
     */
    public int setToken(User user, String token){
        return userMapper.updateTokenByName(token, user.getName());
    }

    /**
     * 清除token
     * @param user 用户信息
     */
    public int clearToken(User user){
        return userMapper.updateTokenByName(null, user.getName());
    }

    /**
     * 添加用户
     * @param user
     */
    public int addUser(User user){
        return userMapper.addUser(user);
    }

    /**
     * 更新用户信息
     * @param token token
     * @param user 需要更新的用户信息
     */
    public int updateUserInfo(String token, User user){
        return userMapper.updateUserInfo(token, user);
    }

    /**
     * 更新用户密码
     * @param token token
     * @param oldPsw 旧密码
     * @param newPsw 新密码
     */
    public int updateUserPassword(String token, String oldPsw, String newPsw){
        User user = getUserInfoByToken(token);
        if (user.getPassword().equals(oldPsw)){
            return userMapper.updateUserPassword(token, newPsw);
        }else return 0;
    }
}
