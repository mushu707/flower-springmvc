package com.ssm.controller;
import com.ssm.entity.Goods;
import com.ssm.service.GoodsService;
import com.ssm.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class GoodsController {

  @Resource
  private GoodsService goodsService;

  /**
   * 获取商品列表
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/getGoodsList/{type}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R getGoodsList(@PathVariable String type, int curPage, int pageSize){
    List<Goods> list = goodsService.getGoodsList(type, curPage, pageSize);
    if (list.size() != 0){
      return R.ok()
              .data("data", list)
              .data("total", goodsService.getTotalByType(type));
    }else {
      return R.error().setMessage(type + "类型的商品为空，请联系相关工作人员核查商品数据");
    }
  }

  /**
   * 获取随机商品
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/getRandomGoodsList", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R getRandomGoodsList(int num){
    List<Goods> list = goodsService.getRandomGoodsList(num);
    return R.ok().data("guessList", list);
  }

  /**
   * 获取商品信息
   * @param hua_id 商品id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/getDetailInfo", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R getDetailInfo(int hua_id){
    Goods goods = goodsService.getDetailInfo(hua_id);
    if (goods != null){
      return R.ok().data("goodsInfo", goods);
    }
    else return R.error();
  }

  /**
   * 获取搜索列表
   * @param key
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/getSearchList", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R getSearchList(String key, String style, int begin, int count){
//    System.out.println("用户搜索关键字: "+ key + ", 类型为: " + style == null ? "所有商品" : style);
    return R.ok().data("searchList", goodsService.getSearchInfo(key, style, begin, count));
  }



  /**
   * 添加或更新商品信息
   * @param token token
   * @param goods 商品信息
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/addOrUpdateGoodsInfo", method = RequestMethod.POST)
  public R addGoodsInfo(@RequestHeader("token") String token, @RequestBody Goods goods){
    if (goodsService.addOrUpdateGoodsInfo(token, goods) == 1) {
      return R.ok();
    } else return R.error();
  }

  /**
   * 删除商品信息
   * @param token token
   * @param id 商品id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/deleteGoodsInfo", method = RequestMethod.GET)
  public R deleteGoodsInfo(@RequestHeader("token") String token, int id){
    if (goodsService.deleteGoodsInfo(token, id) == 1)
      return R.ok();
    else return R.error();
  }

}
