package com.ssm.controller;
import com.ssm.entity.Banner;
import com.ssm.entity.Home;
import com.ssm.service.HomeService;
import com.ssm.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@Controller
public class HomeController {

  @Resource
  private HomeService homeService;

  @ResponseBody
  @RequestMapping(value = "/getHomeInfo", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R getHomeInfo(String type){
    Map<String, Object> map = homeService.homeData(type);
    if (map != null)
      return R.ok().setData(map);
    else return R.error();
  }

  @ResponseBody
  @RequestMapping(value = "/getCategory", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R getCategory(){
    return R.ok().data("category", homeService.getCategory());
  }

  @ResponseBody
  @RequestMapping(value = "/addOrUpdateHomeInfo", method = RequestMethod.POST)
  public R addOrUpdateHomeInfo(@RequestHeader("token") String token, @RequestBody Home home){
    if (homeService.addOrUpdateHomeInfo(token, home) == 1){
      return R.ok();
    }else return R.error();
  }

  @ResponseBody
  @RequestMapping(value = "/deleteHomeInfo", method = RequestMethod.DELETE)
  public R deleteHomeInfo(@RequestHeader("token") String token, @RequestBody int id){
    if (homeService.deleteHomeInfo(token, id) == 1){
      return R.ok();
    }else return R.error();
  }

  @ResponseBody
  @RequestMapping(value = "/addOrUpdateBannerInfo", method = RequestMethod.POST)
  public R addOrUpdateBannerInfo(@RequestHeader("token") String token, @RequestBody Banner banner){
    System.out.println(banner.getIsShow());
    if (homeService.addOrUpdateBannerInfo(token, banner) == 1){
      return R.ok();
    }else return R.error();
  }

  @ResponseBody
  @RequestMapping(value = "/deleteBannerInfo", method = RequestMethod.DELETE)
  public R deleteBannerInfo(@RequestHeader("token") String token, @RequestBody int id){
    if (homeService.deleteBannerInfo(token, id) == 1){
      return R.ok();
    }else return R.error();
  }
}
