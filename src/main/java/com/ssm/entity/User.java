package com.ssm.entity;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class User {
    private String name;        //用户名
    private String password;    //密码
    private String identity;    //身份
    private int sex;            //性别
    private String phone;       //手机
    private String email;       //邮箱
    private Timestamp birth;    //生日
    private String imageUrl;    //头像
    private String token;       //token
}