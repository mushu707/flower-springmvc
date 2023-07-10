package com.ssm.mapper;

import com.ssm.entity.Collections;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CollectionMapper {

  List<Collections> getCollectionByUname(String uname);
  List<Integer> getCollectionIdByUname(String uname);

  Integer getIdByGidAndUname(@Param("g_id") int g_id, @Param("u_name") String uname);

  int addCollection(@Param("g_id") int g_id, @Param("u_name") String uname, @Param("create_time") Timestamp create_time);
  int deleteCollection(@Param("g_id") int g_id, @Param("u_name") String uname);
}
