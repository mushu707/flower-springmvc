package com.ssm.service;
import com.ssm.entity.Goods;

import java.util.List;

public interface GoodsService {

  List<Goods> getGoodsList(String type, int curPage, int pageSize);

  List<Goods> getRandomGoodsList(int num);

  List<Goods> getSearchInfo(String name, String style, int begin, int count);

  Goods getDetailInfo(int id);

  int getTotalByType(String type);

  int addOrUpdateGoodsInfo(String token, Goods goods);

  int deleteGoodsInfo(String token, int id);

}
