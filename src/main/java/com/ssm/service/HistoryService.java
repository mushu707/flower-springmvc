package com.ssm.service;

import java.util.List;
import java.util.Map;

public interface HistoryService{

  Map<String, Object> getTotalHistoryByUname(String token);

  int addHistory(int g_id, String token);

  int deleteHistory(List<Integer> list, String token);
}
