package com.ssm.mapper;

import com.ssm.entity.Banner;
import com.ssm.entity.Goods;
import com.ssm.entity.Home;

import java.util.List;

public interface HomeMapper {

  List<Home> getHomeInfo();
  Home getHomeInfoById(int id);

  List<Banner> getBannerInfo();
  List<Banner> getM_BannerInfo();
  Banner getBannerInfoById(int id);

  List<Home> getCategory();

  List<Goods> getHomeGoodsInfo(String type);

  List<String> getHomeStyle();

  int totalHome();
  int addHomeInfo(Home home);
  int deleteHomeInfoById(int id);
  int updateHomeInfo(Home home);

  int totalBanner();
  int addBannerInfo(Banner banner);
  int deleteBannerInfoById(int id);
  int updateBannerInfo(Banner banner);
}
