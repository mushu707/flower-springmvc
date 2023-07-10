package com.ssm.mapper;

import com.ssm.entity.History;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface HistoryMapper {

  List<History> getTotalHistoryByUname(String u_name);
  Integer getIdByGidAndUname(@Param("g_id") int g_id, @Param("u_name") String u_name);

  int addHistory(@Param("g_id") int g_id, @Param("u_name") String u_name, @Param("create_time")Timestamp create_time);
  int deleteHistoryByGidAndUname(@Param("g_id") int g_id, @Param("u_name") String u_name);
  int deleteHistoryByList(@Param("list") List<Integer> list, @Param("u_name") String u_name);
}
