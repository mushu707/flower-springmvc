package com.ssm.service;

import com.ssm.entity.Banner;
import com.ssm.entity.Goods;
import com.ssm.entity.Home;
import java.util.List;
import java.util.Map;

public interface HomeService {
  Map<String, Object> homeData(String type);

  List<Home> getHomeInfo();

  List<Banner> getBannerInfo(String type);

  List<Goods> getGoodsInfo(String type);

  List<Home> getCategory();

  int addOrUpdateHomeInfo(String token, Home home);

  int deleteHomeInfo(String token, int id);

  int addOrUpdateBannerInfo(String token, Banner banner);

  int deleteBannerInfo(String token, int id);

}
