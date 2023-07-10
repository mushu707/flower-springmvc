package com.ssm.entity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Banner {
  private int id;
  private String img;
  private int isShow;
  private int isMobile;
  private Timestamp create_time;
  private Timestamp update_time;
}

