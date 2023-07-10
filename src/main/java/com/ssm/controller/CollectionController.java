package com.ssm.controller;
import com.ssm.service.CollectionService;
import com.ssm.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class CollectionController {

  @Resource
  private CollectionService collectionService;

  @ResponseBody
  @RequestMapping(value = "/getCollection", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R getCollection(@RequestHeader("token") String token){
    if (token != null && token != "") return R.ok().setData(collectionService.getCollectionByUname(token));
    else return R.error().setMessage("用户未登录");
  }

  @ResponseBody
  @RequestMapping(value = "/addCollection", method = RequestMethod.GET)
  public R addCollection(@RequestHeader("token") String token, int g_id){
    int result = collectionService.addCollection(g_id, token);
    if (result == 1) return R.ok();
    else return R.error();
  }

  @ResponseBody
  @RequestMapping(value = "/deleteCollection", method = RequestMethod.POST)
  public R deleteCollection(@RequestHeader("token") String token, @RequestBody Map<String, Integer> data){
    int result = collectionService.deleteCollection(data.get("g_id"), token);
    if (result == 1) return R.ok();
    else return R.error();
  }

}
