package com.ssm.service;

import java.util.Map;

public interface CollectionService {
  Map<String, Object> getCollectionByUname(String token);

  int addCollection(int g_id, String token);

  int deleteCollection(int g_id, String token);
}
