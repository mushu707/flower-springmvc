package com.ssm.service.impl;
import com.ssm.entity.Banner;
import com.ssm.entity.Goods;
import com.ssm.entity.Home;
import com.ssm.mapper.HomeMapper;
import com.ssm.service.HomeService;
import com.ssm.service.UserService;
import com.ssm.util.time.Time;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

  @Resource
  private HomeMapper homeMapper;
  @Resource
  private UserService userService;

  public Map<String, Object> homeData(String bannerType) {
    Map<String, Object> map = new HashMap<>();
    Map<String, Object> typeData = new HashMap<>();
    List<Home> home = getHomeInfo();

    //获取显示首页的不同类型商品信息
    //home.size 表示主页显示几种商品类型
    String[] types = new String[home.size()];
    for (int i = 0; i < home.size(); i++) {
      types[i] = home.get(i).getStyle();
      typeData.put(types[i], getGoodsInfo(types[i]));
    }

    map.put("homeList", home);
    map.put("bannerList", getBannerInfo(bannerType));
    map.put("goodsList", typeData);
    return map;
  }

  public List<Home> getHomeInfo() {
    return homeMapper.getHomeInfo();
  }

  public List<Banner> getBannerInfo(String type) {
    if (type == null){
      return homeMapper.getBannerInfo();
    }else {
      return homeMapper.getM_BannerInfo();
    }
  }

  public List<Goods> getGoodsInfo(String type){
    return homeMapper.getHomeGoodsInfo(type);
  }

  public List<Home> getCategory() {
    return homeMapper.getCategory();
  }

  public int addOrUpdateHomeInfo(String token, Home home){
    Timestamp time = Time.getTimeByDate();
    if (userService.verifyInfo(token) && home.getId() == 0){
      home.setCreate_time(time);
      home.setUpdate_time(time);
      return homeMapper.addHomeInfo(home);
    }else if (userService.verifyInfo(token) && homeMapper.getHomeInfoById(home.getId()) != null){
      home.setUpdate_time(time);
      return homeMapper.updateHomeInfo(home);
    }else return 0;
  }

  public int deleteHomeInfo(String token, int id){
    if (userService.verifyInfo(token)){
      return homeMapper.deleteHomeInfoById(id);
    }else return 0;
  }

  public int addOrUpdateBannerInfo(String token, Banner banner){
    Timestamp time = Time.getTimeByDate();
    if (userService.verifyInfo(token) && banner.getId() == 0){
      banner.setCreate_time(time);
      banner.setUpdate_time(time);
      return homeMapper.addBannerInfo(banner);
    }else if (userService.verifyInfo(token) && homeMapper.getBannerInfoById(banner.getId()) != null){
      banner.setUpdate_time(time);
      return homeMapper.updateBannerInfo(banner);
    }else return 0;
  }

  public int deleteBannerInfo(String token, int id){
    if (userService.verifyInfo(token)){
      return homeMapper.deleteBannerInfoById(id);
    }else return 0;
  }

}
