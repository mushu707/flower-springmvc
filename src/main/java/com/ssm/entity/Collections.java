package com.ssm.entity;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class Collections {
  private int id;
  private int g_id;
  private Goods g_info;
  private Timestamp create_time;
}
