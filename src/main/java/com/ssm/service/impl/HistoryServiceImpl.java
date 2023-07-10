package com.ssm.service.impl;

import com.ssm.mapper.HistoryMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.service.HistoryService;
import com.ssm.util.time.Time;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {

  @Resource
  private UserMapper userMapper;
  @Resource
  private HistoryMapper historyMapper;

  public Map<String, Object> getTotalHistoryByUname(String token) {
    String uname = userMapper.getUserInfoByToken(token).getName();
    Map<String, Object> map = new HashMap<>();
    map.put("history", historyMapper.getTotalHistoryByUname(uname));
    map.put("u_name", uname);
    return map;
  }

  public int addHistory(int g_id, String token) {
    String uname = userMapper.getUserInfoByToken(token).getName();
    Timestamp time = Time.getTimeByDate();
    if (historyMapper.getIdByGidAndUname(g_id, uname) != null){
      historyMapper.deleteHistoryByGidAndUname(g_id, uname);
    }
    return historyMapper.addHistory(g_id, uname, time);
  }

  public int deleteHistory(List<Integer> list, String token){
    String uname = userMapper.getUserInfoByToken(token).getName();
    return historyMapper.deleteHistoryByList(list, uname);
  }
}
