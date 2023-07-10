package com.ssm.entity;
import lombok.Data;

@Data
public class Goods {
  private int id;          //商品id
  private String type;        //商品类型
  private String img;         //图片地址
  private String name;        //商品名
  private int price;          //售格
  private int sale_count;     //销量
  private int original_price; //原价
  private String slogan;      //标语
  private String describe;    //描述
  private String huayu;       //花语
  private String material;    //材料
  private String packing;     //包装
  private String tag;         //标签
}
