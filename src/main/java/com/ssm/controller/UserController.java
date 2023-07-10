package com.ssm.controller;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import com.ssm.util.time.Time;
import com.ssm.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService userService ;

    /**
     * 用户登录验证
     * @param name 用户名
     * @param password 密码
     * @param identity 身份
     * 先判断是否移动端，命中则判断移动端登录的账户是否存在，存在则返回移动端用户信息，不存在则自动注册新账户，不命中则返回PC端信息
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R Login(String name, String password, String identity, boolean mobile){
        User user = userService.getUserInfoByName(name);
        if (mobile){
            if (user == null){
                User m = userService.toMobileUser(name, password, identity);
                if (userService.addUser(m) == 1) return R.ok().setData(userService.tokenData(m, true));
                else return R.error().setMessage("register error");
            } else return userService.checkUser(user, password, identity, true);
        }else return userService.checkUser(user, password, identity, false);
    }

    /**
     * 用户注册
     * @param user 用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public R Register(@RequestBody User user){
        //如果用户名存在，返回existUser信息
        if (userService.getUserInfoByName(user.getName()) != null){
            return R.error().setCode(403).setMessage("existed user");
        } else {
            if (userService.addUser(user) == 1){
                Map<String, Object> map = userService.tokenData(user, false);
                return R.ok().setData(map);
            }
            else return R.error().setMessage("register error");
        }
    }

    /**
     * 根据token获取用户信息
     * @param token token
     */
    @ResponseBody
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R getInfo(@RequestHeader("token") String token){
        User user = userService.getUserInfoByToken(token);
        if (user != null){
            return R.ok()
                    .data("name", user.getName())
                    .data("identity", user.getIdentity())
                    .data("sex", user.getSex())
                    .data("phone", user.getPhone())
                    .data("email", user.getEmail())
                    .data("imageUrl", user.getImageUrl())
                    .data("birth", user.getBirth())
                    .data("loginTime", Time.getTimeByString())
                    .data("token", user.getToken());
        }else {
            return R.error();
        }
    }

    /**
     * 用户下线
     * @param token token
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public R Logout(@RequestHeader("token") String token){
        User user = userService.getUserInfoByToken(token);
        //清除token
        userService.clearToken(user);
        return R.ok();
    }

    /**
     * 更新用户信息
     * @param token token
     * @param user 用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public R UpdateUserInfo(@RequestHeader("token") String token, @RequestBody User user){
        if (userService.updateUserInfo(token, user) == 1)
            return R.ok();
        else return R.error().setMessage("更新用户信息错误");
    }

    @ResponseBody
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public R ResetUserPassword(@RequestHeader("token") String token, @RequestBody Map<String, String> map){
        if (userService.updateUserPassword(token, map.get("oldPsw"), map.get("newPsw")) == 1) return R.ok();
        else return R.error().setMessage("旧密码错误，请重新输入");
    }
}
