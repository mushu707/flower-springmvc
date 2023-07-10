package com.ssm.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class R {
  private Integer code;   //响应码
  private String message;   //响应消息
  private Map<String, Object> data = new HashMap<>();   //数据

  public static R ok(){
    R r = new R();
    r.setCode(200);
    r.setMessage("success");
    return r;
  }

  public static R error(){
    R r = new R();
    r.setCode(400);
    r.setMessage("error");
    return r;
  }

  public R data(String key, Object value){
    this.data.put(key, value);
    return this;
  }
}
