package com.ssm.service.impl;

import com.ssm.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ssm.entity.Goods;
import com.ssm.mapper.GoodsMapper;
import com.ssm.service.GoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserService userService;

    public List<Goods> getGoodsList(String type, int curPage, int pageSize){
        int start = (curPage - 1) * pageSize;
        if (type.equals("all"))
            return goodsMapper.getGoodsList(start, pageSize);
        else return goodsMapper.getGoodsListByType(type, start, pageSize);
    }

    public List<Goods> getRandomGoodsList(int num){
        return goodsMapper.getRandomGoodsList(num, num + 1);
    }

    public int getTotalByType(String type){
        if (type.equals("all"))
            return goodsMapper.total();
        else return goodsMapper.totalByType(type);
    }

    public List<Goods> getSearchInfo(String key, String style, int begin, int count){
        if (key == "") return getGoodsList("all", 1, 999);
        else return goodsMapper.getGoodsListByKey(key, style, begin, count);
    }

    public Goods getDetailInfo(int id){
        return goodsMapper.getGoodsInfoById(id);
    }

    public int addOrUpdateGoodsInfo(String token, Goods goods){
        if (userService.verifyInfo(token) && goods.getId() == 0){
            return goodsMapper.addGoods(goods);
        } else if (userService.verifyInfo(token) && getDetailInfo(goods.getId()) != null){
            return goodsMapper.updateGoods(goods);
        }else return 0;
    }

    public int deleteGoodsInfo(String token, int id){
        if (userService.verifyInfo(token) && getDetailInfo(id) != null){
            return goodsMapper.deleteGoods(id);
        } else return 0;
    }

}
