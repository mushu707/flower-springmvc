package com.ssm.service.impl;

import com.ssm.entity.Collections;
import com.ssm.mapper.CollectionMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.service.CollectionService;
import com.ssm.util.time.Time;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionServiceImpl implements CollectionService {

  @Resource
  private CollectionMapper collectionMapper;
  @Resource
  private UserMapper userMapper;

  public Map<String, Object> getCollectionByUname(String token) {
    String uname = userMapper.getUserInfoByToken(token).getName();
    Map<String, Object> map = new HashMap<>();
    map.put("collectionData", collectionMapper.getCollectionByUname(uname));
    map.put("collectionId", collectionMapper.getCollectionIdByUname(uname));
    map.put("u_name", uname);
    return map;
  }

  public int addCollection(int g_id, String token) {
    String uname = userMapper.getUserInfoByToken(token).getName();
    Timestamp time = Time.getTimeByDate();
    if (collectionMapper.getIdByGidAndUname(g_id, uname) == null)
      return collectionMapper.addCollection(g_id, uname, time);
    else return 0;
  }

  public int deleteCollection(int g_id, String token) {
    String uname = userMapper.getUserInfoByToken(token).getName();
    return collectionMapper.deleteCollection(g_id, uname);
  }
}
