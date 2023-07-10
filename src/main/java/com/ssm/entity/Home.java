package com.ssm.entity;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Home {
  private int id;
  private String style;
  private String name;
  private String desc;
  private String img;
  private String m_img;
  private List<Category> categories;
  private Timestamp create_time;
  private Timestamp update_time;
}

@Data
class Category {
  private int id;
  private String name;
  private String type;
  private List<Tag> tags;
}

@Data
class Tag {
  private int id;
  private String title;
  private String icon;
  private String type;
  private String style;
}