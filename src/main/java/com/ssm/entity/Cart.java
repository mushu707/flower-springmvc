package com.ssm.entity;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class Cart {
    private int id;                 //id
    private String name;            //商品名
    private String img;             //图片地址
    private Integer price;          //价格
    private Integer original_price;    //市场价
    private Integer buy_count;       //购买数量
    private Integer isChecked;        //是否勾选状态
    private String uname;           //顾客名
    private Timestamp create_time;       //购物车商品创建时间
    private Timestamp update_time;       //购物车商品更新时间
}