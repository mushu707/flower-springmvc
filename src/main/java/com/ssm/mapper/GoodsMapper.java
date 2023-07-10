package com.ssm.mapper;

import com.ssm.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
  List<Goods> getGoodsList(@Param("start") int start, @Param("count") int count);
  List<Goods> getGoodsListByType(@Param("type") String type, @Param("start") int start, @Param("count") int count);
  List<Goods> getRandomGoodsList(@Param("num") int num, @Param("num_size") int num_size);
  List<Goods> getGoodsListByKey(
          @Param("key") String key,
          @Param("style") String style,
          @Param("begin") int begin,
          @Param("count") int count);

  Goods getGoodsInfoById(int id);

  int addGoods(Goods goods);
  int deleteGoods(int id);
  int updateGoods(Goods goods);

  int total();
  int totalByType(String type);

}
